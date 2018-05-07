package com.moderndeveloper.fllawi.model.couponDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {

    @SerializedName("arr_data")
    @Expose
    private ArrData arrData;

    public ArrData getArrData() {
        return arrData;
    }

    public void setArrData(ArrData arrData) {
        this.arrData = arrData;
    }

}