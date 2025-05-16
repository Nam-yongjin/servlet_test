package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.service.MemberServiceImpl;
import com.spring.member.vo.MemberVO;

public class MemberControllerImpl extends MultiActionController implements MemberController {
	private MemberService memberService;

	public void setMemberService(MemberServiceImpl memberService) {
		this.memberService = memberService;
	}

	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}

	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO = new MemberVO();
		bind(request, memberVO);
		int result = 0;
		result = memberService.addMember(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	@Override
	public ModelAndView modMember(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id=request.getParameter("id");	// 요청 파라미터에서 id를 받아옴
		MemberVO memberVO = memberService.selectMemberById(id);	// 서비스 계층을 통해 해당 아이디의 회원 정보를 조회
		String viewName = getViewName(request);	// 요청 URI로부터 뷰 이름을 추출 (예: modMember.jsp)
		ModelAndView mav = new ModelAndView();	// 조회한 회원 정보를 member라는 이름으로 JSP에 전달
		mav.addObject("member", memberVO);
		mav.setViewName(viewName);	// 위에서 구한 뷰 이름으로 이동
		return mav;
	}
	// setViewName : 서버가 포워드 방식으로 memberForm.jsp를 보여줍니다.
	// 브라우저 주소창은 그대로 (/member/modMember.do)
	// 주로 조회/폼 렌더링 시 사용 
	
	// redirect : 브라우저에게 새로운 요청을 보내도록 합니다.
	// 주소창이 /member/listMembers.do로 바뀜
	// 주로 수정, 삭제, 등록 후에 새로고침 방지 & URL 정리용으로 사용
	@Override
	public ModelAndView updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO = new MemberVO();	// 사용자가 폼에서 입력한 값을 MemberVO 객체에 자동으로 바인딩
		bind(request, memberVO);
		int result = 0;
		result = memberService.updateMember(memberVO);	// 바뀐 회원 정보를 DB에 저장
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do"); // 새로운주소창
		return mav;
	}
	
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}
		return fileName;
	}

}
