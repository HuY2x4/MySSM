package com.zucc.kcgl.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
  
public class SimpleCORSFilter implements Filter{  
  
    @Override  
    public void destroy() {  
          
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    		// TODO Auto-generated method stub
    		// place your code here
    	HttpServletRequest req = (HttpServletRequest) request;
    	HttpServletResponse resp = (HttpServletResponse) response;
    	String origin = ((HttpServletRequest) request).getHeader("Origin");
	    if(origin == null) {
	        origin = ((HttpServletRequest) request).getHeader("Referer");
	    }
    	// 鎸囧畾鍏佽鍏朵粬鍩熷悕璁块棶
    	resp.setHeader("Access-Control-Allow-Origin", origin);
    	// 鍝嶅簲绫诲瀷
    	resp.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");
    	// 鍝嶅簲澶磋缃�
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token,X-Access-Token");
    	resp.setHeader("Access-Control-Allow-Credentials", "true");//, 
    	if ("OPTIONS".equals(req.getMethod())){
    			resp.setStatus(resp.SC_NO_CONTENT);
    	}
    	chain.doFilter(request, response);
    	}
    	
  
    @Override  
    public void init(FilterConfig arg0) throws ServletException {  
          
    }  
  
}  