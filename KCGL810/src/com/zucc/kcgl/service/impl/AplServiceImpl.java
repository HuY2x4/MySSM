package com.zucc.kcgl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.ApplicationMapper;
import com.zucc.kcgl.model.Application;
import com.zucc.kcgl.service.AplService;

@Service
public class AplServiceImpl implements AplService{

	
	@Resource
	private ApplicationMapper AplMapper;
	
	@Override
	public boolean addApl(Application apl) {
		// TODO Auto-generated method stub
		if(AplMapper.addApl(apl)==0){
			return false;
		};
		
		return true;
	}

	@Override
	public List<Application> getAplBySort(String method,String loginName,int equId,String state,int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		List<Application> list=new ArrayList<Application>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("startPage", (currentPage-1)*pageSize);
		map.put("pageSize", pageSize);
		if(!(method==null)){
			map.put("method", method);
		}
		if(!(loginName==null)){
			map.put("loginName", loginName);
		}
		if(!(equId==0)){
			map.put("equId", equId);
		}
		if(!(state==null)){
			map.put("state", state);
		}
		list=AplMapper.getAplBySort(map);
		return list;
	}

	@Override
	public Application getApl(int equId) {
		// TODO Auto-generated method stub
		Application apl=new Application();
		apl=AplMapper.getApl(equId);
		return apl;
	}

	@Override
	public boolean updateApl(Application apl) {
		// TODO Auto-generated method stub
		
		if(AplMapper.updateApl(apl)==0){
			return false;
		}
		
		return true;
	}



	@Override
	public int getAplCount() {
		// TODO Auto-generated method stub
		
		return AplMapper.getAplCount();
	}

	@Override
	public Application getOrderApl(int equId) {
		// TODO Auto-generated method stub
		Application apl=new Application();
		apl=AplMapper.getOrderApl(equId);
		return apl;
	}

}
