package com.pony.model;

import java.io.Serializable;
import java.util.List;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

public class CategoryModel implements Serializable{

	private int cId;
	private String cName;
	private String cCreateTime;
	private String cMessage;
	private String cUserId;
	private String flagcollect;
	private String cUserName;
	private String flagMember;
	
	private List<MemberModel>  communityMember;
	
	
	
	public List<MemberModel> getCommunityMember() {
		return communityMember;
	}

	public void setCommunityMember(List<MemberModel> communityMember) {
		this.communityMember = communityMember;
	}

	public String getFlagMember() {
		return flagMember;
	}

	public void setFlagMember(String flagMember) {
		this.flagMember = flagMember;
	}

	public String getcUserName() {
		return cUserName;
	}

	public void setcUserName(String cUserName) {
		this.cUserName = cUserName;
	}

	public String getFlagcollect() {
		return flagcollect;
	}

	public void setFlagcollect(String flagcollect) {
		this.flagcollect = flagcollect;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}

	public String getcMessage() {
		return cMessage;
	}

	public void setcMessage(String cMessage) {
		this.cMessage = cMessage;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcCreateTime() {
		return cCreateTime;
	}

	public void setcCreateTime(String cCreateTime) {
		this.cCreateTime = cCreateTime;
	}

}
