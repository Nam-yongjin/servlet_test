package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;	// java.sql.Date 와 다른 패키지임. >> 년,월,일 만 있음

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 쿠키 첫 생성시 서버에서 클라이언트에 쿠키생성요청, 생성된쿠키 서버로전송
// 재 방문시 서버가 가진 쿠키 확인후 접속
@WebServlet("/set")
public class SetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Date d = new Date();
	
		// 쿠키와 같이 한글을 표현하지 못하는 경우 한글을 ASCII값으로 인코딩해줘야 한다. 이때 URLEncoder 클래스와 URLDecoder 클래스를 사용
		Cookie c = new Cookie("cookieTest", URLEncoder.encode("JSP프로그래밍입니다.", "utf-8"));
		c.setMaxAge(24 * 60 * 60);
		//c.setMaxAge(-1);  //세션 쿠키를 생성합니다. -> 상위 코드를 주석 처리하고 현코드 주석 해지 후 진행
		response.addCookie(c);
		out.println("현재시간 : " + d);
		out.println("<br> 문자열을 Cookie에 저장합니다.");

	}

}
