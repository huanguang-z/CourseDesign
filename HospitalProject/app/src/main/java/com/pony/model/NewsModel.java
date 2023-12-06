package com.pony.model;

import java.io.Serializable;

public class NewsModel implements Serializable {
	private String newsId;
	private String newsName;
	private String newsType;
	private String newsMessage;
	private String newsTime;
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getNewsName() {
		return newsName;
	}
	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getNewsMessage() {
		return newsMessage;
	}
	public void setNewsMessage(String newsMessage) {
		this.newsMessage = newsMessage;
	}
	public String getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}
	
	
	

}
