<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%
// 세션 정보를 가져온다. ==> JSP문서에서는 HttpSession객체는 session이라는 이름으로 만들어져 있다.
// 해당 세션의 'key값'이 없으면 null이 반환된다.
String userId = (String) session.getAttribute("id");
%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	if (userId == null) { //로그인이 안되었을 때
	%>
	<!-- 로그인이 되지 않았을 때 화면 구성 시작 -->
	<form action="<%=request.getContextPath()%>/sessionLogin.do"
		method="post">
		<table border="1">
			<tr>
				<td>ID :</td>
				<td><input type="text" placeholder="ID를 입력하세요" name="id">
				</td>
			</tr>
			<tr>
				<td>PASS :</td>
				<td><input type="password" placeholder="PASSWORD를 입력하세요"
					name="pass"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="Login"></td>
			</tr>
		</table>
	</form>
	<!-- 로그인이 되지 않았을 때 화면 구성 끝 -->
	<%
	} else {
	%>
	<!-- 로그인이 성공했을 때 화면 구성 시작 -->
	<h3> <%=userId%>님 반갑습니다. </h3>
	<br>
	<a href="<%=request.getContextPath()%>/sessionlogOut.do"> Logout</a>
	<%
	}
	%>
	<!-- 로그인이 성공했을 때 화면 구성 끝 -->
</body>
</html>