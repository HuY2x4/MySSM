package com.zucc.kcgl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.SuAplMapper;
import com.zucc.kcgl.mapper.UserMapper;
import com.zucc.kcgl.model.record;
import com.zucc.kcgl.model.sucApplication;
import com.zucc.kcgl.service.SuAplService;

@Service
public class SuAplServiceImpl implements SuAplService{

	@Resource
	private SuAplMapper suAplMapper;
	
	
	@Override
	public boolean addSuApl(sucApplication suapl) {
		// TODO Auto-generated method stub
		suapl.setCode((int)((Math.random()*9+1)*1000));	
		System.out.println(suapl.getCode());
		suAplMapper.addSuApl(suapl);
		return true;
	}

	@Override
	public boolean updateSuApl(int code, String state) {
		// TODO Auto-generated method stub
		sucApplication ifsuapl=new sucApplication();
		ifsuapl =suAplMapper.getOutSuApl(code);
		if(ifsuapl==null){
			return false;
		}
		sucApplication suapl=new sucApplication();
		suapl.setCode(code);
		suapl.setState(state);
		suAplMapper.updateSuApl(suapl);
		return true;
	}

	@Override
	public sucApplication getSuApl(int code) {
		// TODO Auto-generated method stub
		sucApplication suapl=new sucApplication();
		suapl=suAplMapper.getSuApl(code);
		return suapl;
	}

	@Override
	public List<sucApplication> getAllSuAplByUser(int userId) {
		// TODO Auto-generated method stub
		List<sucApplication> list=new ArrayList<sucApplication>();
		list =suAplMapper.getAllSuAplByUser(userId);
		return list;
	}

}
