package com.zucc.kcgl.mapper;

public interface UserIdMapMapper {

	int addUserIdMap(String loginName);
	
	int deleteUserIdMap(String loginName);
	
	int getUserIdMap(String loginName);
}
