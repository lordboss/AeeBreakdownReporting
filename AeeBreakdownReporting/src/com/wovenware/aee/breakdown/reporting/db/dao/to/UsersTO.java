package com.wovenware.aee.breakdown.reporting.db.dao.to;

/**
 * <i>Users Transfer Object.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti
 */
public class UsersTO 
{
	// Attributes...
	private String pkUserId = null;
	private String name = null;
	private String phone = null;
	private String smsInd = null;
	
	// Getters/Setters
	public String getPkUserId() {
		return pkUserId;
	}
	public void setPkUserId(String pkUserId) {
		this.pkUserId = pkUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSmsInd() {
		return smsInd;
	}
	public void setSmsInd(String smsInd) {
		this.smsInd = smsInd;
	}
}
