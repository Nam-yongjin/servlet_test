package practice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/practice")
public class Practice extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

// 서브릿은 메인 메서드 안씀
// import 자동추가 ctrl + shift + o
// servlet 파일로 생성
// web.xml 에 매핑할 필묘없이 생성
//
//
// html에서 method="get" 이면 doGet이 받고 action="login" 이면 매핑을 login으로 만듦
// get 은 아이디와 비밀번호가 노출
// http://localhost:8090/pro06/login?user_id=admin&user_pw=asdf123

// <form> 태그로 전송된 데이터를 받아 오는 메서드
// String getParameter(String name) : name의 값을 알고 있을 때 그리고 name에 대한 전송된 값을 받아오는 데 사용합니다.
// String[] getParameterValues(String name) : 같은 name에 대해 여러 개의 값을 얻을 때 사용합니다.
// Enumeration getParameterNames() : name 값을 모를 때 사용합니다.
//

// ConnectionPool >> 서버가 외부 db에 연결하려면 오래걸리기 때문에 미리 연결하는 객체
//
//
// 쿠키(Cookie) : 웹 페이지들 사이의 공유 정보를 클라이언트 PC에 저장해 놓고 사용하는 방법
// 정보가 클라이언트 PC에 저장됨
// 저장 정보 용량에 제한이 있음(파일 용량은 4kb)
// 보안이 취약함
// 클라이언트 브라우저에서 사용 유무를 설정할 수 있음
// 도메인당 쿠키가 만들어짐(웹 사이트당 하나의 쿠키)

//	
// 세션(Session) : 웹 페이지들 사이의 공유 정보를 서버의 메모리에 저장해 놓고 사용하는 방법
// 정보가 서버의 메모리에 저장
// 브라우저의 세션 연동은 세션 쿠키를 이용함
// 쿠키보다 보안에 유리
// 서버에 부하를 줄 수 있음
// 브라우저(사용자)당 한 개의 세션(세션 id)이 생성됨
// 세션은 유효 시간을 가짐(기본 유효 시간은 30분)		// 활동이 없을때
// 로그인 상태 유지 기능이나 쇼핑몰의 장바구니 담기 기능 등에 주로 사용됨

//
// JSP(일종의 서브릿) >> Java >> Class >> HTML 로 변환
//
// JSP파일이 이유없는 에러 발생시 JSP 맨 위라인을 엔터 쳐서 공백만든후 다시 삭제하고 실행하기
//
// 선언 : <%! 멤버변수/메서드 선언 %>
// 출력 : <%=변수명/메서드명 %>
// 주석 : <%-- 내용 --%>
//
//
// Core 태그 라이브러리 기능 : 자바로 구현한 변수 선언, 조건식 ,반복문 기능등을 태그로 대체함
// 사용 전 반드시 taglib 디렉티브 태그를 선언해야 함.<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

// 
//
//
//
//
//
