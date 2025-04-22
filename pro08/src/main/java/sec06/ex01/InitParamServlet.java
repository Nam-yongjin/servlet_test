package sec06.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ServletConfig: ServletContext 객체를 얻는 기능, 서블릿에 대한 초기화 작업 기능

@WebServlet(
		// 매핑 2개이상 가능
		urlPatterns = { 
				"/sInit", 
				"/sInit2"
		}, 
		// 매개변수 설정
		initParams = { 
				@WebInitParam(name = "email", value = "admin@jweb.com"), 
				@WebInitParam(name = "tel", value = "010-1111-2222")
		})
public class InitParamServlet extends HttpServlet {
	// 모든 Class는 UID를 가지고 있는데 Class의 내용이 변경되면 UID값 역시 같이 바뀌어 버립니다. 
	// 직렬화하여 통신하고 UID값으로 통신한게 정상인지 확인하는데 그 값이 바뀌게 되면 다른 Class로 인식을 해버리게 된다. 
	// 이를 방지하기 위해 고유값으로 미리 명시를 해주는 부분이 바로 "private static final long serialVersionUID" 이 부분이다.
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		
		out.print("<html><body>");
		out.print("<table><tr>");
		out.print("<td>email: </td><td>" + email + "</td></tr>");
		out.print("<tr><td>휴대전화: </td><td>" + tel + "</td>");
		out.print("</tr></table></body></html>");

	}

}
