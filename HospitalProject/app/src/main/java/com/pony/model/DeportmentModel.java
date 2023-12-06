package com.pony.model;

import java.io.Serializable;
import java.util.List;

public class DeportmentModel implements Serializable {


    private String departmentId;
    private String departmentName;
    private String departmentInfor;


    private boolean departmentChoice;


    public String getDepartmentInfor() {
        return departmentInfor;
    }

    public void setDepartmentInfor(String departmentInfor) {
        this.departmentInfor = departmentInfor;
    }

    public boolean isDepartmentChoice() {
        return departmentChoice;
    }

    public void setDepartmentChoice(boolean departmentChoice) {
        this.departmentChoice = departmentChoice;
    }

    private List<DortorModel> doctorList;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<DortorModel> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<DortorModel> doctorList) {
        this.doctorList = doctorList;
    }
}
