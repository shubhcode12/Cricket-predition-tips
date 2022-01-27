package com.crickpred.app.Models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private int id;
    private String user_code;
    private String user_type;
    @SerializedName("full_name")
    private String name;
    private String email;
    @SerializedName("pwd")
    private String password;
    private String phone;
    @SerializedName("perm")
    private String confirm_code;
    private String total_point;
    private String status;
    private String deviceid;

    public User( String deviceid,String name, String email, String password, String phone, String confirm_code) {

        this.deviceid=deviceid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.confirm_code = confirm_code;

    }

    public int getId() {
        return id;
    }

    public String getUser_code() {
        return user_code;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getConfirm_code() {
        return confirm_code;
    }

    public String getTotal_point() {
        return total_point;
    }

    public String isStatus() {
        return status;
    }

    public String getDeviceid(){return deviceid;}
}
