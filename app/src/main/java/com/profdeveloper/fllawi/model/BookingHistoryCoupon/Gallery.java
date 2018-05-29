package com.profdeveloper.fllawi.model.BookingHistoryCoupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gallery {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("coupon_id")
    @Expose
    private Integer couponId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("is_default")
    @Expose
    private Integer isDefault;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}