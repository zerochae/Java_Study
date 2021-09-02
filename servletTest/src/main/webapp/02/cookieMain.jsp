<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	'id 기억하기' 체크박스를 체크한 후 'Login'버튼을 클릭하면 입력했던 'ID'를
	쿠키에 저장하고 쿠키에 'ID'값이 저장되어 있으면 'ID'입력 창에 쿠키에 저장되었던
	'ID'가 나타나도록 하고 체크박스도 체크가 된 상태로 유지되도록 한다.
	
	체크박스를 해제한 후 로그인을 하면 쿠키에 저장된 'ID'를 삭제하고 체크박스도 해제된 상태가 되도록 한다.
	
	로그인 성공 ID와 Password는 'test'와 '1234'이다
	로그인이 성공하면 'cookieMain.jsp'로 이동하고 실패하면 'cookieLogin.jsp'
	실패하면 'cookieLogin.jsp'로 이동하도록 한다.
	
	로그인 처리를 하는 서블릿의 URL패턴은 '/cookieLoginServlet.do'로 한다.
 -->
 
 <h2> cookie 연습용 Main 페이지 입니다.</h2>
 <br>
 <br>
 
 <a href="<%=request.getContextPath()%>/02/cookieLogin.jsp">Login 창으로 이동</a> <br> <br>

</body>
</html>