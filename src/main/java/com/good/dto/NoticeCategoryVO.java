package com.good.dto;

import java.util.Date;

public class NoticeCategoryVO {
	private int categoryId;
	private String categoryName;
	private int authority;

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}

}	// End - public class NoticeCategoryVO