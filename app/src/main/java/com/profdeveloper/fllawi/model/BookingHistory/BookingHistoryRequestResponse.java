package com.profdeveloper.fllawi.model.BookingHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookingHistoryRequestResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("image_url")
    @Expose
    private String image_url;
    @SerializedName("obj_accomodation_booking")
    @Expose
    private ObjAccomodationBooking objAccomodationBooking;

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

    public ObjAccomodationBooking getObjAccomodationBooking() {
        return objAccomodationBooking;
    }

    public void setObjAccomodationBooking(ObjAccomodationBooking objAccomodationBooking) {
        this.objAccomodationBooking = objAccomodationBooking;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}