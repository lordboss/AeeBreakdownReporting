package com.wovenware.aee.breakdown.reporting;

import java.util.PropertyResourceBundle;

import com.wovenware.aee.breakdown.reporting.Constants.Bundles;

/**
 * <i>Environment Settings.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti, Nelson Perez
 */
public class Environment
{
	private static Environment _theInstance = null;

	private String _emailServer;
	private String _emailPort;
	private String _emailUsername;
	private String _emailPassword;
	private String _emailDefaultSender;
	
	/**
	 * @return the _emailServer
	 */
	public String getEmailServer() 
	{
		return _emailServer;
	}

	/**
	 * @param _emailServer the _emailServer to set
	 */
	public void setEmailServer(String _emailServer) 
	{
		this._emailServer = _emailServer;
	}

	/**
	 * @return the _emailPort
	 */
	public String getEmailPort() 
	{
		return _emailPort;
	}

	/**
	 * @param _emailPort the _emailPort to set
	 */
	public void setEmailPort(String _emailPort) 
	{
		this._emailPort = _emailPort;
	}

	/**
	 * @return the _emailUsername
	 */
	public String getEmailUsername() 
	{
		return _emailUsername;
	}

	/**
	 * @param _emailUsername the _emailUsername to set
	 */
	public void setEmailUsername(String _emailUsername) 
	{
		this._emailUsername = _emailUsername;
	}

	/**
	 * @return the _emailPassword
	 */
	public String getEmailPassword() 
	{
		return _emailPassword;
	}

	/**
	 * @param _emailPassword the _emailPassword to set
	 */
	public void setEmailPassword(String _emailPassword) 
	{
		this._emailPassword = _emailPassword;
	}

	/**
	 * @return the _emailDefaultSender
	 */
	public String getEmailDefaultSender() 
	{
		return _emailDefaultSender;
	}

	/**
	 * @param _emailDefaultSender the _emailDefaultSender to set
	 */
	public void setEmailDefaultSender(String _emailDefaultSender) 
	{
		this._emailDefaultSender = _emailDefaultSender;
	}

	/**
	 * Get Environment Instance. Singleton implementation.
	 * 
	 * @return Environment Class
	 */
	public static Environment Instance()
	{
		if (_theInstance == null)
		{
			_theInstance = new Environment();
		}
		
		return _theInstance;
	}
	
	private Environment()
	{
		PropertyResourceBundle ep = (PropertyResourceBundle)PropertyResourceBundle.getBundle(Bundles.ENVIRONMENT);
		
		// Email Properties
		_emailServer = ep.getString("email_server");
		_emailPort = ep.getString("email_port");
		_emailUsername = ep.getString("email_username");
		_emailPassword = ep.getString("email_password");
		_emailDefaultSender = ep.getString("email_defaultSender");
	}
}
