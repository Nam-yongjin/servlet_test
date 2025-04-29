<%@ page language="java" contentType="text/html; charset=UTF-8"
     import="java.util.Date"
     pageEncoding="UTF-8"     
     isELIgnored="false" %>
     
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%   request.setCharacterEncoding("UTF-8");  %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포매팅 태그 라이브러리 예제</title>
</head>
<body>
	<h2>fmt의 number 태그를 이용한 숫자 포맷팅 예제</h2>
	<c:set var="price" value="100000000"/>
	<fmt:formatNumber value="${price }" type="number" var="priceNumber" />
	
	<%-- currency : 통화 형식, currencySymbol : 통화 기호 --%>
	<%-- groupingUsed : 1000 단위 마다 콤마( , )를 적용 --%>
	통화로 표현시 : <fmt:formatNumber type="currency" currencySymbol="￦" value="${price }" groupingUsed="true"/><br>
	
	<%-- type="percent" : 백분율로 표시 --%>
	퍼센트로 표현시 : <fmt:formatNumber value="${price}" type="percent"   groupingUsed="false" /><br>
	
	<%-- 위에서 포맷한 숫자 (type="number") 출력됨 --%>
    일반 숫자로 표현 시 : ${priceNumber}<br>

	<h2>formatDate 예제</h2> 
	<%-- 현재 시각을 now에 저장, (스크립틀릿 <%=new Date()%>로 객체 생성 → EL에 넘김 --%>
    <c:set  var="now" value="<%=new Date() %>" />
    
    <%-- full : 년-월-일-요일, short : 년.월.일 --%>
    <fmt:formatDate  value="${now }"  type="date" dateStyle="full"/><br>
    <fmt:formatDate  value="${now }" type="date" dateStyle="short" /><br>
    
    <%-- time : 시간만 출력, type="both" dateStyle="full" timeStyle="full" : 날짜, 시간 모추출력 --%>
    <fmt:formatDate  value="${now }" type="time" /><br>
    <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"  /><br>
    
    <%-- 커스텀 패턴 포맷 --%>
    <fmt:formatDate  value="${now }" pattern="YYYY-MM-dd :hh:mm:ss" /><br>
     
    <br><br>
    한국 현재 시간:
    <fmt:formatDate  value="${now }" type="both" dateStyle="full"  timeStyle="full"/><br><br>

    <fmt:timeZone  value="America/New York" >
    뉴욕 현재 시간:<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br>
    </fmt:timeZone>

</body>
</html>