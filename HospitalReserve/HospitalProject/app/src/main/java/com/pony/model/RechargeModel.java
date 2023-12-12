package com.pony.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/9/16.
 */

public class RechargeModel implements Serializable {



    private String rechargeUserName;
    private String rechargeTime;
    private int rechargeUserId;
    private String rechargeMoney;
    private int rechargeId;

    public String getRechargeUserName() {
        return rechargeUserName;
    }

    public void setRechargeUserName(String rechargeUserName) {
        this.rechargeUserName = rechargeUserName;
    }

    public String getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(String rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public int getRechargeUserId() {
        return rechargeUserId;
    }

    public void setRechargeUserId(int rechargeUserId) {
        this.rechargeUserId = rechargeUserId;
    }

    public String getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(String rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public int getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(int rechargeId) {
        this.rechargeId = rechargeId;
    }
}
