package com.zucc.kcgl.mapper;

import com.zucc.kcgl.model.User;



public interface UserMapper {

	int addUser(User user);

	int deleteUser(String loginName);
	
	User getUserInfByloginName(String loginName);
	
	User getUserAllInf(String loginName);
	
	int updateUserInf(User user);
	
	int updateUserPassword(User user);
	
	String hasLoginNameRepeat(String loginName);
	
	int getUserCount();
}
