package com.pony.model;

import java.io.Serializable;

public class CCModel implements Serializable{

	private String ccName;
	private String ccId;
	private String ccCreateTime;

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public String getCcId() {
		return ccId;
	}

	public void setCcId(String ccId) {
		this.ccId = ccId;
	}

	public String getCcCreateTime() {
		return ccCreateTime;
	}

	public void setCcCreateTime(String ccCreateTime) {
		this.ccCreateTime = ccCreateTime;
	}

}
