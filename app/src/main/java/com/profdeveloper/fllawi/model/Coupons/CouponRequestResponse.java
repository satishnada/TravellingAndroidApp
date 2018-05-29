package com.profdeveloper.fllawi.model.Coupons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CouponRequestResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("applied_coupon")
    @Expose
    private Object appliedCoupon;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getAppliedCoupon() {
        return appliedCoupon;
    }

    public void setAppliedCoupon(Object appliedCoupon) {
        this.appliedCoupon = appliedCoupon;
    }

}