package com.zucc.kcgl.mapper;

import com.zucc.kcgl.model.UserSchoolInf;

public interface UserSchoolInfMapper {
	int addUserSchoolInf(UserSchoolInf userSchoolInf);
	
	UserSchoolInf getUserSchoolInf(String userSchoolId);
	
    String hasUserSchoolInf(String hasUserSchoolInf);
}
