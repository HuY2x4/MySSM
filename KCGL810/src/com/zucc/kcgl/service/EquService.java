package com.zucc.kcgl.service;

import java.util.Date;
import java.util.List;

import com.zucc.kcgl.model.equipment;

public interface EquService {

	public boolean addEqu(equipment equ);
	
	public boolean deleteEqu(int id);
	
	public boolean updateEqu(equipment equ);
	
	public boolean updateEquState(int equId,String state);
	
	public boolean updateEqutime(Date date,int equId,String state);
	
	public equipment getEqu(int id);
	
	public List<equipment> getAllEqu(int page,int num);
	
	public List<equipment> getAllEquSort(int page,int num,String type,String state);

	public int getEquCount();

}
