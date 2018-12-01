package com.zucc.kcgl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.SuAplMapper;
import com.zucc.kcgl.model.SucApplication;
import com.zucc.kcgl.service.SuAplService;

@Service
public class SuAplServiceImpl implements SuAplService{

	@Resource
	private SuAplMapper suAplMapper;
	
	
	@Override
	public boolean addSuApl(SucApplication suApl) {
		// TODO Auto-generated method stub
		suApl.setCode(Integer.toString((int)((Math.random()*9+1)*1000)));	
		System.out.println("提货码:"+suApl.getCode());
		if(!(suAplMapper.addSuApl(suApl)==0)){
			return false;
		}
		return true;
	}

	@Override
	public String updateSuApl(String code, String state) {
		// TODO Auto-generated method stub
		SucApplication hasSuApl=new SucApplication();
		hasSuApl =suAplMapper.getSuApl(code);
		if(hasSuApl==null){
			return "None";
		}
		else if(hasSuApl.getState().equals("out")){
			return "Out";
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code", code);
		map.put("state", state);
		suAplMapper.updateSuAplByCode(map);
		return "Ok";
	}

	@Override
	public SucApplication getSuApl(String code) {
		// TODO Auto-generated method stub
		SucApplication suApl=new SucApplication();
		suApl=suAplMapper.getSuApl(code);
		return suApl;
	}

	@Override
	public List<SucApplication> getAllSuAplByUser(String loginName) {
		// TODO Auto-generated method stub
		List<SucApplication> list=new ArrayList<SucApplication>();
		list =suAplMapper.getAllSuAplByUser(loginName);
		return list;
	}

	@Override
	public SucApplication getSuAplByEquAndIn(int equId) {
		// TODO Auto-generated method stub
		SucApplication suApl=new SucApplication();
		suApl=suAplMapper.getSuAplByEquAndIn(equId);
		return suApl;
	}

	@Override
	public boolean updateSuAplToDie(int sucAplId) {
		// TODO Auto-generated method stub
		if(suAplMapper.updateSuAplToDie(sucAplId)!=0){
			return true;
		}
		
		return false;
	}
	

}
