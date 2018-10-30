package com.zucc.kcgl.mapper;

import java.util.List;

import com.zucc.kcgl.model.application;
import com.zucc.kcgl.model.equipment;

public interface ApplicationMapper {

	void addApplication(application apl);
	
	List<application> getAllBaseApl(application apl);
	
	application getApl(int id);
	
	void updateApplication(application apl);
	
	application getOrderApl(int equId);
	
	void updateOrderState(int id);
	
	List<application> getAllAplByUser(int userId);
	
	int getAplCount();
	
}
