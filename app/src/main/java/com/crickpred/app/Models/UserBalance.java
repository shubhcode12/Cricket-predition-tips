package com.crickpred.app.Models;

import java.io.Serializable;

public class UserBalance implements Serializable {
    private int UserId;

    public void setuBalance(String uBalance) {
        this.uBalance = uBalance;
    }

    private String uBalance;

    public UserBalance(String uBalance) {
       this.uBalance = uBalance;
    }

    public String getuBalance() {
        return uBalance;
    }
}
