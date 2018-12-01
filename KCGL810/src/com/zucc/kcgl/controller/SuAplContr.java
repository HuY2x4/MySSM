package com.zucc.kcgl.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.zucc.kcgl.model.SucApplication;
import com.zucc.kcgl.service.EquService;
import com.zucc.kcgl.service.SuAplService;
import com.zucc.kcgl.service.UserService;
import com.zucc.kcgl.util.UtilsC;
@Controller
public class SuAplContr {
	
	@Resource
	private SuAplService suAplService;
	@Resource
	private EquService equService;
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/getSuApl", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String getSuApl(@RequestBody String parms,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		Map<Object,Object> data = new HashMap<>();  
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String code=UtilsC.hasKeyOfMap("code", jsonObject);
		SucApplication suApl=new SucApplication();
		if(code==null||code.equals("")){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			suApl=suAplService.getSuApl(code);
			if(suApl==null){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "提货码不存在");
			}
			else{
				data.put("sucApcId", suApl.getSucAplId());
				data.put("equId", suApl.getEquId());
				data.put("equName",suApl.getEquipment().getEquName() );
				data.put("loginName", suApl.getLoginName());
				data.put("userName", suApl.getUser().getUserName());
				data.put("version", suApl.getEquipment().getVersion());
				data.put("code", suApl.getCode());
				data.put("state",suApl.getState());
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
				map.put("data", data);
			}
		}
		
		System.out.println("getSuApl:"+map.toString());
		String json = JSONObject.fromObject(map).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	@RequestMapping(value = "/getAllSuApl", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String getAllSuApl(@RequestBody String parms,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		List<SucApplication> data=new ArrayList<SucApplication>();
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String loginName=UtilsC.hasKeyOfMap("loginName", jsonObject);
		HttpSession session = request.getSession(false);
		if(loginName==null||loginName.equals("")){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
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
			if(!userService.hasLoginNameRepeat(loginName)){ 
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "登录账号不存在");
			}
			else{
				data=suAplService.getAllSuAplByUser(loginName);
				List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
				for(SucApplication sucApl:data){
					Map<String,Object> mapinlist=new HashMap<String,Object>();
					mapinlist.put("sucAplId", sucApl.getSucAplId());
					mapinlist.put("equId", sucApl.getEquId());
					mapinlist.put("equName", sucApl.getEquipment().getEquName());
					mapinlist.put("version", sucApl.getEquipment().getVersion());
					mapinlist.put("userLoginName", sucApl.getLoginName());
					mapinlist.put("userName", sucApl.getUser().getUserName());
					mapinlist.put("code", sucApl.getCode());
					mapinlist.put("state", sucApl.getState());
					newlist.add(mapinlist);
				}
				if(newlist==null){
					map.put("success", "false");
					map.put("err_code", "404");
					map.put("message", "暂无记录");
				}
				else{
					map.put("data", newlist);
					map.put("success", "true");
					map.put("err_code", "0");
					map.put("message", "ok");
				}
				
			}
			
		}
		
		System.out.println("getAllSuApl:"+map.toString());
		String json = JSONObject.fromObject(map).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}

}
