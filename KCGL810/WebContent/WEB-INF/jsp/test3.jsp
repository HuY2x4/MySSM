<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">

		function login() {
			alert("<%=basePath%>");
			var bao = {
						
						
		        };
		
		    $.ajax({
		    //几个参数需要注意一下
		   	    data: bao,
		        type: "POST",//方法类型
		        dataType: "json",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
		        url: "<%=basePath%>HasExpired" , 
		        beforeSend: function (xhr) {  
		            xhr.setRequestHeader("Access-Control-Request-Method", "POST");  
		            xhr.setRequestHeader("Access-Control-Request-Headers", "content-type");  
		            xhr.setRequestHeader("Origin", "http://localhost:6667");  
		            xhr.setRequestHeader("Content-Type", "application/json");  
		        }, 
		        contentType:"application/x-www-form-urlencoded; charset=utf-8",
		        success: function (data) {   //result就是返回的String或json
		        	alert(data); 
		        },
		        error : function() {
		            alert("错的");
		        }
		    });
		}
        function test1() {
        	var bao = {
        			
        				
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "GET",//方法类型
                dataType: "json",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>getAllEquBaseInf" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {   //result就是返回的String或json
                	var objs=eval(data);
                	alert("ok"); 
                	
                	alert(data.list[0].equName); 
                	alert(data.list[0].img); 
                	alert("ok1"); 
                	var element = document.getElementById('img');
                	
                    element.src =data.list[0].img;  
                
                },
                error : function() {
                    alert("错的");
                }
            });
        }
        
        function test2() {
        	var code=$("#code").val()
        	var bao = {
        				
        				"code" : code,
        				
        				
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>getSuApl" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {   //result就是返回的String或json
                	              
                },
                error : function() {
                    alert("错的");
                }
            });
        }
        
        function test3() {
        	var bao = {
        			"loginName" : "test1",
        				
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>getAllSuApl" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {   //result就是返回的String或json
                	alert(data); 
                
                },
                error : function() {
                    alert("错的");
                }
            });
        }
        function test4() {
        	var bao = {
        			"equId" : "2",
        			
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>getRecordByEqu" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {   //result就是返回的String或json
                	alert(data); 
                
                },
                error : function() {
                    alert("错的");
                }
            });
        }
        function test5() {
        	var bao = {
        			"loginName" : "test1",

        				
                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>getRecordByUser" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {   //result就是返回的String或json
                	alert(data); 
                
                },
                error : function() {
                    alert("错的");
                }
            });
        }
        function test6() {
        	var code=$("#code").val()
        	var bao = {
        			"code" : code,

                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>outEqu" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {   //result就是返回的String或json
                	alert(data); 
                
                },
                error : function() {
                    alert("错的");
                }
            });
        }
        
        function test7() {
        	var code=$("#code").val()
        	var bao = {
        			"equId" : "2",
        			"remark" : "无",

                };
     
            $.ajax({
            //几个参数需要注意一下
           	    data: bao,
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型    //有text 和json两种类型，一个是从后端返回String类型  一个是从后端返回json类型
                url: "<%=basePath%>inEqu" , 
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {   //result就是返回的String或json
                	alert(data); 
                
                },
                error : function() {
                    alert("错的");
                }
            });
        }
        
        
    </script>
<title>Insert title here</title>
</head>
<body>
	<p><input type="button" value="登录" onclick="login()"></p>
	<p>将要审核的id<input name="cheakId" type="text" id="cheakId" value=""/>
	    <input type="button" value="cha" onclick="test1()"></p>
	
    <p>提货码查询<input name="code" type="text" id="code" value=""/>
   		 <input type="button" value="提货码查询" onclick="test2()"></p>
   		 
    <p><input type="button" value="查询该用户全部已成功申请记录" onclick="test3()"></p>
    <p><input type="button" value="根据设备查记录" onclick="test4()"></p>
    <p><input type="button" value="根据用户查记录" onclick="test5()"></p>
    <p><input type="button" value="出库" onclick="test6()"></p> 
    <p><input type="button" value="入库" onclick="test7()"></p>
    <p><img alt="" src="" id="img"></p>
    
</body>
</html>