package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// request.getParameter(”address”); 사용불가
		// 화면단에서 <form> 로 넘어오는 것이 아니기 때문
		String address = (String) request.getAttribute("address");
		out.println("<html><body>");
		out.println("주소:" + address);
		out.println("<br>");
		out.println("redirect를 이용한 바인딩 실습입니다.");
		out.println("</body></html>");
	}

}
