<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>login test</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="ajax方式">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">
        function login() {
        	var name=$("#username").val()
        	var bao = {
        				"loginName" : "hyxzucc",
        				"password"  : "123456",
        		  //  "purpose" : "1",
                   // "remark" : "1",
                  //  "userName" : "1",
                   // "version" : "1",
                   // "price" : "1",
                   // "owner" : "1",
                   // "chargePerson" : "1",
                   // "reamrk" : "1",
                   // "state" : "8",
                   // "failureState" : "9",
                   // "failureStateRemark" : "10",
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>login" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {   //result就是返回的String或json
                	alert("登录成功"); 
                	
                	  
                    if (data=='3') {
                        alert("SUCCESS");
                           //就是访问这个main下面的这个函数 
                    }
                    ;
                },
                error : function() {
                    alert("错的");
                }
            });
        }
        
        function test2() {
        	var name=$("#username").val()
        	var bao = {
                    
                    "method" : "rent",
                    "phone" : "phone",
                    "purpose" : "目的",
                    "remark" : "wu",
                    "userName" : "黄叶轩",
                    "equId" : "4",
                    "returnTime" : "2018-9-1 15:00:00",
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>addApl" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (result) {   //result就是返回的String或json
                	alert("申请添加成功");
                	
                    if (true) {
                       // alert(result.userName);
                           //就是访问这个main下面的这个函数 
                    }
                    ;
                },
                error : function() {
                    alert("还是错的!!!!!1.47");
                }
            });
        }
        
        function test3() {
        	var name=$("#username").val()
        	var bao = {
                    
                    "id" : "34",
                    "state" : "pass",
                   
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>cheakApl" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (result) {   //result就是返回的String或json
                	alert("同意申请");
                	
                    if (true) {
                       // alert(result.userName);
                           //就是访问这个main下面的这个函数 
                    }
                    ;
                },
                error : function() {
                    alert("还是错的");
                }
            });
        }
        
        
        function test4() {
        	var name=$("#username").val()
        	var bao = {
                    
        		 "method" : "order",
                 "phone" : "phone",
                 "purpose" : "目的",
                 "remark" : "wu",
                 "userName" : "黄叶轩",
                 "equId" : "4",
                 "returnTime" : "24",
                   
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>addApl" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (result) {   //result就是返回的String或json
                	alert("预约成功");
                	
                    if (true) {
                       // alert(result.userName);
                           //就是访问这个main下面的这个函数 
                    }
                    ;
                },
                error : function() {
                    alert("还是错的!!!!!1.47");
                }
            });
        }
        function test5() {
        	var name=$("#username").val()
        	var bao = {
                    
        		
                 "remark" : "",
                 
                 "equId" : "9",
                
                   
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>inEqu" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (result) {   //result就是返回的String或json
                	alert(result);
                	
                    if (true) {
                       // alert(result.userName);
                           //就是访问这个main下面的这个函数 
                    }
                    ;
                },
                error : function() {
                    alert("还是错的!!!!!1.47");
                }
            });
        }
        
        function test6() {
        	var name=$("#username").val()
        	var bao = {
                    
        		
                 "code" : name,
                 
                
                   
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>outEqu" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (result) {   //result就是返回的String或json
                	alert("出库成功");
                	
                    if (true) {
                       // alert(result.userName);
                           //就是访问这个main下面的这个函数 
                    }
                    ;
                },
                error : function() {
                    alert("还是错的!!!!!1.47");
                }
            });
        }
        
        function test7() {
        	var name=$("#username").val()
        	var bao = {
                    
                    "id" : "31",
                    "state" : "pass",
                   
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>cheakApl" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (result) {   //result就是返回的String或json
                	alert("同意申请");
                	
                    if (true) {
                       // alert(result.userName);
                           //就是访问这个main下面的这个函数 
                    }
                    ;
                },
                error : function() {
                    alert("还是错的");
                }
            });
        }
        
        function test8() {
        	var name=$("#username").val()
        	var bao = {
                    
                    "name" : "电脑",
                    "type" : "电子产品",
                    "version" : "666",
                    "price" : "6666",
                    "owner" : "hyx",
                    "chargePerson" : "hyx",
                    "remark" : "无",
                    "state" : "in",
                    "imagePath" : $("#image"),
                   
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>addEqu" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (result) {   //result就是返回的String或json
                	alert("同意申请");
                	
                    if (true) {
                       // alert(result.userName);
                           //就是访问这个main下面的这个函数 
                    }
                    ;
                },
                error : function() {
                    alert("还是错的");
                }
            });
        }
    </script>
</head>
<body>
<div id="form-div">
    <form id="form1"  action="<%=basePath%>main" method="post">  
   		 <p>姓名：<input name="userName" type="text" id="username" tabindex="1" size="15" value=""/></p>
        <p>用户名：<input name="loginName" type="text" id="loginname" tabindex="1" size="15" value=""/></p>
        <p>密　码：<input name="password" type="password" id="textbox2" tabindex="2" size="16" value=""/></p>
        <p>手机号：<input name="phone" type="text" id="phone" tabindex="1" size="15" value=""/></p>
        <input name="img" type="file" id="image">
        <p><input type="button" value="登录" onclick="login()">&nbsp;<input type="submit" value="页面跳转"></p>
        <p><input type="button" value="添加申请" onclick="test2()"></p>
        <p><input type="button" value="同意" onclick="test3()"></p>
         <p><input type="button" value="出库" onclick="test6()"></p>
        <p><input type="button" value="预约" onclick="test4()"></p>
        <p><input type="button" value="同意预约" onclick="test7()"></p>
         <p><input type="button" value="入库" onclick="test5()"></p>
          <p><input type="button" value="添加设备" onclick="test8()"></p>
    </form>
</div>
</body>
</html>