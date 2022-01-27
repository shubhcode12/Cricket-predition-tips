package com.crickpred.app.Models;

public class Wallet_Data {
    private String amount;
    private String type;
    private String date;
    private String txn;
    public Wallet_Data(String amount, String type, String date, String txn) {
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.txn = txn;
    }

    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getTxn() {
        return txn;
    }


}
