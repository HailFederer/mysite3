package com.bigdata2017.mysite.vo;

public class GalleryVO {

	private Long no;
	private String comments;
	private String image_url;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	@Override
	public String toString() {
		return "GalleryVO [no=" + no + ", comments=" + comments + ", image_url=" + image_url + "]";
	}
}
