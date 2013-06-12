package com.wovenware.aee.breakdown.reporting.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.wovenware.aee.breakdown.reporting.db.dao.to.CityAreasTO;

/**
 * <i>City Areas Direct Access Object (DAO).</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Nelson Perez
 */
public class CityAreasDAO
{
	// Attributes...
	private Connection _conn = null;
	
	private Logger _log = Logger.getLogger( CityAreasDAO.class.getName() );

	// Constructors...
	public CityAreasDAO( Connection conn )
	{
		this._conn = conn;
	}
	
	// Methods...
	public List<CityAreasTO> findCities(String userId)
		throws Exception
	{
		List<CityAreasTO> cityAreasTOList = new ArrayList<CityAreasTO>();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"SELECT DISTINCT `ca`.`CITY` " +
				"FROM `aeebk`.`city_areas` ca " +
				"WHERE AREA NOT IN (" +
				"SELECT `b2u`.`AREA` " +
				"FROM `aeebk`.`bk_2_users` b2u " +
				"WHERE FK_USER_ID = ?)";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
			pStmt.setString(i++, userId);
			_log.finest( "FK_USER_ID[" + userId + "]" );
					
			rs = pStmt.executeQuery();
			
			if(rs != null)
			{
				while(rs.next() )
				{
					CityAreasTO cityAreasTO = new CityAreasTO();
					cityAreasTO.setCity(rs.getString(1));
					
					cityAreasTOList.add(cityAreasTO);
				}
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
		
		return cityAreasTOList;
	}
	
	public List<CityAreasTO> findAreas(String city, String userId)
			throws Exception
	{
		List<CityAreasTO> cityAreasTOList = new ArrayList<CityAreasTO>();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		
		final String SELECT_SQL =
				"SELECT `ca`.`CITY`,`ca`.`AREA` " +
				"FROM `aeebk`.`city_areas` ca " +
				"WHERE CITY = ? AND AREA NOT IN (" +
				"SELECT `b2u`.`AREA` " +
				"FROM `aeebk`.`bk_2_users` b2u " +
				"WHERE FK_USER_ID = ?)";
		
		try
		{
			pStmt = this._conn.prepareStatement( SELECT_SQL );
			
			_log.finest( "Will execute sql[" + SELECT_SQL + "]..." );
			
			int i = 1;
			pStmt.setString(i++, city);
			_log.finest( "CITY[" + city + "]" );
			pStmt.setString(i++, userId);
			_log.finest( "FK_USER_ID[" + userId + "]" );
					
			rs = pStmt.executeQuery();
			
			if(rs != null)
			{
				while(rs.next() )
				{
					CityAreasTO cityAreasTO = new CityAreasTO();
					cityAreasTO.setCity(rs.getString(1));
					cityAreasTO.setArea(rs.getString(2));
					
					cityAreasTOList.add(cityAreasTO);
				}
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
		
		return cityAreasTOList;
	}
}
