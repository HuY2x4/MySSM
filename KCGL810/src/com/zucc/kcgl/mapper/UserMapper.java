package com.zucc.kcgl.mapper;

import com.zucc.kcgl.model.MdUser;

public interface UserMapper {

	void saveUser(MdUser user);
	
	void deleteUser(String loginname);
	
	MdUser getUserInf(String loginname);
	
	MdUser getUserInfById(int id);
	
	void updateUserInf(MdUser user);
	
	void updateUserPassword(MdUser user);
	
	String ifLoginNameRepeat(String loginname);
	
	int getUserCount();
}
