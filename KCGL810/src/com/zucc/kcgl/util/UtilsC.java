package com.zucc.kcgl.util;

import java.util.Map;

import net.sf.json.JSONObject;

public class UtilsC {
	public static String hasKeyOfMap (String str,JSONObject jsonObject){
		Map<String,Object> map=jsonObject;
		String restr = null;
		if(map.containsKey(str)){
			restr=(String) map.get(str);
		}
		return restr;
		
	}
	public static String KongToNull(String str){
		String restr=null;
		if(str==null){
			return restr;
		}
		if(!str.equals("")){
			restr=str;
		}
		return restr;
	}
}
