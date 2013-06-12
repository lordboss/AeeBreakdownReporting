package com.wovenware.aee.breakdown.reporting;

/**
 * <i>App Constants.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti, Nelson Perez
 */
public class Constants {
	
	public static class Bundles
	{
		public static final String ENVIRONMENT = "com.wovenware.aee.breakdown.reporting.Environment";
	}
	
	public static class Session
	{
		public static final String USER_EMAIL = "UserEmail";
		public static final String USER_NAME = "UserName";
	}
	
	public static class Services
	{
		public static final String JNDI_JDBC_APP = "jdbc/aeebk";
	}
	
	public static class AlertTypes
	{
		public static final String SUCCESS = "alert-success";
		public static final String WARNING = "alert-block";
		public static final String ERROR = "alert-error";
	}
	
	private Constants() {}
}
