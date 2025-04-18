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

// ConnectionPool >> 서버가 외부 db에 연결하려면 오래걸리기 때문에 미리 연결하는 객체
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
