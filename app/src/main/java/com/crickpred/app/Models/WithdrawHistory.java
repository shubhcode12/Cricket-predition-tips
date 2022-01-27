package com.crickpred.app.Models;

public class WithdrawHistory {

    private String Description;
    private String Paydetails;
    private String Date;
    private String Coin;
    private String Status;


    public String getDescription() {
        return Description;
    }

    public String getPaydetails() {
        return Paydetails;
    }

    public void setPaydetails(String paydetails) {
        this.Paydetails = paydetails;
    }

    public String getDate() {
        return Date;
    }

    public String getCoin() {
        return Coin;
    }

    public String getStatus() {
        return Status;
    }


    public WithdrawHistory(String description, String paydetails, String date, String coin, String status) {
        Description = description;
        Paydetails= paydetails;
        Date = date;
        Coin = coin;
        Status = status;
    }
}
