package com.bigdata2017.mysite.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bigdata2017.mysite.service.BoardService;
import com.bigdata2017.mysite.vo.BoardVO;
import com.bigdata2017.mysite.vo.UserVO;
import com.bigdata2017.security.Auth;
import com.bigdata2017.security.AuthUser;
import com.bigdata2017.web.util.MyUtil;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(
			@ModelAttribute BoardVO boardVO,
			@AuthUser UserVO authUser
			) {
		
		boardVO.setMemberNo(authUser.getNo());
		boardService.write(boardVO);
		
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(
			@RequestParam(value="pageNo", required=false) int pageNo,
			Model model) {
		
		model.addAttribute("pageNo", pageNo);
		
		return "board/write";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(
			@RequestParam(value="boardNo", required=false) Long boardNo,
			@RequestParam(value="pageNo", required=false) int pageNo
			) {
		
		boardService.delete(boardNo);
		
		return "redirect:/board?pageNo="+pageNo;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(
			@RequestParam(value="boardNo", required=false) Long boardNo,
			@RequestParam(value="pageNo", required=false) int pageNo,
			Model model) {
		
		BoardVO vo = boardService.getView(boardNo);
		
		model.addAttribute("vo", vo);
		model.addAttribute("pageNo", pageNo);
		
		return "board/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(
			@RequestParam(value="boardNo", required=false) Long boardNo,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="content", required=false) String content,
			@RequestParam(value="pageNo", required=false) int pageNo,
			Model model) {
		
		BoardVO vo = new BoardVO();
		vo.setNo(boardNo);
		vo.setTitle(title);
		vo.setContent(content);
		
		boardService.modify(vo);
		
		return "redirect:/board/view?boardNo="+boardNo+"&pageNo="+pageNo;
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String reply(
			@RequestParam(value="boardNo", required=false) Long boardNo,
			@RequestParam(value="pageNo", required=false) int pageNo,
			Model model) {
		
		BoardVO vo = boardService.getView(boardNo);
		
		String temp = "\r\n--------------------------\r\n\r\n";
		temp += "[답변]\r\n";
		
		vo.setContent(vo.getContent() + temp);
		vo.setTitle("[답변]" + vo.getTitle());
		
		model.addAttribute("vo", vo);
		model.addAttribute("pageNo", pageNo);
		
		return "board/reply";
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(
			@RequestParam(value="boardNo", required=false) Long boardNo,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="content", required=false) String content,
			@RequestParam(value="pageNo", required=false) int pageNo,
			HttpSession session,
			Model model) {

		UserVO authUser = (UserVO)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		BoardVO vo = boardService.getView(boardNo);
		boardService.orderNoUpdate(vo.getGroupNo(), vo.getOrderNo());
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setParent(Math.toIntExact(vo.getNo()));
		vo.setDepth(vo.getDepth()+1);
		vo.setOrderNo(vo.getOrderNo()+1);
		vo.setMemberNo(authUser.getNo());
		boardService.replyWrite(vo);
		
		return "redirect:/board?pageNo="+pageNo;
	}
	
	@RequestMapping(value="/view")
	public String view(
			@RequestParam(value="boardNo", required=false) Long boardNo,
			@RequestParam(value="pageNo", required=false) int pageNo,
			HttpSession session,
			Model model) {
		
		BoardVO vo = boardService.getView(boardNo);
		boardService.hitCountUpdate(boardNo);

		UserVO authUser = (UserVO)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		List<Long> writedList = boardService.writedList(authUser.getNo());
		if(writedList.contains(boardNo)) {
			vo.setDeleteRight(true);
		} else {
			vo.setDeleteRight(false);
		}
		
		model.addAttribute("vo", vo);
		model.addAttribute("pageNo", pageNo);
		
		return "board/view";
	}
	
	@RequestMapping(value="")
	public String list(
			@RequestParam(value="pageNo", required=false) String pageNo,
			HttpSession session,
			Model model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
	    String contextPath = attr.getRequest().getContextPath();
		MyUtil myUtil = new MyUtil();
		
		int numPerPage = 5;
		int totalPage = 0;
		int totalDataCount = 0;
		
		int currentPage = 1;
		
		if(pageNo != null && !"".equals(pageNo)) {
			currentPage = Integer.parseInt(pageNo);
		}
		
		totalDataCount = boardService.getDataCount();
		
		if(totalDataCount != 0) {
			totalPage = myUtil.getPageCount(numPerPage, totalDataCount);
		}
		
		if(currentPage > totalPage) {
			currentPage = totalPage;
		}
		
		int start = (currentPage-1) * numPerPage + 1;
		int end = currentPage * numPerPage;
		
		// 로그인 한 유저가 작성한 글 목록 불러오기.
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		List<Long> writedList = boardService.writedList(authUser.getNo());
		
		List<BoardVO> list = boardService.getBoardList(start, end);
		int listNum, n=0;
		Iterator<BoardVO> it = list.iterator();
		int depth = -1;
		
		int initDepth = 0;
		boolean firstDepthZero = false;
		boolean firstDepthGapMinus = false;
		int firstWriteDepth = 0;
		
		while(it.hasNext()) {
			
			BoardVO vo = it.next();
			
			listNum = totalDataCount - (start+n-1);
			
			vo.setListNum(listNum);
			n++;
			
			int depthGap = depth - vo.getDepth() + 1;	
			if(depthGap < 0) {
				if(initDepth == 0) {
					initDepth = 0 - depthGap;
					firstDepthGapMinus = true;
					firstWriteDepth = vo.getDepth();
				}
				depthGap = depthGap + initDepth;
			}
			if(firstDepthGapMinus == true && firstDepthZero == false) {
				if(vo.getDepth() == 0) {
					firstDepthZero = true;
					depthGap = depthGap - firstWriteDepth;
				}
			}
			vo.setDepthGap(depthGap);
			
			depth = vo.getDepth();
			
			vo.setReplyNum(boardService.replyNum(vo.getNo()));
			vo.setSiblingNum(boardService.siblingNum(vo.getParent(), vo.getOrderNo()));
			
			List<Integer> verticalList = new ArrayList<Integer>();
			
			for(int verticalDepth=1; verticalDepth<vo.getDepth(); verticalDepth++){
				
				verticalList.add(boardService.verticalList(vo.getGroupNo(), vo.getOrderNo(), verticalDepth));
			}
			
			vo.setVerticalList(verticalList);
			
			if(writedList.contains(vo.getNo())) {
				vo.setDeleteRight(true);
			} else {
				vo.setDeleteRight(false);
			}
		}
		
		String param = "";
		String urlList = "";
		String urlArticle = "";
		
		/*if(!dto.getSearchValue().equals("")){
			
			param = "searchKey=" + dto.getSearchKey();
			param += "&searchValue=" + URLEncoder.encode(dto.getSearchValue(), "UTF-8");
		}*/
		
		urlList = contextPath + "/board";
		urlArticle = contextPath + "/board/view?pageNo=" + currentPage;
		
		/*if(!param.equals("")){
			
			urlList += "?" + param;
			urlArticle += "&" + param;
		}*/

		model.addAttribute("pageNo", pageNo);
		model.addAttribute("list", list);
		model.addAttribute("restDiv", n);
		model.addAttribute("totalDataCount", totalDataCount);
		model.addAttribute("pageIndexList", myUtil.pageIndexList(currentPage, totalPage, urlList));
		model.addAttribute("urlArticle", urlArticle);
		
		return "board/list";
	}
}
