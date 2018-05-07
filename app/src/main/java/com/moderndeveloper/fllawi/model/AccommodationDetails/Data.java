package com.moderndeveloper.fllawi.model.AccommodationDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{

    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("arr_allowable")
    @Expose
    private List<String> arrAllowable = null;
    @SerializedName("arr_not_allowable")
    @Expose
    private List<String> arrNotAllowable = null;
    @SerializedName("arr_included")
    @Expose
    private List<String> arrIncluded = null;
    @SerializedName("arr_not_included")
    @Expose
    private List<String> arrNotIncluded = null;
    @SerializedName("arr_requirment")
    @Expose
    private List<String> arrRequirment = null;
    @SerializedName("arr_data")
    @Expose
    private ArrData arrData;
    @SerializedName("arr_similar")
    @Expose
    private List<ArrSimilar> arrSimilar = null;
    @SerializedName("arr_review")
    @Expose
    private List<ArrReview> arrReview = null;
    @SerializedName("json_avail_date")
    @Expose
    private String jsonAvailDate;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getArrAllowable() {
        return arrAllowable;
    }

    public void setArrAllowable(List<String> arrAllowable) {
        this.arrAllowable = arrAllowable;
    }

    public List<String> getArrNotAllowable() {
        return arrNotAllowable;
    }

    public void setArrNotAllowable(List<String> arrNotAllowable) {
        this.arrNotAllowable = arrNotAllowable;
    }

    public List<String> getArrIncluded() {
        return arrIncluded;
    }

    public void setArrIncluded(List<String> arrIncluded) {
        this.arrIncluded = arrIncluded;
    }

    public List<String> getArrNotIncluded() {
        return arrNotIncluded;
    }

    public void setArrNotIncluded(List<String> arrNotIncluded) {
        this.arrNotIncluded = arrNotIncluded;
    }

    public List<String> getArrRequirment() {
        return arrRequirment;
    }

    public void setArrRequirment(List<String> arrRequirment) {
        this.arrRequirment = arrRequirment;
    }

    public ArrData getArrData() {
        return arrData;
    }

    public void setArrData(ArrData arrData) {
        this.arrData = arrData;
    }

    public List<ArrSimilar> getArrSimilar() {
        return arrSimilar;
    }

    public void setArrSimilar(List<ArrSimilar> arrSimilar) {
        this.arrSimilar = arrSimilar;
    }

    public List<ArrReview> getArrReview() {
        return arrReview;
    }

    public void setArrReview(List<ArrReview> arrReview) {
        this.arrReview = arrReview;
    }

    public String getJsonAvailDate() {
        return jsonAvailDate;
    }

    public void setJsonAvailDate(String jsonAvailDate) {
        this.jsonAvailDate = jsonAvailDate;
    }

}