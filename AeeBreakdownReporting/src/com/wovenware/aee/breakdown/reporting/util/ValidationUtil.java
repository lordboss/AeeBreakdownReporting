package com.wovenware.aee.breakdown.reporting.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
}
