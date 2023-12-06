package com.pony.model;

import java.io.Serializable;

public class MemberModel implements Serializable{

	private String mId;
	private String mCategoryId;
	private String mState;
	private String mUserName;
	private String mCategoryName;
	private String mUserId;

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmCategoryId() {
		return mCategoryId;
	}

	public void setmCategoryId(String mCategoryId) {
		this.mCategoryId = mCategoryId;
	}

	public String getmState() {
		return mState;
	}

	public void setmState(String mState) {
		this.mState = mState;
	}

	public String getmUserName() {
		return mUserName;
	}

	public void setmUserName(String mUserName) {
		this.mUserName = mUserName;
	}

	public String getmCategoryName() {
		return mCategoryName;
	}

	public void setmCategoryName(String mCategoryName) {
		this.mCategoryName = mCategoryName;
	}

	public String getmUserId() {
		return mUserId;
	}

	public void setmUserId(String mUserId) {
		this.mUserId = mUserId;
	}

}
