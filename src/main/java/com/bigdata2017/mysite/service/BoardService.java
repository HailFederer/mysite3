package com.bigdata2017.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdata2017.mysite.repository.BoardDAO;
import com.bigdata2017.mysite.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	public void write(BoardVO boardVo) {
		boardDAO.write(boardVo);
	}	
	
	public void delete(Long boardNo) {
		boardDAO.delete(boardNo);
	}	
	
	public void modify(BoardVO vo) {
		boardDAO.modify(vo);
	}
	
	public void replyWrite(BoardVO vo) {
		boardDAO.replyWrite(vo);
	}
	
	public void orderNoUpdate(int groupNo, int orderNo) {
		boardDAO.orderNoUpdate(groupNo, orderNo);
	}
	
	public void hitCountUpdate(Long boardNo) {
		boardDAO.hitCountUpdate(boardNo);
	}
	
	public BoardVO getView(Long boardNo) {
		return boardDAO.view(boardNo);
	}
	
	public List<BoardVO> getBoardList(int start, int end){
		return boardDAO.getList(start, end);
	}
	
	public int getDataCount() {
		return boardDAO.getDataCount();
	}
	
	public List<Long> writedList(Long userNo){
		return boardDAO.writedList(userNo);
	}
	
	public int replyNum(Long parent) {
		return boardDAO.replyNum(parent);
	}
	
	public int siblingNum(int parent, int orderNo) {
		return boardDAO.siblingNum(parent, orderNo);
	}
	
	public int verticalList(int groupNo, int orderNo, int depth) {
		return boardDAO.verticalList(groupNo, orderNo, depth);
	}
}
