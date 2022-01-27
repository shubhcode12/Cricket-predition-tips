package com.crickpred.app.Models;

public class AdsItems {
    private int ads_icon;
    private String ads_tittle;
    private String ads_description;
    private String ads_points;

    public AdsItems(int ads_icon, String ads_tittle, String ads_description, String ads_points) {
        this.ads_icon = ads_icon;
        this.ads_tittle = ads_tittle;
        this.ads_description = ads_description;
        this.ads_points = ads_points;
    }

    public int getAds_icon() {
        return ads_icon;
    }

    public void setAds_icon(int ads_icon) {
        this.ads_icon = ads_icon;
    }

    public String getAds_tittle() {
        return ads_tittle;
    }

    public void setAds_tittle(String ads_tittle) {
        this.ads_tittle = ads_tittle;
    }

    public String getAds_description() {
        return ads_description;
    }

    public void setAds_description(String ads_description) {
        this.ads_description = ads_description;
    }

    public String getAds_points() {
        return ads_points;
    }

    public void setAds_points(String ads_points) {
        this.ads_points = ads_points;
    }
}
