package com.crickpred.app.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class View_IDTXN_Data {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idimageurl")
    @Expose
    private String idimageurl;

    @SerializedName("idname")
    @Expose
    private String idname;

    @SerializedName("userMobile")
    @Expose
    private String userMobile;
    @SerializedName("txnType")
    @Expose
    private String txnType;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("createdId")
    @Expose
    private String createdId;
    @SerializedName("payMentMode")
    @Expose
    private String payMentMode;
    @SerializedName("txnStatus")
    @Expose
    private String txnStatus;
    @SerializedName("idUsername")
    @Expose
    private String idUsername;
    @SerializedName("idPassword")
    @Expose
    private String idPassword;
    @SerializedName("adminComment")
    @Expose
    private String adminComment;
    @SerializedName("txnDate")
    @Expose
    private String txnDate;
    @SerializedName("cancelledDate")
    @Expose
    private String cancelledDate;
    @SerializedName("verifiedDate")
    @Expose
    private String verifiedDate;
    public View_IDTXN_Data(String id,String idimageurl,String idname, String userMobile, String txnType, String orderId, String amount, String createdId,
                           String payMentMode, String txnStatus, String idUsername, String idPassword, String adminComment,
                           String txnDate, String cancelledDate, String verifiedDate) {
        this.id = id;
        this.idimageurl = idimageurl;
        this.idname = idname;
        this.userMobile = userMobile;
        this.txnType = txnType;
        this.orderId = orderId;
        this.amount = amount;
        this.createdId = createdId;
        this.payMentMode = payMentMode;
        this.txnStatus = txnStatus;
        this.idUsername = idUsername;
        this.idPassword = idPassword;
        this.adminComment = adminComment;
        this.txnDate = txnDate;
        this.cancelledDate = cancelledDate;
        this.verifiedDate = verifiedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdimageurl() {
        return idimageurl;
    }

    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname;
    }

    public void setIdimageurl(String idimageurl) {
        this.idimageurl = idimageurl;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
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

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public String getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(String cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }
}
