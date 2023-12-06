package com.pony.model;

import com.pony.adapter.DoctorAdapter;

import java.io.Serializable;

public class ApplyModel implements Serializable{

	private String applyUserName;
	private String applyId;
	private String applyMessageName;
	private String applyTime;
	private String applyMessageId;
	private String applyChoiceTime;

	
	private DortorModel doctorMsg;


	public String getApplyChoiceTime() {
		return applyChoiceTime;
	}

	public void setApplyChoiceTime(String applyChoiceTime) {
		this.applyChoiceTime = applyChoiceTime;
	}

	public DortorModel getDoctorMsg() {
		return doctorMsg;
	}
	public void setDoctorMsg(DortorModel doctorMsg) {
		this.doctorMsg = doctorMsg;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getApplyMessageName() {
		return applyMessageName;
	}
	public void setApplyMessageName(String applyMessageName) {
		this.applyMessageName = applyMessageName;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getApplyMessageId() {
		return applyMessageId;
	}
	public void setApplyMessageId(String applyMessageId) {
		this.applyMessageId = applyMessageId;
	}
	
	
	
	
	
}
