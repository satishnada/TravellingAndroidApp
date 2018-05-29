package com.profdeveloper.fllawi.model.BookingHistoryCoupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingHistoryCouponRequestResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("obj_coupon_booking")
    @Expose
    private ObjCouponBooking objCouponBooking;

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

    public ObjCouponBooking getObjCouponBooking() {
        return objCouponBooking;
    }

    public void setObjCouponBooking(ObjCouponBooking objCouponBooking) {
        this.objCouponBooking = objCouponBooking;
    }

}