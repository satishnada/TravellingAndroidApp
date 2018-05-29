package com.profdeveloper.fllawi.model.Coupons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArrDatum implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("base_amount")
    @Expose
    private float baseAmount;
    @SerializedName("discount_applicable")
    @Expose
    private int discountApplicable;
    @SerializedName("discount")
    @Expose
    private int discount;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("used_qty")
    @Expose
    private String usedQty;
    @SerializedName("total_qty")
    @Expose
    private int totalQty;
    @SerializedName("avail_qty")
    @Expose
    private String availQty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(float baseAmount) {
        this.baseAmount = baseAmount;
    }

    public int getDiscountApplicable() {
        return discountApplicable;
    }

    public void setDiscountApplicable(int discountApplicable) {
        this.discountApplicable = discountApplicable;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsedQty() {
        return usedQty;
    }

    public void setUsedQty(String usedQty) {
        this.usedQty = usedQty;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public String getAvailQty() {
        return availQty;
    }

    public void setAvailQty(String availQty) {
        this.availQty = availQty;
    }

}