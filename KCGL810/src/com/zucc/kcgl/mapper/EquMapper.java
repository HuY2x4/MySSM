package com.zucc.kcgl.mapper;

import java.util.List;
import java.util.Map;

import com.zucc.kcgl.model.Equipment;


public interface EquMapper {

	int addEqu(Equipment equ);
	
	int deleteEqu(int equId);
	
	int updateEqu(Equipment equ);
	
	Equipment getEqu(int equId);
	
	List<Equipment> getAllEqu();
	
	List<Equipment> getPageEquSort(Map<String,Object> map);
	
	int getEquCount();
}
