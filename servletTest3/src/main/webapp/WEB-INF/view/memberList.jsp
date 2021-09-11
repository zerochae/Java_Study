<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$(function(){
	$('#addBtn').on('click',function(){
		location.href = "<%=request.getContextPath()%>/member/memberInsert.do"
	});
});

</script>

<%
List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memberList");
%>

<title>회원 목록</title>
</head>
<body>

	<h2>회원 목록 보기</h2>
	<table border="1">
		<thead>
			<tr>
				<td colspan="5" style="text-align:right; padding-right: 3px;">
				<input type="button" value="회원추가" id="addBtn">
			</tr>
			<tr>
				<td>ID</td>
				<td>비밀번호</td>
				<td>이름</td>
				<td>전화</td>
				<td>주소</td>
			</tr>
		</thead>
		<tbody>

			<%
			for (MemberVO memvo : memList) {
			%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/member/memberView.do?mem_id=<%=memvo.getMem_id()%>"><%=memvo.getMem_id()%> </a></td>
				<td><%=memvo.getMem_pass()%></td>
				<td><%=memvo.getMem_name()%></td>
				<td><%=memvo.getMem_tel()%></td>
				<td><%=memvo.getMem_addr()%></td>
			</tr>

			<%
			}
			%>

		</tbody>
	</table>

</body>
</html>