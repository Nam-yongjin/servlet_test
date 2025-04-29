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
<h1>여러 가지 비교 연산자</h1>
<h1>
	<!-- eq : == -->
	\${10==10}: ${10==10}<br>	
	\${10 eq 10}: ${10 eq 10}<br>
	\${"hello"=="hello"}: ${"hello"=="hello"}<br>
	\${"hello" eq "hello"}: ${"hello" eq "hello"}<br>
	
	<!-- ne : != -->
	\${20!=10}: ${20!=10}<br>
	\${20 ne 10}: ${20 ne 10}<br>
	\${"hello"!="apple"}: ${"hello"!="apple"}<br>
	\${"hello" ne "apple"}: ${"hello" ne "apple"}<br>
	
	<!-- lt : < -->
	\${10<10}: ${10<10}<br>
	\${10 lt 10}: ${10 lt 10}<br>
	
	<!-- gt : > -->
	\${100>10}: ${100>10}<br>
	\${100 gt 10}: ${100 gt 10}<br>
	
	<!-- le : <= -->
	\${100<=10}: ${100<=10}<br>
	\${100 le 10}: ${100 le 10}<br>
	
	<!-- ge : >= -->
	\${100>=10}: ${100>=10}<br>
	\${100 ge 10}: ${100 ge 10}<br>
</h1>

</body>
</html>