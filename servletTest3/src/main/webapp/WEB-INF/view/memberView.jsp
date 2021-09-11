<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$(function(){
	
 $('#memListBtn').on('click',function(){
	 $('#memberForm').attr("action","<%=request.getContextPath()%>/member/memberList.do")
 });
 
 $('#deleteBtn').on('click',function(){
	 $('#memberForm').attr('method','get');
	 $('#memberForm').attr("action","<%=request.getContextPath()%>/member/memberDelete.do")
	 $('#memberForm').submit(); // form데이터 전송
 });
 
 $('#updateBtn').on('click',function(){
	 $('#memberForm').attr('method','post');
	 $('#memberForm').attr("action","<%=request.getContextPath()%>/member/memberUpdate.do")
	 $('#memberForm').submit(); // form데이터 전송
 	});
});
</script>


<%
	MemberVO memvo = (MemberVO)request.getAttribute("MemberVO");
%>
	<h2>회원 정보 상세 보기</h2>
	<form method="post" action="" id="memberForm" action="">
	<input type="hidden" name="mem_id" value="<%=memvo.getMem_id()%>">
	<table border="1">
		<tr>
			<td>회원 ID</td> 
			<td> <%=memvo.getMem_id()%>
			</td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td> <%=memvo.getMem_pass()%>
			</td>
		</tr>
		<tr>
			<td>회원 이름</td>
			<td><%=memvo.getMem_name()%>
			</td> 
		</tr>
		
		<tr>
			<td>전화번호</td>
			<td> <%=memvo.getMem_tel()%>
			</td>
		</tr>
		<tr>
			<td>회원주소</td>
			<td> <%=memvo.getMem_addr()%>
			</td>
		</tr>
<tr>
	<td colspan="2" style="text-align:center;">
		<input type="button" id="updateBtn" value="수정"/>
		<input type="button" id="deleteBtn" value="삭제"/>
		<input type="button" id="membListBtn" value="회원목록"/>
	</td>
</tr>
	</table>
	</form>
</body>
</html>