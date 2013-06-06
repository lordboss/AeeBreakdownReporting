package com.wovenware.aee.breakdown.reporting.db.dao.to;

/**
 * <i>Breakdown to Users Transfer Object.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti
 */
public class Bk2UsersTO 
{
	// Attributes...
	private String city = null;
	private String area = null;
	private String fkUserId = null;
	
	// Getters/Setters
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFkUserId() {
		return fkUserId;
	}
	public void setFkUserId(String fkUserId) {
		this.fkUserId = fkUserId;
	}
}
