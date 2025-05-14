package com.spring.ex03;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;

public class MemberDAO {
	public static SqlSessionFactory sqlMapper = null;
	
	private static SqlSessionFactory getInstance() {
		if (sqlMapper == null) {
			try {
				String resource = "mybatis/SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}
	
	public List<MemberVO> selectAllMemberList(){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> memList = null;
		memList = session.selectList("mapper.member.selectAllMemberList");
		return memList;
	}
	
	public MemberVO selectMemberById(String id){
	      sqlMapper=getInstance();
	      SqlSession session=sqlMapper.openSession();
	      // selectOne(query_id, 조건) -> id에대한 select문 실행후 지정된 타입의 하나의 레코드만 반환, 조건도전달
	      // 아이디는 중복불가
	      MemberVO memberVO=session.selectOne("mapper.member.selectMemberById",id);
	      return memberVO;		
	   }

	public List<MemberVO> selectMemberByPwd(int pwd) {
	      sqlMapper = getInstance();
	      SqlSession session = sqlMapper.openSession();
	      List<MemberVO> membersList = null;
	   // selectList(query_id, 조건) -> id에대한 select문 실행후 여러 레코드를 List로 반환, 조건도전달 
	      // 비밀번호는 중복가능
	      membersList= session.selectList("mapper.member.selectMemberByPwd", pwd);
	     return membersList;
	}


	
}
