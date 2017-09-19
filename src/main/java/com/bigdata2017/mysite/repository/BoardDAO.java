package com.bigdata2017.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigdata2017.mysite.vo.BoardVO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<Long> writedList(Long userNo) {
		
		List<Long> list = sqlSession.selectList("board.writedList", userNo);
		return list;
	}
	
	public int replyWrite(BoardVO vo) {
		int count = sqlSession.insert("board.replyWrite", vo);
		return count;
	}
	
	public int orderNoUpdate(int groupNo, int orderNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		
		int count = sqlSession.update("board.orderNoUpdate", map);
		return count;
	}
	
	public int hitCountUpdate(Long no) {
		int count = sqlSession.update("board.hitCountUpdate", no);
		return count;
	}
	
	public int delete(Long no) {		
		int count = sqlSession.delete("board.delete", no);
		return count;
	}
	
	public int modify(BoardVO vo) {	
		int count = sqlSession.update("board.modify", vo);
		return count;
	}
	
	public BoardVO view(Long boardNo) {
		
		BoardVO boardVo = sqlSession.selectOne("board.view", boardNo);
		return boardVo ;
	}
	
	public int write(BoardVO vo) {
		int count = sqlSession.insert("board.write", vo);
		return count;
	}
	
	public int verticalList(int groupNo, int orderNo, int depth) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		map.put("depth", depth);
		
		int count = sqlSession.selectOne("board.verticalList", map);
		return count;
	}
	
	public int siblingNum(int parent, int orderNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parent", parent);
		map.put("orderNo", orderNo);
		
		int count = sqlSession.selectOne("board.siblingNum", map);
		return count;
	}
	
	public int replyNum(Long parent) {
		int count = sqlSession.selectOne("board.replyNum", parent);
		return count;
	}
	
	public int getDataCount() {
		int count = sqlSession.selectOne("board.getDataCount");
		return count;
	}
	
	public List<BoardVO> getList(int start, int end){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list = sqlSession.selectList("board.getList", map);
		return list;
	}
}












