package com.wovenware.aee.breakdown.reporting.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.wovenware.aee.breakdown.reporting.db.dao.to.BksArchivedTO;

/**
 * <i>Breakdowns Archived Direct Access Object (DAO).</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti
 */
public class BksArchivedDAO 
{
	// Attributes...
	private Connection _conn = null;
	
	private Logger _log = Logger.getLogger( BksReportedDAO.class.getName() );

	// Constructors...
	public BksArchivedDAO( Connection conn )
	{
		this._conn = conn;
	}
	
	// Methods...
	public List<BksArchivedTO> find()
			throws Exception
		{
			List<BksArchivedTO> list = new ArrayList<>();
			BksArchivedTO bksArchivedTO = null;
			ResultSet rs = null;
			PreparedStatement pStmt = null;
			
			final String SELECT_SQL =
					"SELECT " + 
						"`br`.`CITY`, " +
						"`br`.`AREA`, " +
						"`br`.`STATUS`, " +
						"`br`.`RPTD_LAST_UPDATE_TS`, " +
						"`br`.`OPEN_TS`, " +
						"`br`.`CLOSE_TS` " +
						"FROM `aeebk`.`bks_archived` br ";
			
			try
			{
				pStmt = this._conn.prepareStatement( SELECT_SQL );
				
				_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
				
				rs = pStmt.executeQuery();
				
				while ( rs != null && rs.next() )
				{
					bksArchivedTO = new BksArchivedTO();
					
					bksArchivedTO.setCity(				rs.getString(1));
					bksArchivedTO.setArea(				rs.getString(2));
					bksArchivedTO.setStatus(			rs.getString(3));
					bksArchivedTO.setRptdLastUpdateTs(	rs.getTimestamp(4));
					bksArchivedTO.setOpenTs(			rs.getTimestamp(5));
					bksArchivedTO.setCloseTs(			rs.getTimestamp(5));
					
					list.add( bksArchivedTO );
				}
			}
			finally
			{
				if ( rs != null )
				{
					rs.close();
				}
				
				if ( pStmt != null && ! pStmt.isClosed() )
				{
					pStmt.close();
				}
			}
			
			return list;
		}
	
	public void create( BksArchivedTO bksArchivedTO )
			throws Exception
	{
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"INSERT INTO `aeebk`.`bks_archived` (`CITY`,`AREA`,`STATUS`,`RPTD_LAST_UPDATE_TS`,`OPEN_TS`,`CLOSE_TS`) " +
				"VALUES(?,?,?,?,?,?) ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
			pStmt.setString(i++, bksArchivedTO.getCity());
			_log.finest( "CITY[" + bksArchivedTO.getCity() + "]" );
			pStmt.setString(i++, bksArchivedTO.getArea());
			_log.finest( "AREA[" + bksArchivedTO.getArea() + "]" );
			pStmt.setString(i++, bksArchivedTO.getStatus());
			_log.finest( "STATUS[" + bksArchivedTO.getStatus() + "]" );
			if ( bksArchivedTO.getRptdLastUpdateTs() != null )
			{
				pStmt.setTimestamp(i++, 
						new java.sql.Timestamp( bksArchivedTO.getRptdLastUpdateTs().getTime() ) );
			}
			else
			{
				pStmt.setTimestamp(i++, null);
			}
			_log.finest( "RPTD_LAST_UPDATE_TS[" + bksArchivedTO.getRptdLastUpdateTs() + "]" );
			
			if( bksArchivedTO.getOpenTs() != null )
			{
				pStmt.setTimestamp(i++, 
						new java.sql.Timestamp( bksArchivedTO.getOpenTs().getTime() ) );
			}
			else
			{
				pStmt.setTimestamp(i++, null);
			}			
			_log.finest( "OPEN_TS[" + bksArchivedTO.getOpenTs() + "]" );
			
			if( bksArchivedTO.getCloseTs() != null )
			{
				pStmt.setTimestamp(i++, 
						new java.sql.Timestamp( bksArchivedTO.getCloseTs().getTime() ) );
			}
			else
			{
				pStmt.setTimestamp(i++, null);
			}			
			_log.finest( "CLOSE_TS[" + bksArchivedTO.getCloseTs() + "]" );
			
//			int cnt = 
					pStmt.executeUpdate();
		}
		finally
		{
			if ( pStmt != null && ! pStmt.isClosed() )
			{
				pStmt.close();
			}
		}
	}
	
//	public void update( BksReportedTO bksReportedTO )
//			throws Exception
//	{
//		PreparedStatement pStmt = null;
//		
//		final String SELECT_SQL =
//				"UPDATE `aeebk`.`users` SET `NAME` = ?, `PHONE` = ?, `SMS_IND` = ? " +
//				"WHERE `PK_USER_ID` = ? ";
//		
//		try
//		{
//			pStmt = this._conn.prepareStatement( SELECT_SQL );
//			
//			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
//			
//			int i = 1;
//		
//			// Set
//			pStmt.setString(i++, userTO.getName());
//			_log.finest( "NAME[" + userTO.getName() + "]" );
//			pStmt.setString(i++, userTO.getPhone());
//			_log.finest( "PHONE[" + userTO.getPhone() + "]" );
//			pStmt.setString(i++, userTO.getSmsInd());
//			_log.finest( "SMS_IND[" + userTO.getSmsInd() + "]" );
//			
//			// Where
//			pStmt.setString(i++, userTO.getPkUserId());
//			_log.finest( "PK_USER_ID[" + userTO.getPkUserId() + "]" );
//			
////			int cnt = 
//					pStmt.executeUpdate();
//		}
//		finally
//		{
//			if ( pStmt != null && ! pStmt.isClosed() )
//			{
//				pStmt.close();
//			}
//		}
//	}
	
//	public void delete( BksArchivedTO bksArchivedTO )
//			throws Exception
//	{
//		PreparedStatement pStmt = null;
//		
//		final String SELECT_SQL =
//				"DELETE FROM `aeebk`.`bks_archived` " +
//				"WHERE `CITY` = ? " +
//					"AND `AREA` = ? ";
//		
//		try
//		{
//			pStmt = this._conn.prepareStatement( SELECT_SQL );
//			
//			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
//			
//			int i = 1;
//		
//			// Where
//			pStmt.setString(i++, bksArchivedTO.getCity());
//			_log.finest( "CITY[" + bksArchivedTO.getCity() + "]" );
//			pStmt.setString(i++, bksArchivedTO.getArea());
//			_log.finest( "AREA[" + bksArchivedTO.getArea() + "]" );
//			
////			int cnt = 
//					pStmt.executeUpdate();
//		}
//		finally
//		{
//			if ( pStmt != null && ! pStmt.isClosed() )
//			{
//				pStmt.close();
//			}
//		}
//	}
}
