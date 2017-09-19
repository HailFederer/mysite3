package com.bigdata2017.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdata2017.mysite.repository.GuestBookDAO;
import com.bigdata2017.mysite.vo.GuestBookVO;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookDAO guestBookDAO;
	
	public List<GuestBookVO> getGuestBookList(Long no){
		return guestBookDAO.getList(no);
	}
	
	public int getRestOfList(Long no){
		return guestBookDAO.getRestOfList(no);
	}
	
	public List<GuestBookVO> getGuestBookList(){
		return guestBookDAO.getList();
	}
	
	public boolean insertGuestBook(GuestBookVO guestBookVO){
		
		return guestBookDAO.insert(guestBookVO) == 1;
	}
	
	public boolean deleteGuestBook(GuestBookVO guestBookVO){
		
		return guestBookDAO.delete(guestBookVO) == 1;
	}
	
	public void deleteGuestBook(long no){
		
		guestBookDAO.delete(no);
	}
}
