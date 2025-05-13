package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

// MultiActionController를 상속받아 하나의 컨트롤러 클래스에 여러 메서드를 정의
// 이 메서드는 /test/login.do 요청 시 실행
public class UserController extends MultiActionController {
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userID = "";
		String passwd = "";
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
		String viewName = getViewName(request);
		
		// 폼(form)에서 전송된 userID와 passwd 값을 가져옴
		userID = request.getParameter("userID");
		passwd =  request.getParameter("passwd");
		
		// JSP에 전달할 데이터를 ModelAndView 객체에 담음
		mav.addObject("userID", userID);
		mav.addObject("passwd", passwd);
		
		// 결과를 보여줄 뷰 이름을 설정 → /test/result.jsp
		// setViewName를 사용해 login.do에서 login만 때어내어 읽어 같은login인 login.jsp로 이동
		mav.setViewName(viewName);
		System.out.println("ViewName: "+viewName);
		
		// 최종적으로 ModelAndView를 리턴하여 뷰로 이동
		return mav;
	}

	// /test/memberInfo.do요청
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		mav.addObject("id", id);
		mav.addObject("pwd", pwd);
		mav.addObject("name", name);
		mav.addObject("email", email);
		mav.setViewName("memberInfo");
		return mav;
	}

	private  String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
		if(uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
		
		int begin = 0;
		if(!((contextPath==null) || ("".equals(contextPath)))) {
			begin = contextPath.length();	// 전체 요청명의 길이를 구함
		}
		
		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";"); 	// uri에 ;이 있을 경우 ;문자 위치를 구함
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?"); 	// uri에 ?가 있을 경우 ?문자 위치를 구함
		} else {
			end = uri.length();
		}
		
		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			// 요청명에서 역순 최초 .위치를 구한 후 .do앞에까지의 문자열 구함
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			// 요청명에서 역순 최초 /위치를 구한 후 / 다음부터의 문자열을 구함
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());			
		}
		return fileName;

	}
}
