package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/get")
public class GetAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ServletContext ctx = getServletContext();  
		HttpSession sess = request.getSession();    
		
		String ctxMesg = (String) ctx.getAttribute("context");
		String sesMesg = (String) sess.getAttribute("session");
		String reqMesg = (String) request.getAttribute("request");

		out.print("context값 : " + ctxMesg + "<br>");	// ServletContext 프로젝트 모든 서브릿 불러옴
		out.print("session값 : " + sesMesg + "<br>");	// Session 브라우저를 끄고 get 또는 다른브라우저 get하면 못불러옴
		out.print("request값 : " + reqMesg + "<br>");	// request는 서브릿 다르면 못불러와서 null값

	}

}
