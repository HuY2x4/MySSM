package com.zucc.kcgl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zucc.kcgl.model.equipment;
import com.zucc.kcgl.model.record;
import com.zucc.kcgl.service.EquService;
import com.zucc.kcgl.service.RecordService;
import com.zucc.kcgl.service.UserService;

@Controller
public class RecordContr {

	@Resource
	private RecordService recService;
	@Resource
	private EquService equService;
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/getRecordByEqu", method = RequestMethod.POST)
	public  @ResponseBody  String getRecordByEqu(HttpServletRequest request, HttpServletResponse response,int equId) throws IOException{
		HashMap<Object,Object> map = new HashMap<>();  
		List<record> list=new ArrayList<record>();
		if(equService.getEqu(equId)==null){
			map.put("flag", "no");
		}
		else{
			list=recService.getRecordByEqu(equId);
			map.put("data", list);
			map.put("flag", "ok");
		}
		
		
		
		String json = JSONObject.fromObject(map).toString();
		System.out.println(json);
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	@RequestMapping(value = "/getRecordByUser", method = RequestMethod.POST)
	public  @ResponseBody  String getRecordByUser(HttpServletRequest request, HttpServletResponse response,int userId) throws IOException{
		HashMap<Object,Object> map = new HashMap<>();  
		List<record> list=new ArrayList<record>();
		if(userService.getUserInfById(userId)==null){
			map.put("flag", "no");
		}
		else{
			list=recService.getRecordByUser(userId);
			map.put("data", list);
			map.put("flag", "ok");
		}
		String json = JSONObject.fromObject(map).toString();
	
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
}
