package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/get")
public class GetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
	    PrintWriter out=response.getWriter();
	    
	    Cookie[] allValues=request.getCookies();
	    // 쿠키와 같이 한글을 표현하지 못하는 경우 한글을 ASCII값으로 인코딩해줘야 한다.
	    // 이때 URLEncoder 클래스와 URLDecoder 클래스를 사용
	    for(int i=0; i<allValues.length; i++){
	    	if(allValues[i].getName().equals("cookieTest")){
	    		out.println("<h2>Cookie 값 가져오기 : "+URLDecoder.decode(allValues[i].getValue(),"utf-8"));
	    	}
	    }
	}

}
