package com.wovenware.aee.breakdown.reporting.bean.signup;

/**
 * <i>Sign Up Bean.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti, Nelson Perez
 */

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
 
    public void save ()
    {
    	if(_name.isEmpty()) {
    		_feedback = "warning";
    	} else {
    		_feedback = "OK";
    	}
    }
}