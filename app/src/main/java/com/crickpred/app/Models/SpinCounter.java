package com.crickpred.app.Models;

public class SpinCounter  {


    public String Spin_Count;
    public String Spin_Bid_Count;
    public String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public SpinCounter(String spin_Count, String spin_Bid_Count) {
        Spin_Count = spin_Count;
        Spin_Bid_Count = spin_Bid_Count;
    }

    public String getSpin_Count() {
        return Spin_Count;
    }

    public void setSpin_Count(String spin_Count) {
        Spin_Count = spin_Count;
    }

    public String getSpin_Bid_Count() {
        return Spin_Bid_Count;
    }

    public void setSpin_Bid_Count(String spin_Bid_Count) {
        Spin_Bid_Count = spin_Bid_Count;
    }

}
