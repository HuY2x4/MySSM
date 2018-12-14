package com.zucc.kcgl.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zucc.kcgl.mapper.UserMapper;
import com.zucc.kcgl.model.User;
import com.zucc.kcgl.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;

	
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		/*杩欓噷鏈変釜浜嬬墿鎻愪氦鐨勯棶棰樿繕瑕佸幓鐮旂┒,鍦ㄥ闈�
		 * 
		 * */
//		System.out.println("銆愭墽琛屼腑銆慤serServiceImpl锛歴aveUser");
		if(userMapper.addUser(user)==0){//娣诲姞鐢ㄦ埛
			return false;
		};
		
		return true;
	}

	@Override
	public boolean deleteUser(String loginName) {
		// TODO Auto-generated method stub
		if(userMapper.deleteUser(loginName)==0){//鍒犻櫎鐢ㄦ埛
			return false;
		};
	
		return true;
	}

	@Override
	public User getUserInfByLoginName(String loginName) {
		// TODO Auto-generated method stub
		User user=new User();
		user=userMapper.getUserInfByloginName(loginName);
		return user;
	}

	@Override
	public boolean updateUserInf(User user) {
		// TODO Auto-generated method stub
		userMapper.updateUserInf(user);
		return true;
	}

	@Override
	public boolean updateUserPassword(String loginname, String password) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setLoginName(loginname);
		user.setPassword(password);
		userMapper.updateUserPassword(user);
		return true;
	}

	@Override
	public boolean hasLoginNameRepeat(String loginname) {
		// TODO Auto-generated method stub
		String name=userMapper.hasLoginNameRepeat(loginname);
		if(name==null){
			return false;
		}
		if(name.equals(loginname)){
			return true;
		}
		
		return false;
	}



	@Override
	public int getUserCount() {
		// TODO Auto-generated method stub
		return userMapper.getUserCount();
	}

	@Override
	public User getUserAllInf(String loginName) {
		// TODO Auto-generated method stub
		User user=userMapper.getUserInfByloginName(loginName);
		if(user.getUserSchoolId()==null||user.getUserSchoolId().equals("")){
			return userMapper.getUserAllInfNoBD(loginName);
		}
		
		return userMapper.getUserAllInf(loginName);
	}

	@Override
	public boolean updAccessKey(String accesskey, String loginName,String time) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accesskey", accesskey);
		map.put("loginName", loginName);
		map.put("validtime", time);
		userMapper.updAccessKey(map);
		
		return true;
	}

	@Override
	public boolean hasExpires(String accesskey) {
		// TODO Auto-generated method stub
		try {
			String datesql = userMapper.getAccessKey(accesskey);
			if(datesql==null||datesql.equals("")){
				return true;
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Long timesql = df.parse(datesql).getTime();
		
		
			Long time = System.currentTimeMillis();
			time -=6*60*1000*60;
			
			if(time>timesql){
				return true;
			}
		
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public String getLoginNameByKey(String accesskey) {
		// TODO Auto-generated method stub

		return userMapper.getLoginNameByKey(accesskey);
	}

	
}
