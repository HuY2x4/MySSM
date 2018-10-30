package com.zucc.kcgl.service;

import java.util.Date;
import java.util.List;

import com.zucc.kcgl.model.record;

public interface RecordService {

	public boolean addRecord(record record);
	
	public boolean updateRecord(record record);
	
	public boolean deleteRecord(int id);
	
	public List<record> getRecordByEqu(int equid);
	
	public List<record> getRecordByUser(int userid);
	
	public Date getTime(int equId,String state);
}
