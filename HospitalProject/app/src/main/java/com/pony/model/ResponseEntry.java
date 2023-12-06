/*
 *******************************************
 * File: ResponseEntry.java
 * Author: Lijc
 * Date: 2015-3-23
 * Company: BlueMobi
 ********************************************/
package com.pony.model;

import java.io.Serializable;
public class ResponseEntry implements Serializable{

	public static final String OK="000000";
	public static final String NO="111";
	private String repCode;
	private String repMsg;
	private String data;
	public String getRepCode() {
		return repCode;
	}
	public void setRepCode(String repCode) {
		this.repCode = repCode;
	}
	public String getRepMsg() {
		return repMsg;
	}
	public void setRepMsg(String repMsg) {
		this.repMsg = repMsg;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
