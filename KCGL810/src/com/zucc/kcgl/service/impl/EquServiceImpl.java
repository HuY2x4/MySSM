package com.zucc.kcgl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.EquMapper;
import com.zucc.kcgl.model.Equipment;
import com.zucc.kcgl.service.EquService;

@Service
public class EquServiceImpl implements EquService{
	@Resource
	private EquMapper equMapper;
	
	@Override
	public boolean addEqu(Equipment equ) {
		// TODO Auto-generated method stub
		if(equMapper.addEqu(equ)!=1){
			return false;
		};
		
		return true;
	}

	@Override
	public boolean deleteEqu(int id) {
		// TODO Auto-generated method stub
		
		Equipment hasEqu=new Equipment();
		hasEqu=equMapper.getEqu(id);
		if(hasEqu==null){
			return false;
		}
		/*还要加上查询相关的记录是否有id
		 * 如有，报错
		 * 
		 * */
		equMapper.deleteEqu(id);
		return true;
	}

	@Override
	public boolean updateEqu(Equipment equ) {
		// TODO Auto-generated method stub
		Equipment hasEqu=new Equipment();
		hasEqu=equMapper.getEqu(equ.getEquId());
		if(hasEqu==null){
			return false;
		}
		

		equMapper.updateEqu(equ);
		
		return true;
	}

	@Override
	public Equipment getEqu(int id) {
		// TODO Auto-generated method stub
		Equipment equ=new Equipment();
		System.out.println("service:"+id);
		equ=equMapper.getEqu(id);
		if(equ==null){
			System.out.println("service空的");
		}
		
		return equ;
	}

	@Override
	public List<Equipment> getAllEqu() {
		// TODO Auto-generated method stub
		List<Equipment> list=new ArrayList<Equipment>();
		list=equMapper.getAllEqu();
		
		return list;
	}



	@Override
	public boolean updateEquState(int equId, String state) {
		// TODO Auto-generated method stub
		Equipment hasEqu=new Equipment();
		hasEqu=equMapper.getEqu(equId);
		if(hasEqu==null){
			return false;
		}
		hasEqu.setEquId(equId);
		hasEqu.setState(state);
		System.out.println(hasEqu.toString());
		equMapper.updateEqu(hasEqu);
		return true;
	}

	@Override
	public boolean updateEquTime(Date date, int equId, String state) {
		// TODO Auto-generated method stub
		Equipment hasEqu=new Equipment();
		hasEqu=equMapper.getEqu(equId);
		if(hasEqu==null){
			return false;
		}

		if(state.equals("in")){
			hasEqu.setInDate(date);
			equMapper.updateEqu(hasEqu);
		}
		else if(state.equals("out")){
			hasEqu.setOutDate(date);
			equMapper.updateEqu(hasEqu);
		}
		
		return true;
	}

	@Override
	public int getEquCount() {
		// TODO Auto-generated method stub
		return equMapper.getEquCount();
	}



	@Override
	public List<Equipment> getPageEquSort(int currentPage, int pageSize,
			String type, String state,String equName) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("startPage", (currentPage-1)*pageSize);
		map.put("pageSize", pageSize);
		if(!(type==null)){
			map.put("type", type);
		}
		if(!(state==null)){
			map.put("state", state);
		}
		if(!(equName==null)){
			map.put("equName", equName);
		}
		List<Equipment> list=new ArrayList<Equipment>();
		list=equMapper.getPageEquSort(map);
		return list;
	}

}
