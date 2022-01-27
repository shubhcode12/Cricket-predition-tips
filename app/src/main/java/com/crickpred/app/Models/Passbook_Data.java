package com.crickpred.app.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passbook_Data {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("createdId")
    @Expose
    private String createdId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("txnType")
    @Expose
    private String txnType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("txnId")
    @Expose
    private String txnId;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("approvalDate")
    @Expose
    private String approvalDate;

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("idWebsite")
    @Expose
    private String idWebsite;

    @SerializedName("idName")
    @Expose
    private String idName;

    public Passbook_Data(String id, String username, String mobile, String createdId, String amount, String txnType,
                         String status, String txnId,String createdDate,String approvalDate,String image,String idWebsite,String idName) {
        this.id = id;
        this.username = username;
        this.mobile = mobile;
        this.createdId = createdId;
        this.amount = amount;
        this.txnType = txnType;
        this.status = status;
        this.txnId = txnId;
        this.createdDate = createdDate;
        this.approvalDate = approvalDate;
        this.image=image;
        this.idWebsite=idWebsite;
        this.idName=idName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreatedId() {
        return createdId;
    }

    public void setCreatedId(String createdId) {
        this.createdId = createdId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdWebsite() {
        return idWebsite;
    }

    public void setIdWebsite(String idWebsite) {
        this.idWebsite = idWebsite;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }
}
