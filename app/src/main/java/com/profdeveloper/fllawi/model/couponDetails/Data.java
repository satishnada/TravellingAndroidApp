package com.profdeveloper.fllawi.model.couponDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.profdeveloper.fllawi.model.ArrReview;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    @SerializedName("arr_data")
    @Expose
    private ArrData arrData;
    @SerializedName("arr_review")
    @Expose
    private List<ArrReview> arrReview = null;

    public ArrData getArrData() {
        return arrData;
    }

    public void setArrData(ArrData arrData) {
        this.arrData = arrData;
    }

    public List<ArrReview> getArrReview() {
        return arrReview;
    }

    public void setArrReview(List<ArrReview> arrReview) {
        this.arrReview = arrReview;
    }
}