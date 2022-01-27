package com.crickpred.app.Models;

public class Redeem_Data {
    private String amount;
    private String paytm_no;
    private String type;
    private String req_date;
    private String paid_date;
    private String cancelled_date;
    private String txn;
    private String payment_method;
    public Redeem_Data(String payment_method, String amount, String paytm_no, String type,String req_date,String paid_date,
                       String cancelled_date, String txn) {
        this.payment_method=payment_method;
        this.amount = amount;
        this.paytm_no = paytm_no;
        this.type = type;
        this.req_date = req_date;
        this.paid_date = paid_date;
        this.cancelled_date = cancelled_date;
        this.txn = txn;

    }

    public String getPayment_method() {
        return payment_method;
    }

    public String getAmount() {
        return amount;
    }

    public String getPaytm_no() {
        return paytm_no;
    }

    public String getType() {
        return type;
    }

    public String getReq_date() {
        return req_date;
    }

    public String getPaid_date() {
        return paid_date;
    }

    public String getCancelled_date() {
        return cancelled_date;
    }

    public String getTxn() {
        return txn;
    }


}
