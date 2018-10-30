package com.zucc.kcgl.mapper;

import java.util.Date;
import java.util.List;

import com.zucc.kcgl.model.equipment;
import com.zucc.kcgl.model.record;

public interface RecordMapper {

	void addRecord(record record);
	
	void updateRecord(record record);
	
	void deleteRecord(int id);
	
	List<record> getRecordByEqu(int equId);
	
	List<record> getRecordByUser(int userId);
	
	Date getTime(record record);
}
