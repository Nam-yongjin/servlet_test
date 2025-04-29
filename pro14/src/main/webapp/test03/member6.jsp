<%@ page language="java" contentType="text/html; charset=UTF-8"
     import="java.util.*"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  List dataList=new ArrayList();
  dataList.add("hello");
  dataList.add("world");
  dataList.add("안녕하세요!!");
%>
<c:set  var="list"  value="<%=dataList  %>" /> <%-- EL에서 사용할 수 있도록 변수에 dataList를 할당 --%>  
<html>
<head>
<meta charset=”UTF-8">
<title>반복문 실습</title>
</head>
<body>

	<%-- for문과 같은 역할 --%>
   <c:forEach  var="i" begin="1" end="10" step="1"  varStatus="loop">	<%-- loop : 상태 체크용 --%>
    i=  ${i}   &nbsp;&nbsp;&nbsp;  반복횟수: ${loop.count} <br>
   </c:forEach>
 <br>
   <c:forEach  var="i" begin="1" end="10" step="2" >
        5 * ${i} = ${5*i}<br>
   </c:forEach>
<br>
   <c:forEach  var="data" items="${list}" >		<%-- default값은 0부터시작, 마지막까지출력, 1만큼증가 --%>
       ${data } <br> 
   </c:forEach>
<br>
<c:forEach  var="data" items="${list}" begin="1">		
       ${data } <br> 
   </c:forEach>
<br>

<c:set var="fruits" value="사과, 파인애플, 바나나, 망고, 귤"  />
<c:forTokens  var="token" items="${fruits}" delims="," >	<%-- , 를 구분자로 사용하여 토큰으로 나누어 출력 --%>
    ${token} <br> 
</c:forTokens>
</body>
</html>
