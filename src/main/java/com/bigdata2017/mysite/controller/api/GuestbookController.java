package com.bigdata2017.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigdata2017.mysite.dto.JSONResult;
import com.bigdata2017.mysite.service.GuestBookService;
import com.bigdata2017.mysite.vo.GuestBookVO;

@Controller("guestbookAPIController")
@RequestMapping("/api/guestBook")
public class GuestbookController {

	@Autowired
	private GuestBookService guestBookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public JSONResult list(
			@RequestParam(value="no", required=true, defaultValue="0") Long no
			) {
		
		List<GuestBookVO> list = guestBookService.getGuestBookList(no);
		
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/restOfList")
	public JSONResult restOfList(
			@RequestParam(value="no", required=true, defaultValue="0") Long no
			) {
		
		int count = guestBookService.getRestOfList(no);
		
		return JSONResult.success(count);
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public JSONResult insert(
			@ModelAttribute GuestBookVO guestBookVo
			) {
		
		boolean successYN = guestBookService.insertGuestBook(guestBookVo);
		
		if(successYN == false) {
			return JSONResult.fail("방명록 등록에 실패했습니다.");
		} else {
			return JSONResult.success(guestBookVo);
		}
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult delete(
			@ModelAttribute GuestBookVO guestbookVo
			) {
		
		boolean bSuccess = guestBookService.deleteGuestBook(guestbookVo);	
		
		return JSONResult.success(bSuccess ? guestbookVo.getNo() : -1);
	}
}
