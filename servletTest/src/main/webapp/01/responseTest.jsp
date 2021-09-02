<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2> forward, sendRedirect 연습</h2>
<hr>

<form method="post" action="<%=request.getContextPath()%>/ResponseTest01.do">
	forward 방식 : <input type="text" name="username">
	<input type="submit" value="확인">
</form>

	<br>
	<hr>
	<br>

<form method="post" action="<%=request.getContextPath()%>/ResponseTest02.do">
	sendRedirect 방식 : <input type="text" name="username">
	<input type="submit" value="확인">
</form>

</body>
</html>