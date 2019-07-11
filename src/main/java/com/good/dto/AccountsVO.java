package com.good.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class AccountsVO {
	private int accountId;
	@Email(message="올바른 이메일 형식이 아닙니다.")
	@NotBlank(message="필수항목입니다.")
	@Length(min=5, max=30, message="이메일은 5자 이상, 30자이하여야 합니다.")
	private String email;
	@NotBlank(message="필수항목입니다.")
	@Length(min=8, max=20, message="비밀번호는 8자 이상, 20자이하여야 합니다.")
	private String pwd;
	@NotBlank(message="필수항목입니다.")
	@Length(min=5, max=16, message="닉네임은 5자 이상, 16자이하여야 합니다.")
	private String nickname;
	private String picture;
	private String footer;
	private Date reg_date;
	private Date mod_date;
	private int authority;
	private MultipartFile uploadFile;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
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

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}
	
	public MultipartFile getUploadFile() {

		return uploadFile;

    }

    public void setUploadFile(MultipartFile uploadFile) {

    	this.uploadFile = uploadFile;

    }

	@Override
	public String toString() {
		return "AccountsVO [accountId=" + accountId + ", email=" + email + ", pwd=" + pwd + ", nickname=" + nickname
				+ ", picture=" + picture + ", footer=" + footer + ", reg_date=" + reg_date + ", mod_date=" + mod_date
				+ ", authority=" + authority + "]";
	}

}
