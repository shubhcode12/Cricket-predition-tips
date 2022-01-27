package com.crickpred.app.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Create_ID_Data {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_name")
    @Expose
    private String idName;
    @SerializedName("id_image")
    @Expose
    private String idImage;
    @SerializedName("id_website")
    @Expose
    private String idWebsite;
    @SerializedName("id_status")
    @Expose

    private String demoId;
    @SerializedName("demoPass")
    @Expose
    private String demoPass;
    @SerializedName("demoLink")
    @Expose
    private String demoLink;
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
    @SerializedName("id_created_date")
    @Expose
    private String idStatus;
    @SerializedName("id_created_date")
    @Expose
    private String idCreatedDate;
    @SerializedName("id_updated_date")
    @Expose
    private String idUpdatedDate;
    @SerializedName("id_total_created")
    @Expose
    private String idTotalCreated;
    public Create_ID_Data(String id, String idName, String idImage, String idWebsite,
                          String idStatus, String demoId, String demoPass, String demoLink, String minRefill, String minWithdrawal, String minMaintainBal, String maxWithdrawal,
                          String idCreatedDate, String idUpdatedDate, String idTotalCreated) {
        this.id = id;
        this.idName = idName;
        this.idImage = idImage;
        this.idWebsite = idWebsite;
        this.idStatus = idStatus;
        this.demoId = demoId;
        this.demoPass = demoPass;
        this.demoLink = demoLink;
        this.minRefill = minRefill;
        this.minWithdrawal = minWithdrawal;
        this.minMaintainBal = minMaintainBal;
        this.maxWithdrawal = maxWithdrawal;
        this.idCreatedDate = idCreatedDate;
        this.idUpdatedDate = idUpdatedDate;
        this.idTotalCreated = idTotalCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getIdWebsite() {
        return idWebsite;
    }

    public void setIdWebsite(String idWebsite) {
        this.idWebsite = idWebsite;
    }

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
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

    public String getIdTotalCreated() {
        return idTotalCreated;
    }

    public void setIdTotalCreated(String idTotalCreated) {
        this.idTotalCreated = idTotalCreated;
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

    public String getDemoLink() {
        return demoLink;
    }

    public void setDemoLink(String demoLink) {
        this.demoLink = demoLink;
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
