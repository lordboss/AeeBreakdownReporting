package com.wovenware.aee.breakdown.reporting.util;

/**
 * <i>Validation Utility.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Nelson Perez
 */

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wovenware.aee.breakdown.reporting.Constants;
import com.wovenware.aee.breakdown.reporting.db.dao.UsersDAO;

public class ValidationUtil {
	private static Pattern emailPattern = Pattern.compile( 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	private ValidationUtil() {}
	
	public static boolean isValidEmail(String email) {
		boolean isValid = false;
		
		if(email != null) {
			Matcher matcher = emailPattern.matcher(email);
			
			isValid = matcher.matches();
		}
		
		return isValid;
	}
	
	public static boolean exists(String email) {
		boolean exists = false;
	    	
    	Connection connection = null;
    	
    	try {
    		connection = ConnectionUtil.createConnection(
    				Constants.Services.JNDI_JDBC_APP, false);
	    		
    		UsersDAO usersDAO = new UsersDAO(connection);
	    	exists = usersDAO.find(email) != null;
	    		
	    	connection.commit();
    	} catch(Exception e) {
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
		
		return exists;
	}
}
