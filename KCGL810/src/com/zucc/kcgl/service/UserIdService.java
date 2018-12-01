package com.zucc.kcgl.service;

public interface UserIdService {
	public boolean addUserIdMap(String loginName);
	
	public boolean deleteUserIdMap(String loginName);
	
	public int getUserIdMap(String loginName);
}
