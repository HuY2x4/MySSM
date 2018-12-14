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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zucc.kcgl.model.Application;
import com.zucc.kcgl.model.EquRecord;
import com.zucc.kcgl.model.Equipment;
import com.zucc.kcgl.model.SucApplication;
import com.zucc.kcgl.service.AplService;
import com.zucc.kcgl.service.EquService;
import com.zucc.kcgl.service.RecordService;
import com.zucc.kcgl.service.SuAplService;
import com.zucc.kcgl.service.UserService;
import com.zucc.kcgl.util.UtilsC;

@Controller
public class AdminContr {
	@Resource
	private RecordService recService;
	@Resource
	private EquService equService;
	@Resource
	private AplService aplService;
	@Resource
	private SuAplService suAplService;
	@Resource
	private UserService userService;
	
	@RequestMapping("/adminAddEqu")
	public String adminAddEqu(){  
		return "admin/adminAddEqu";
	}
	
	@RequestMapping("/adminAplList")
	public String adminAplList(){  
		return "admin/adminAplList";
	}
	
	@RequestMapping("/adminAplMain")
	public String adminAplMain(){  
		return "admin/adminAplMain";
	}
	
	@RequestMapping("/adminInEqu")
	public String adminInEqu(){  
		return "admin/adminInEqu";
	}
	
	@RequestMapping("/adminMain")
	public String adminMain(){  
		return "admin/adminMain";
	}
	
	@RequestMapping("/adminOutEqu")
	public String adminOutEqu(){  
		return "admin/adminOutEqu";
	}
	
	@RequestMapping("/adminUpdEqu")
	public String adminUpdEqu(){  
		return "admin/adminUpdEqu";
	}
	
	@RequestMapping("/adminUpdUser")
	public String adminUpdUser(){  
		return "admin/adminUpdUser";
	}
	
	
	@RequestMapping(value = "/outEqu", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String outEqu(@RequestBody String parms ,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		Map<Object,Object> map = new HashMap<>(); 
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String code=UtilsC.hasKeyOfMap("code", jsonObject);
		
		
		String Token = request.getHeader("X-Access-Token");
		if(userService.hasExpires(Token)){
			map.put("success", "false");
			map.put("err_code", "401");
			map.put("message", "身份过期需要重新登录");
		}
		else if(code==null||code.equals("")){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			String res=suAplService.updateSuApl(code, "out");
			if(res.equals("None")){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "提货码不存在");
			}
			else if(res.equals("Out")){
				map.put("success", "false");
				map.put("err_code", "-1");
				map.put("message", "该提货码已被使用过");
			}
			else{
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
			}
			
		}
		System.out.println("outEqu:"+map.toString());
		String json = JSONObject.fromObject(map).toString();
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

	//,String equId,String remark
	@RequestMapping(value = "/inEqu", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String inEqu(@RequestBody String parms,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		Map<Object,Object> map = new HashMap<>();  
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String equId=UtilsC.hasKeyOfMap("equId", jsonObject);
		String remark=UtilsC.hasKeyOfMap("remark", jsonObject);
		
		String Token = request.getHeader("X-Access-Token");
		if(userService.hasExpires(Token)){
			map.put("success", "false");
			map.put("err_code", "401");
			map.put("message", "身份过期需要重新登录");
		}
		else if(equId==null||remark==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			
			Equipment hasEqu=new Equipment();
			hasEqu =equService.getEqu(Integer.parseInt(equId));
			if(hasEqu==null){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "设备不存在");
				String json = JSONObject.fromObject(map).toString();
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
			SucApplication sucapl=new SucApplication();
			sucapl=suAplService.getSuAplByEquAndIn(Integer.parseInt(equId));//鍦ㄦ垚鍔熺敵璇风殑璁板綍涓牴鎹澶嘔D鎵捐褰�
			if(sucapl==null){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "申请不存在");
				String json = JSONObject.fromObject(map).toString();
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
			suAplService.updateSuAplToDie(sucapl.getSucAplId());
			
			Date date = new Date();
	        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
	        String nowTime = sdf.format(date);
	        Date time = sdf.parse( nowTime );
	        Equipment equ=new Equipment();
	        equ=equService.getEqu(Integer.parseInt(equId));
	        EquRecord rec=new EquRecord();
	        rec.setRegistrar( userService.getLoginNameByKey(Token));
			rec.setDate(time);
			rec.setEquId(Integer.parseInt(equId));
			rec.setRemark(remark);
			rec.setState("in");
			
			List<EquRecord> list=new ArrayList<EquRecord>();
			list=recService.getRecordByEqu(Integer.parseInt(equId));//娣诲姞鍏ュ簱璁板綍
			rec.setLoginName(list.get(list.size()-1).getLoginName());
			recService.addRecord(rec);
			
			//equService.updateEquState(equId, "in");
			equ.setState("in");
			equ.setInDate(time);
			equService.updateEqu(equ);//淇敼璁惧鐘舵��
	
			Application aplOrder=new Application();
			
			Application apl=aplService.getOrderApl(Integer.parseInt(equId));//鑾峰彇棰勭害淇℃伅鏍规嵁璁惧ID
	
			if(apl!=null){
				SucApplication suapl=new SucApplication();
				suapl.setEquId(apl.getEquId());
				suapl.setState("in");
				suapl.setLoginName(apl.getLoginName());
				suAplService.addSuApl(suapl);//灏嗛绾︾殑淇℃伅鍙樹负鎴愬姛鐨勪俊鎭�
				apl.setOrderState("invalid");
				aplService.updateApl(apl);//灏嗛绾︾敵璇峰け鏁�
				
				EquRecord recOrder=new EquRecord();
				Date dateOrder = new Date();
		        SimpleDateFormat sdfOrder =new SimpleDateFormat( "yyyy-MM-dd" );
		        String nowTimeOrder = sdfOrder.format(dateOrder);
		        Date timeOrder = sdf.parse( nowTimeOrder );
		        
		        recOrder.setRegistrar(userService.getLoginNameByKey(Token));
		        recOrder.setDate(timeOrder);
		        recOrder.setEquId(apl.getEquId());
		        recOrder.setRemark("");
		        recOrder.setState("out");
		        recOrder.setLoginName(apl.getLoginName());
				recService.addRecord(recOrder);//娣诲姞宸插嚭搴撶殑璁惧璁板綍
				
				Equipment equOrder=new Equipment();
				equOrder=equService.getEqu(apl.getEquId());
				equOrder.setOutDate(timeOrder);
				equOrder.setState("out");
				equService.updateEqu(equOrder);//淇敼璁惧鐨勭浉鍏充俊鎭�
			}
			map.put("success", "true");
			map.put("err_code", "0");
			map.put("message", "ok");
		}
		System.out.println("inEqu:"+map.toString());
		String json = JSONObject.fromObject(map).toString();
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
	
	//String aplId,String state
	@RequestMapping(value = "/cheakApl", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public  @ResponseBody  String updateEqu(@RequestBody String parms,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		Map<Object,Object> map = new HashMap<>();
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String aplId=UtilsC.hasKeyOfMap("aplId", jsonObject);
		String state=UtilsC.hasKeyOfMap("state", jsonObject);
		
		
		String Token = request.getHeader("X-Access-Token");
		if(userService.hasExpires(Token)){
			map.put("success", "false");
			map.put("err_code", "401");
			map.put("message", "身份过期需要重新登录");
		}
		else if(aplId==null||state==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			
	
			Application apl=new Application();
			apl=aplService.getApl(Integer.parseInt(aplId));
			if(apl==null){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "aplId不存在");
			}
			else{
				apl.setState(state);
				aplService.updateApl(apl);//淇敼鐢宠琛ㄧ殑鐘舵�佷俊鎭�
				
				if(apl.getMethod().equals("rent")&&state.equals("pass")){
					EquRecord rec=new EquRecord();
					Date date = new Date();
			        SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd" );
			        String nowTime = sdf.format(date);
			        Date time = sdf.parse( nowTime );
			        
			        rec.setRegistrar(userService.getLoginNameByKey(Token));
					rec.setDate(time);
					rec.setEquId(apl.getEquId());
					rec.setRemark("");
					rec.setState("out");
					rec.setLoginName(apl.getLoginName());
					recService.addRecord(rec);//娣诲姞宸插嚭搴撶殑璁惧璁板綍
					
					Equipment equ=new Equipment();
					equ=equService.getEqu(apl.getEquId());
					equ.setOutDate(time);
					equ.setState("out");
					equService.updateEqu(equ);//淇敼璁惧鐨勭浉鍏充俊鎭�
					
					
					SucApplication suapl=new SucApplication();
					suapl.setEquId(apl.getEquId());
					suapl.setState("in");//in琛ㄧず杩樺湪浠撳簱鍐�
					suapl.setLoginName(apl.getLoginName());
					suAplService.addSuApl(suapl);//娣诲姞鎴愬姛鐢宠鍒扮殑琛ㄥ崟
					
				}
				else if(apl.getMethod().equals("order")&&state.equals("pass")){
					Equipment equ=new Equipment();
					equ=equService.getEqu(apl.getEquId());
					equ.setState("order");
					equService.updateEqu(equ);//淇敼璁惧鐨勭浉鍏充俊鎭�
				}
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
				
			}
			
	
			
		}
		System.out.println("cheakApl:"+map.toString());
		String json = JSONObject.fromObject(map).toString();
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
