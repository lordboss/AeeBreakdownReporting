package com.wovenware.aee.breakdown.reporting.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * <i>Encryption Utility.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Nelson Perez
 */

public class EncryptionUtil {
	private static final String SALT = "xOYcHdMpyBI1WH6ZsNpDLnnd6wX7BIVP";

	private EncryptionUtil() {}
	
	public static String encrypt(String value) throws Exception {
		String encryptedValue = null;
		
		if(value != null && !value.trim().isEmpty()) {
			String saltedValue = value + SALT;
			
			MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(saltedValue.getBytes("iso-8859-1"), 0, saltedValue.length());
	        
	        encryptedValue = (new BigInteger(1, md.digest())).toString(16);
		}
		
		return encryptedValue;
	}
}
