package com.profdeveloper.fllawi.model.HomeTopDestination;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopDestinationRequestResponse {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("arr_top_accomodation")
    @Expose
    private List<Object> arrTopAccomodation = null;
    @SerializedName("arr_top_thingtodo")
    @Expose
    private List<Object> arrTopThingtodo = null;
    @SerializedName("arr_top_coupon")
    @Expose
    private List<ArrTopCoupon> arrTopCoupon = null;
    @SerializedName("arr_top_event")
    @Expose
    private List<Object> arrTopEvent = null;
    @SerializedName("arr_top_package")
    @Expose
    private List<ArrTopPackage> arrTopPackage = null;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Object> getArrTopAccomodation() {
        return arrTopAccomodation;
    }

    public void setArrTopAccomodation(List<Object> arrTopAccomodation) {
        this.arrTopAccomodation = arrTopAccomodation;
    }

    public List<Object> getArrTopThingtodo() {
        return arrTopThingtodo;
    }

    public void setArrTopThingtodo(List<Object> arrTopThingtodo) {
        this.arrTopThingtodo = arrTopThingtodo;
    }

    public List<ArrTopCoupon> getArrTopCoupon() {
        return arrTopCoupon;
    }

    public void setArrTopCoupon(List<ArrTopCoupon> arrTopCoupon) {
        this.arrTopCoupon = arrTopCoupon;
    }

    public List<Object> getArrTopEvent() {
        return arrTopEvent;
    }

    public void setArrTopEvent(List<Object> arrTopEvent) {
        this.arrTopEvent = arrTopEvent;
    }

    public List<ArrTopPackage> getArrTopPackage() {
        return arrTopPackage;
    }

    public void setArrTopPackage(List<ArrTopPackage> arrTopPackage) {
        this.arrTopPackage = arrTopPackage;
    }

}