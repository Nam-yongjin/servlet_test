package sec01.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/mem.do")
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	// 한글인코딩
		
		List<MemberVO> membersList = memberDAO.listMembers();	// DAO로 받아온 데이터 list형식으로 받음
		request.setAttribute("membersList", membersList);	// 요청한 데이터 set 저장
		// listMembers에 dispatch로 전달
		RequestDispatcher dispatch = request.getRequestDispatcher("/test01/listMembers.jsp");
		dispatch.forward(request, response);	// dispatch로 포워딩
	}

}
