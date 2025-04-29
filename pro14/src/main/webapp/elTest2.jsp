<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>	<!-- 기본값이 false이므로 안써도됨 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어의 여러 가지 연산자들</title>
</head>
<body>
<h1>여러 가지 산술 연산자</h1>
<h1>
	\${10+10}: ${10+10}<br>
	\${20-10}: ${20-10}<br>
	\${10*10}: ${10*10}<br>
	\${100/9}: ${100/9}<br>
	\${100 div 9}: ${100 div 9}<br>	<!-- div : 나누기 -->
	\${100%9}: ${100%9}<br>
	\${100 mod 9}: ${100 mod 9}<br>	<!-- mod : 나머지 -->
</h1>

</body>
</html>