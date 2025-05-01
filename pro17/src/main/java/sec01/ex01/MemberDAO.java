package sec01.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;		// 데이터베이스 커넥션을 생성하는 팩토리 역할을 하는 객체(연결생성객체/공급자)
	private Connection conn;			// db의 실제 연결 객체(실제연결세션)
	private PreparedStatement pstmt;	// SQL 문을 미리 컴파일하고 실행할 수 있게 해주는 객체(실행준비)
	
	// JNDI를 사용하여 데이터베이스 연결을 위한 DataSource 객체를 가져오는 과정
	public MemberDAO() {
		try {
			Context ctx = new InitialContext(); 	// JNDI의 시작점인 초기 컨텍스트 객체를 생성
			// 애플리케이션 환경(Context)의 하위 컨텍스트를 가져옴
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			// JNDI에 등록된 이름 "jdbc/oracle"로 DataSource 객체를 조회하고 할당
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// db에서 MemberVO 이용해서 데이터 받아올 list
	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList();
		try {
			// db에 연결
			conn = dataFactory.getConnection();
			String query = "select * from  t_member order by joinDate desc";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate);
				membersList.add(memberVO);	// db데이터 list에 저장
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersList;
	}

	// 회원가입 (db에 데이터 삽입)
	public void addMember(MemberVO m) {
		try {
			conn = dataFactory.getConnection();
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String email = m.getEmail();
			// VALUES의 ?에 각각 데이터 들어감
			String query = "INSERT INTO t_member(id, pwd, name, email)" + " VALUES(?, ? ,? ,?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);		// 1번 자리에 id 
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
