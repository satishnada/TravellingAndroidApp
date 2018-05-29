package com.profdeveloper.fllawi.model.Coupons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetCouponRequestResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}