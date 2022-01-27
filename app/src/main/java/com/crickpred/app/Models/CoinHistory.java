package com.crickpred.app.Models;

public class CoinHistory {

    private String Description;
    private String Date;
    private String Coin;


    public String getDescription() {
        return Description;
    }

    public String getDate() {
        return Date;
    }

    public String getCoin() {
        return Coin;
    }



    public CoinHistory(String description, String date, String coin) {
        Description = description;
        Date = date;
        Coin = coin;

    }


}
