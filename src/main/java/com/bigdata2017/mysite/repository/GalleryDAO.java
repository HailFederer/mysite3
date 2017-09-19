package com.bigdata2017.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigdata2017.mysite.vo.GalleryVO;

@Repository
public class GalleryDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVO> getList(){
		List<GalleryVO> list = sqlSession.selectList("gallery.getList");
		return list;
	}
	
	public int delete(Long no) {		
		int count = sqlSession.delete("gallery.delete", no);
		return count;
	}
	
	public String getImage_url(Long no) {		
		String image_url = sqlSession.selectOne("gallery.getImage_url", no);
		return image_url;
	}
	
	public int upload(GalleryVO galleryVo) {
		int count = sqlSession.insert("gallery.upload", galleryVo);
		return count;
	}
}
