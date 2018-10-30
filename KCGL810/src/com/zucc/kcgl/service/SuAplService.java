package com.zucc.kcgl.service;

import java.util.List;

import com.zucc.kcgl.model.sucApplication;

public interface SuAplService {

	public boolean addSuApl(sucApplication suapl);
	
	public boolean updateSuApl(int code,String state);
	
	public sucApplication getSuApl(int code);
	
	public List<sucApplication> getAllSuAplByUser(int userId);
}
