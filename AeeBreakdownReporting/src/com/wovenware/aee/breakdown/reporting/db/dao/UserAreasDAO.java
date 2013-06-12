package com.wovenware.aee.breakdown.reporting.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.wovenware.aee.breakdown.reporting.db.dao.to.UserAreasTO;

/**
 * <i>User Areas with Breakdowns Direct Access Object (DAO).</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Nelson Perez
 */
public class UserAreasDAO
{
	// Attributes...
	private Connection _conn = null;
	
	private Logger _log = Logger.getLogger( UserAreasDAO.class.getName() );

	// Constructors...
	public UserAreasDAO( Connection conn )
	{
		this._conn = conn;
	}
	
	// Methods...
	public int countBreakdowns(String userId)
			throws Exception
	{
		int breakdownsCount = 0;
		
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL = "SELECT COUNT(`b2u`.`NAME`) " +
					"FROM `aeebk`.`bk_2_users` b2u " +
					"INNER JOIN `aeebk`.`bks_reported` br " +
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
			
			if ( rs != null && rs.next() )
			{
				breakdownsCount = rs.getInt(1);
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
		
		return breakdownsCount;
	}
	
	public List<UserAreasTO> findBreakdowns(String userId)
			throws Exception
	{
		List<UserAreasTO> userAreasTOList = new ArrayList<UserAreasTO>();
		
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL = "SELECT " + 
				"`b2u`.`CITY`, " +
				"`b2u`.`AREA`, " +
				"`b2u`.`FK_USER_ID`, " +
				"`b2u`.`NAME`, " +
				"`ar`.`STATUS`, " +
				"`ar`.`RPTD_LAST_UPDATE_TS`, " +
				"`ar`.`OPEN_TS`, " +
				"`ar`.`CLOSE_TS` " +
				"FROM `aeebk`.`bk_2_users` b2u " +
				"INNER JOIN `aeebk`.`bks_archived` ar " +
					"ON `b2u`.`CITY` = `ar`.`CITY` " +
						"AND `b2u`.`AREA` = `ar`.`AREA` " +
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
				UserAreasTO userAreasTO = new UserAreasTO();
				
				userAreasTO.setCity(			rs.getString(1));
				userAreasTO.setArea(			rs.getString(2));
				userAreasTO.setFkUserId(		rs.getString(3));
				userAreasTO.setName(			rs.getString(4));
				userAreasTO.setStatus(			rs.getString(5));
				userAreasTO.setRptdLastUpdateTs(rs.getString(6));
				userAreasTO.setOpenTs(			rs.getTimestamp(7));
				userAreasTO.setCloseTs(			rs.getTimestamp(8));
				
				userAreasTOList.add(userAreasTO);
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
		
		return userAreasTOList;
	}
	
	public List<UserAreasTO> find(String userId)
			throws Exception
	{
		List<UserAreasTO> userAreasTOList = new ArrayList<UserAreasTO>();
		
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL = "SELECT " + 
					"`b2u`.`CITY`, " +
					"`b2u`.`AREA`, " +
					"`b2u`.`FK_USER_ID`, " +
					"`b2u`.`NAME`, " +
					"`br`.`STATUS`, " +
					"`br`.`RPTD_LAST_UPDATE_TS`, " +
					"`br`.`OPEN_TS` " +
					"FROM `aeebk`.`bk_2_users` b2u " +
					"LEFT OUTER JOIN `aeebk`.`bks_reported` br " +
						"ON `b2u`.`CITY` = `br`.`CITY` " +
							"AND `b2u`.`AREA` = `br`.`AREA` " +
					"WHERE `b2u`.`FK_USER_ID` = ? " +
					"ORDER BY `b2u`.`NAME` ASC";
		
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
				UserAreasTO userAreasTO = new UserAreasTO();
				
				userAreasTO.setCity(			rs.getString(1));
				userAreasTO.setArea(			rs.getString(2));
				userAreasTO.setFkUserId(		rs.getString(3));
				userAreasTO.setName(			rs.getString(4));
				userAreasTO.setStatus(			rs.getString(5));
				userAreasTO.setRptdLastUpdateTs(rs.getString(6));
				userAreasTO.setOpenTs(			rs.getTimestamp(7));
				
				userAreasTOList.add(userAreasTO);
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
		
		return userAreasTOList;
	}
}
