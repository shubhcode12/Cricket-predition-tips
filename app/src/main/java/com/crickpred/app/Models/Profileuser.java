package com.crickpred.app.Models;

public class Profileuser {

    private String id;
    private String mobile;
    private String password;
    private String name;
    private String city;
    private String email;
    private String wallet;
    private String totalPaid;
    private String totalRedeemed;
    private String userReferalCode;
    private String refferedBy;
    private String refferedPaid;
    private String totalReferals;
    private String allow;
    private String deviceId;
    private String profilePic;
    private String activeDate;
    private String onesignalPlayerid;
    private String onesignalPushtoken;
    private String joiningTime;


    public Profileuser(String id, String mobile, String password, String name, String city, String email, String wallet, String totalPaid, String totalRedeemed, String userReferalCode, String refferedBy, String refferedPaid, String totalReferals, String allow, String deviceId, String profilePic, String activeDate, String onesignalPlayerid, String onesignalPushtoken, String joiningTime) {
        super();
        this.id = id;
        this.mobile = mobile;
        this.password = password;
        this.name = name;
        this.city = city;
        this.email = email;
        this.wallet = wallet;
        this.totalPaid = totalPaid;
        this.totalRedeemed = totalRedeemed;
        this.userReferalCode = userReferalCode;
        this.refferedBy = refferedBy;
        this.refferedPaid = refferedPaid;
        this.totalReferals = totalReferals;
        this.allow = allow;
        this.deviceId = deviceId;
        this.profilePic = profilePic;
        this.activeDate = activeDate;
        this.onesignalPlayerid = onesignalPlayerid;
        this.onesignalPushtoken = onesignalPushtoken;
        this.joiningTime = joiningTime;
    }

    public String getId() {
    return id;
}

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getTotalRedeemed() {
        return totalRedeemed;
    }

    public void setTotalRedeemed(String totalRedeemed) {
        this.totalRedeemed = totalRedeemed;
    }

    public String getUserReferalCode() {
        return userReferalCode;
    }

    public void setUserReferalCode(String userReferalCode) {
        this.userReferalCode = userReferalCode;
    }

    public String getRefferedBy() {
        return refferedBy;
    }

    public void setRefferedBy(String refferedBy) {
        this.refferedBy = refferedBy;
    }

    public String getRefferedPaid() {
        return refferedPaid;
    }

    public void setRefferedPaid(String refferedPaid) {
        this.refferedPaid = refferedPaid;
    }

    public String getTotalReferals() {
        return totalReferals;
    }

    public void setTotalReferals(String totalReferals) {
        this.totalReferals = totalReferals;
    }

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    public String getOnesignalPlayerid() {
        return onesignalPlayerid;
    }

    public void setOnesignalPlayerid(String onesignalPlayerid) {
        this.onesignalPlayerid = onesignalPlayerid;
    }

    public String getOnesignalPushtoken() {
        return onesignalPushtoken;
    }

    public void setOnesignalPushtoken(String onesignalPushtoken) {
        this.onesignalPushtoken = onesignalPushtoken;
    }

    public String getJoiningTime() {
        return joiningTime;
    }

    public void setJoiningTime(String joiningTime) {
        this.joiningTime = joiningTime;
    }
}