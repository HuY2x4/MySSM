<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--上传图片、文件的代码-->
	<form action="http://120.79.32.211:8080/KCGL810/addEqu"  method="post" enctype="multipart/form-data" >
	    <input type="file" id="imagePath">
	    <input type="text" id="equName">
	    <input type="text" id="type">
	    <input type="text" id="version">
	    <input type="text" id="price">
	    <input type="text" id="owner">
	    <input type="text" id="manager">
	    <input type="text" id="remark">
	    <input type="text" id="state">
 	   
 	   <input type="submit">
	</form>
	<!--接收-->
<!-- 	 <img src="image/1.png" width=100 height=100/> -->
	<img src="http://120.79.32.211:8080/KCGL810/static/image/1.png" width=100 height=100/> 
	
</body>
</html>