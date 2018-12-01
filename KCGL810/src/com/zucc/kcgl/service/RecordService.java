package com.zucc.kcgl.service;

import java.util.List;

import com.zucc.kcgl.model.EquRecord;

public interface RecordService {

	public boolean addRecord(EquRecord record);
	
	public boolean updateRecord(EquRecord record);
	
	public boolean deleteRecord(int recordId);
	
	public EquRecord getRecord(int recordId);

	
	public List<EquRecord> getRecordByEqu(int equId);
	
	public List<EquRecord> getRecordByUser(String loginName);
	
//	public Date getTime(int equId,String state);
}
