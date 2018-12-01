package com.zucc.kcgl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.UserMapper;
import com.zucc.kcgl.model.User;
import com.zucc.kcgl.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;

	
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		/*这里有个事物提交的问题还要去研究,在外面
		 * 
		 * */
//		System.out.println("【执行中】UserServiceImpl：saveUser");
		if(userMapper.addUser(user)==0){//添加用户
			return false;
		};
		
		return true;
	}

	@Override
	public boolean deleteUser(String loginName) {
		// TODO Auto-generated method stub
		if(userMapper.deleteUser(loginName)==0){//删除用户
			return false;
		};
	
		return true;
	}

	@Override
	public User getUserInfByLoginName(String loginName) {
		// TODO Auto-generated method stub
		User user=new User();
		user=userMapper.getUserInfByloginName(loginName);
		return user;
	}

	@Override
	public boolean updateUserInf(User user) {
		// TODO Auto-generated method stub
		userMapper.updateUserInf(user);
		return true;
	}

	@Override
	public boolean updateUserPassword(String loginname, String password) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setLoginName(loginname);
		user.setPassword(password);
		userMapper.updateUserPassword(user);
		return true;
	}

	@Override
	public boolean hasLoginNameRepeat(String loginname) {
		// TODO Auto-generated method stub
		String name=userMapper.hasLoginNameRepeat(loginname);
		if(name==null){
			return false;
		}
		if(name.equals(loginname)){
			return true;
		}
		
		return false;
	}



	@Override
	public int getUserCount() {
		// TODO Auto-generated method stub
		return userMapper.getUserCount();
	}

	@Override
	public User getUserAllInf(String loginName) {
		// TODO Auto-generated method stub
		
		return userMapper.getUserAllInf(loginName);
	}

	
}
