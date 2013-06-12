package com.wovenware.aee.breakdown.reporting.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.wovenware.aee.breakdown.reporting.db.dao.to.Bk2UsersTO;

/**
 * <i>Breakdowns to Users Direct Access Object (DAO).</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti
 */
public class Bk2UsersDAO 
{
	// Attributes...
	private Connection _conn = null;
	
	private Logger _log = Logger.getLogger( UsersDAO.class.getName() );

	// Constructors...
	public Bk2UsersDAO( Connection conn )
	{
		this._conn = conn;
	}
	
	// Methods...
	public List<Bk2UsersTO> find( String pkUserId )
		throws Exception
	{
		List<Bk2UsersTO> list = new ArrayList<>();
		Bk2UsersTO bk2UsersTO = null;
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"SELECT `b2u`.`CITY`,`b2u`.`AREA`,`b2u`.`FK_USER_ID`,`b2u`.`NAME` " +
				"FROM `aeebk`.`bk_2_users` b2u " +
				"WHERE `b2u`.`FK_USER_ID` = ? ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
			pStmt.setString(i++, pkUserId);
			_log.finest( "FK_USER_ID[" + pkUserId + "]" );
					
			rs = pStmt.executeQuery();
			
			while ( rs != null && rs.next() )
			{
				bk2UsersTO = new Bk2UsersTO();
				
				bk2UsersTO.setCity( 	rs.getString(1));
				bk2UsersTO.setArea(		rs.getString(2));
				bk2UsersTO.setFkUserId(	rs.getString(3));
				bk2UsersTO.setName(		rs.getString(4));
				
				list.add( bk2UsersTO );
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
	
	public List<Bk2UsersTO> find( String city, String area )
			throws Exception
		{
			List<Bk2UsersTO> list = new ArrayList<>();
			Bk2UsersTO bk2UsersTO = null;
			ResultSet rs = null;
			PreparedStatement pStmt = null;
			
			final String SELECT_SQL =
					"SELECT `b2u`.`CITY`,`b2u`.`AREA`,`b2u`.`FK_USER_ID`,`b2u`.`NAME` " +
					"FROM `aeebk`.`bk_2_users` b2u " +
					"WHERE `b2u`.`CITY` = ? AND `b2u`.`AREA` = ? ";
			
			try
			{
				pStmt = this._conn.prepareStatement( SELECT_SQL );
				
				_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
				
				int i = 1;
				pStmt.setString(i++, city);
				_log.finest( "CITY" + city + "]" );
				pStmt.setString(i++, area);
				_log.finest( "AREA[" + area + "]" );
						
				rs = pStmt.executeQuery();
				
				while ( rs != null && rs.next() )
				{
					bk2UsersTO = new Bk2UsersTO();
					
					bk2UsersTO.setCity( 	rs.getString(1));
					bk2UsersTO.setArea(		rs.getString(2));
					bk2UsersTO.setFkUserId(	rs.getString(3));
					bk2UsersTO.setName(		rs.getString(4));
					
					list.add( bk2UsersTO );
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
	
	public void create( Bk2UsersTO bk2UsersTO )
			throws Exception
	{
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"INSERT INTO `aeebk`.`bk_2_users` (`CITY`,`AREA`,`FK_USER_ID`,`NAME`) " +
				"VALUES (?,?,?,?) ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
			pStmt.setString(i++, bk2UsersTO.getCity());
			_log.finest( "CITY[" + bk2UsersTO.getCity() + "]" );
			pStmt.setString(i++, bk2UsersTO.getArea());
			_log.finest( "AREA[" + bk2UsersTO.getArea() + "]" );
			pStmt.setString(i++, bk2UsersTO.getFkUserId());
			_log.finest( "FK_USER_ID[" + bk2UsersTO.getFkUserId() + "]" );
			pStmt.setString(i++, bk2UsersTO.getName());
			_log.finest( "NAME[" + bk2UsersTO.getName() + "]" );
			
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
	
	public void update(String userId, String currentName, String newName)
			throws Exception
	{
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"UPDATE `aeebk`.`bk_2_users` " +
				"SET NAME = ? " +
				"WHERE `FK_USER_ID` = ? AND `NAME` = ?";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
		
			// Where
			pStmt.setString(i++, newName);
			_log.finest( "NAME(new)[" + newName + "]" );
			pStmt.setString(i++, userId);
			_log.finest( "FK_USER_ID[" + userId + "]" );
			pStmt.setString(i++, currentName);
			_log.finest( "NAME(current)[" + currentName + "]" );
			
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
	
	public void delete( Bk2UsersTO bk2UsersTO )
			throws Exception
	{
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"DELETE FROM `aeebk`.`bk_2_users` " +
				"WHERE `CITY` = ? AND `AREA` = ? AND `FK_USER_ID` = ? ";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
		
			// Where
			pStmt.setString(i++, bk2UsersTO.getCity());
			_log.finest( "CITY[" + bk2UsersTO.getCity() + "]" );
			pStmt.setString(i++, bk2UsersTO.getArea());
			_log.finest( "AREA[" + bk2UsersTO.getArea() + "]" );
			pStmt.setString(i++, bk2UsersTO.getFkUserId());
			_log.finest( "FK_USER_ID[" + bk2UsersTO.getFkUserId() + "]" );
			
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
	
	public void delete(String userId, String name)
			throws Exception
	{
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"DELETE FROM `aeebk`.`bk_2_users` " +
				"WHERE `FK_USER_ID` = ? AND `NAME` = ?";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
		
			// Where
			pStmt.setString(i++, userId);
			_log.finest( "FK_USER_ID[" + userId + "]" );
			pStmt.setString(i++, name);
			_log.finest( "NAME[" + name + "]" );
			
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
