package com.zucc.kcgl.service;

import java.util.List;

import com.zucc.kcgl.model.Application;

public interface AplService {

	public boolean addApl(Application apl);
	
	public List<Application> getAplBySort(String method,String loginName,int equId,String state,int currentPage,int pageSize);
	
	public Application getApl(int aplId);
	
	public Application getOrderApl(int equId);
	
	public boolean updateApl(Application apl);
		
	public int getAplCount();
}
