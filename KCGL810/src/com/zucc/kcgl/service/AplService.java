package com.zucc.kcgl.service;

import java.util.List;

import com.zucc.kcgl.model.application;

public interface AplService {

	public boolean addApplication(application apl);
	
	public List<application> getAllBaseApl(String method,String state,int page,int num);
	
	public application getApl(int id);
	
	public application getOrderApl(int equId);
	
	public boolean updateApplication(int id ,String state);
	
	public void updateOrderState(int id);
	
	public List<application> getAllAplByUser(int userId);
	
	public int getAplCount();
}
