package com.zucc.kcgl.model;

import java.util.Date;

public class Application {
	private int aplId;
	private String method;
	private String loginName;
	private int equId;
	private String purpose;
	private Date returnTime;
	private String phone;
	private String remark;
	private String state;
	private User user;
	private Equipment equipment;
	private String orderState ;
	
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public int getAplId() {
		return aplId;
	}
	public void setAplId(int aplId) {
		this.aplId = aplId;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public int getEquId() {
		return equId;
	}
	public void setEquId(int equId) {
		this.equId = equId;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	@Override
	public String toString() {
		return "Application [aplId=" + aplId + ", method=" + method
				+ ", loginName=" + loginName + ", equId=" + equId
				+ ", purpose=" + purpose + ", returnTime=" + returnTime
				+ ", phone=" + phone + ", remark=" + remark + ", state="
				+ state + ", user=" + user + ", equipment=" + equipment
				+ ", orderState=" + orderState + "]";
	}
	
}
