package com.bigdata2017.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigdata2017.mysite.service.GuestBookService;
import com.bigdata2017.mysite.vo.GuestBookVO;
import com.bigdata2017.security.Auth;
import com.bigdata2017.security.Auth.Role;

@Auth(role = Role.USER)
@Controller
@RequestMapping("/guestBook")
public class GuestBookController {

	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping(value="/ajax")
	public String ajax() {
		return "guestbook/index-ajax";
	}
	
	
	
	@RequestMapping(value="")
	public String list(Model model) {
		
		List<GuestBookVO> list = guestBookService.getGuestBookList();
		
		model.addAttribute("list", list);
		model.addAttribute("newLine", "\n");
		
		return "guestbook/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(
			/*@RequestBody GuestBookVO guestBookVo*/
			@ModelAttribute GuestBookVO guestBookVo
			) {
		
		guestBookService.insertGuestBook(guestBookVo);
		
		return "redirect:/guestBook";
	}
	
	/*@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(
			@RequestParam(value="no", required=true, defaultValue="") Long no,
			Model model) {
		
		model.addAttribute("no", no);
		
		return "guestbook/deleteform";
	}*/
	
	@RequestMapping(value="/delete", method={RequestMethod.POST})
	public String delete(@ModelAttribute GuestBookVO guestBookVO) {
		
		guestBookService.deleteGuestBook(guestBookVO);
		
		return "redirect:/guestBook";
	}
}
















