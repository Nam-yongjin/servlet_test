package sec02.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;
	
	public void init() throws ServletException {
		memberDAO = new MemberDAO();	// 실행할때 MemberDAO 미리 실행
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;		// 다음 페이지 주소 담을 변수
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	// 한글인코딩
		
		String action = request.getPathInfo(); 		// 넘어온 페이지의 주소를 받음
		System.out.println("action:" + action);
		
		if (action == null || action.equals("/listMembers.do")) {
			List<MemberVO> membersList = memberDAO.listMembers();	// DAO로 받아온 데이터 list형식으로 받음
			// 요청해서 받은 memberList 데이터를 같은이름인 "memberList"변수에 set
			request.setAttribute("membersList", membersList);
			
			nextPage = "/test02/listMembers.jsp";		// 해당 페이지로 이동
			
		} else if (action.equals("/addMember.do")) {	
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			
			nextPage = "/member/listMembers.do";		// 회원 등록후 다시 회원 조회
			
		} else if (action.equals("/memberForm.do")) {
			
			nextPage = "/test02/memberForm.jsp";
			
		} else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			
			nextPage = "/test02/listMembers.jsp";
			
		}
		// nextPage에 dispatch로 전달
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);	// dispatch로 포워딩

	}

}
