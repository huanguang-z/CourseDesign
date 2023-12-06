package com.pony.model;

import java.io.Serializable;

public class DortorModel implements Serializable{

	private String doctorImage;
	private String doctorId;
	private String doctorMessage;
	private String doctorSex;
	private String departmentId;
	private String doctorYear;
	private String doctorName;
	private String departmentHosName;
	private String departmentName;

	private String doctorMoney;
	private String doctorLevel;


	public String getDoctorMoney() {
		return doctorMoney;
	}

	public void setDoctorMoney(String doctorMoney) {
		this.doctorMoney = doctorMoney;
	}

	public String getDoctorLevel() {
		return doctorLevel;
	}

	public void setDoctorLevel(String doctorLevel) {
		this.doctorLevel = doctorLevel;
	}

	public String getDoctorImage() {
		return doctorImage;
	}

	public void setDoctorImage(String doctorImage) {
		this.doctorImage = doctorImage;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorMessage() {
		return doctorMessage;
	}

	public void setDoctorMessage(String doctorMessage) {
		this.doctorMessage = doctorMessage;
	}

	public String getDoctorSex() {
		return doctorSex;
	}

	public void setDoctorSex(String doctorSex) {
		this.doctorSex = doctorSex;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDoctorYear() {
		return doctorYear;
	}

	public void setDoctorYear(String doctorYear) {
		this.doctorYear = doctorYear;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDepartmentHosName() {
		return departmentHosName;
	}

	public void setDepartmentHosName(String departmentHosName) {
		this.departmentHosName = departmentHosName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
