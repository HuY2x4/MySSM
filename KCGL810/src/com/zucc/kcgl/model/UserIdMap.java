package com.zucc.kcgl.model;

public class UserIdMap {
	private int userId;
	private String loginName;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	@Override
	public String toString() {
		return "UserMapper [userId=" + userId + ", loginName=" + loginName
				+ ", user=" + user + "]";
	}
	
	
}
