package com.bigdata2017.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bigdata2017.mysite.service.FileUploadService;
import com.bigdata2017.mysite.service.GalleryService;
import com.bigdata2017.mysite.vo.GalleryVO;
import com.bigdata2017.security.Auth;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String index(
			Model model
			) {
		
		List<GalleryVO> list = galleryService.getList();
		model.addAttribute("list", list);
		return "gallery/index";
	}

	@Auth(role=Auth.Role.ADMIN)
	@RequestMapping("/upload")
	public String upload(
			@ModelAttribute GalleryVO galleryVo,
			@RequestParam(value="file", required=false, defaultValue="") MultipartFile file
			) {

		String image_url = fileUploadService.restore(file);
		galleryVo.setImage_url(image_url);
		galleryService.upload(galleryVo);
		
		return "redirect:/gallery";
	}

	@Auth(role=Auth.Role.ADMIN)
	@RequestMapping("/delete")
	public String delete(
			@RequestParam(value="no", required=false, defaultValue="") Long no
			) {

		String image_url = galleryService.getImage_url(no);
		fileUploadService.deleteFile(image_url);
		galleryService.delete(no);
		
		return "redirect:/gallery";
	}
}
