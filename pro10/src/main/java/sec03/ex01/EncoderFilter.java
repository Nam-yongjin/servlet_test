package sec03.ex01;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/*")
public class EncoderFilter implements Filter {
	ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("utf-8 인코딩............");
		context = fConfig.getServletContext();
	} 

	// doFilter(ServletRequest request... 에서 Http가 빠져서 다운캐스팅해야함
	// Servlet을 호출하기전 필터를 이용해 인코딩을 해준다 >>> 서브릿마다 인코딩을 할 필요가 없어짐
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter 호출");
		request.setCharacterEncoding("utf-8");
		
//		형변환(캐스팅) == 
//		String context = request.getContextPath(); 
//		String url = request.getRequestURL().toString();
//		String mapping = request.getServletPath();
//		String uri = request.getRequestURI(); 
		String context = ((HttpServletRequest) request).getContextPath();
		String pathinfo = ((HttpServletRequest) request).getRequestURI();
		String realPath = request.getServletContext().getRealPath(pathinfo);
		
		String mesg = " Context  정보:" + context + "\n URI 정보 : " + pathinfo + "\n 물리적 경로:  " + realPath;
		System.out.println(mesg);

		chain.doFilter(request, response); // 다음 필터로 넘기는 작업을 수행한다. 다음 필터가 없으면 서블릿 수행
	}

	public void destroy() {
		System.out.println("destroy 호출");
	}

}
