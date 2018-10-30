package com.zucc.kcgl.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zucc.kcgl.model.MdUser;
import com.zucc.kcgl.model.equipment;
import com.zucc.kcgl.service.EquService;
import com.zucc.kcgl.service.UserService;

@Controller
public class EquContr {

	@Resource
	private EquService equService;
	
	@RequestMapping("/equipmentList")
	public String equipmentList(){  
		return "equipment/equipmentList";
	}
	
	@RequestMapping("/equipmentMain")
	public String equipmentMain(){  
		return "equipment/equipmentMain";
	}
	
	@RequestMapping(value = "/addEqu", method = RequestMethod.POST)
	public  @ResponseBody  String addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		equipment equ=new equipment();
		
		Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        
		equ.setName(request.getParameter("name"));
		equ.setType(request.getParameter("type"));
		equ.setVersion(request.getParameter("version"));
		equ.setIndata(time);
		equ.setPrice(Integer.parseInt(request.getParameter("price")));
		equ.setOwner(request.getParameter("owner"));
		equ.setChargePersion(request.getParameter("chargePerson"));
		equ.setRemark(request.getParameter("reamrk"));
		equ.setState(request.getParameter("state"));
		equ.setFailureState("normal");
	
		if(equService.addEqu(equ)){
			return "ok";
		}
		else{
			return "no";
		}
		
		
	}
	
	
	
	
	@RequestMapping(value = "/deleteEqu", method = RequestMethod.POST)
	public  @ResponseBody  String deleteUser(HttpServletRequest request, HttpServletResponse response,int equId) throws IOException, ParseException{
		
		
		if(equService.deleteEqu(equId)){
			return "ok";
		}
		else{
			return "no";
		}
		
		
	}
	
	
	
	@RequestMapping(value = "/updateEqu", method = RequestMethod.POST)
	public  @ResponseBody  String updateEqu(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		equipment equ=new equipment();
		
		equ.setEquid(Integer.parseInt(request.getParameter("equId")));
		equ.setName(request.getParameter("name"));
		equ.setType(request.getParameter("type"));
		equ.setVersion(request.getParameter("version"));
		equ.setPrice(Integer.parseInt(request.getParameter("price")));
		equ.setOwner(request.getParameter("owner"));
		equ.setChargePersion(request.getParameter("chargePerson"));
		equ.setRemark(request.getParameter("reamrk"));
		equ.setState(request.getParameter("state"));
		
	
		if(equService.updateEqu(equ)){
			return "ok";
		}
		else{
			return "no";
		}
		
		
	}
	
	
	
	@RequestMapping(value = "/getEqu", method = RequestMethod.GET)//json//如果id不存在
	public  @ResponseBody  String getUser(HttpServletRequest request, HttpServletResponse response,int  equId) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		
		
		equipment equ=new equipment();
		
		equ=equService.getEqu(equId);
		System.out.println(equ.getName());
		map.put("equId", equ.getEquid());
		map.put("name", equ.getName());
		map.put("type", equ.getType());
		map.put("version", equ.getVersion());
		map.put("inDate", equ.getIndata());///////空的这里
		map.put("outDate", equ.getOutdata());
		map.put("price", equ.getPrice());
		map.put("owner", equ.getOwner());
		map.put("chargePerson", equ.getChargePersion());
		map.put("remark", equ.getRemark());
		map.put("state", equ.getState());
		map.put("flag", "ok");
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	
	@RequestMapping(value = "/getAllEquBaseInf", method = RequestMethod.GET)
	public  @ResponseBody  String getAllEquBaseInf(HttpServletRequest request, HttpServletResponse response,int page,int num) throws IOException{
		HashMap<Object,Object> map = new HashMap<>();  
		List<equipment> list=new ArrayList<equipment>();
		list=equService.getAllEqu(page, num);
		map.put("data", list);
		map.put("flag", "ok");
		String json = JSONObject.fromObject(map).toString();
	
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	
	@RequestMapping(value = "/getAllEquBaseInfBySort", method = RequestMethod.GET)
	public  @ResponseBody  String getAllEquBaseInfBySort(HttpServletRequest request, HttpServletResponse response,int page,int num,String type,String state) throws IOException{
		HashMap<Object,Object> map = new HashMap<>();  
		List<equipment> list=new ArrayList<equipment>();
		list=equService.getAllEquSort(page, num, type, state);
		map.put("data", list);
		map.put("flag", "ok");
		
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	@RequestMapping(value = "/getEquCount", method = RequestMethod.POST)
	public  @ResponseBody  String getEquCount(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		
			int count=equService.getEquCount();
		
			return Integer.toString(count);
		
		
		
	}
	
	
	
}
