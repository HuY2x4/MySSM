package com.zucc.kcgl.mapper;

import java.util.Date;
import java.util.List;

import com.zucc.kcgl.model.equipment;

public interface EquMapper {

	void addEqu(equipment equ);
	
	void deleteEqu(int id);
	
	void updateEqu(equipment equ);
	
	void updateEquState(equipment equ);
	
	void updateEquIndate(equipment equ);
	
	void updateEquOutdate(equipment equ);
	
	equipment getEqu(int id);
	
	List<equipment> getAllEqu();
	
	List<equipment> getAllEquSort(equipment equ);
	
	int getEquCount();
}
