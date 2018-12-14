package com.zucc.kcgl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zucc.kcgl.model.EquRecord;
import com.zucc.kcgl.service.EquService;
import com.zucc.kcgl.service.RecordService;
import com.zucc.kcgl.service.UserService;
import com.zucc.kcgl.util.UtilsC;



@Controller
public class RecordContr {

	@Resource
	private RecordService recService;
	@Resource
	private EquService equService;
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/getRecordByEqu", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String getRecordByEqu( @RequestBody String parms ,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		List<EquRecord> list=new ArrayList<EquRecord>();
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String equId=UtilsC.hasKeyOfMap("equId", jsonObject);
		String Token = request.getHeader("X-Access-Token");
		if(userService.hasExpires(Token)){
			map.put("success", "false");
			map.put("err_code", "401");
			map.put("message", "身份过期需要重新登录");
		}
		else if(equId==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			if(equService.getEqu(Integer.parseInt(equId))==null){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "设备不存在");
			}
			else{
				list=recService.getRecordByEqu(Integer.parseInt(equId));
				List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
				for(EquRecord record:list){
					Map<String,Object> mapinlist=new HashMap<String,Object>();
					mapinlist.put("recordId",record.getRecordId());
					mapinlist.put("equId",record.getEquId());
					mapinlist.put("equName", record.getEquipment().getEquName());
					mapinlist.put("version", record.getEquipment().getVersion());
					mapinlist.put("state",record.getState());
					mapinlist.put("date", record.getDate());
					mapinlist.put("loginName",record.getLoginName());
					mapinlist.put("userName",record.getUser().getUserName());
					mapinlist.put("remark", record.getRemark());
					mapinlist.put("registrar", record.getRegistrar());
					newlist.add(mapinlist);
				}
				map.put("data", newlist);
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
			}
		}
		
		
		
		
		String json = JSONObject.fromObject(map).toString();
		System.out.println("getRecordByEqu:"+map.toString());
		String origin = request.getHeader("Origin");
	    if(origin == null) {
	        origin = request.getHeader("Referer");
	    }
	    response.setHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	@RequestMapping(value = "/getRecordByUser", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String getRecordByUser(@RequestBody String parms ,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		List<EquRecord> list=new ArrayList<EquRecord>();
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String loginName=UtilsC.hasKeyOfMap("loginName", jsonObject);
		String Token = request.getHeader("X-Access-Token");
		if(userService.hasExpires(Token)){
			map.put("success", "false");
			map.put("err_code", "401");
			map.put("message", "身份过期需要重新登录");
		}
		else if(loginName==null||loginName.equals("")){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			if(loginName.equals("currentUser")){
				loginName = userService.getLoginNameByKey(Token);
			}
			
			if(!userService.hasLoginNameRepeat(loginName)){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "登录账号不存在");
			}
			else{
				list=recService.getRecordByUser(loginName);
				List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
				for(EquRecord record:list){
					Map<String,Object> mapinlist=new HashMap<String,Object>();
					mapinlist.put("recordId",record.getRecordId());
					mapinlist.put("equId",record.getEquId());
					mapinlist.put("equName", record.getEquipment().getEquName());
					mapinlist.put("version", record.getEquipment().getVersion());
					mapinlist.put("state",record.getState());
					mapinlist.put("date", record.getDate());
					mapinlist.put("loginName",record.getLoginName());
					mapinlist.put("userName",record.getUser().getUserName());
					mapinlist.put("remark", record.getRemark());
					mapinlist.put("registrar", record.getRegistrar());
					newlist.add(mapinlist);
				}
				map.put("data", newlist);
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
			}
		}
		
		String json = JSONObject.fromObject(map).toString();
		System.out.println("getRecordByUser:"+map.toString());
		String origin = request.getHeader("Origin");
	    if(origin == null) {
	        origin = request.getHeader("Referer");
	    }
	    response.setHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
}
