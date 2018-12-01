package com.zucc.kcgl.service;

import com.zucc.kcgl.model.UserSchoolInf;

public interface UserSchoolInfService {
	int addUserSchoolInf(UserSchoolInf userSchoolInf);
	
	boolean hasUserSchoolInf(String userSchoolId);
}
