<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	request.setAttribute("address", "서울시 강남구");	// member2.jsp 회원창의 request에 대해 다시 바인딩
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward</title>
</head>
<body>
<jsp:forward page="member3.jsp"></jsp:forward> <!-- 포워딩 -->
</body>
</html>