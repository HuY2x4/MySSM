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
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zucc.kcgl.model.Application;
import com.zucc.kcgl.model.User;
import com.zucc.kcgl.service.AplService;
import com.zucc.kcgl.service.UserService;
import com.zucc.kcgl.util.UtilsC;


@Controller
public class AplContr {

	@Resource
	private AplService aplService;
	@Resource
	private UserService userService;

	
	@RequestMapping("/ApplicationList")
	public String ApplicationList(){  
		return "Application/ApplicationList";
	}
	
	@RequestMapping("/ApplicationMain")
	public String ApplicationMain(){  
		return "Application/ApplicationMain";
	}
	
	@RequestMapping("/ApplicationPassList")
	public String ApplicationPassList(){  
		return "Application/ApplicationPassList";
	}
	
	@RequestMapping("/ApplicationSubmit")
	public String ApplicationSubmit(){  
		return "Application/ApplicationSubmit";
	}
	
	//String method,String equId,String purpose,String returnTime,String phone,String remark
	@RequestMapping(value = "/addApl", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String addApl(@RequestBody String parms,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		HttpSession session = request.getSession(false);
		Map<Object,Object> map = new HashMap<>();  
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String method=UtilsC.hasKeyOfMap("method", jsonObject);
		String phone=UtilsC.hasKeyOfMap("phone", jsonObject);
		String purpose=UtilsC.hasKeyOfMap("purpose", jsonObject);
		String remark=UtilsC.hasKeyOfMap("remark", jsonObject);
		String returnTime=UtilsC.hasKeyOfMap("returnTime", jsonObject);
		String equId=UtilsC.hasKeyOfMap("equId", jsonObject);

		if(method==null||phone==null||purpose==null||remark==null||returnTime==null||equId==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else if(session.getAttribute("loginName")==null){
			map.put("success", "false");
			map.put("err_code", "401");
			map.put("message", "身份过期");
		}
		else{
			Application apl=new Application();
			apl.setMethod(method);
			apl.setPhone(phone);
			apl.setPurpose(purpose);
			apl.setRemark(remark);
			apl.setEquId(Integer.parseInt(equId));
			Date date = new Date();
	        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
	        Date time = sdf.parse(returnTime);
	        apl.setReturnTime(time);
			apl.setState("stay");   //stay  pass  fail
			apl.setLoginName((String)session.getAttribute("loginName"));
			
			if(aplService.addApl(apl)){
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
			}
			else{
				map.put("success", "false");
				map.put("err_code", "500");
				map.put("message", "添加设备失败");
			}
		}
		System.out.println("addApl:"+map.toString());
		String json = JSONObject.fromObject(map).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
		
	}
	
	
	//,String method,String loginName,String equId,String state,String currentPage,String pageSize
	@RequestMapping(value = "/getAplBySort", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String getAplBySort(@RequestBody String parms ,HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(false);
		Map<Object,Object> map = new HashMap<>();  
		List<Application> list=new ArrayList<Application>();
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String method=UtilsC.hasKeyOfMap("method", jsonObject);
		String loginName=UtilsC.hasKeyOfMap("loginName", jsonObject);
		String equId=UtilsC.hasKeyOfMap("equId", jsonObject);
		String state=UtilsC.hasKeyOfMap("state", jsonObject);
		String currentPage=UtilsC.hasKeyOfMap("currentPage", jsonObject);
		String pageSize=UtilsC.hasKeyOfMap("pageSize", jsonObject);
		
		method=UtilsC.KongToNull(method);
		loginName=UtilsC.KongToNull(loginName);
		equId=UtilsC.KongToNull(equId);
		state=UtilsC.KongToNull(state);
		
		if(loginName==null){
			loginName="";
		}
		if(currentPage==null||pageSize==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "currentPage或pageSize为空");
		}
		else {
			if(loginName.equals("currentUser")){
				if(session.getAttribute("loginName")==null){
					map.put("success", "false");
					map.put("err_code", "401");
					map.put("message", "身份过期");
					String json = JSONObject.fromObject(map).toString();
					response.setHeader("Access-Control-Allow-Origin", "*");
					response.setCharacterEncoding("UTF-8");
					response.flushBuffer();
					response.getWriter().write(json);
					response.getWriter().flush();  
					response.getWriter().close();
					return null;
				}
				else{
					loginName=(String)session.getAttribute("loginName");
				}
				
			}
			if(loginName.equals("")){
				loginName=null;
			}
			int intEquId;
			if(equId==null||equId.equals("")){
				intEquId=0;
			}
			else{
				intEquId=Integer.parseInt(equId);
			}
			list=aplService.getAplBySort(method,loginName,intEquId, state,Integer.parseInt(currentPage),Integer.parseInt(pageSize));
			List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
			for(Application apl:list){
				Map<String,Object> mapinlist=new HashMap<String,Object>();
				mapinlist.put("aplId", apl.getAplId());
				mapinlist.put("method", apl.getMethod());
				mapinlist.put("loginName", apl.getLoginName());
				mapinlist.put("equId", apl.getEquId());
				mapinlist.put("purpose", apl.getPurpose());
				mapinlist.put("returnTime", apl.getReturnTime());
				mapinlist.put("phone", apl.getPhone());
				mapinlist.put("remark", apl.getRemark());
				mapinlist.put("state", apl.getState());
				mapinlist.put("userName", userService.getUserInfByLoginName(apl.getLoginName()).getUserName());
				newlist.add(mapinlist);
			}
			map.put("data", newlist);
			map.put("success", "true");
			map.put("err_code", "0");
			map.put("message", "ok");
			String json = JSONObject.fromObject(map).toString();
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setCharacterEncoding("UTF-8");
			response.flushBuffer();
			response.getWriter().write(json);
			response.getWriter().flush();  
			response.getWriter().close();
			return null;
		}
			
		System.out.println("getAplBySort:"+map.toString());
		String json = JSONObject.fromObject(map).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	@RequestMapping(value = "/getApl", method = RequestMethod.POST, produces="application/json;charset=UTF-8")//json
	public  @ResponseBody  String getApl(@RequestBody String parms,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		Map<Object,Object> data = new HashMap<>();  
		Application apl=new Application();
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String aplId=UtilsC.hasKeyOfMap("aplId", jsonObject);
		if(aplId==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			apl=aplService.getApl(Integer.parseInt(aplId));
			if(apl==null){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "编号不存在");
			}
			else{
				User user=new User();
				user=userService.getUserInfByLoginName(apl.getLoginName());
				
				data.put("aplId", apl.getAplId());
				data.put("loginName", user.getLoginName());
				data.put("userName", user.getUserName());
				data.put("method", apl.getMethod());
				data.put("equId", apl.getEquId());
				data.put("purpose", apl.getPurpose());
				data.put("remark", apl.getRemark());
				data.put("returnTime", apl.getReturnTime());
				data.put("state", apl.getState());
				data.put("phone", apl.getPhone());
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
				map.put("data", data);
				
			}
			
		}
		
		
		System.out.println("getApl:"+map.toString());
		String json = JSONObject.fromObject(map).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	
	
	
	
	

	
	
	@RequestMapping(value = "/getAplCount", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String getAplCount(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		Map<Object,Object> map = new HashMap<>(); 
		Map<Object,Object> data = new HashMap<>(); 
		int count=aplService.getAplCount();
		data.put("count", count);
		map.put("data", data);
		map.put("success", "true");
		map.put("err_code", "0");
		map.put("message", "ok");
		String json = JSONObject.fromObject(map).toString();
		System.out.println("getAplCount:"+map.toString());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
	}
	
	
}
