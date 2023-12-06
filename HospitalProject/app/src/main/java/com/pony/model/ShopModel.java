package com.pony.model;

import java.io.Serializable;

public class ShopModel implements Serializable{

	private String sCreatime;
	private String sMoney;
	private String sName;
	private String userId;
	private String sPhone;
	private String sMessage;
	private String sId;
	private String sImage;
	
	private String  sPayUsererId;
	private String  sState;
	private String  sPayUserName;
	
	
	

	public String getsPayUsererId() {
		return sPayUsererId;
	}

	public void setsPayUsererId(String sPayUsererId) {
		this.sPayUsererId = sPayUsererId;
	}

	public String getsState() {
		return sState;
	}

	public void setsState(String sState) {
		this.sState = sState;
	}

	public String getsPayUserName() {
		return sPayUserName;
	}

	public void setsPayUserName(String sPayUserName) {
		this.sPayUserName = sPayUserName;
	}

	public String getsImage() {
		return sImage;
	}

	public void setsImage(String sImage) {
		this.sImage = sImage;
	}

	public String getsCreatime() {
		return sCreatime;
	}

	public void setsCreatime(String sCreatime) {
		this.sCreatime = sCreatime;
	}

	public String getsMoney() {
		return sMoney;
	}

	public void setsMoney(String sMoney) {
		this.sMoney = sMoney;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getsPhone() {
		return sPhone;
	}

	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}

	public String getsMessage() {
		return sMessage;
	}

	public void setsMessage(String sMessage) {
		this.sMessage = sMessage;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

}
