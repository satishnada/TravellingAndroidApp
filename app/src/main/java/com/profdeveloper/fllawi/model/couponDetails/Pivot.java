package com.profdeveloper.fllawi.model.couponDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pivot implements Serializable {

    @SerializedName("coupon_id")
    @Expose
    private int couponId;
    @SerializedName("inclusion_id")
    @Expose
    private int inclusionId;
    @SerializedName("is_applicable")
    @Expose
    private int isApplicable;

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getInclusionId() {
        return inclusionId;
    }

    public void setInclusionId(int inclusionId) {
        this.inclusionId = inclusionId;
    }

    public int getIsApplicable() {
        return isApplicable;
    }

    public void setIsApplicable(int isApplicable) {
        this.isApplicable = isApplicable;
    }

}