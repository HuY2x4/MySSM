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
    	// 指定允许其他域名访问
    	resp.setHeader("Access-Control-Allow-Origin", "*");
    	// 响应类型
    	resp.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");
    	// 响应头设置
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token");
    	if ("OPTIONS".equals(req.getMethod())){
    			resp.setStatus(resp.SC_NO_CONTENT);
    	}
    	chain.doFilter(request, response);
    	}
    	
  
    @Override  
    public void init(FilterConfig arg0) throws ServletException {  
          
    }  
  
}  