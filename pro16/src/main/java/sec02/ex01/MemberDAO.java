package sec02.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public MemberDAO() {
		try {    
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean overlappedID(String id){
		boolean result = false;
		try {
			con = dataFactory.getConnection();
			// decode : 검색해서 1개있으면 참일때 true, 거짓일때 false반환 
//			String query = "select decode(count(*),1,'true','false') as result from t_member";
			
			// db의 기본키는 중복이 될 오류가 발생하지는 않아서 위의 코드도 맞음
			// id 찾았을때 없으면 false, 있으면 true
			// false와 true는 문자열형태
			String query = "select decode(count(*),0,'false','true') as result from t_member";
			query += " where id=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			// result에 받아온 문자열 형태의 false, true를 boolean로 변환
			result =Boolean.parseBoolean(rs.getString("result"));
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
}
