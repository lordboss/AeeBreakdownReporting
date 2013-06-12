package com.wovenware.aee.breakdown.reporting.bean;

/**
 * <i>Generic Bean.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti, Nelson Perez
 */

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.wovenware.aee.breakdown.reporting.Constants;

@ManagedBean
@SessionScoped
public class GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String _userEmail = null;
	protected String _userName = null;
	protected String _feedback = null;

    // User Email
    public String getUserEmail() {
        return _userEmail;
    }
    
    // User Name
    public String getUserName() {
        return _userName;
    }
    
    // Feedback
    public void setFeedback(String feedback) {
    	_feedback = feedback;
    }

    public String getFeedback() {
        return _feedback;
    }
    
    public boolean getOnLoad() {
    	_userEmail = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
    			Constants.Session.USER_EMAIL);
    	_userName = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
    			Constants.Session.USER_NAME);
    	
    	if(_userEmail == null) {
    		try {
    			FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
    		} catch(Exception e) {
    			// Do nothing...
    		}
    	}
    	
    	return true;
    }
    
    public void logout() {
    	_feedback = null;
    	
    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(
				Constants.Session.USER_EMAIL);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(
				Constants.Session.USER_NAME);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
		} catch(Exception e) {
			// Do nothing...
		}
    }
}