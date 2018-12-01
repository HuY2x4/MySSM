package com.zucc.kcgl.service;

import java.util.List;

import com.zucc.kcgl.model.SucApplication;

public interface SuAplService {

	public boolean addSuApl(SucApplication suApl);
	
	public String updateSuApl(String code,String state);
	
	public boolean updateSuAplToDie(int sucAplId);
	
	public SucApplication getSuApl(String code);
	
	public SucApplication getSuAplByEquAndIn(int equId);
	
	public List<SucApplication> getAllSuAplByUser(String loginName);
}
