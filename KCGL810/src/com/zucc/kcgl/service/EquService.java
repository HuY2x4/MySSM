package com.zucc.kcgl.service;

import java.util.Date;
import java.util.List;

import com.zucc.kcgl.model.Equipment;

public interface EquService {

	public boolean addEqu(Equipment equ);
	
	public boolean deleteEqu(int id);
	
	public boolean updateEqu(Equipment equ);
	
	public boolean updateEquState(int equId,String state);
	
	public boolean updateEquTime(Date date,int equId,String state);
	
	public Equipment getEqu(int id);
	
	public List<Equipment> getAllEqu();
	
	public List<Equipment> getPageEquSort(int currentPage,int pageSize,String type,String state,String equName);

	public int getEquCount();

}
