package com.wovenware.aee.breakdown.reporting.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.wovenware.aee.breakdown.reporting.db.dao.to.BksReportedTO;

/**
 * <i>Breakdowns Reported Direct Access Object (DAO).</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti
 */
public class BksReportedDAO
{
	// Attributes...
	private Connection _conn = null;
	
	private Logger _log = Logger.getLogger( BksReportedDAO.class.getName() );

	// Constructors...
	public BksReportedDAO( Connection conn )
	{
		this._conn = conn;
	}
	
	// Methods...
	public BksReportedTO find( String city, String area )
		throws Exception
	{
		BksReportedTO bksReportedTO = null;
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"SELECT " + 
					"`br`.`CITY`, " +
					"`br`.`AREA`, " +
					"`br`.`STATUS`, " +
					"`br`.`RPTD_LAST_UPDATE_TS`, " +
					"`br`.`OPEN_TS` " +
					"FROM `aeebk`.`bks_reported` br " +
					"WHERE `br`.`CITY` = ? " +
						"and `br`.`AREA` = ? ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
			pStmt.setString(i++, city);
			_log.finest( "CITY[" + city + "]" );		
			pStmt.setString(i++, area);
			_log.finest( "AREA[" + area + "]" );
			
			rs = pStmt.executeQuery();
			
			if ( rs != null && rs.next() )
			{
				bksReportedTO = new BksReportedTO();
				
				bksReportedTO.setCity(				rs.getString(1));
				bksReportedTO.setArea(				rs.getString(2));
				bksReportedTO.setStatus(			rs.getString(3));
				bksReportedTO.setRptdLastUpdateTs(	rs.getString(4));
				bksReportedTO.setOpenTs(			rs.getTimestamp(5));
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
		
		return bksReportedTO;
	}
	
	public List<BksReportedTO> find( String userId )
			throws Exception
	{
		List<BksReportedTO> bksReportedTOList = new ArrayList<BksReportedTO>();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"SELECT " + 
					"`br`.`CITY`, " +
					"`br`.`AREA`, " +
					"`br`.`STATUS`, " +
					"`br`.`RPTD_LAST_UPDATE_TS`, " +
					"`br`.`OPEN_TS` " +
					"FROM `aeebk`.`bks_reported` br " +
					"INNER JOIN `aeebk`.`bk_2_users` b2u " +
						"ON `b2u`.`CITY` = `br`.`CITY` " +
							"AND `b2u`.`AREA` = `br`.`AREA` " +
					"WHERE `b2u`.`FK_USER_ID` = ? ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
			pStmt.setString(i++, userId);
			_log.finest( "FK_USER_ID[" + userId + "]" );
			
			rs = pStmt.executeQuery();
			
			while ( rs != null && rs.next() )
			{
				BksReportedTO bksReportedTO = new BksReportedTO();
				
				bksReportedTO.setCity(				rs.getString(1));
				bksReportedTO.setArea(				rs.getString(2));
				bksReportedTO.setStatus(			rs.getString(3));
				bksReportedTO.setRptdLastUpdateTs(	rs.getString(4));
				bksReportedTO.setOpenTs(			rs.getTimestamp(5));
				
				bksReportedTOList.add(bksReportedTO);
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
		
		return bksReportedTOList;
	}
	
	public List<BksReportedTO> find()
			throws Exception
	{
		List<BksReportedTO> list = new ArrayList<>();
		BksReportedTO bksReportedTO = null;
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"SELECT " + 
					"`br`.`CITY`, " +
					"`br`.`AREA`, " +
					"`br`.`STATUS`, " +
					"`br`.`RPTD_LAST_UPDATE_TS`, " +
					"`br`.`OPEN_TS` " +
					"FROM `aeebk`.`bks_reported` br ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			rs = pStmt.executeQuery();
			
			while ( rs != null && rs.next() )
			{
				bksReportedTO = new BksReportedTO();
				
				bksReportedTO.setCity(				rs.getString(1));
				bksReportedTO.setArea(				rs.getString(2));
				bksReportedTO.setStatus(			rs.getString(3));
				bksReportedTO.setRptdLastUpdateTs(	rs.getString(4));
				bksReportedTO.setOpenTs(			rs.getTimestamp(5));
				
				list.add( bksReportedTO );
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
	
	public void create( BksReportedTO bksReportedTO )
			throws Exception
	{
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"INSERT INTO `aeebk`.`bks_reported` (`CITY`,`AREA`,`STATUS`,`RPTD_LAST_UPDATE_TS`,`OPEN_TS`) " +
				"VALUES(?,?,?,?,?) ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
			pStmt.setString(i++, bksReportedTO.getCity());
			_log.finest( "CITY[" + bksReportedTO.getCity() + "]" );
			pStmt.setString(i++, bksReportedTO.getArea());
			_log.finest( "AREA[" + bksReportedTO.getArea() + "]" );
			pStmt.setString(i++, bksReportedTO.getStatus());
			_log.finest( "STATUS[" + bksReportedTO.getStatus() + "]" );
			pStmt.setString(i++, bksReportedTO.getRptdLastUpdateTs());
			_log.finest( "RPTD_LAST_UPDATE_TS[" + bksReportedTO.getRptdLastUpdateTs() + "]" );
			
			if( bksReportedTO.getOpenTs() != null )
			{
				pStmt.setTimestamp(i++, 
						new java.sql.Timestamp( bksReportedTO.getOpenTs().getTime() ) );
			}
			else
			{
				pStmt.setTimestamp(i++, null);
			}			
			_log.finest( "OPEN_TS[" + bksReportedTO.getOpenTs() + "]" );
			
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
	
	public void delete( BksReportedTO bksReportedTO )
			throws Exception
	{
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"DELETE FROM `aeebk`.`bks_reported` " +
				"WHERE `CITY` = ? " +
					"AND `AREA` = ? ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
		
			// Where
			pStmt.setString(i++, bksReportedTO.getCity());
			_log.finest( "CITY[" + bksReportedTO.getCity() + "]" );
			pStmt.setString(i++, bksReportedTO.getArea());
			_log.finest( "AREA[" + bksReportedTO.getArea() + "]" );
			
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
}
