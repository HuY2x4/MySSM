package com.zucc.kcgl.service;

import com.zucc.kcgl.model.User;

public interface UserService {

	public boolean addUser(User user);
	
	public boolean deleteUser(String loginName);
	
	public User getUserInfByLoginName(String loginName);
	
	public User getUserAllInf(String loginName);
	
	public boolean updateUserInf(User user);
	
	public boolean updateUserPassword(String loginName,String password);
	
	public boolean hasLoginNameRepeat(String loginName);
	
	public int getUserCount();
	
	public boolean updAccessKey(String accesskey,String loginName,String time);
	
	public boolean hasExpires(String accesskey);
	
	public String getLoginNameByKey(String accesskey);

}
