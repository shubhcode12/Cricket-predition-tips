package com.crickpred.app.Models;

public class Refer_Data {
    private String status_paid;
    private String joining_date;
    private String name;
    public Refer_Data(String status_paid, String joining_date, String name) {
        this.status_paid = status_paid;
        this.joining_date = joining_date;
        this.name = name;

    }

    public String getStatus_paid() {
        return status_paid;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public String getName() {
        return name;
    }




}
