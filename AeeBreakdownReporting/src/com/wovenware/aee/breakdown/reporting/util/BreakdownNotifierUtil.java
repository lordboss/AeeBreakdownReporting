package com.wovenware.aee.breakdown.reporting.util;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import com.wovenware.aee.breakdown.reporting.db.dao.Bk2UsersDAO;
import com.wovenware.aee.breakdown.reporting.db.dao.to.Bk2UsersTO;
import com.wovenware.aee.breakdown.reporting.db.dao.to.BksReportedTO;

/**
 * <i>Breakdown Notifier Utility.</i>
 * 
 * Provides means to send notifications to registered users (currently via SMTP).
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti
 */
public class BreakdownNotifierUtil 
{
	// Constants...
	public class PushTypes
	{
		static final String RAISE = "RAISE";
		static final String CLEAR = "CLEAR";
		
		private PushTypes() {}
	}
	
	static public final String ZONE_PLACEHOLDER = "@@ZONE@@";
	
	// Attributes...
	private EmailUtil eUtil = new EmailUtil();
	private Logger _log = Logger.getLogger( BreakdownNotifierUtil.class.getName() );

	// Methods...
	public void push( Connection conn, BksReportedTO bksReportedTO, String title, String message )
			throws Exception 
	{
		try
		{
			_log.finest( "Entry" );
			
			// Determine if there are any users registered for given city/area.
			Bk2UsersTO bk2UsersTO = null;
			Bk2UsersDAO bk2UsersDAO = new Bk2UsersDAO( conn );
			
			List<Bk2UsersTO> listRegs =
					bk2UsersDAO.find( bksReportedTO.getCity(), bksReportedTO.getArea() );
			while ( listRegs != null && ! listRegs.isEmpty() )
			{
				// For each registered user, send notification.
				bk2UsersTO = listRegs.remove(0);
				
				// TODO: Revise message.  Use message bundles.
				String subject = title;
				String body = message;
				
				if ( body.contains( ZONE_PLACEHOLDER ) )
				{
					String zone = bk2UsersTO.getName();
					if(!zone.equals(bk2UsersTO.getArea() + ", " + bk2UsersTO.getCity())) 
					{
						zone += " (" + bk2UsersTO.getArea() + ", " + bk2UsersTO.getCity() + ")";
					}
					body = body.replace( ZONE_PLACEHOLDER, zone );
				}
					
				this.eUtil.sendMail(null, bk2UsersTO.getFkUserId(), subject, body);
			}
		}
		catch ( Exception ex )
		{
			// Do nothing.
			_log.severe( "Failure detected while attempting to notify on [" +
						bksReportedTO.getCity() + ":" + bksReportedTO.getArea() + "] - " + ex.getLocalizedMessage() ); 
		}
		finally
		{
			_log.finest( "Exit" );
		}
	}
}
