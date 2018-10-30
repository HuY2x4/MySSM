package com.zucc.kcgl.controller;



import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zucc.kcgl.model.MdUser;
import com.zucc.kcgl.service.UserService;



@Controller
public class UserContr {
	@Resource
	private UserService userService;
	
	@RequestMapping("/")
	public String login(){  
		
		return "user/login";
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
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public  @ResponseBody  String saveUser(HttpServletRequest request, HttpServletResponse response,String userName,String loginName,String password ,String phone) throws IOException{//HttpServletRequest request, HttpServletResponse response,
		//String name=request.getParameter("name");   //从前端发来的数据中取值
		HashMap<Object,Object> map = new HashMap<>();  
		map.put("flag", "ok"); 
		String json = JSONObject.fromObject(map).toString();//
		
		List<String> list=new ArrayList<String>();
		
		//将数据返回
		/*response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();*/
		
		MdUser user=new MdUser();
		user.setUserName(userName);
		user.setLoginName(loginName);
		user.setPassword(password);
		user.setPhone(phone);
		
		/*if(userService.ifLoginNameRepeat(loginName)){
			return "noNR";
		}*/
		
		if(userService.saveUser(user) ){//  //这个是从数据库取值的，用的mybatis 我另外一个项目研究的  
			return "ok";//这里就写死了，
		}
		else{
			return "no";
		}
		
	
		
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public  @ResponseBody  String deleteUser(HttpServletRequest request, HttpServletResponse response,String loginName) throws IOException{
		
		if(userService.deleteUser(loginName)){
			return "ok";
		}
		else{
			return "no";
		}
		
	
		
	}
	
	
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)//json
	public  @ResponseBody  String getUser(HttpServletRequest request, HttpServletResponse response,String loginName) throws IOException{
		Map<Object,Object> map = new HashMap<>();  
		
		System.out.println(loginName);
		MdUser user=new MdUser();
		if(!userService.ifLoginNameRepeat(loginName)){
			map.put("flag", "no");
			String json = JSONObject.fromObject(map).toString();
			return json;
		}
		user=userService.getUserInf(loginName);
		map.put("userId", user.getUserId());
		
		map.put("userName", user.getUserName());
		map.put("loginName", user.getLoginName());
		map.put("password", user.getPassword());
		map.put("phone", user.getPhone());
		map.put("email", user.getEmail());
		map.put("stuOrTea", user.getStuOrTea());
		map.put("userNum", user.getUserNum());
		map.put("userFrom", user.getUserFrom());
		map.put("points", user.getPoints());
		map.put("flag", "ok");
		System.out.println(user.getUserId());
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
		
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public  @ResponseBody  String dengLu(HttpServletRequest request, HttpServletResponse response,String loginName,String password) throws IOException{	
		MdUser user=userService.getUserInf(loginName);
		String mima=user.getPassword();
		String userName=user.getUserName();
		int userId=user.getUserId();
		HttpSession session = request.getSession();	
		if(mima.equals(password)){	
			System.out.println("登录成功");
			session.setAttribute("loginName", loginName);
			session.setAttribute("userName", userName);
			session.setAttribute("userId", Integer.toString(userId));
			return "ok";
		}
		else{
			return "no";//账号或密码错误
		}
		
		
		
	}
	
	
	
	@RequestMapping(value = "/updateUserInf", method = RequestMethod.POST)
	public  @ResponseBody  String updateUser(HttpServletRequest request, HttpServletResponse response,String loginName,String userName,String phone,String email,String stuOrTea,String userNum,String userFrom) throws IOException{
		
		MdUser user=new MdUser();
		user.setEmail(email);
		user.setLoginName(loginName);
		user.setPhone(phone);
		user.setStuOrTea(stuOrTea);
		user.setUserFrom(userFrom);
		user.setUserName(userName);
		user.setUserNum(userNum);
		
		if(userService.updateUserInf(user)){ 
			return "ok";
		}
		else{
			return "no";
		}
		
	
		
	}
	
	
	@RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST)
	public  @ResponseBody  String updateUserPassword(HttpServletRequest request, HttpServletResponse response,String password) throws IOException{
		HttpSession session = request.getSession(false);
		String loginName = (String)session.getAttribute("loginName");
		if(loginName.equals("")||loginName==null){
			return "loginError";
		}
		
		if(userService.updateUserPassword(loginName, password)){ 
			return "ok";
		}
		else{
			return "no";
		}
		
	
		
	}
	
	@RequestMapping(value = "/cheakLoginName", method = RequestMethod.POST)
	public  @ResponseBody  String ifLoginNameRepeat(HttpServletRequest request, HttpServletResponse response,String loginName) throws IOException{
		
		
		if(userService.ifLoginNameRepeat(loginName)){ 
			return "no";//重复了
		}
		else{
			return "ok";
		}
		
	
		
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public  @ResponseBody  String zhuXiao(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(false);
		session.removeAttribute("loginName");
	
		return "ok";
		
		
	
		
	}
	
	@RequestMapping(value = "/getUserCount", method = RequestMethod.POST)
	public  @ResponseBody  String getUserCount(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		
			int count=userService.getUserCount();
		
			return Integer.toString(count);
		
		
		
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public  @ResponseBody  String test(HttpServletRequest request, HttpServletResponse response,String time) throws IOException{
		//window.location.href = "<%=basePath%>main"
			
		
			return "ok";
		
	}
	

}
