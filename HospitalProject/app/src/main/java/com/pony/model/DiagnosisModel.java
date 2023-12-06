package com.pony.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/11/19.
 */

public class DiagnosisModel implements Serializable {




    private String diagnosisMethods;
    private String diagnosisDrug;
    private String diagnosisInfor;
    private int diagnosisApplyId;
    private int diagnosisId;
    private String diagnosisTime;
    private int diagnosisDoctorId;

    private DortorModel doctorMsg;

    public DortorModel getDoctorMsg() {
        return doctorMsg;
    }

    public void setDoctorMsg(DortorModel doctorMsg) {
        this.doctorMsg = doctorMsg;
    }

    public String getDiagnosisMethods() {
        return diagnosisMethods;
    }

    public void setDiagnosisMethods(String diagnosisMethods) {
        this.diagnosisMethods = diagnosisMethods;
    }

    public String getDiagnosisDrug() {
        return diagnosisDrug;
    }

    public void setDiagnosisDrug(String diagnosisDrug) {
        this.diagnosisDrug = diagnosisDrug;
    }

    public String getDiagnosisInfor() {
        return diagnosisInfor;
    }

    public void setDiagnosisInfor(String diagnosisInfor) {
        this.diagnosisInfor = diagnosisInfor;
    }

    public int getDiagnosisApplyId() {
        return diagnosisApplyId;
    }

    public void setDiagnosisApplyId(int diagnosisApplyId) {
        this.diagnosisApplyId = diagnosisApplyId;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getDiagnosisTime() {
        return diagnosisTime;
    }

    public void setDiagnosisTime(String diagnosisTime) {
        this.diagnosisTime = diagnosisTime;
    }

    public int getDiagnosisDoctorId() {
        return diagnosisDoctorId;
    }

    public void setDiagnosisDoctorId(int diagnosisDoctorId) {
        this.diagnosisDoctorId = diagnosisDoctorId;
    }
}
