package com.crickpred.app.Models.CheckUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckUser {

    @SerializedName("Result")
    @Expose
    private List<Result> result = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public CheckUser() {
    }

    /**
     *
     * @param result
     */
    public CheckUser(List<Result> result) {
        super();
        this.result = result;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}
