package com.pony.model;

import java.io.Serializable;

/**
 * 
 * @author wangxuan
 * 
 */
public class UserModel implements Serializable{



	/**
	 * uid : 15
	 * utime : 2020-11-16 15:13
	 * upswd : 123456
	 * uphone : 15249243001
	 * umoney : 0
	 * uname : 小米
	 * userImage :
	 * uSex : 男
	 * uCard : 618250
	 */

	private String uid;
	private String utime;
	private String upswd;
	private String uphone;
	private String umoney;
	private String uname;
	private String userImage;
	private String uSex;
	private String uCard;


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUtime() {
		return utime;
	}

	public void setUtime(String utime) {
		this.utime = utime;
	}

	public String getUpswd() {
		return upswd;
	}

	public void setUpswd(String upswd) {
		this.upswd = upswd;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public String getUmoney() {
		return umoney;
	}

	public void setUmoney(String umoney) {
		this.umoney = umoney;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getuSex() {
		return uSex;
	}

	public void setuSex(String uSex) {
		this.uSex = uSex;
	}

	public String getuCard() {
		return uCard;
	}

	public void setuCard(String uCard) {
		this.uCard = uCard;
	}
}
