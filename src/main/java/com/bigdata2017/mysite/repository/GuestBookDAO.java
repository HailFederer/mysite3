package com.bigdata2017.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.bigdata2017.mysite.vo.GuestBookVO;

@Repository
public class GuestBookDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int delete( long no) {
		
		int count = sqlSession.delete("guestbook.delete2", no);
		return count;
	}
	
	public int delete( GuestBookVO vo) {
		
		int count = sqlSession.delete("guestbook.delete", vo);
		return count;
	}
	
	public int insert( GuestBookVO vo) {
		
		int count = sqlSession.insert("guestbook.insert", vo);
		return count;
	}
	
	public List<GuestBookVO> getList(Long no){
		
		List<GuestBookVO> list = sqlSession.selectList("guestbook.getList2", no);
		
		return list;
	}
	public int getRestOfList(Long no){
		
		int count = sqlSession.selectOne("guestbook.getRestOfList", no);
		return count;
	}
	
	public List<GuestBookVO> getList(){
		
		List<GuestBookVO> list = sqlSession.selectList("guestbook.getList");
		
		return list;
	}
}
