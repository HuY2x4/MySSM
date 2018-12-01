package com.zucc.kcgl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.UserSchoolInfMapper;
import com.zucc.kcgl.model.UserSchoolInf;
import com.zucc.kcgl.service.UserSchoolInfService;
@Service
public class UserSchoolInfServiceImpl implements UserSchoolInfService{

	@Resource
	private UserSchoolInfMapper userSchoolInfMapper;
	
	@Override
	public int addUserSchoolInf(UserSchoolInf userSchoolInf) {
		// TODO Auto-generated method stub
		
		return userSchoolInfMapper.addUserSchoolInf(userSchoolInf);
	}

	@Override
	public boolean hasUserSchoolInf(String userSchoolId) {
		// TODO Auto-generated method stub
		UserSchoolInf userSchoolInf=new UserSchoolInf();
		String schoolId=userSchoolInfMapper.hasUserSchoolInf(userSchoolId);
		if(schoolId==null){
			return false;
		}
		return true;
	}

}
