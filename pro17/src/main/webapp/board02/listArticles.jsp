<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%    request.setCharacterEncoding("UTF-8");   %>  
<!DOCTYPE html>
<html> 
<head>
<style>
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
</style>
<meta charset="UTF-8">
<title>글목록창</title>
</head>
<body>
<table align="center" border="1"  width="80%"  >
	<tr height="10" align="center"  bgcolor="lightgreen">
		<td >글번호</td>
     	<td >작성자</td>              
     	<td >제목</td>
     	<td >작성일</td>
 	</tr>
	<c:choose>
	  	<c:when test="${articlesList ==null}">
	    	<tr  height="10">
	      		<td colspan="4">
	         	<p align="center">
	            	<b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
	        	</p>
	      		</td>  
	    	</tr>
	  	</c:when>
		<c:when test="${articlesList !=null}">
			<%-- 글 목록 표시 --%>
			<c:forEach var="article" items="${articlesList}" varStatus="articleNum">
		    	<tr align="center">
				    <%-- 글번호 태그를 DB번호와 다르게 1부터 다시 표시 --%>
					<td width="5%">${articleNum.count}</td>
					<td width="10%">${article.id }</td>
					<td align='left'  width="35%">
						<%-- 여백을 준 후 글제목 표시 --%>
					  	<span style="padding-right:30px"></span>
					   	<c:choose>
					   		<%-- level이 1보다 크면 자식글 >> LPAD 사용불가하니 대신 레벨만큼 들여쓰기 20px --%>
					      	<c:when test='${article.level > 1}'>  
						        <c:forEach begin="1" end="${article.level }" step="1">
						        	<span style="padding-left:20px"></span>    
						        </c:forEach>
					         	<span style="font-size:12px;">[답변]</span>
					         	<%-- 공백 다음 자식글 표시 --%>
				                <a class='cls1' href="#">${article.title}</a>
					      	</c:when>
					      	<%-- 들여쓰기 없는 부모글 표시 --%>
					      	<c:otherwise>
					            <a class='cls1' href="#">${article.title}</a>
					     	</c:otherwise>
					    </c:choose>
				  	</td>
				  	<td width="10%"><fmt:formatDate value="${article.writeDate}" /></td> 
				</tr>
		    </c:forEach>
		</c:when>
    </c:choose>
</table>
<%-- 새글 쓰기 창 --%>
<a  class="cls1"  href="${contextPath}/board/articleForm.do"><p class="cls2">글쓰기</p></a> 
</body>
</html>
  