package com.zucc.kcgl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.RecordMapper;
import com.zucc.kcgl.model.EquRecord;
import com.zucc.kcgl.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService{

	@Resource
	private RecordMapper recordMapper;
	
	@Override
	public boolean addRecord(EquRecord record) {
		// TODO Auto-generated method stub
		if(recordMapper.addRecord(record)==0){
			return false;
		}
		return true;
	}

	@Override
	public boolean updateRecord(EquRecord record) {
		// TODO Auto-generated method stub
		if(recordMapper.updateRecord(record)==0){
			return false;
		}
		
		return true;
	}
	
	@Override
	public EquRecord getRecord(int recordId) {
		// TODO Auto-generated method stub
		EquRecord record =recordMapper.getRecord(recordId);
		
		return record;
	}

	@Override
	public boolean deleteRecord(int recordId) {
		// TODO Auto-generated method stub
		if(recordMapper.deleteRecord(recordId)==0){
			return false;
		}
		return true;
	}

	@Override
	public List<EquRecord> getRecordByEqu(int equId) {
		// TODO Auto-generated method stub
		List<EquRecord> list=new ArrayList<EquRecord>();
		list=recordMapper.getRecordByEqu(equId);
		return list;
	}

	@Override
	public List<EquRecord> getRecordByUser(String loginName) {
		// TODO Auto-generated method stub
		List<EquRecord> list=new ArrayList<EquRecord>();
		list=recordMapper.getRecordByUser(loginName);
		return list;
	}

//	@Override
//	public Date getTime(int equId, String state) {
//		// TODO Auto-generated method stub
//		record rec=new record();
//		rec.setEquId(equId);
//		rec.setState(state);
//		Date date=recordMapper.getTime(rec);
//		return date;
//	}

}
