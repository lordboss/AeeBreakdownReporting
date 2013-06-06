package com.wovenware.aee.breakdown.reporting.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.wovenware.aee.breakdown.reporting.Environment;

/**
 * <i>Email Utility.</i>
 * 
 * Provides means to send emails via SMTP.
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti
 */
public class EmailUtil 
{
	// Constants...
	static private final String DFLT_MIME_TYPE = "text/html";
	
	// Methods...
	public void sendMail( String fromAddress, String toAddress, String subject, String body)
			throws Exception 
	{
		String tmpFromAddress = fromAddress;
		if ( tmpFromAddress == null )
		{
			tmpFromAddress = Environment.Instance().getEmailDefaultSender();
		}
		
		Message message = new MimeMessage(getSession());

		message.addRecipient(RecipientType.TO, new InternetAddress(toAddress));
		message.addFrom(new InternetAddress[] { new InternetAddress(fromAddress) });
		message.setSubject(subject);
		message.setContent(body, DFLT_MIME_TYPE);

		Transport.send(message);
	}
	
	private Session getSession() 
	{
		Session session = null;
		
		Properties properties = new Properties();
		Environment env = Environment.Instance();

		properties.setProperty("mail.smtp.host", env.getEmailServer());
		properties.setProperty("mail.smtp.port", env.getEmailPort());
		properties.put("mail.smtp.starttls.enable","true");
		
		if ( env.getEmailUsername() != null )
		{
			Authenticator authenticator = 
					new Authenticator(env.getEmailUsername(), env.getEmailPassword());
			
			properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
			properties.setProperty("mail.smtp.auth", "true");
			
			session = Session.getInstance(properties, authenticator);
		}
		else
		{
			session = Session.getInstance(properties);
		}

		return session;
	}

	class Authenticator extends javax.mail.Authenticator 
	{
		private PasswordAuthentication authentication;

		public Authenticator( String username, String password ) 
		{
			authentication = new PasswordAuthentication(username, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() 
		{
			return authentication;
		}
	}
}
