package com.zucc.kcgl.model;

public class SucApplication {
	private int sucAplId;
	private int equId;
	private String state;
	private String code;
	private String loginName;
	private Equipment equipment;
	private User user ;
	public int getSucAplId() {
		return sucAplId;
	}
	public void setSucAplId(int sucAplId) {
		this.sucAplId = sucAplId;
	}
	public int getEquId() {
		return equId;
	}
	public void setEquId(int equId) {
		this.equId = equId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	@Override
	public String toString() {
		return "SucApplication [sucApcId=" + sucAplId + ", equId=" + equId
				+ ", state=" + state + ", code=" + code + ", loginName="
				+ loginName + ", equipment=" + equipment + ", user=" + user
				+ "]";
	}
	
	
}
