package com.pony.model;

import java.io.Serializable;

public class MeetingRoomModel implements Serializable{

	private String mtId;
	private String mtLongitude;
	private String mtName;
	private String mtCreatime;
	private String mtSeatNumber;
	private String mtState;
	private String mtLatitude;
	private String mtAddress;

	public String getMtId() {
		return mtId;
	}

	public void setMtId(String mtId) {
		this.mtId = mtId;
	}

	public String getMtLongitude() {
		return mtLongitude;
	}

	public void setMtLongitude(String mtLongitude) {
		this.mtLongitude = mtLongitude;
	}

	public String getMtName() {
		return mtName;
	}

	public void setMtName(String mtName) {
		this.mtName = mtName;
	}

	public String getMtCreatime() {
		return mtCreatime;
	}

	public void setMtCreatime(String mtCreatime) {
		this.mtCreatime = mtCreatime;
	}

	public String getMtSeatNumber() {
		return mtSeatNumber;
	}

	public void setMtSeatNumber(String mtSeatNumber) {
		this.mtSeatNumber = mtSeatNumber;
	}

	public String getMtState() {
		return mtState;
	}

	public void setMtState(String mtState) {
		this.mtState = mtState;
	}

	public String getMtLatitude() {
		return mtLatitude;
	}

	public void setMtLatitude(String mtLatitude) {
		this.mtLatitude = mtLatitude;
	}

	public String getMtAddress() {
		return mtAddress;
	}

	public void setMtAddress(String mtAddress) {
		this.mtAddress = mtAddress;
	}

}
