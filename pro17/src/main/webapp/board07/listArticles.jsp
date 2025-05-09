<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%-- HashMap으로 넘어온 값들은 이름이 길어서 c:set으로 짧은 변수이름으로 저장 --%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set  var="articlesList"  value="${articlesMap.articlesList}" />
<c:set  var="totArticles"  value="${articlesMap.totArticles}" />
<c:set  var="section"  value="${articlesMap.section}" />
<c:set  var="pageNum"  value="${articlesMap.pageNum}" />

<%    request.setCharacterEncoding("UTF-8");   %>  
<!DOCTYPE html>
<html> 
<head>
<style>
	.no-uline {text-decoration:none;}
   .sel-page{text-decoration:none;color:red;}	<%-- 선택된 페이지번호를 빨간색으로 표시 --%>

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
				                <a class='cls1' 
				                href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
					      	</c:when>
					      	<%-- 들여쓰기 없는 부모글 표시 --%>
					      	<c:otherwise>
					            <a class='cls1' 
					            href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
					     	</c:otherwise>
					    </c:choose>
				  	</td>
				  	<td width="10%"><fmt:formatDate value="${article.writeDate}" /></td> 
				</tr>
		    </c:forEach>
		</c:when>
    </c:choose>
</table>

	<div class="cls2">
		<%-- 페이징 넘버 --%>
		<%-- totArticles가 null이거나 0인 경우, 페이지 번호를 표시하지 않음 --%>
		<c:if test="${totArticles > 0}">
			<c:choose>
				<%-- 총 게시글 개수가 100 초과인경우 >> 11~20페이지 추가--%>
				<c:when test="${totArticles > 100 }">
					<c:forEach var="page" begin="1" end="10" step="1">
						<%-- pre : 이전섹션 이동 네비게이션 --%>
						<c:if test="${section > 1 && page == 1}">
							<a class="no-uline"
								href="${contextPath}/board/listArticles.do?section=${section - 1}&pageNum=${(section - 1) * 10}">
								pre </a>
						</c:if>
						<%-- 수정된 부분: pageNum에 올바른 값(절대적인 페이지 번호)을 설정 --%>
						<%-- 섹션번호*10 까지 네이게이션 생성 예) 2섹션 : 11~20 --%>
						
				<%-- 	<c:if
							test="${(section - 1) * 10 + page <= (totArticles + 9) / 10}">
							<a class="no-uline"
								href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${(section - 1) * 10 + page}">${(section - 1) * 10 + page}</a>
						</c:if> 
				--%>
				
						<%-- totArticles > 100인 경우에도 클릭한 페이지 번호에 sel-page 클래스를 적용 --%>
						<c:if
							test="${(section - 1) * 10 + page <= (totArticles + 9) / 10}">
							<c:choose>
								<c:when test="${(section - 1) * 10 + page == pageNum}">
									<a class="sel-page"
										href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${(section - 1) * 10 + page}">
										${(section - 1) * 10 + page} </a>
								</c:when>
								<c:otherwise>
									<a class="no-uline"
										href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${(section - 1) * 10 + page}">
										${(section - 1) * 10 + page} </a>
								</c:otherwise>
							</c:choose>
						</c:if>
						<%-- next : 다음섹션 이동 네비게이션 --%>
						<c:if test="${totArticles > 100 * section && page == 10}">
							<a class="no-uline"
								href="${contextPath}/board/listArticles.do?section=${section + 1}&pageNum=${section * 10 + 1}">
								next</a>
						</c:if>
					</c:forEach>
				</c:when>

			  	<%-- 등록된 글 개수가 100개인경우  10페이지만 표시 --%>
		<%--	<c:when test="${totArticles ==100 }">	
					<c:forEach var="page" begin="1" end="10" step="1">
						<a class="no-uline" href="#">${page } </a>
					</c:forEach>
				</c:when> 
				 --%>
				 
				<c:when test="${totArticles == 100}">
					<%-- totArticles == 100인 경우에도 클릭한 페이지 번호에 sel-page 클래스를 적용 --%>
					<c:forEach var="page" begin="1" end="10" step="1">
						<c:choose>
							<c:when test="${page == pageNum}">
								<a class="sel-page"
									href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">
									${page} </a>
							</c:when>
							<c:otherwise>
								<a class="no-uline"
									href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">
									${page} </a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>


				<c:when test="${totArticles< 100 }">
					<%-- 등록된 글 개수가 100개 미만인 경우 >> 전체글수를 10으로 나누어 1을 더한페이지까지 표시 --%>
					<c:forEach var="page" begin="1" end="${totArticles/10 +1}" step="1">
						<c:choose>
							<c:when test="${page==pageNum }">
								<a class="sel-page"
									href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }
								</a>
							</c:when>
							<c:otherwise>
								<a class="no-uline"
									href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }
								</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
			</c:choose>
		</c:if>

	</div>
	<%-- 새글 쓰기 창 --%>
	<a  class="cls1"  href="${contextPath}/board/articleForm.do"><p class="cls2">글쓰기</p></a> 
</body>
</html>
  