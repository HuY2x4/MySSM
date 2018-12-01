package com.zucc.kcgl.model;

public class Admin {
	private int adminId;
	private String loginName;
	private int level;
	private User user;
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", loginName=" + loginName
				+ ", level=" + level + ", user=" + user + "]";
	}
	
	
}
