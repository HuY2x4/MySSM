package com.zucc.kcgl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.UserIdMapMapper;
import com.zucc.kcgl.service.UserIdService;
@Service
public class UserIdServiceImpl implements UserIdService{

	@Resource
	private UserIdMapMapper userIdMapper;
	
	@Override
	public boolean addUserIdMap(String loginName) {
		// TODO Auto-generated method stub
		if(userIdMapper.addUserIdMap(loginName)==0){//添加用户的ID
			return false;
		};
		return true;
		
	}

	@Override
	public boolean deleteUserIdMap(String loginName) {
		// TODO Auto-generated method stub
		if(userIdMapper.deleteUserIdMap(loginName)==0){//删除用户的ID
			return false;
		};
		return true;
	}

	@Override
	public int getUserIdMap(String loginName) {
		// TODO Auto-generated method stub
		
		return userIdMapper.getUserIdMap(loginName);
	}

}
