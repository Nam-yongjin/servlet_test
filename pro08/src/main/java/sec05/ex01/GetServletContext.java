package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.e;

// set으로 값넣은후 get으로 불러옴
@WebServlet("/cget")
public class GetServletContext extends HttpServlet {
	
	// ServletContext: 서블릿과 컨테이너 간의 연동을 위해 사용, 컨테이너 실행 시 생성되고 컨테이너 종료 시 소멸됨
	// 프로젝트 종료 전에는 공유되는 자원 >> 크롬에서 엣지로 바꿔도 유지
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();

	    ServletContext context = getServletContext();
	    List member = (ArrayList)context.getAttribute("member");
	    String name = (String)member.get(0);	// 이순신
	    int age = (Integer)member.get(1);   // 30
	    out.print("<html><body>");
	    out.print(name +"<br>");
	    out.print(age + "<br>");
	    out.print("</body></html>");

	}

}
