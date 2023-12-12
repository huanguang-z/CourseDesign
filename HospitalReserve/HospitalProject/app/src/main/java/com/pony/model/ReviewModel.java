package com.pony.model;

public class ReviewModel {

	private String rCategoryId;
	private String rUserName;
	private String rCreatime;
	private String rUserId;
	private String rReviewContent;
	private String rId;

	public String getrCategoryId() {
		return rCategoryId;
	}

	public void setrCategoryId(String rCategoryId) {
		this.rCategoryId = rCategoryId;
	}

	public String getrUserName() {
		return rUserName;
	}

	public void setrUserName(String rUserName) {
		this.rUserName = rUserName;
	}

	public String getrCreatime() {
		return rCreatime;
	}

	public void setrCreatime(String rCreatime) {
		this.rCreatime = rCreatime;
	}

	public String getrUserId() {
		return rUserId;
	}

	public void setrUserId(String rUserId) {
		this.rUserId = rUserId;
	}

	public String getrReviewContent() {
		return rReviewContent;
	}

	public void setrReviewContent(String rReviewContent) {
		this.rReviewContent = rReviewContent;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

}
