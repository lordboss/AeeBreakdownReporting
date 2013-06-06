package com.wovenware.aee.breakdown.reporting.bean.signup;

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

@ManagedBean
@SessionScoped
public class Signup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String _feedback = null;
	private String _name = null;
	private String _email = null;
	private String _telephone = null;
	private String _password = null;

	// Feedback
	public String getFeedback() {
        return _feedback;
    }
	
	// Name
    public void setName(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }
    
    // Email
    public void setEmail(String email) {
        _email = email;
    }

    public String getEmail() {
        return _email;
    }
    
    // Telephone
    public void setTelephone(String telephone) {
        _telephone = telephone;
    }

    public String getTelephone() {
        return _telephone;
    }
    
    // Password
    public void setPassword(String password) {
    	_password = password;
    }

    public String getPassword() {
        return _password;
    }
 
    public void save () {
    	Connection connection = null;
    	try {
	    	_feedback = validateForm();
	    	
	    	if(_feedback == null) {
	    		connection = ConnectionUtil.createConnection(
	    				Constants.Services.JNDI_JDBC_APP, false);
	    		
	    		UsersTO usersTO = new UsersTO();
	    		usersTO.setName(_name);
	    		usersTO.setPkUserId(_email);
	    		usersTO.setPhone(_telephone);
	    		//usersTO.setSmsInd(null);
	    		//usersTO.setPassword(_password);
	    		
	    		UsersDAO usersDAO = new UsersDAO(connection);
	    		usersDAO.create(usersTO);
	    		
	    		connection.commit();
	    		
	    		FacesContext.getCurrentInstance().getExternalContext().redirect("signup.jsf");
	    	}
    	} catch(Exception e) {
    		_feedback = e.getMessage();
    		
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
    
    public String validateForm() {
    	String message = null;
    	
    	// TODO: Validations...
    	
    	return message;
    }
}