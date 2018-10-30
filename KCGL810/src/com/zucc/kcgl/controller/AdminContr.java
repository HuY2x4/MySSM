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
	
	
	@RequestMapping(value = "/outEqu", method = RequestMethod.POST)//�����
	public  @ResponseBody  String outEqu(HttpServletRequest request, HttpServletResponse response,int code) throws IOException, ParseException{
		
		
		
		//�����������ȡ�豸��ɾ�������
		if(suAplService.updateSuApl(code, "out")){
			return "ok";
		}
		else{
			return "codeError";
		}
		
		
			
		
		
		
		
	}

	@RequestMapping(value = "/inEqu", method = RequestMethod.POST)//�豸��id����ע
	public  @ResponseBody  String inEqu(HttpServletRequest request, HttpServletResponse response,int equId,String remark) throws IOException, ParseException{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userName")==null){
			return "loginError";
		}
		//application apl=new application();
		//apl=aplService.getApl(equId);///////////////////////////////////////////////�����ⲻӦ�ô�������������Ϣ
		//��������¼
		
		//�鿴�豸id�Ƿ����
		equipment ifequ=new equipment();
		ifequ =equService.getEqu(equId);
		if(ifequ==null){
			return "idError";
		}
		
		
		
		record rec=new record();
		
		Date date = new Date();//���ϵͳʱ��.
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
		//�޸��豸״̬
		
		equService.updateEquState(equId, "in");
		//�޸��豸��������ʱ��
		//Date indate=recService.getTime(equId, "in");
		equService.updateEqutime(time, equId, "in");
		//�ж��Ƿ�����ԤԼ
		application apl=new application();
		System.out.println(equId);
		apl=aplService.getOrderApl(equId);
		if(apl!=null){
			System.out.println("ԤԼ����");
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
		//����һ���ѳɹ����루��ԤԼ��
		
		return "ok";
	
		
		
		
	}
}
