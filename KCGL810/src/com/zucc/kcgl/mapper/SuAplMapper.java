package com.zucc.kcgl.mapper;

import java.util.List;
import java.util.Map;

import com.zucc.kcgl.model.SucApplication;

public interface SuAplMapper {

	int addSuApl(SucApplication suApl);
	
	int updateSuAplByCode(Map<String,Object> map);
	
	int updateSuAplToDie(int sucAplId);
	
	SucApplication getSuApl(String code);
	
	SucApplication getSuAplByEquAndIn(int equId);
	
//	SucApplication getOutSuApl(String code);
	
	List<SucApplication> getAllSuAplByUser(String loginName);
}
