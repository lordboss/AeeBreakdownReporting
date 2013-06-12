package com.wovenware.aee.breakdown.reporting.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prepa.breakdown.persistence.xsd.BreakdownArea;
import com.prepa.breakdown.persistence.xsd.BreakdownSummary;
import com.prepa.breakdown.ws.BreakdownReportPortTypeProxy;
import com.wovenware.aee.breakdown.reporting.Constants.Services;
import com.wovenware.aee.breakdown.reporting.Environment;
import com.wovenware.aee.breakdown.reporting.db.dao.BksArchivedDAO;
import com.wovenware.aee.breakdown.reporting.db.dao.BksReportedDAO;
import com.wovenware.aee.breakdown.reporting.db.dao.to.BksArchivedTO;
import com.wovenware.aee.breakdown.reporting.db.dao.to.BksReportedTO;
import com.wovenware.aee.breakdown.reporting.util.BreakdownNotifierUtil;
import com.wovenware.aee.breakdown.reporting.util.ConnectionUtil;

/**
 * <i>Breakdown Servlet.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti
 */
public class BreakdownServlet extends HttpServlet 
{
	// Constants...
	private static final long serialVersionUID = 2717677766209642755L;
	private static final String ID_DELIMITER = "||";
	private static final String APP_NAME = "TeNotifi.co";
	
	// Attributes...
	protected static Integer TIMER_LOCK = new Integer(1);
	
	protected Long timerIntervalMs = 60000L;
	protected Boolean isStarted = false;
	protected Timer timer = null;
	protected BreakdownTimerTask timerTask = new BreakdownTimerTask();
	protected Logger _log = Logger.getLogger( BreakdownServlet.class.getName() );
	
	
	// Methods...
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
		
		this.timer = new Timer( BreakdownTimerTask.class.getSimpleName(), true);
		this.timer.scheduleAtFixedRate(
				this.timerTask, 0, this.timerIntervalMs );
		
		// Setup conn to be sure it is available.
		Connection conn = null;
		try
		{
			conn = ConnectionUtil.createConnection(Services.JNDI_JDBC_APP, false);
		}
		catch ( Exception ex )
		{
			_log.severe( "Failed to setup connection " + ex.getLocalizedMessage() );
		}
		finally
		{
			try
			{
				if ( conn != null && ! conn.isClosed() )
				{
					conn.rollback();
					conn.close();
				}
			}
			catch ( Exception ex )
			{
				_log.severe( "Failed to close connection " + ex.getLocalizedMessage() );
			}			
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		super.doGet(req, resp);
		
		//http://localhost:8080/aeebrk/brkpush?_Action_=Test
		String action = req.getParameter( "_Action_" );
		if ( "test".equalsIgnoreCase( action ) )
		{
			this.timerTask.run();
		}
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		if ( this.timer != null )
		{
			this.timer.cancel();
		}
		this.timer = null;
	}
	/**
	 * @author Alberto Aresti
	 * 
	 * Task to by executed whenever the scheduler kickoff
	 */
	protected class BreakdownTimerTask extends TimerTask
	{
		// Constants...

		// Attributes...		
		protected BreakdownServlet myCreator = null;
	
		// Constructors
		protected BreakdownTimerTask()
		{
		}
	
		public BreakdownTimerTask( BreakdownServlet servlet )
		{
			this.myCreator = servlet;
		}
		
		// Methods
		public void run()
		{
			synchronized( BreakdownServlet.TIMER_LOCK )
			{
				Connection conn = null;
				String key = null;
				BksReportedTO bksReportedTO = null;
				Environment env = Environment.Instance();
				Date now = new Date();
				BreakdownNotifierUtil bnu = new BreakdownNotifierUtil();
				
				try
				{
					_log.fine( "BreakdownTimerTask::run()::Entry" );
					
					// ASSUMPTION: city|area are unique keys.
		
					// Setup
					conn = ConnectionUtil.createConnection(Services.JNDI_JDBC_APP, false);
					
					BksReportedDAO bksReportedDAO = new BksReportedDAO( conn );
					BksArchivedDAO bksArchivedDAO = new BksArchivedDAO( conn );
		
					// Get list of previous breakdown reporting.
					List<BksReportedTO> prevList = bksReportedDAO.find();
					conn.commit();
					Map<String, BksReportedTO> prevMap = new TreeMap<>();
					List<String> processedKeys = new ArrayList<>();
					
					while ( !  prevList.isEmpty() )
					{
						bksReportedTO = prevList.remove(0);
						key = bksReportedTO.getCity() + ID_DELIMITER + bksReportedTO.getArea();
						prevMap.put( key, bksReportedTO );
					}
					
					// Get list of current breakdown reporting.
					BreakdownReportPortTypeProxy wsProxy = 
							new BreakdownReportPortTypeProxy( env.getWsAeeBrkEndpoint() );
					
					BreakdownSummary[] bkSumList = wsProxy.getBreakdownsSummary();
										
					// For each current breakdown city WITH reported issues.
					for ( int i = 0; bkSumList != null && i < bkSumList.length; i++ )
					{
						BreakdownSummary brksumm = bkSumList[i];
						
						// If reported issues.
						if ( brksumm.getR1TownOrCity() != null && 
								brksumm.getR2TotalBreakdowns() != null && 
								brksumm.getR2TotalBreakdowns() > 0 )
						{						
							BreakdownArea[] brkdtls = 
									 wsProxy.getBreakdownsByTownOrCity( brksumm.getR1TownOrCity() );
							
							// For each reported breakdown.
							for ( int j = 0; brkdtls != null && j < brkdtls.length; j++ )
							{
								BreakdownArea brkArea = brkdtls[j];
								key = brkArea.getR1TownOrCity() + ID_DELIMITER + brkArea.getR2Area();
														
								if ( prevMap.containsKey( key ) )
								{
									processedKeys.add( key );
									
									// If previously reported, remove from prev list and do nothing.
									prevMap.remove( key );
								}
								else 
								{												
									// Add for future reference.
									bksReportedTO = new BksReportedTO();
									
									bksReportedTO.setCity( brkArea.getR1TownOrCity() );
									bksReportedTO.setArea( brkArea.getR2Area() );
									bksReportedTO.setStatus( brkArea.getR3Status() );
									bksReportedTO.setRptdLastUpdateTs( brkArea.getR4LastUpdate() );
									bksReportedTO.setOpenTs( now );
									
									if ( processedKeys.contains( key ) )
									{
										// if already processed, remove and add latest.
										// ASSUMPTION:  Latest item in list is latest item in terms of event time (when status was updated).
										bksReportedDAO.delete( bksReportedTO );
									}
									else
									{
										processedKeys.add( key );
										
										// If not yet reported, push raised event and add to list.
										bnu.push( conn, bksReportedTO, APP_NAME + " - Averia reportada"
												, "Saludos,<br/>" +
												  "<br/>" +
												  "Se ha reportado una aver&iacute;a en el &aacute;rea: <b>" + BreakdownNotifierUtil.ZONE_PLACEHOLDER + "</b><br/>" +
												  "<br/>" + 
												  "Considere tomar medidas pertinentes.<br/>" +
												  "<br/>" +
												  "Atentamente,<br/>" +
												  "-" + APP_NAME
												  );
										conn.commit(); 
									}
									
									bksReportedDAO.create( bksReportedTO );
									conn.commit();
								}	
							}
						}
					}
					
					// For any previous breakdown that was not in current list
					Iterator<String> it = prevMap.keySet().iterator();
					while ( it.hasNext() )
					{
						key = it.next();
						bksReportedTO = prevMap.get( key );
						
						// Push cleared event						
						bnu.push( conn, bksReportedTO, APP_NAME + " - Cese de averia reportada",
								  "Saludos,<br/>" +
								  "<br/>" +
								  "Ya no se reporta aver&iacute;a en el &aacute;rea: <b>" + BreakdownNotifierUtil.ZONE_PLACEHOLDER + "</b><br/>" +
								  "<br/>" +
								  "Atentamente,<br/>" +
								  "-" + APP_NAME
								  );
						conn.commit();
						
						// and move to archive
						BksArchivedTO bksArchivedTO = new BksArchivedTO();
						bksArchivedTO.setCity( bksReportedTO.getCity() );
						bksArchivedTO.setArea( bksReportedTO.getArea() );
						bksArchivedTO.setStatus( bksReportedTO.getStatus() );
						bksArchivedTO.setRptdLastUpdateTs( bksReportedTO.getRptdLastUpdateTs() );
						bksArchivedTO.setOpenTs( bksReportedTO.getOpenTs() );
						bksArchivedTO.setCloseTs( now );
						
						bksArchivedDAO.create( bksArchivedTO );
						bksReportedDAO.delete( bksReportedTO );

						conn.commit();
					}
				}
				catch ( Exception ex )
				{
					String msg = "Failure during push process - " + ex.getLocalizedMessage();
					_log.severe( msg );
				}
				finally
				{
					try 
					{
						if ( conn != null && ! conn.isClosed() )
						{
							conn.rollback();
							conn.close();
						}
					}
					catch ( Exception ex )
					{
						_log.severe( "Failed to close conn - " + ex.getLocalizedMessage() );
					}
					
					_log.fine( "BreakdownTimerTask::run()::Exit" );
				}		
			}
		}
	}
}
