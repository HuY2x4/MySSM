package com.zucc.kcgl.model;

public class UserSchoolInf {
	private String userSchoolId;
	private String stuOrTea;
	private String userFrom;
	public String getUserSchoolId() {
		return userSchoolId;
	}
	public void setUserSchoolId(String userSchoolId) {
		this.userSchoolId = userSchoolId;
	}
	public String getStuOrTea() {
		return stuOrTea;
	}
	public void setStuOrTea(String stuOrTea) {
		this.stuOrTea = stuOrTea;
	}
	public String getUserFrom() {
		return userFrom;
	}
	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}
	@Override
	public String toString() {
		return "UserSchoolInf [userSchoolId=" + userSchoolId + ", stuOrTea="
				+ stuOrTea + ", userFrom=" + userFrom + "]";
	}
	
}
