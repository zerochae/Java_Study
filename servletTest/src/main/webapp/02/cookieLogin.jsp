<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	#inputid
	{
	
	 margin-left: 20px;
	
	}
	
	#submit
	{
		margin-left: 90px;
		
	}
	input[type="checkbox"]
	{
	width: 500px;
	height : 500px;
	}

</style>

<%

String id = "";

Cookie cookies[] = request.getCookies();

for(Cookie cookie : cookies){
	String name = cookie.getName();
	if(name.equals("id")){
		id = cookie.getValue();
	}
}

%>

</head>


<body>

	<form action="http://localhost:80/servletTest/CookieLoginServlet.do" method="post">
	
	<label>ID :   </label>
	   <input type="text" placeholder="id 입력하셈 ㅋㅋ" id='inputid' name = 'id' value="<%=id%>"> <br> 
	<label>PASS :</label>   
	<input type="password" placeholder="password 입력하셈 ㅋㅋ" name = 'pass'> <br>
		   <input type="checkbox" value="check" name = 'check'> id 기억하기 <br> <br>
		   <input type="submit" value="Login" id='submit'>
	
	</form>

</body>
</html>