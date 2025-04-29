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
<h1>여러 가지 논리 연산자</h1>
<h1>
	<!-- 논리연산ㄴ이 참일시 true -->
	<!-- and : && -->
	\${(10==10)&&(20==20)}: ${(10==10)&&(20==20)}<br>	
	\${(10==10) and (20!=20)}: ${(10==10) and (20!=20)}<br>
	
	<!-- or : || -->
	\${(10==10) || (20!=30)}: ${(10==10) || (20!=30)}<br>
	\${(10!=10) or (20!=20)}: ${(10!=10) or (20!=20)}<br>
	
	<!-- not : ! -->
	\${!(20==10)}: ${!(20==10)}<br>
	\${not (20==10)}: ${not (20==10)}<br>
	
	<!--  -->
	\${!(20!=10)}: ${!(20!=10)}<br>
	\${not(20!=10)}: ${not(20!=10)}<br>

</body>
</html>