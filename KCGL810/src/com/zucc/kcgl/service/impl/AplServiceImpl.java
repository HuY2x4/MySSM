package com.zucc.kcgl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.ApplicationMapper;
import com.zucc.kcgl.mapper.EquMapper;
import com.zucc.kcgl.model.application;
import com.zucc.kcgl.model.equipment;
import com.zucc.kcgl.service.AplService;
import com.zucc.kcgl.service.EquService;

@Service
public class AplServiceImpl implements AplService{

	
	@Resource
	private ApplicationMapper AplMapper;
	
	@Override
	public boolean addApplication(application apl) {
		// TODO Auto-generated method stub
		AplMapper.addApplication(apl);
		
		return true;
	}

	@Override
	public List<application> getAllBaseApl(String method,String state, int page, int num) {
		// TODO Auto-generated method stub
		List<application> list=new ArrayList<application>();
		application apl=new application();
		apl.setMethod(method);
		apl.setState(state);
		list=AplMapper.getAllBaseApl(apl);
		List<application> listNew=new ArrayList<application>();
		for(int i=0;i<num;i++){
			listNew.add(list.get((page-1)*num+i));
		}
		return listNew;
	}

	@Override
	public application getApl(int id) {
		// TODO Auto-generated method stub
		application apl=new application();
		apl=AplMapper.getApl(id);
		return apl;
	}

	@Override
	public boolean updateApplication(int id, String state) {
		// TODO Auto-generated method stub
		application apl=new application();
		apl.setId(id);
		apl.setState(state);
		AplMapper.updateApplication(apl);
		
		return true;
	}

	@Override
	public application getOrderApl(int equId) {
		// TODO Auto-generated method stub
		application apl=new application();
		apl=AplMapper.getOrderApl(equId);
		return apl;
	}

	@Override
	public List<application> getAllAplByUser(int userId) {
		// TODO Auto-generated method stub
		List<application> list=new ArrayList<application>();
		list=AplMapper.getAllAplByUser(userId);
		return list;
	}

	@Override
	public void updateOrderState(int id) {
		// TODO Auto-generated method stub
		AplMapper.updateOrderState(id);
		
		return;
	}

	@Override
	public int getAplCount() {
		// TODO Auto-generated method stub
		
		return AplMapper.getAplCount();
	}

}
