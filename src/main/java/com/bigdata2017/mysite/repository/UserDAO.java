package com.bigdata2017.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigdata2017.mysite.exception.UserDaoException;
import com.bigdata2017.mysite.vo.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int update(UserVO vo) {
		
		int count = sqlSession.update("user.update", vo);
		return count;
	}
	
	public UserVO get(String email) {
		
		UserVO userVo = sqlSession.selectOne("user.getByEmail", email);
		
		return userVo;
	}
	
	public UserVO get(Long no) {
		
		UserVO userVo = sqlSession.selectOne("user.getByNo", no);
		
		return userVo;
	}
	
	public UserVO get(String email, String password) throws UserDaoException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("email", email);
		map.put("password", password);
		
		UserVO userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
		
		return userVo;
	}
	
	public int insert( UserVO vo) {
		
		int count = sqlSession.insert("user.insert", vo);
		return count;
	}
	
	/*private Connection getConnection() throws SQLException {
		
		Connection conn = null;
		
		try {
			//1. JDBC 드라이버 로딩(JDBC 클래스 로딩)
			Class.forName( "oracle.jdbc.driver.OracleDriver" );

			//2. Connection 가져오기
			String url = "jdbc:oracle:thin:@localhost:1521:TestDB";
			conn = DriverManager.getConnection( url, "webdb", "webdb" );
		
		} catch (ClassNotFoundException e) {
			System.out.println( "드라이버 로딩 실패:" + e );
		}
		
		return conn;
	}*/
}
