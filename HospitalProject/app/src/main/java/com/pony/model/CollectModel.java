package com.pony.model;

import java.io.Serializable;

public class CollectModel implements Serializable{

	private String cUserName;
	private String cUserId;
	private String cCategoryId;
	private String cCategoryName;
	private String cId;

	public String getcUserName() {
		return cUserName;
	}

	public void setcUserName(String cUserName) {
		this.cUserName = cUserName;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}

	public String getcCategoryId() {
		return cCategoryId;
	}

	public void setcCategoryId(String cCategoryId) {
		this.cCategoryId = cCategoryId;
	}

	public String getcCategoryName() {
		return cCategoryName;
	}

	public void setcCategoryName(String cCategoryName) {
		this.cCategoryName = cCategoryName;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

}
