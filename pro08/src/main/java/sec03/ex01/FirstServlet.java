package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	
	// dispatch방식 : 서브릿이 직접 다른서브릿orJSP에 요청 (일반적인 포워딩)
	// 화면클라이언트로 안넘어가기 때문에 url주소가 second로 넘어가지 않고 first 그대로인 상태
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();
	 // get으로 dispath방식 값넘기기
	    RequestDispatcher dispatch = request.getRequestDispatcher("second?name=lee");	
	    dispatch.forward(request, response);
	}

}
