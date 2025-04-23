package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/show")
public class ShowMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        
        
        String id ="", pwd="" ;
        Boolean isLogon=false;
        HttpSession session =  request.getSession(false);	// 새로운 세션 생성 막음		

        if( session != null){  // 세션이 생성이 되어 있으면
	    isLogon=(Boolean)session.getAttribute("isLogon");
           if(isLogon==true){  // 로그인 상태이면
        	   
        	   id = (String)session.getAttribute("login.id");
               pwd = (String)session.getAttribute("login.pwd");
               
               MemberDAO dao = new MemberDAO();
               List list = dao.listMembers();
               
               request.setAttribute("list", list);
//      ==     list = (List) request.getAttribute("list");
                	   
        	   
	      	    out.print("<html><body>");

	      	  for(int i=0; i< list.size(); i++) {
                MemberVO memberVO = (MemberVO) list.get(i);
                if(id.equals(memberVO.getId())) {
                   String memberName = memberVO.getName(); 
                   out.println("<h1>" + memberName + " 님 환영!!!</h1>");
                   break;
                }
             }
	      	     
	      	     out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
	      	     out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>");
      	    
	      	     for (int i=0; i<list.size();i++){
	      	 		MemberVO memberVO=(MemberVO) list.get(i);
	      	 		id=memberVO.getId();
	      	 		pwd = memberVO.getPwd();
	      	 		String name = memberVO.getName();
	      	 		String email =memberVO.getEmail();
	      	 		Date joinDate = memberVO.getJoinDate();
	      	 		out.print("<tr><td>"+id+"</td><td>"
	      	 			                +pwd+"</td><td>"
	      	 			                +name+"</td><td>"
	      	 			                +email+"</td><td>"
	      	 			                +joinDate+"</td><td>"
	      	 			               +"<a href='/pro09/show?command=delMember&id="+id+"'>삭제 </a></td></tr>");			                 
	      	 	 }
	      	 	 out.print("</table></body></html>");
	      	 	 
      	 	 
	    }else{  // 로그인 상태가 아니면
	       response.sendRedirect("login3.html");
	    }
         }else{  // 세션이 생성되어 있지 않으면
            response.sendRedirect("login3.html");
        }
	}

	

}
