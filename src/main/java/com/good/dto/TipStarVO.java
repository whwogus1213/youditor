package com.good.dto;

import java.util.Date;

public class TipStarVO {
	private int starId;
	private int boardId;
	private int accountId;
	private int star;
	private Date reg_date;
	private String subject;
	private float starCount;

	public int getStarId() {
		return starId;
	}

	public void setStarId(int starId) {
		this.starId = starId;
	}

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

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public float getStarCount() {
		return starCount;
	}

	public void setStarCount(float starCount) {
		this.starCount = starCount;
	}

	@Override
	public String toString() {
		return "TipStarVO [starId=" + starId + ", boardId=" + boardId + ", accountId=" + accountId + ", star=" + star
				+ ", reg_date=" + reg_date + ", subject=" + subject + ", starCount=" + starCount + "]";
	}

	

	
} // End - TipStarVO
