<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#lprodBtn').on("click",function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/lprod/lprodList.do",
			type : "post", 
			// data는 생략
			success : function(data){
				let htmlCode = "<table border='1'>";
				htmlCode += "<tr><td>LPROD_ID</td><td>LPROD_GU</td><td>LPROD_NM</td></tr>";
				$.each(data, function(i,v){
					htmlCode += "<tr><td>" + v.lprod_id + "</td>";
					htmlCode += "<td>" + v.lprod_gu + "</td>";
					htmlCode += "<td>" + v.lprod_nm + "</td></tr>";
				});
				htmlCode += "</table>";
				
				$("#result").html(htmlCode);
			},
			dataType : "json"
		});
	});
	
	$("#lprodBtn2").on("click",function(){
		location.href = "<%=request.getContextPath()%>/lprod/lprodList2.do";
	});
});
</script>
</head>
<body>
	<form action="">
		<input type="button" id="lprodBtn" value="Lprod자료 가져오기">
		<input type="button" id="lprodBtn2" value="Lprod자료 가져오기">
	</form>
	<h2>Lprod 자료 목록</h2>
	<div id="result"></div>
</body>
</html>