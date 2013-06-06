package com.wovenware.aee.breakdown.reporting.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.wovenware.aee.breakdown.reporting.db.dao.to.UsersTO;

/**
 * <i>Users Direct Access Object (DAO).</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti
 */
public class UsersDAO
{
	// Attributes...
	private Connection _conn = null;
	
	private Logger _log = Logger.getLogger( UsersDAO.class.getName() );

	// Constructors...
	public UsersDAO( Connection conn )
	{
		this._conn = conn;
	}
	
	// Methods...
	public UsersTO find( String pkUserId )
		throws Exception
	{
		UsersTO userTO = null;
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"SELECT `u`.`PK_USER_ID`, `u`.`NAME`, `u`.`PHONE`, `u`.`SMS_IND` " +
				"FROM `aeebk`.`users` u " +
				"where u.PK_USER_ID = ? ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
			pStmt.setString(i++, pkUserId);
			_log.finest( "PK_USER_ID[" + pkUserId + "]" );
					
			rs = pStmt.executeQuery();
			
			if ( rs != null && rs.next() )
			{
				userTO = new UsersTO();
				
				userTO.setPkUserId(	rs.getString(1));
				userTO.setName(		rs.getString(2));
				userTO.setPhone(	rs.getString(3));
				userTO.setSmsInd(	rs.getString(4));
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
		
		return userTO;
	}
	
	public void create( UsersTO userTO )
			throws Exception
	{
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"SELECT `u`.`PK_USER_ID`, `u`.`NAME`, `u`.`PHONE`, `u`.`SMS_IND` " +
				"FROM `aeebk`.`users` u " +
				"where u.PK_USER_ID = ? ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
//				pStmt.setString(i++, pkUserId);
//				_log.finest( "PK_USER_ID[" + pkUserId + "]" );
					
			rs = pStmt.executeQuery();
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
	}
}
