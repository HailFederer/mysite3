package com.bigdata2017.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdata2017.mysite.repository.GalleryDAO;
import com.bigdata2017.mysite.vo.GalleryVO;

@Service
public class GalleryService {

	@Autowired
	private GalleryDAO galleryDAO;
	
	public boolean upload(GalleryVO galleryVo) {
		int count = galleryDAO.upload(galleryVo);
		return count == 1;
	}	
	
	public void delete(Long no) {
		galleryDAO.delete(no);
	}	
	
	public List<GalleryVO> getList(){
		return galleryDAO.getList();
	}
	
	public String getImage_url(Long no) {
		return galleryDAO.getImage_url(no);
	}
}
