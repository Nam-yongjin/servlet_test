package sec01.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "system";		// 계정 정보
	private static final String pwd = "12345";
	private Connection con;
	private PreparedStatement pstmt;
	public List<MemberVO> listMembers() {	// 제네릭<MemberVO> 생략해도됨
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			connDB();		// 위 네가지 정보로 데이터베이스 연결
			String query = "select * from t_member ";
			System.out.println(query);
			pstmt = con.prepareStatement(query);		// 쿼리문을 미리 번역
			ResultSet rs = pstmt.executeQuery(query);		// sql문으로 회원정보 조회(select문 나머지 executeUpdate())
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
	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
//			pstmt = con.createStatement();			// 쿼리문을 위에서 미리 번역하여 필요없음
//			System.out.println("Statement 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

