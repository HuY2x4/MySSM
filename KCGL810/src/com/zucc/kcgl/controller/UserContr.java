package com.zucc.kcgl.controller;



import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zucc.kcgl.model.User;
import com.zucc.kcgl.model.UserSchoolInf;
import com.zucc.kcgl.service.UserIdService;
import com.zucc.kcgl.service.UserSchoolInfService;
import com.zucc.kcgl.service.UserService;
import com.zucc.kcgl.util.MD5Utils;
import com.zucc.kcgl.util.PhotoUtil;
import com.zucc.kcgl.util.UtilsC;


/*@CrossOrigin( maxAge = 3600)*/
@Controller
public class UserContr {
	@Resource
	private UserService userService;
	@Resource
	private UserIdService userIdService;
	@Resource
	private UserSchoolInfService userSchoolInfService;
	

	@RequestMapping("/")
	public String login(){  
		
		return "test2";
	}
	
	@RequestMapping("/test3")
	public String test3(){  
		
		return "test3";
	}
	
	@RequestMapping("/main")
	public String Fmain(){  
		return "main";
	}
	
	
	@RequestMapping("/register")
	public String register(){  
		return "user/register";
	}
	
	@RequestMapping("/userCenter")
	public String userCenter(){  
		return "user/userCenter";
	}
	
	@RequestMapping("/userEdit")
	public String userEdit(){  
		return "user/userEdit";
	}
	
	@RequestMapping("/userEditPassword")
	public String userEditPassword(){  
		return "user/userEditPassword";
	}
	
	@RequestMapping("/userOtherInf")
	public String userOtherInf(){  
		return "user/userOtherInf";
	}
	
	@RequestMapping(value = "/hasExpired", method = RequestMethod.POST)
	public  @ResponseBody  String HasExpired(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		String Token = request.getHeader("X-Access-Token");
		if(userService.hasExpires(Token)){
			map.put("success", "false");
			map.put("err_code", "401");
			map.put("message", "身份过期需要重新登录");
		}
		else{
			map.put("success", "true");
			map.put("err_code", "0");
			map.put("message", "ok");
		}
		
		
		
		System.out.println("hasExpired:"+map.toString());
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
	
	@RequestMapping(value = "/cheakLoginName", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String ifLoginNameRepeat(@RequestBody String parms,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String loginName=UtilsC.hasKeyOfMap("loginName", jsonObject);
		if(userService.hasLoginNameRepeat(loginName)){ 
			map.put("success", "true");
			map.put("err_code", "0");
			map.put("message", "ok");
		}
		else{
			map.put("success", "false");
			map.put("err_code", "404");
			map.put("message", "用户不存在");
		}
		System.out.println("cheakLoginName:"+map.toString());
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
		
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String addUser(@RequestBody User user,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		if(user==null||user.getLoginName().equals("")||user.getPassword().equals("")||user.getUserName().equals("")){
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的用户信息为空");
		}
		else if(userService.hasLoginNameRepeat(user.getLoginName())){//如果登入账号重复
			map.put("success", "false");
			map.put("err_code", "-1");
			map.put("message", "登录账号已存在");
		}
		else if(!userService.addUser(user)){
			map.put("success", "false");
			map.put("err_code", "500");
			map.put("message", "服务器添加用户失败");
		}
		else if(!userIdService.addUserIdMap(user.getLoginName())){
			map.put("success", "false");
			map.put("err_code", "500");
			map.put("message", "服务器添加用户映射表失败");
		}
		else{
			
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String strn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
			String md5=MD5Utils.MD5Encode(strn, "utf8");
			
			//将有效时间和md5存到数据库中
			userService.updAccessKey(md5, user.getLoginName(), strn);
			
			
//			response.addCookie(currentTime);
			map.put("access_key", md5);
			map.put("success", "true");
			map.put("err_code", "0");
			map.put("message", "ok");
		}
		System.out.println("addUser:"+map.toString());
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
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String deleteUser(@RequestBody String parms, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
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
			map.put("message", "传入的用户信息为空");
		}
		else if(!userService.hasLoginNameRepeat(loginName)){ 
			map.put("success", "false");
			map.put("err_code", "404");
			map.put("message", "登录账号不存在");
		}
		else if(!userService.deleteUser(loginName)){
			map.put("success", "false");
			map.put("err_code", "500");
			map.put("message", "服务器删除用户失败");
		}
		else if(!userIdService.deleteUserIdMap(loginName)){
			map.put("success", "false");
			map.put("err_code", "500");
			map.put("message", "服务器删除用户映射表失败");
		}
		else{
			map.put("success", "true");
			map.put("err_code", "0");
			map.put("message", "ok");
		}
		System.out.println("deleteUser:"+map.toString());
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
	
	/* getUser的结构
	 * 
	 * if(loginName为空){
	 * 	map加入400code
	 *}
	 *else{
	 * 	if(loginName指定当前登录账号){
	 * 		if(身份过期){
	 * 			map加入401code
	 * 			return map			
	 * 		}
	 * 		else {
	 * 			获得当前登录账号
	 * 		}
	 * 	}
	 * 
	 * 	if(登录账号不存在){
	 * 		map加入404code
	 * 	}
	 *  else{
	 *  	map将用户信息加入
	 *  }
	 *}
	 *return map
	 * */
	@RequestMapping(value = "/getUser", method = RequestMethod.POST, produces="application/json;charset=UTF-8")//json
	public  @ResponseBody  String getUser(@RequestBody String parms,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>(); 
		User user=new User();
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
			map.put("message", "传入的用户信息为空");
		}
		else {
			if(loginName.equals("currentUser")){//如果是当前用户
				String currentTime = "";
				Cookie[] cookies = request.getCookies();
				if(cookies!=null&&cookies.length>0){
					//遍历Cookie
					for(int i=0;i<cookies.length;i++){
						Cookie cookie = cookies[i];
						//此处类似与Map有
						System.out.println("cookie遍历："+cookie.getName());
						/*if("currentTime".equals(cookie.getName())){
							currentTime = cookie.getValue();
							System.out.println(currentTime);
							Cookie currentTimeC = new Cookie("currentTime",currentTime);	
							currentTimeC.setPath(request.getContextPath()+"/");
							currentTimeC.setMaxAge(0);
//							response.addCookie(currentTimeC);
							map.put("access_key", currentTimeC);
						}*/
					}
				}
				else{
					System.out.println("cookie空的");
				}
				
				
				
		
				loginName = userService.getLoginNameByKey(Token);
				
			}
			System.out.println("debug-  loginName:"+loginName);
			if(!userService.hasLoginNameRepeat(loginName)){
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "登录账号不存在");
			}
			else {
				Map<Object,Object> data = new HashMap<>(); 
				user=userService.getUserAllInf(loginName);
				data.put("userId",user.getUserMapper().getUserId());
				data.put("userName", user.getUserName());
				data.put("loginName", user.getLoginName());
				data.put("password", user.getPassword());
				data.put("phone", user.getPhone());
				String Email="";
				if(user.getEmail()!=null){
					Email=user.getEmail();
				}
				System.out.println("debug-  Email:"+Email);
				data.put("email", user.getEmail());
				data.put("points", user.getPoints());
				String StuOrTea="";
				String UserSchoolId="";
				String UserFrom="";
				if(user.getUserSchoolInf()!=null){
					StuOrTea=user.getUserSchoolInf().getStuOrTea();
					UserSchoolId=user.getUserSchoolInf().getUserSchoolId();
					UserFrom=user.getUserSchoolInf().getUserFrom();
				}
				
				System.out.println("debug-  UserFrom:"+UserFrom);
				data.put("stuOrTea", StuOrTea);
				data.put("userSchoolId", UserSchoolId);
				data.put("userFrom", UserFrom);
				System.out.println("debug-  5");
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
				map.put("data", data);
			
			}
		}
		System.out.println("getUser:"+map.toString());
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
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String login(@RequestBody User user1,HttpServletRequest request, HttpServletResponse response) throws IOException{	
		Map<Object,Object> map = new HashMap<>(); 
		System.out.println("user:"+user1.toString());
		String loginName=user1.getLoginName();
		String password=user1.getPassword();
		if(loginName==null||loginName.equals("")){ 
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的用户信息为空");
		}
		else if(!userService.hasLoginNameRepeat(loginName)){
			System.out.println("loginName:"+loginName+"  "+password);
			map.put("success", "false");
			map.put("err_code", "404");
			map.put("message", "登录账号不存在");
		}
		else{
			User user=userService.getUserInfByLoginName(loginName);
			String mima=user.getPassword();
			if(mima.equals(password)){	

				
				Timestamp time = new Timestamp(System.currentTimeMillis());
				String strn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
				String md5=MD5Utils.MD5Encode(strn, "utf8");
				
				//将有效时间和md5存到数据库中
				userService.updAccessKey(md5, loginName, strn);
				
//				Cookie currentTime = new Cookie("access_key",md5);	
//				currentTime.setPath(request.getContextPath()+"/");
//				currentTime.setMaxAge(60*60*24);
//				response.addCookie(currentTime);
				map.put("token", md5);
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
			}
			else{
				map.put("success", "false");
				map.put("err_code", "-1");
				map.put("message", "密码错误");
			}
		}
		
		
		System.out.println("login:"+map.toString());
		String json = JSONObject.fromObject(map).toString();
		String origin = request.getHeader("Origin");
	    if(origin == null) {
	        origin = request.getHeader("Referer");
	    }
	    response.setHeader("Access-Control-Allow-Origin", origin);
//		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	
	
	@RequestMapping(value = "/updateUserInf", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String updateUserInf(@RequestBody User user,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>(); 
		String Token = request.getHeader("X-Access-Token");
		if(userService.hasExpires(Token)){
			map.put("success", "false");
			map.put("err_code", "401");
			map.put("message", "身份过期需要重新登录");
		}
		else if(user==null){ 
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的用户信息为空");
		}
		else {
			if(user.getLoginName().equals("currentUser")){
				
				user.setLoginName(userService.getLoginNameByKey(Token));
				
			}
			
			if(!userService.hasLoginNameRepeat(user.getLoginName())){ 
				map.put("success", "false");
				map.put("err_code", "404");
				map.put("message", "登录账号不存在");
			}
			else{
				User newuser=userService.getUserInfByLoginName(user.getLoginName());
				newuser.setEmail(user.getEmail());
				newuser.setPhone(user.getPhone());
				if(userService.updateUserInf(newuser)){ 
					map.put("success", "true");
					map.put("err_code", "0");
					map.put("message", "ok");
				}
				else{
					map.put("success", "false");
					map.put("err_code", "500");
					map.put("message", "更新用户信息失败");
				}
				
			}
			
		}
		System.out.println("updateUserInf:"+map.toString());
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
	
	
	@RequestMapping(value = "/updateUserSchoolInf", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String updateUserSchoolInf(@RequestBody UserSchoolInf userSchoolInf ,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>(); 
		String Token = request.getHeader("X-Access-Token");
		if(userService.hasExpires(Token)){
			map.put("success", "false");
			map.put("err_code", "401");
			map.put("message", "身份过期需要重新登录");
		}
		else if(userSchoolInf==null){ 
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			
			String loginName = userService.getLoginNameByKey(Token);
			User user2=userService.getUserAllInf(loginName);
			if(!(user2.getUserSchoolId()==null)){
				map.put("success", "false");
				map.put("err_code", "-1");
				map.put("message", "学生或教师信息已绑定");
			}
			else if(userSchoolInfService.hasUserSchoolInf(userSchoolInf.getUserSchoolId())){
				map.put("success", "false");
				map.put("err_code", "-1");
				map.put("message", "学生或教师信息已绑定");
			}
			else if(userSchoolInfService.addUserSchoolInf(userSchoolInf)==0){ 
				map.put("success", "false");
				map.put("err_code", "500");
				map.put("message", "绑定信息失败");
			}
			else{
				User user=userService.getUserInfByLoginName(loginName);
				user.setUserSchoolId(userSchoolInf.getUserSchoolId());
				if(!userService.updateUserInf(user)){
					map.put("success", "false");
					map.put("err_code", "500");
					map.put("message", "修改用户信息失败");
				}
				else{
					map.put("success", "true");
					map.put("err_code", "0");
					map.put("message", "ok");
				}
			}
			
		}
		System.out.println("updateUserSchoolInf:"+map.toString());
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
	
	
	@RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String updateUserPassword(@RequestBody String parms ,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>(); 
		JSONObject jsonObject = JSONObject.fromObject(parms);
		String password=UtilsC.hasKeyOfMap("password", jsonObject);
		String Token = request.getHeader("X-Access-Token");
		if(userService.hasExpires(Token)){
			map.put("success", "false");
			map.put("err_code", "401");
			map.put("message", "身份过期需要重新登录");
		}
		else if(password==null||password.equals("")){ 
			map.put("success", "false");
			map.put("err_code", "400");
			map.put("message", "传入的信息为空");
		}
		else{
			
			String loginName = userService.getLoginNameByKey(Token);
			if(userService.updateUserPassword(loginName, password)){ 
				map.put("success", "true");
				map.put("err_code", "0");
				map.put("message", "ok");
			}
			else{
				map.put("success", "false");
				map.put("err_code", "500");
				map.put("message", "修改用户密码错误");
			}
			
			
		}
		System.out.println("updateUserPassword:"+map.toString());
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
	
	
	
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<Object,Object> map = new HashMap<>(); 
		String Token = request.getHeader("X-Access-Token");
		String loginName = userService.getLoginNameByKey(Token);
		userService.updAccessKey("", loginName, "");
		map.put("success", "true");
		map.put("err_code", "0");
		map.put("message", "ok");
	
		System.out.println("logout:"+map.toString());
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
	
	@RequestMapping(value = "/getUserCount", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public  @ResponseBody  String getUserCount(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		Map<Object,Object> map = new HashMap<>(); 
		Map<Object,Object> data = new HashMap<>(); 
		int count=userService.getUserCount();
		data.put("count",count);
		map.put("success", "true");
		map.put("err_code", "0");
		map.put("message", "ok");
		map.put("data", data);
		System.out.println("getUserCount:"+map.toString());
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
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public    String test(Model model,@RequestParam("images") MultipartFile file
		     , HttpServletRequest request) throws IOException{
		//window.location.href = "<%=basePath%>main"
		//锟斤拷一锟街凤拷锟斤拷页锟斤拷姆锟斤拷锟�
        //model.addAttribute("img",PhotoUtil.saveFile(file,request));
       //锟节讹拷锟街凤拷锟斤拷页锟斤拷姆锟斤拷锟�
        request.setAttribute("images",PhotoUtil.saveFile(file,request));
        return "test";
		
			
		
	}
	

}
