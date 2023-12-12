package com.pony.model;

import java.io.Serializable;

public class HospitalModel implements Serializable{

	private String hospitalAddress;
	private String hospitalTime;
	private String hospitalImage;
	private String hospitalName;
	private String hospitalId;
	private String hospitalMessage;

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getHospitalTime() {
		return hospitalTime;
	}

	public void setHospitalTime(String hospitalTime) {
		this.hospitalTime = hospitalTime;
	}

	public String getHospitalImage() {
		return hospitalImage;
	}

	public void setHospitalImage(String hospitalImage) {
		this.hospitalImage = hospitalImage;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalMessage() {
		return hospitalMessage;
	}

	public void setHospitalMessage(String hospitalMessage) {
		this.hospitalMessage = hospitalMessage;
	}

}
