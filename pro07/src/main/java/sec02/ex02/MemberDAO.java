package sec02.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;		// 인터페이스
import javax.naming.InitialContext;	// 구현 클래스- naming 작동을 위한 context 시작 클래스
import javax.sql.DataSource;

public class MemberDAO {

	
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers() {	// 제네릭<MemberVO> 생략해도됨
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			con = dataFactory.getConnection();	// DataSource를 이용해 DB에 연결		
			String query = "select * from t_member ";
			System.out.println(query);
			pstmt = con.prepareStatement(query);		// 쿼리문을 미리 번역
			ResultSet rs = pstmt.executeQuery(query);		// sql문으로 회원정보 조회
			while (rs.next()) {
				String id = rs.getString("id");			// 조회한 레코드의 각 컬럽값 받아옴
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);					// 받아온 컬럼값 MemberVO객체에 설정
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);			// ArrayList에 설정한 MemberVO객체 저장
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;					// 조회한 레코드의 개수만큼 MemberVO 객제를 저장한 ArrayList 반환
	}
	
	public void addMember(MemberVO memberVO) {
		try {
			con = dataFactory.getConnection();
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			String query = "insert into t_member";	// 데이터 삽입
			query += " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			// 0부터 시작안하고 1부터 시작
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			pstmt.setString(3,name);
			pstmt.setString(4,email);
			// 작성시간은 없어도됨
			pstmt.close();	// 데이터 추가는 1개시스템만 닫음
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

