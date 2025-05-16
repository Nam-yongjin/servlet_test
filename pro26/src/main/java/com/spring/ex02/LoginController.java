package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("loginController")
public class LoginController {
	
	@RequestMapping(value="/test/loginForm.do", method=RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}
	
	@RequestMapping(value="/test/login.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(@RequestParam("userID") String userID, 
			@RequestParam(value="userName", required=true) String userName, // 기본값 true
			@RequestParam(value="email", required=false) String email,  // false면 만드시 email이아니여도들어옴
							HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
//		System.out.println("userID: "+userID);
//		System.out.println("userName: "+userName);
//		System.out.println("email: "+email);
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		mav.addObject("email", email);
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping(value="/test/login3.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login3(@RequestParam Map<String, String> info, // 값이 몇개가 들어오든 받아옴
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
//		String userID = info.get("userID");
//		String userName = info.get("userName");
//		System.out.println("userID: "+userID);
//		System.out.println("userName: "+userName);
		mav.addObject("info", info);
		mav.setViewName("result");
		return mav;
	}
	
	// VO에 매개변수 값 설정
	@RequestMapping(value="/test/login4.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login4(@ModelAttribute("info") LoginVO loginVO, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
//		System.out.println("userID: "+loginVO.getUserID());
//		System.out.println("userName: "+loginVO.getUserName());
		mav.setViewName("result");
		return mav;
	}
	
	// Model 클래스 : ModelAndView보다 최신버전, 값을 직접넣어줘야할때, MAV리턴이 필요하지 않을때
//	Model 클래스를 이용하면 메서드 호출 시 JSP로 값을 바로 바인딩하여 전달할 수 있음
//	Model 클래스의 addAttribute() 메서드는 ModelAndView의 addObject() 메서드와 같은 기능 수행
//	Model 클래스는 따로 뷰 정보를 전달할 필요가 없을 때 사용하면 편리함
	@RequestMapping(value="/test/login5.do", method={RequestMethod.GET,RequestMethod.POST})
	public String login5(Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		model.addAttribute("userID", "hong");
		model.addAttribute("userName", "홍길동");
		return "result";
	}
	
}
