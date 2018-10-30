package com.zucc.kcgl.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zucc.kcgl.mapper.UserMapper;
import com.zucc.kcgl.model.MdUser;
import com.zucc.kcgl.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;
	
	@Override
	public boolean saveUser(MdUser user) {
		// TODO Auto-generated method stub
		boolean result=false;
		System.out.println("22");
		userMapper.saveUser(user);
		result=true;
		return result;
	}

	@Override
	public boolean deleteUser(String loginname) {
		// TODO Auto-generated method stub
		userMapper.deleteUser(loginname);
		return true;
	}

	@Override
	public MdUser getUserInf(String loginname) {
		// TODO Auto-generated method stub
		MdUser user=new MdUser();
		user=userMapper.getUserInf(loginname);
		return user;
	}

	@Override
	public boolean updateUserInf(MdUser user) {
		// TODO Auto-generated method stub
		userMapper.updateUserInf(user);
		return true;
	}

	@Override
	public boolean updateUserPassword(String loginname, String password) {
		// TODO Auto-generated method stub
		MdUser user=new MdUser();
		user.setLoginName(loginname);
		user.setPassword(password);
		userMapper.updateUserPassword(user);
		return true;
	}

	@Override
	public boolean ifLoginNameRepeat(String loginname) {
		// TODO Auto-generated method stub
		String name=userMapper.ifLoginNameRepeat(loginname);
		if(name.equals(loginname)){
			return true;
		}
		
		
		
		
		return false;
	}

	@Override
	public MdUser getUserInfById(int userid) {
		// TODO Auto-generated method stub
		MdUser user=new MdUser();
		user=userMapper.getUserInfById(userid);
		return user;
	}

	@Override
	public int getUserCount() {
		// TODO Auto-generated method stub
		return userMapper.getUserCount();
	}

	
}
