package com.profdeveloper.fllawi.model.AccommodationBooking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArrCalculationBreakDown implements Serializable {

    @SerializedName("discount_applicable")
    @Expose
    private boolean discountApplicable;
    @SerializedName("discount_percent")
    @Expose
    private int discountPercent;
    @SerializedName("discount_amount")
    @Expose
    private int discountAmount;
    @SerializedName("sub_total")
    @Expose
    private float subTotal;
    @SerializedName("addons_total")
    @Expose
    private int addonsTotal;
    @SerializedName("total_before_discount")
    @Expose
    private float totalBeforeDiscount;
    @SerializedName("total_after_discount")
    @Expose
    private float totalAfterDiscount;

    public boolean isDiscountApplicable() {
        return discountApplicable;
    }

    public void setDiscountApplicable(boolean discountApplicable) {
        this.discountApplicable = discountApplicable;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public int getAddonsTotal() {
        return addonsTotal;
    }

    public void setAddonsTotal(int addonsTotal) {
        this.addonsTotal = addonsTotal;
    }

    public float getTotalBeforeDiscount() {
        return totalBeforeDiscount;
    }

    public void setTotalBeforeDiscount(float totalBeforeDiscount) {
        this.totalBeforeDiscount = totalBeforeDiscount;
    }

    public float getTotalAfterDiscount() {
        return totalAfterDiscount;
    }

    public void setTotalAfterDiscount(float totalAfterDiscount) {
        this.totalAfterDiscount = totalAfterDiscount;
    }

}