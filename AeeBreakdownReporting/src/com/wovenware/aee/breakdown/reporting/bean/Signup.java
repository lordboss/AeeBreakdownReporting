package com.wovenware.aee.breakdown.reporting.bean;

/**
 * <i>Sign Up Bean.</i>
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
public class Signup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String _feedback = null;
	private String _name = null;
	private String _nameFeedback = null;
	private String _email = null;
	private String _emailFeedback = null;
	private String _telephone = null;
	private String _telephoneFeedback = null;
	private String _password = null;
	private String _passwordFeedback = null;

	// Feedback
	public String getFeedback() {
        return _feedback;
    }
	
	// Name
	public String getName() {
        return _name;
    }
	
    public void setName(String name) {
        _name = name;
    }

    public String getNameFeedback() {
        return _nameFeedback;
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
    
    // Telephone
    public String getTelephone() {
        return _telephone;
    }
    
    public void setTelephone(String telephone) {
        _telephone = telephone;
    }

    public String getTelephoneFeedback() {
        return _telephoneFeedback;
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
 
    public void save () {
    	validateForm();
    	
    	if(_feedback == null) {
    		Connection connection = null;
        	
    		try {
	    		connection = ConnectionUtil.createConnection(
	    				Constants.Services.JNDI_JDBC_APP, false);
	    		
	    		UsersTO usersTO = new UsersTO();
	    		usersTO.setName(_name);
	    		usersTO.setPkUserId(_email);
	    		usersTO.setPhone(_telephone);
	    		//usersTO.setSmsInd(null);
	    		usersTO.setPassword(_password);
	    		
	    		UsersDAO usersDAO = new UsersDAO(connection);
	    		usersDAO.create(usersTO);
	    		
	    		connection.commit();
	    		
	    		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
						Constants.Session.USER_EMAIL, usersTO.getPkUserId());
	    		FacesContext.getCurrentInstance().getExternalContext().redirect("main.jsf");
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
    	_nameFeedback = null;
    	_emailFeedback = null;
    	_telephoneFeedback = null;
    	_passwordFeedback = null;
    	
    	if(_name == null || _name.trim().isEmpty()) {
    		_nameFeedback = FeedbackUtil.formatFieldFeedback("Requerido");
    	}
    	
    	if(_email == null || _email.trim().isEmpty()) {
    		_emailFeedback = FeedbackUtil.formatFieldFeedback("Requerido");
    	} else if(!ValidationUtil.isValidEmail(_email)) {
    		_emailFeedback = FeedbackUtil.formatFieldFeedback("Invalido");
    	}
    	
//    	if(_telephone == null || _telephone.trim().isEmpty()) {
//    		_telephoneFeedback = FeedbackUtil.formatFieldFeedback("Requerido");
//    	} else {
//    		// TODO: Validate telephone...
//    	}
    	
    	if(_password == null || _password.trim().isEmpty()) {
    		_passwordFeedback = FeedbackUtil.formatFieldFeedback("Requerido");
    	}
    	
    	if(_nameFeedback != null || _emailFeedback != null 
    			|| _telephoneFeedback != null || _passwordFeedback != null) {
	    	_feedback = FeedbackUtil.formatGeneralFeedback(
					"Campos requeridos no fueron entrados o los valores son inv&aacute;lidos.");
    	}
    }
}