package com.wovenware.aee.breakdown.reporting.db.dao.to;

import java.util.Date;

/**
 * <i>User Areas with Breakdown Info.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Nelson Perez
 */
public class UserAreasTO 
{
	// Attributes...
	private String fkUserId = null;
	private String name = null;
	private String city = null;
	private String area = null;
	private String status = null;
	private String rptdLastUpdateTs = null;
	private Date openTs = null;
	private Date closeTs = null;
		
	// Getters/Setters
	public String getFkUserId() {
		return fkUserId;
	}
	public void setFkUserId(String fkUserId) {
		this.fkUserId = fkUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRptdLastUpdateTs() {
		return rptdLastUpdateTs;
	}
	public void setRptdLastUpdateTs(String rptdLastUpdateTs) {
		this.rptdLastUpdateTs = rptdLastUpdateTs;
	}
	public Date getOpenTs() {
		return openTs;
	}
	public void setOpenTs(Date openTs) {
		this.openTs = openTs;
	}
	public Date getCloseTs() {
		return closeTs;
	}
	public void setCloseTs(Date closeTs) {
		this.closeTs = closeTs;
	}
}
