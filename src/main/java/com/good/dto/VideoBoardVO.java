package com.good.dto;

import java.util.Date;

public class VideoBoardVO {
	private int boardId;
	private int accountId;
	private int categoryId;
	private String subject;
	private String object;
	private String youtubeLink;
	private Date reg_date;
	private Date mod_date;
	private int viewCount;
	
	private String categoryName;
	
	private int replyCount;
	private float starCount;
	private int starSelect;
	
	private String nickname;
	private String footer;
	private String picture;
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getYoutubeLink() {
		return youtubeLink;
	}
	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public float getStarCount() {
		return starCount;
	}
	public void setStarCount(float starCount) {
		this.starCount = starCount;
	}
	public int getStarSelect() {
		return starSelect;
	}
	public void setStarSelect(int starSelect) {
		this.starSelect = starSelect;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Override
	public String toString() {
		return "VideoBoardVO [boardId=" + boardId + ", accountId=" + accountId + ", categoryId=" + categoryId
				+ ", subject=" + subject + ", object=" + object + ", youtubeLink=" + youtubeLink + ", reg_date="
				+ reg_date + ", mod_date=" + mod_date + ", viewCount=" + viewCount + ", categoryName=" + categoryName
				+ ", replyCount=" + replyCount + ", starCount=" + starCount + ", starSelect=" + starSelect
				+ ", nickname=" + nickname + ", footer=" + footer + ", picture=" + picture + "]";
	}
	
}
