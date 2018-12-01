package com.zucc.kcgl.model;

import java.util.List;

public class User {
	private String loginName;
	private String password;
	private String userName;
	private String phone;
	private String email;
	private String userSchoolId;
	private int points;
	private UserSchoolInf userSchoolInf;
	private UserIdMap userMapper;
	private List<Application> listApplication;
	private List<EquRecord> listEquRecord;
	private List<SucApplication> listSucApplication;
	
	public UserIdMap getUserMapper() {
		return userMapper;
	}
	public void setUserMapper(UserIdMap userMapper) {
		this.userMapper = userMapper;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserSchoolId() {
		return userSchoolId;
	}
	public void setUserSchoolId(String userSchoolId) {
		this.userSchoolId = userSchoolId;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public UserSchoolInf getUserSchoolInf() {
		return userSchoolInf;
	}
	public void setUserSchoolInf(UserSchoolInf userSchoolInf) {
		this.userSchoolInf = userSchoolInf;
	}
	public List<Application> getListApplication() {
		return listApplication;
	}
	public void setListApplication(List<Application> listApplication) {
		this.listApplication = listApplication;
	}
	public List<EquRecord> getListEquRecord() {
		return listEquRecord;
	}
	public void setListEquRecord(List<EquRecord> listEquRecord) {
		this.listEquRecord = listEquRecord;
	}
	public List<SucApplication> getListSucApplication() {
		return listSucApplication;
	}
	public void setListSucApplication(List<SucApplication> listSucApplication) {
		this.listSucApplication = listSucApplication;
	}
	@Override
	public String toString() {
		return "User [loginName=" + loginName + ", password=" + password
				+ ", userName=" + userName + ", phone=" + phone + ", email="
				+ email + ", userSchoolId=" + userSchoolId + ", points="
				+ points + ", userSchoolInf=" + userSchoolInf + ", userMapper="
				+ userMapper + ", listApplication=" + listApplication
				+ ", listEquRecord=" + listEquRecord + ", listSucApplication="
				+ listSucApplication + "]";
	}
	
	
}
