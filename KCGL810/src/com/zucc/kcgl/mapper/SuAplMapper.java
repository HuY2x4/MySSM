package com.zucc.kcgl.mapper;

import java.util.List;

import com.zucc.kcgl.model.sucApplication;

public interface SuAplMapper {

	void addSuApl(sucApplication suapl);
	
	void updateSuApl(sucApplication suapl);
	
	sucApplication getSuApl(int code);
	
	sucApplication getOutSuApl(int code);
	
	List<sucApplication> getAllSuAplByUser(int userId);
}
