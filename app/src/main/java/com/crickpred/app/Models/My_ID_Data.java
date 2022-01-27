package com.crickpred.app.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class My_ID_Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("userMobile")
    @Expose
    private String userMobile;
    @SerializedName("CorderId")
    @Expose
    private String corderId;
    @SerializedName("createdId")
    @Expose
    private String createdId;
    @SerializedName("payMentMode")
    @Expose
    private String payMentMode;
    @SerializedName("idStatus")
    @Expose
    private String idStatus;
    @SerializedName("idUsername")
    @Expose
    private String idUsername;
    @SerializedName("idPassword")
    @Expose
    private String idPassword;
    @SerializedName("idBalance")
    @Expose
    private String idBalance;
    @SerializedName("idWithdrawal")
    @Expose
    private String idWithdrawal;
    @SerializedName("idTotalDeposited")
    @Expose
    private String idTotalDeposited;
    @SerializedName("idTotalWithdrawals")
    @Expose
    private String idTotalWithdrawals;
    @SerializedName("idApproval")
    @Expose
    private String idApproval;
    @SerializedName("adminComment")
    @Expose
    private String adminComment;
    @SerializedName("idCreatedDate")
    @Expose
    private String idCreatedDate;
    @SerializedName("idUpdatedDate")
    @Expose
    private String idUpdatedDate;
    @SerializedName("idCancelledDate")
    @Expose
    private String idCancelledDate;
    @SerializedName("idVerifiedDate")
    @Expose
    private String idVerifiedDate;
    @SerializedName("id_name")
    @Expose
    private String idName;
    @SerializedName("id_website")
    @Expose
    private String idWebsite;
    @SerializedName("demoLink")
    @Expose
    private String demoLink;
    @SerializedName("demoId")
    @Expose
    private String demoId;
    @SerializedName("demoPass")
    @Expose
    private String demoPass;
    @SerializedName("id_created_ddate")
    @Expose
    private String idCreatedDdate;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("minRefill")
    @Expose
    private String minRefill;
    @SerializedName("minWithdrawal")
    @Expose
    private String minWithdrawal;
    @SerializedName("minMaintainBal")
    @Expose
    private String minMaintainBal;

    @SerializedName("maxWithdrawal")
    @Expose
    private String maxWithdrawal;
    public My_ID_Data(String id, String userMobile, String corderId, String createdId, String payMentMode, String idStatus,
                      String idUsername, String idPassword, String idBalance, String idWithdrawal, String idTotalDeposited,
                      String idTotalWithdrawals, String idApproval, String adminComment, String idCreatedDate, String idUpdatedDate,
                      String idCancelledDate, String idVerifiedDate ,String idName, String idWebsite, String demoLink, String demoId,
                      String demoPass, String minRefill, String minWithdrawal,String minMaintainBal,String idCreatedDdate, String img,String maxWithdrawal)
    {
        this.id = id;
        this.userMobile = userMobile;
        this.corderId = corderId;
        this.createdId = createdId;
        this.payMentMode = payMentMode;
        this.idStatus = idStatus;
        this.idUsername = idUsername;
        this.idPassword = idPassword;
        this.idBalance = idBalance;
        this.idWithdrawal = idWithdrawal;
        this.idTotalDeposited = idTotalDeposited;
        this.idTotalWithdrawals = idTotalWithdrawals;
        this.idApproval = idApproval;
        this.adminComment = adminComment;
        this.idCreatedDate = idCreatedDate;
        this.idUpdatedDate = idUpdatedDate;
        this.idCancelledDate = idCancelledDate;
        this.idVerifiedDate = idVerifiedDate;
        this.idName = idName;
        this.idWebsite = idWebsite;
        this.demoLink = demoLink;
        this.demoId = demoId;
        this.demoPass = demoPass;
        this.minRefill=minRefill;
        this.minWithdrawal=minWithdrawal;
        this.minMaintainBal=minMaintainBal;
        this.idCreatedDdate = idCreatedDdate;
        this.img = img;
        this.maxWithdrawal=maxWithdrawal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getCorderId() {
        return corderId;
    }

    public void setCorderId(String corderId) {
        this.corderId = corderId;
    }

    public String getCreatedId() {
        return createdId;
    }

    public void setCreatedId(String createdId) {
        this.createdId = createdId;
    }

    public String getPayMentMode() {
        return payMentMode;
    }

    public void setPayMentMode(String payMentMode) {
        this.payMentMode = payMentMode;
    }

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }

    public String getIdUsername() {
        return idUsername;
    }

    public void setIdUsername(String idUsername) {
        this.idUsername = idUsername;
    }

    public String getIdPassword() {
        return idPassword;
    }

    public void setIdPassword(String idPassword) {
        this.idPassword = idPassword;
    }

    public String getIdBalance() {
        return idBalance;
    }

    public void setIdBalance(String idBalance) {
        this.idBalance = idBalance;
    }

    public String getIdWithdrawal() {
        return idWithdrawal;
    }

    public void setIdWithdrawal(String idWithdrawal) {
        this.idWithdrawal = idWithdrawal;
    }

    public String getIdTotalDeposited() {
        return idTotalDeposited;
    }

    public void setIdTotalDeposited(String idTotalDeposited) {
        this.idTotalDeposited = idTotalDeposited;
    }

    public String getIdTotalWithdrawals() {
        return idTotalWithdrawals;
    }

    public void setIdTotalWithdrawals(String idTotalWithdrawals) {
        this.idTotalWithdrawals = idTotalWithdrawals;
    }

    public String getIdApproval() {
        return idApproval;
    }

    public void setIdApproval(String idApproval) {
        this.idApproval = idApproval;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public String getIdCreatedDate() {
        return idCreatedDate;
    }

    public void setIdCreatedDate(String idCreatedDate) {
        this.idCreatedDate = idCreatedDate;
    }

    public String getIdUpdatedDate() {
        return idUpdatedDate;
    }

    public void setIdUpdatedDate(String idUpdatedDate) {
        this.idUpdatedDate = idUpdatedDate;
    }

    public String getIdCancelledDate() {
        return idCancelledDate;
    }

    public void setIdCancelledDate(String idCancelledDate) {
        this.idCancelledDate = idCancelledDate;
    }

    public String getIdVerifiedDate() {
        return idVerifiedDate;
    }

    public void setIdVerifiedDate(String idVerifiedDate) {
        this.idVerifiedDate = idVerifiedDate;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdWebsite() {
        return idWebsite;
    }

    public void setIdWebsite(String idWebsite) {
        this.idWebsite = idWebsite;
    }

    public String getDemoLink() {
        return demoLink;
    }

    public void setDemoLink(String demoLink) {
        this.demoLink = demoLink;
    }

    public String getDemoId() {
        return demoId;
    }

    public void setDemoId(String demoId) {
        this.demoId = demoId;
    }

    public String getDemoPass() {
        return demoPass;
    }

    public void setDemoPass(String demoPass) {
        this.demoPass = demoPass;
    }

    public String getIdCreatedDdate() {
        return idCreatedDdate;
    }

    public void setIdCreatedDdate(String idCreatedDdate) {
        this.idCreatedDdate = idCreatedDdate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMinRefill() {
        return minRefill;
    }

    public void setMinRefill(String minRefill) {
        this.minRefill = minRefill;
    }

    public String getMinWithdrawal() {
        return minWithdrawal;
    }

    public void setMinWithdrawal(String minWithdrawal) {
        this.minWithdrawal = minWithdrawal;
    }

    public String getMinMaintainBal() {
        return minMaintainBal;
    }

    public void setMinMaintainBal(String minMaintainBal) {
        this.minMaintainBal = minMaintainBal;
    }

    public String getMaxWithdrawal() {
        return maxWithdrawal;
    }

    public void setMaxWithdrawal(String maxWithdrawal) {
        this.maxWithdrawal = maxWithdrawal;
    }
}
