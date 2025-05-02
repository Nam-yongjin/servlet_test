package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/*")
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
		
		String action = request.getPathInfo();
		System.out.println("action:" + action);

		if (action == null || action.equals("/listMembers.do")) {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
			
		} else if (action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			request.setAttribute("msg", "addMember");		// 회원가입완료시 msg에 'addMember'문자열을 담아 전송
			nextPage = "/member/listMembers.do";
			
		} else if (action.equals("/memberForm.do")) {		// 회원가입창으로 이동
			nextPage = "/test03/memberForm.jsp";
			
		} else if(action.equals("/modMemberForm.do")){		// 회원정보 수정
		    	 String id=request.getParameter("id");
		    	 MemberVO memInfo = memberDAO.findMember(id);  // 수정할 회원정보 가져오는 findMember
		    	 request.setAttribute("memInfo", memInfo);
		    	 nextPage="/test03/modMemberForm.jsp";		//회원정보 수정 창으로 이동
		    	 
		} else if(action.equals("/modMember.do")){
		    	 String id=request.getParameter("id");
		    	 String pwd=request.getParameter("pwd");
		    	 String name= request.getParameter("name");
	             String email= request.getParameter("email");
		    	 MemberVO memberVO = new MemberVO(id, pwd, name, email);
		    	 memberDAO.modMember(memberVO);
		    	 
		    	 request.setAttribute("msg", "modified");		// 회원정부 수정 완료시 'modified'문자열을 전송
		    	 nextPage="/member/listMembers.do";

		}else if(action.equals("/delMember.do")){		// 회원정보 삭제
		     String id=request.getParameter("id");
		     memberDAO.delMember(id);
		     request.setAttribute("msg", "deleted");
		     nextPage="/member/listMembers.do";
		     
		}else {
		     List<MemberVO> membersList = memberDAO.listMembers();
		     request.setAttribute("membersList", membersList);
		     nextPage = "/test03/listMembers.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}


}
