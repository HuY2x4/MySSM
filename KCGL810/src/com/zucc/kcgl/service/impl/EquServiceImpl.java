package com.zucc.kcgl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.EquMapper;
import com.zucc.kcgl.mapper.UserMapper;
import com.zucc.kcgl.model.equipment;
import com.zucc.kcgl.service.EquService;

@Service
public class EquServiceImpl implements EquService{
	@Resource
	private EquMapper equMapper;
	
	@Override
	public boolean addEqu(equipment equ) {
		// TODO Auto-generated method stub
		equMapper.addEqu(equ);
		
		
		return true;
	}

	@Override
	public boolean deleteEqu(int id) {
		// TODO Auto-generated method stub
		equipment equ=new equipment();
		equ=equMapper.getEqu(id);
		if(equ==null){
			return false;
		}
		
		equMapper.deleteEqu(id);
		return true;
	}

	@Override
	public boolean updateEqu(equipment equ) {
		// TODO Auto-generated method stub
		equipment ifequ=new equipment();
		ifequ=equMapper.getEqu(equ.getEquid());
		if(ifequ==null){
			return false;
		}
		
		equMapper.updateEqu(equ);
		
		return true;
	}

	@Override
	public equipment getEqu(int id) {
		// TODO Auto-generated method stub
		equipment equ=new equipment();
		equ=equMapper.getEqu(id);
		
		
		return equ;
	}

	@Override
	public List<equipment> getAllEqu(int page,int num) {
		// TODO Auto-generated method stub
		List<equipment> list=new ArrayList<equipment>();
		list=equMapper.getAllEqu();
		List<equipment> listNew=new ArrayList<equipment>();
		for(int i=0;i<num;i++){
			listNew.add(list.get((page-1)*num+i));
		}
		
		return listNew;
	}

	@Override
	public List<equipment> getAllEquSort(int page,int num,String type, String state) {
		// TODO Auto-generated method stub
		equipment equ=new equipment();
		equ.setType(type);
		equ.setState(state);
		List<equipment> list=new ArrayList<equipment>();
		list=equMapper.getAllEquSort(equ);
		List<equipment> listNew=new ArrayList<equipment>();
		for(int i=0;i<num;i++){
			listNew.add(list.get((page-1)*num+i));
		}
		return listNew;
	}

	@Override
	public boolean updateEquState(int equId, String state) {
		// TODO Auto-generated method stub
		equipment ifequ=new equipment();
		ifequ=equMapper.getEqu(equId);
		if(ifequ==null){
			return false;
		}
		
		
		equipment equ=new equipment();
		equ.setEquId(equId);
		equ.setState(state);
		equMapper.updateEquState(equ);
		return true;
	}

	@Override
	public boolean updateEqutime(Date date, int equId, String state) {
		// TODO Auto-generated method stub
		equipment ifequ=new equipment();
		ifequ=equMapper.getEqu(equId);
		if(ifequ==null){
			return false;
		}
		
		equipment equ=new equipment();
		equ.setEquid(equId);
		if(state.equals("in")){
			equ.setInDate(date);
			equMapper.updateEquIndate(equ);
		}
		else if(state.equals("out")){
			equ.setOutDate(date);
			equMapper.updateEquOutdate(equ);
		}
		
		return true;
	}

	@Override
	public int getEquCount() {
		// TODO Auto-generated method stub
		return equMapper.getEquCount();
	}

}
