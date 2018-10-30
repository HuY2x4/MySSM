package com.zucc.kcgl.service;

import com.zucc.kcgl.model.MdUser;

public interface UserService {

	public boolean saveUser(MdUser user);
	
	public boolean deleteUser(String loginname);
	
	public MdUser getUserInf(String loginname);
	
	public MdUser getUserInfById(int userid);
	
	public boolean updateUserInf(MdUser user);
	
	public boolean updateUserPassword(String loginname,String password);
	
	public boolean ifLoginNameRepeat(String loginname);
	
	public int getUserCount();

}
