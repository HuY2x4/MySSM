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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zucc.kcgl.model.equipment;
import com.zucc.kcgl.model.sucApplication;
import com.zucc.kcgl.service.SuAplService;

@Controller
public class SuAplContr {
	
	@Resource
	private SuAplService suAplService;
	
	@RequestMapping(value = "/getSuApl", method = RequestMethod.POST)//json//如果id不存在
	public  @ResponseBody  String getSuApl(HttpServletRequest request, HttpServletResponse response,int  code) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		sucApplication suapl=new sucApplication();
		suapl=suAplService.getSuApl(code);
		if(suapl==null){
			map.put("flag", "no");
		}
		else{
			map.put("id", suapl.getId());
			map.put("equId", suapl.getEquId());
			map.put("equName",suapl.getEquName() );
			map.put("userId", suapl.getUserId());
			map.put("userName", suapl.getUserName());
			map.put("version", suapl.getVersion());
			map.put("code", suapl.getCode());
			map.put("state",suapl.getState() );
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
	
	@RequestMapping(value = "/getAllSuApl", method = RequestMethod.POST)
	public  @ResponseBody  String getAllSuApl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(false);
		HashMap<Object,Object> map = new HashMap<>();  
		List<sucApplication> list=new ArrayList<sucApplication>();
		if(session.getAttribute("userId")==null){
			map.put("flag", "loginError");
		}
		else{
			list=suAplService.getAllSuAplByUser(Integer.parseInt((String)session.getAttribute("userId")));
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
