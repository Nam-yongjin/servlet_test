<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인클루드 디랙티브</title>
</head>
<body>
<!-- 정적처리 : 하나의 파일에 다른파일이 끼어들어오는 방식 -->
	<h1>안녕하세요. 쇼핑몰 중심 JSP 시작입니다!!! </h1><br>
	<%@ include file="duke_image.jsp" %><br>
	<h1>안녕하세요. 쇼핑몰 중심 JSP 끝 부분입니다.!!!</h1>

</body>
</html>