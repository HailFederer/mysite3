package com.bigdata2017.mysite.vo;

import java.util.List;

public class BoardVO {

	private String name;
	private Long no;
	private String title;
	private String content;
	private String reg_date;
	private int hit;
	private int groupNo;
	private int parent;
	private int orderNo;
	private int depth;
	private Long memberNo;
	
	private int replyNum;
	private int depthGap;
	private List<Integer> verticalList;
	private int siblingNum;
		
	private int listNum;
	
	private String searchKey;
	private String searchValue;
	private String pageNum;
	
	private boolean deleteRight;
	
	public boolean isDeleteRight() {
		return deleteRight;
	}
	public void setDeleteRight(boolean deleteRight) {
		this.deleteRight = deleteRight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public int getDepthGap() {
		return depthGap;
	}
	public void setDepthGap(int depthGap) {
		this.depthGap = depthGap;
	}
	public List<Integer> getVerticalList() {
		return verticalList;
	}
	public void setVerticalList(List<Integer> verticalList) {
		this.verticalList = verticalList;
	}
	public int getSiblingNum() {
		return siblingNum;
	}
	public void setSiblingNum(int siblingNum) {
		this.siblingNum = siblingNum;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	@Override
	public String toString() {
		return "BoardVO [name=" + name + ", no=" + no + ", title=" + title + ", content=" + content + ", reg_date="
				+ reg_date + ", hit=" + hit + ", groupNo=" + groupNo + ", parent=" + parent + ", orderNo=" + orderNo
				+ ", depth=" + depth + ", memberNo=" + memberNo + ", replyNum=" + replyNum + ", depthGap=" + depthGap
				+ ", verticalList=" + verticalList + ", siblingNum=" + siblingNum + ", listNum=" + listNum
				+ ", searchKey=" + searchKey + ", searchValue=" + searchValue + ", pageNum=" + pageNum + "]";
	}
}
