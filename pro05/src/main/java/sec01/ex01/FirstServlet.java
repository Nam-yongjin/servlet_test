package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서브릿은 메인 메서드 안씀
// import 자동추가 ctrl + shift + o
public class FirstServlet extends HttpServlet {
	//한번 호출시 다음은 호출안됨 (서브릿은 스레드 방식)
	public void init() throws ServletException{
		System.out.println("init 메서드 호출");
	}
	
	// 요청마다 계속갱신
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("doGet 메서드 호출");
	}
	
	// 코드 수정 갱신시 호출
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}
}
