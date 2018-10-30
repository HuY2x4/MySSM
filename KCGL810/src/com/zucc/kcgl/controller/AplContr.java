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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zucc.kcgl.model.MdUser;
import com.zucc.kcgl.model.application;
import com.zucc.kcgl.model.equipment;
import com.zucc.kcgl.model.record;
import com.zucc.kcgl.model.sucApplication;
import com.zucc.kcgl.service.AplService;
import com.zucc.kcgl.service.EquService;
import com.zucc.kcgl.service.RecordService;
import com.zucc.kcgl.service.SuAplService;
import com.zucc.kcgl.service.UserService;


@Controller
public class AplContr {

	@Resource
	private AplService aplService;
	@Resource
	private RecordService recService;
	@Resource
	private UserService userService;
	@Resource
	private EquService equService;
	@Resource
	private SuAplService suAplService;
	
	@RequestMapping("/applicationList")
	public String applicationList(){  
		return "application/applicationList";
	}
	
	@RequestMapping("/applicationMain")
	public String applicationMain(){  
		return "application/applicationMain";
	}
	
	@RequestMapping("/applicationPassList")
	public String applicationPassList(){  
		return "application/applicationPassList";
	}
	
	@RequestMapping("/applicationSubmit")
	public String applicationSubmit(){  
		return "application/applicationSubmit";
	}
	
	@RequestMapping(value = "/addApl", method = RequestMethod.POST)//申请人姓名和id  申请物  时间
	public  @ResponseBody  String addApl(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userName")==null){
			return "loginError";
		}
		application apl=new application();
		
		Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date time = sdf.parse( request.getParameter("returnTime") );

		apl.setMethod(request.getParameter("method"));
		apl.setPhone(request.getParameter("phone"));
		apl.setPurpose(request.getParameter("purpose"));
		apl.setRemark(request.getParameter("remark"));
		apl.setReturnTime(time);

		apl.setUserName(request.getParameter("userName"));
		apl.setState("stay");   //stay pass  fail
		apl.setApplicant((String)session.getAttribute("userName"));
		apl.setUserId(Integer.parseInt((String)session.getAttribute("userId")));
		apl.setEquId(Integer.parseInt(request.getParameter("equId")));

		
		equipment equ=new equipment();
		equ=equService.getEqu(Integer.parseInt(request.getParameter("equId")));
		
		apl.setEquName(equ.getName());
		if(aplService.addApplication(apl)){
			return "ok";
		}
		else{
			return "no";
		}
		
		
	}
	
	
	
	@RequestMapping(value = "/getAllAplBaseInf", method = RequestMethod.POST)
	public  @ResponseBody  String getAllBaseApl(HttpServletRequest request, HttpServletResponse response,String method,String state,int page,int num) throws IOException{
		HashMap<Object,Object> map = new HashMap<>();  
		List<application> list=new ArrayList<application>();
		list=aplService.getAllBaseApl(method,state, page, num);
		map.put("data", list);
		map.put("state", "ok");
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	@RequestMapping(value = "/getApl", method = RequestMethod.POST)//json
	public  @ResponseBody  String getApl(HttpServletRequest request, HttpServletResponse response,int  id) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
	
		application apl=new application();
		
		apl=aplService.getApl(id);
		
		if(apl!=null){
			map.put("id", apl.getId());
			map.put("userName", apl.getUserName());
			map.put("method", apl.getMethod());
			map.put("purpose", apl.getPurpose());
			map.put("remark", apl.getRemark());///////空的这里
			map.put("returnTime", apl.getReturnTime());
			map.put("state", apl.getState());
			map.put("phone", apl.getPhone());
			map.put("flag", "ok");
		}
		else{
			map.put("flag", "no");
		}
		
		
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	
	
	@RequestMapping(value = "/cheakApl", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public  @ResponseBody  String updateEqu(HttpServletRequest request, HttpServletResponse response,int id,String state) throws IOException, ParseException{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userName")==null){
			return "loginError";
		}
		application apl=new application();
		apl=aplService.getApl(id);
		if(apl==null){
			return "no";
		}
		
		if(apl.getMethod().equals("rent")&&state.equals("pass")){//if通过，增加一条出库记录      //还有预约的没写
			record rec=new record();
			
			Date date = new Date();//获得系统时间.
	        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
	        String nowTime = sdf.format(date);
	        Date time = sdf.parse( nowTime );
	        
	   
	        
	        rec.setAdmin((String)session.getAttribute("userName"));
			rec.setDate(time);
			rec.setEquId(apl.getEquId());
			rec.setEquName(apl.getEquName());
			rec.setRemark("");
			rec.setState("out");
			rec.setUserId(apl.getUserId());
			rec.setUserName(apl.getApplicant());
			recService.addRecord(rec);
			
			equService.updateEquState(apl.getEquId(), "out");//修改设备状态
			
			equService.updateEqutime(time, apl.getEquId(), "out");
			
			
			sucApplication suapl=new sucApplication();
			suapl.setEquId(apl.getEquId());
			suapl.setEquName(apl.getEquName());
			suapl.setState("in");
			suapl.setUserId(apl.getUserId());
			suapl.setUserName(apl.getApplicant());
			equipment equ=equService.getEqu(apl.getEquId());
			suapl.setVersion(equ.getVersion());
			
			suAplService.addSuApl(suapl);
			
		}
		else if(apl.getMethod().equals("order")&&state.equals("pass")){
			
			
		}
		
		
		
		
		if(aplService.updateApplication(id, state)){//修改申请状态
			
			return "ok";
		}
		else{
			return "no";
		}
		
		
	}
	
	
	@RequestMapping(value = "/getAllAplByUser", method = RequestMethod.POST)
	public  @ResponseBody  String getAllAplByUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(false);
		HashMap<Object,Object> map = new HashMap<>();  
		List<application> list=new ArrayList<application>();
		if(session.getAttribute("userName")==null){
			map.put("flag", "loginError");
		}
		else{
			list=aplService.getAllAplByUser(Integer.parseInt((String)session.getAttribute("userId")));
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
	
	
	@RequestMapping(value = "/getAplCount", method = RequestMethod.POST)
	public  @ResponseBody  String getAplCount(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		
			int count=aplService.getAplCount();
		
			return Integer.toString(count);
		
		
		
	}
	
	
}
