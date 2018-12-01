package com.zucc.kcgl.mapper;

import java.util.List;
import java.util.Map;

import com.zucc.kcgl.model.Application;



public interface ApplicationMapper {

	int addApl(Application apl);
	
	List<Application> getAplBySort(Map<String,Object> map);
	
	Application getApl(int aplId);
	
	int updateApl(Application apl);
	
	int getAplCount();
	
	Application getOrderApl(int equId);
	
	
	
}
