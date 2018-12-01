package com.zucc.kcgl.model;

import java.util.Date;

public class EquRecord {
	private int recordId;
	private String state;
	private Date date;
	private String remark;
	private int equId;
	private String loginName;
	private String registrar;
	private Equipment equipment;
	private User user;
	private Admin admin;
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getEquId() {
		return equId;
	}
	public void setEquId(int equId) {
		this.equId = equId;
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRegistrar() {
		return registrar;
	}
	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "EquRecord [recordId=" + recordId + ", state=" + state
				+ ", date=" + date + ", remark=" + remark + ", equId=" + equId
				+ ", loginName=" + loginName + ", registrar=" + registrar
				+ ", equipment=" + equipment + ", user=" + user + ", admin="
				+ admin + "]";
	}
	
}
