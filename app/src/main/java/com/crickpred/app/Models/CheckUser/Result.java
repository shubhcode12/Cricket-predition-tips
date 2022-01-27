package com.crickpred.app.Models.CheckUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("user_code")
    @Expose
    private String userCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("wallet")
    @Expose
    private String wallet;
    @SerializedName("success")
    @Expose
    private String success;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param wallet
     * @param phone
     * @param success
     * @param name
     * @param userCode
     * @param email
     */
    public Result(String userCode, String name, String email, String phone, String wallet, String success) {
        super();
        this.userCode = userCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.wallet = wallet;
        this.success = success;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}