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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zucc.kcgl.model.Equipment;
import com.zucc.kcgl.service.EquService;
import com.zucc.kcgl.util.PhotoUtil;
import com.zucc.kcgl.util.UtilsC;
import com.zucc.kcgl.util.getImgBase64;

@Controller
public class EquContr {

	@Resource
	private EquService equService;
	
	@RequestMapping("/EquipmentList")
	public String EquipmentList(){  
		return "Equipment/EquipmentList";
	}
	
	@RequestMapping("/EquipmentMain")
	public String EquipmentMain(){  
		return "Equipment/EquipmentMain";
	}
	
	@RequestMapping(value = "/addEqu", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String addEqu( @RequestBody Equipment equipment,@RequestParam("imagePath") MultipartFile file
			,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		Map<Object,Object> map = new HashMap<>();  
		if(equipment==null||file==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			Date date = new Date();
	        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
	        String nowTime = sdf.format(date);
	        Date time = sdf.parse( nowTime );
	        equipment.setInDate(date);
	        equipment.setImg(PhotoUtil.saveFile(file,request));
		
			if(equService.addEqu(equipment)){
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
		String json = JSONObject.fromObject(map).toString();
		System.out.println("addEqu:"+map.toString());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
		
	}
	
	
	
	
	@RequestMapping(value = "/deleteEqu", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String deleteUser(@RequestBody String parms ,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		
		Map<Object,Object> map = new HashMap<>();  
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String equId=UtilsC.hasKeyOfMap("equId", jsonObject);
		if(equId==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			if(equService.deleteEqu(Integer.parseInt(equId))){
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
			}
			else{
				map.put("success", "false");
				map.put("err_code", "500");
				map.put("message", "删除设备失败");
			}
		}
		String json = JSONObject.fromObject(map).toString();
		System.out.println("deleteEqu:"+map.toString());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	
	
	@RequestMapping(value = "/updateEqu", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String updateEqu(@RequestBody Equipment equipment,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		Map<Object,Object> map = new HashMap<>(); 
		if(equipment==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			Equipment oldEqu=new Equipment();
			oldEqu=equService.getEqu(equipment.getEquId());
			if(oldEqu.getEquId()==0){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "设备不存在");
			}
			else{
				equipment.setImg(oldEqu.getImg());
				equipment.setInDate(oldEqu.getInDate());
				equipment.setOutDate(oldEqu.getOutDate());
				if(equService.updateEqu(equipment)){
					map.put("success", "true");
					map.put("err_code", "0");
					map.put("message", "ok");
				}
				else{
					map.put("success", "false");
					map.put("err_code", "500");
					map.put("message", "更新设备失败");
				}
			}
			
		}
		
		String json = JSONObject.fromObject(map).toString();
		System.out.println("updateEqu:"+map.toString());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	@RequestMapping(value = "/updateEquImg", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String updateEquImg(@RequestBody String parms ,@RequestParam("imagePath") MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		Map<Object,Object> map = new HashMap<>(); 
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String equId=UtilsC.hasKeyOfMap("equId", jsonObject);
		if(file==null||equId==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			Equipment equipment=new Equipment();
			equipment=equService.getEqu(Integer.parseInt(equId));
			if(equipment==null){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "设备不存在");
			}
			else{
				equipment.setImg(PhotoUtil.saveFile(file,request));
				if(equService.updateEqu(equipment)){
					map.put("success", "true");
					map.put("err_code", "0");
					map.put("message", "ok");
				}
				else{
					map.put("success", "false");
					map.put("err_code", "500");
					map.put("message", "更新设备图片失败");
				}
				
			}
			
		}
		String json = JSONObject.fromObject(map).toString();
		System.out.println("updateEquImg:"+map.toString());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
		
	}
	
	
	
	@RequestMapping(value = "/getEqu", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String getEqu(@RequestBody String parms,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		Map<Object,Object> data = new HashMap<>();  
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String equId=UtilsC.hasKeyOfMap("equId", jsonObject);
		if(equId==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			Equipment equ=new Equipment();
			equ=equService.getEqu(Integer.parseInt(equId));
			if(equ==null){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "设备不存在");
			}
			else{
				data.put("equId", equ.getEquId());
				data.put("equName", equ.getEquName());
				data.put("type", equ.getType());
				data.put("version", equ.getVersion());
				data.put("inDate", equ.getInDate());
				data.put("outDate", equ.getOutDate());
				data.put("price", equ.getPrice());
				data.put("owner", equ.getOwner());
				data.put("manager", equ.getManager());
				data.put("remark", equ.getRemark());
				data.put("state", equ.getState());
				String imageBase64=getImgBase64.getImageStr(equ.getImg());
				data.put("img", imageBase64);
				
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
				map.put("data", data);
			}
			
		}
		
		String json = JSONObject.fromObject(map).toString();
		System.out.println("getEqu:"+map.toString());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	
	@RequestMapping(value = "/getAllEquBaseInf", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String getAllEquBaseInf(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  

		List<Equipment> list=new ArrayList<Equipment>();
		list=equService.getAllEqu();
		if(list==null){
			map.put("success", "false");
			map.put("err_code", "404");
			map.put("message", "查找失败");
		}
		else{
			for(Equipment equ:list){
				String imgstr=getImgBase64.getImageStr(equ.getImg());
				equ.setImg(imgstr);
			}
			map.put("data", list);
			map.put("success", "true");
			map.put("err_code", "0");
			map.put("message", "ok");
		}
		
		String json = JSONObject.fromObject(map).toString();
		System.out.println("getAllEquBaseInf:data");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	//String currentPage,String pageSize,String type,String state,String equName
	@RequestMapping(value = "/getAllEquBaseInfBySort", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String getAllEquBaseInfBySort(@RequestBody String parms,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		List<Equipment> list=new ArrayList<Equipment>();
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String currentPage=UtilsC.hasKeyOfMap("currentPage", jsonObject);
		String pageSize=UtilsC.hasKeyOfMap("pageSize", jsonObject);
		String type=UtilsC.hasKeyOfMap("type", jsonObject);
		String state=UtilsC.hasKeyOfMap("state", jsonObject);
		String equName=UtilsC.hasKeyOfMap("equName", jsonObject);
	
		type=UtilsC.KongToNull(type);
		state=UtilsC.KongToNull(state);
		equName=UtilsC.KongToNull(equName);
		System.out.println("parms:"+parms.toString());
		System.out.println("type:"+type);

		if(currentPage==null||pageSize==null){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "currentPage或pageSize为空");
		}
		else{
			list=equService.getPageEquSort(Integer.parseInt(currentPage),Integer.parseInt(pageSize), type, state,equName);
			for(Equipment equ:list){
				equ.setImg(getImgBase64.getImageStr(equ.getImg()));
			}
			map.put("data", list);
			map.put("success", "true");
			map.put("err_code", "0");
			map.put("message", "ok");
		}
		
		
		String json = JSONObject.fromObject(map).toString();
		System.out.println("getAllEquBaseInfBySort:data");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	@RequestMapping(value = "/getEquCount", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String getEquCount(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
			Map<Object,Object> map = new HashMap<>(); 
			Map<Object,Object> data = new HashMap<>(); 
			int count=equService.getEquCount();
			data.put("count", count);
			map.put("data", data);
			map.put("success", "true");
			map.put("err_code", "0");
			map.put("message", "ok");
			String json = JSONObject.fromObject(map).toString();
			System.out.println("getEquCount:"+map.toString());
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setCharacterEncoding("UTF-8");
			response.flushBuffer();
			response.getWriter().write(json);
			response.getWriter().flush();  
			response.getWriter().close();
			return null;
		
		
	}
	
	
	
}
