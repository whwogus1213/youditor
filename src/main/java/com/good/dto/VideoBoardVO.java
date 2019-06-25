package com.good.dto;

import java.util.Date;

public class VideoBoardVO {
	private int boardId;
	private int accountId;
	private String subject;
	private String object;
	private Date reg_date;
	private int categoryId;
	private String youtubeLink;
	private Date mod_date;
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
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getYoutubeLink() {
		return youtubeLink;
	}
	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}
	
	@Override
	public String toString() {
		return "VideoBoardVO [boardId=" + boardId + ", accountId=" + accountId + ", subject=" + subject + ", object="
				+ object + ", reg_date=" + reg_date + ", categoryId=" + categoryId + ", youtubeLink=" + youtubeLink
				+ ", mod_date=" + mod_date + "]";
	}
}