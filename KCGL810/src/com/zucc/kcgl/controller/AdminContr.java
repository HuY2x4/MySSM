package com.zucc.kcgl.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zucc.kcgl.model.application;
import com.zucc.kcgl.model.equipment;
import com.zucc.kcgl.model.record;
import com.zucc.kcgl.model.sucApplication;
import com.zucc.kcgl.service.AplService;
import com.zucc.kcgl.service.EquService;
import com.zucc.kcgl.service.RecordService;
import com.zucc.kcgl.service.SuAplService;

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
	
	
	@RequestMapping(value = "/outEqu", method = RequestMethod.POST)//提货码
	public  @ResponseBody  String outEqu(HttpServletRequest request, HttpServletResponse response,int code) throws IOException, ParseException{
		
		
		
		//根据提货码提取设备，删除提货码
		if(suAplService.updateSuApl(code, "out")){
			return "ok";
		}
		else{
			return "codeError";
		}
		
		
			
		
		
		
		
	}

	@RequestMapping(value = "/inEqu", method = RequestMethod.POST)//设备的id，备注
	public  @ResponseBody  String inEqu(HttpServletRequest request, HttpServletResponse response,int equId,String remark) throws IOException, ParseException{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userName")==null){
			return "loginError";
		}
		//application apl=new application();
		//apl=aplService.getApl(equId);///////////////////////////////////////////////有问题不应该从申请里面找信息
		//增加入库记录
		
		//查看设备id是否存在
		equipment ifequ=new equipment();
		ifequ =equService.getEqu(equId);
		if(ifequ==null){
			return "idError";
		}
		
		
		
		record rec=new record();
		
		Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        equipment equ=new equipment();
        equ=equService.getEqu(equId);
        
        rec.setAdmin((String)session.getAttribute("userName"));
		rec.setDate(time);
		rec.setEquId(equId);
		rec.setEquName(equ.getName());
		rec.setRemark("");
		rec.setState("in");
		List<record> list=new ArrayList<record>();
		list=recService.getRecordByEqu(equId);
		
		rec.setUserId(list.get(list.size()-1).getUserId());
		rec.setUserName(list.get(list.size()-1).getUserName());
		recService.addRecord(rec);
		//修改设备状态
		
		equService.updateEquState(equId, "in");
		//修改设备最近出入库时间
		//Date indate=recService.getTime(equId, "in");
		equService.updateEqutime(time, equId, "in");
		//判断是否有人预约
		application apl=new application();
		System.out.println(equId);
		apl=aplService.getOrderApl(equId);
		if(apl!=null){
			System.out.println("预约进入");
			sucApplication suapl=new sucApplication();
			suapl.setEquId(apl.getEquId());
			suapl.setEquName(apl.getEquName());
			suapl.setState("in");
			suapl.setUserId(apl.getUserId());
			suapl.setUserName(apl.getApplicant());
			equipment equ1=equService.getEqu(equId);
			suapl.setVersion(equ1.getVersion());
			suAplService.addSuApl(suapl);
			aplService.updateOrderState(apl.getId());
		}
		//增加一条已成功申请（在预约里
		
		return "ok";
	
		
		
		
	}
}
