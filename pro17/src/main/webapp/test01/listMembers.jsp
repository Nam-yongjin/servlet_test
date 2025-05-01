<%@ page language="java" contentType="text/html; charset=UTF-8"
	import=" java.util.*,sec01.ex01.*"
	pageEncoding="UTF-8"
	isELIgnored="false"%> 	<%-- EL문 사용 --%>
<%-- JSTL의 국제화 및 포맷팅(formatting) 태그를 사용 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%--  JSTL의 Core(핵심) 태그들을 사용하겠다는 선언 (ex  if, forEach, when, out) --%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");	// 한글인코딩
%>    
<html>
<head>
   <meta  charset="UTF-8">
   <title>회원 정보 출력창</title>
<style>
     .cls1 {
       font-size:40px;
       text-align:center;
     }
    
     .cls2 {
       font-size:20px;
       text-align:center;
     }
  </style>
  
</head>
<body>
 <p class="cls1">회원정보</p>
   <table align="center" border="1" >
      <tr align="center" bgcolor="lightgreen">
         <td width="7%" ><b>아이디</b></td>
         <td width="7%" ><b>비밀번호</b></td>
         <td width="7%" ><b>이름</b></td>
         <td width="7%"><b>이메일</b></td>
         <td width="7%" ><b>가입일</b></td>
   </tr>
<%-- c:choose == switch --%>
<c:choose>	
	<%-- c:when == if --%>	
    <c:when test="${ empty membersList}" >	<%-- 멤버list가 비어있다면 --%>
      <tr>
        <td colspan=5 align="center">
          <b>등록된 회원이 없습니다.</b>
       </td>  
      </tr>
   </c:when>  
   <c:when test="${!empty membersList }" >
   	  <%-- c:forEach == for --%>
      <c:forEach  var="mem" items="${membersList }" >	<%-- memberList값을 mem변수로 반복문돌림 --%>
        <tr align="center">
          <td>${mem.id }</td>
          <td>${mem.pwd }</td>
          <td>${mem.name}</td>     
          <td>${mem.email }</td>     
          <td>${mem.joinDate}</td>     
       </tr>
     </c:forEach>
</c:when>
</c:choose>
   </table>  
 <a href="#"><p class="cls2">회원 가입하기</p></a>	<%-- 구현 안된 부분 --%>
</body>
</html>
   