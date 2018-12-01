package com.zucc.kcgl.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zucc.kcgl.model.EquRecord;



public interface RecordMapper {

	int addRecord(EquRecord record);
	
	int updateRecord(EquRecord record);
	
	int deleteRecord(int recordId);
	
	EquRecord getRecord(int recordId);
	
	List<EquRecord> getRecordByEqu(int equId);
	
	List<EquRecord> getRecordByUser(String loginName);
	
	Date getTime(Map<String,Object> map);
}
