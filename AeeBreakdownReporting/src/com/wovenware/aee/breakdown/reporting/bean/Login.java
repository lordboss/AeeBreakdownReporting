package com.wovenware.aee.breakdown.reporting.bean;

/**
 * <i>Login Bean.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti, Nelson Perez
 */

import java.io.Serializable;
import java.sql.Connection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.wovenware.aee.breakdown.reporting.Constants;
import com.wovenware.aee.breakdown.reporting.db.dao.UsersDAO;
import com.wovenware.aee.breakdown.reporting.db.dao.to.UsersTO;
import com.wovenware.aee.breakdown.reporting.util.ConnectionUtil;
import com.wovenware.aee.breakdown.reporting.util.FeedbackUtil;
import com.wovenware.aee.breakdown.reporting.util.ValidationUtil;

@ManagedBean
@SessionScoped
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String _feedback = null;
	private String _email = null;
	private String _emailFeedback = null;
	private String _password = null;
	private String _passwordFeedback = null;

	// Feedback
	public String getFeedback() {
        return _feedback;
    }
	
    // Email
	public String getEmail() {
        return _email;
    }
	
    public void setEmail(String email) {
        _email = email;
    }

    public String getEmailFeedback() {
        return _emailFeedback;
    }
    
    // Password
    public String getPassword() {
        return _password;
    }
    
    public void setPassword(String password) {
    	_password = password;
    }

    public String getPasswordFeedback() {
        return _passwordFeedback;
    }
 
    public void doLogin () {
	    validateForm();
	    	
    	if(_feedback == null) {
    		Connection connection = null;
    		
    		try {
	    		connection = ConnectionUtil.createConnection(
	    				Constants.Services.JNDI_JDBC_APP, false);
	    		
	    		UsersDAO usersDAO = new UsersDAO(connection);
	    		UsersTO usersTO = usersDAO.find(_email);
	    		
	    		connection.commit();
	    		
	    		// TODO: Password encryption...
	    		if(usersTO != null && usersTO.getPassword().equals(_password)) {
	    			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
	    					Constants.Session.USER_EMAIL, usersTO.getPkUserId());
	    			FacesContext.getCurrentInstance().getExternalContext().redirect("main.jsf");
	    		} else {
	    			_feedback = FeedbackUtil.formatGeneralFeedback("El email y/o contrase&ntilde;a provistos son inv&aacute;lidos.");
	    		}
    		} catch(Exception e) {
    			_feedback = FeedbackUtil.formatGeneralFeedback(e.getMessage());
        		
        		try {
        			if(connection != null && !connection.isClosed()) {
        				connection.rollback();
        			}
        		} catch (Exception e1) {
        			// Do nothing...
        		}
        	} finally {
        		try {
        			if(connection != null && !connection.isClosed()) {
        				connection.close();
        			}
        		} catch (Exception e) {
        			// Do nothing...
        		} finally {
        			connection = null;
        		}
        	}
    	}
    }
    
    public void validateForm() {
    	_feedback = null;
    	_emailFeedback = null;
    	_passwordFeedback = null;
    	
    	if(_email == null || _email.trim().isEmpty()) {
    		_emailFeedback = FeedbackUtil.formatFieldFeedback("Requerido");
    	} else if(!ValidationUtil.isValidEmail(_email)) {
    		_emailFeedback = FeedbackUtil.formatFieldFeedback("Invalido");
    	}
    	
    	if(_password == null || _password.trim().isEmpty()) {
    		_passwordFeedback = FeedbackUtil.formatFieldFeedback("Requerido");
    	}
    	
    	if(_emailFeedback != null || _passwordFeedback != null) {
	    	_feedback = FeedbackUtil.formatGeneralFeedback(
					"Campos requeridos no fueron entrados o los valores son inv&aacute;lidos.");
    	}
    }
}