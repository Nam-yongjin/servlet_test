package sec03.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login2")
public class SessionTest4 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");

		if(session.isNew()) {	// 새션 새 요청시
			if(user_id != null) {	// 로그인 통해서
				session.setAttribute("user_id", user_id);
				out.println("<a href='login2'>로그인 상태 확인</a>");	// 서브릿 매핑 login으로 재요청
			}else {   // 새요청이면서 로그인 통하지 않고  (localhost:8090/pro09/login 했을 경우)
				out.print("<a href='login2.html'>다시 로그인 하세요!!</a>");
				session.invalidate(); // 세션 강제 삭제
			}

		}else{    //  재요청시
			user_id = (String) session.getAttribute("user_id");  // ***
			if (user_id != null && user_id.length() != 0) {
				out.print("안녕하세요 " + user_id + "님!!!");
			} else {    // 재요청이면서 로그인 통하지 않고  (localhost:8090/pro09/login 했을 경우)
				out.print("<a href='login2.html'>다시 로그인 하세요!!</a>");
				session.invalidate();
			}
		}

	}
	

}
