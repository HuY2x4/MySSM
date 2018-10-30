package com.zucc.kcgl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.RecordMapper;
import com.zucc.kcgl.model.equipment;
import com.zucc.kcgl.model.record;
import com.zucc.kcgl.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService{

	@Resource
	private RecordMapper recordMapper;
	
	@Override
	public boolean addRecord(record record) {
		// TODO Auto-generated method stub
		recordMapper.addRecord(record);
		return true;
	}

	@Override
	public boolean updateRecord(record record) {
		// TODO Auto-generated method stub
		recordMapper.updateRecord(record);
		
		return true;
	}

	@Override
	public boolean deleteRecord(int id) {
		// TODO Auto-generated method stub
		recordMapper.deleteRecord(id);
		return true;
	}

	@Override
	public List<record> getRecordByEqu(int equid) {
		// TODO Auto-generated method stub
		List<record> list=new ArrayList<record>();
		list=recordMapper.getRecordByEqu(equid);
		return list;
	}

	@Override
	public List<record> getRecordByUser(int userid) {
		// TODO Auto-generated method stub
		List<record> list=new ArrayList<record>();
		list=recordMapper.getRecordByUser(userid);
		return list;
	}

	@Override
	public Date getTime(int equId, String state) {
		// TODO Auto-generated method stub
		record rec=new record();
		rec.setEquId(equId);
		rec.setState(state);
		Date date=recordMapper.getTime(rec);
		return date;
	}

}
