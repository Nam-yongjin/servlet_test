package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	
	// 바인딩: 웹 프로그램 실행 시 자원(데이터)를 서블릿 관련 객체에 저장하는 방법
	// 저장된 자원은 프로그램 실행 시 서블릿이나 JSP에서 공유해서 사용
	
	// dispatch 포워딩시 바인딩은 같은 first와 second가 객체에서 저장하고 가져온다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		request.setAttribute("address", "서울시 성북구");
		RequestDispatcher dispatch = request.getRequestDispatcher("second");
		dispatch.forward(request, response);

	}

}
