package com.pony.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/11/16.
 */

public class BannerModel implements Serializable {

    private String bannerId;
    private String bannerDoctorId;
    private String bannerDoctorName;
    private String bannerImg;
    private DortorModel doctorMsg;

    public DortorModel getDoctorMsg() {
        return doctorMsg;
    }

    public void setDoctorMsg(DortorModel doctorMsg) {
        this.doctorMsg = doctorMsg;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerDoctorId() {
        return bannerDoctorId;
    }

    public void setBannerDoctorId(String bannerDoctorId) {
        this.bannerDoctorId = bannerDoctorId;
    }

    public String getBannerDoctorName() {
        return bannerDoctorName;
    }

    public void setBannerDoctorName(String bannerDoctorName) {
        this.bannerDoctorName = bannerDoctorName;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }
}
