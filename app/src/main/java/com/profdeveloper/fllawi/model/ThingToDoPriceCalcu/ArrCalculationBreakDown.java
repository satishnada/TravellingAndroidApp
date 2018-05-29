package com.profdeveloper.fllawi.model.ThingToDoPriceCalcu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArrCalculationBreakDown implements Serializable {

    @SerializedName("discount_applicable")
    @Expose
    private boolean discountApplicable;
    @SerializedName("discount_percent")
    @Expose
    private double discountPercent;
    @SerializedName("discount_amount")
    @Expose
    private double discountAmount;
    @SerializedName("sub_total")
    @Expose
    private double subTotal;
    @SerializedName("addons_total")
    @Expose
    private double addonsTotal;
    @SerializedName("total_before_discount")
    @Expose
    private double totalBeforeDiscount;
    @SerializedName("total_after_discount")
    @Expose
    private double totalAfterDiscount;

    public boolean isDiscountApplicable() {
        return discountApplicable;
    }

    public void setDiscountApplicable(boolean discountApplicable) {
        this.discountApplicable = discountApplicable;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public double getAddonsTotal() {
        return addonsTotal;
    }

    public void setAddonsTotal(int addonsTotal) {
        this.addonsTotal = addonsTotal;
    }

    public double getTotalBeforeDiscount() {
        return totalBeforeDiscount;
    }

    public void setTotalBeforeDiscount(int totalBeforeDiscount) {
        this.totalBeforeDiscount = totalBeforeDiscount;
    }

    public double getTotalAfterDiscount() {
        return totalAfterDiscount;
    }

    public void setTotalAfterDiscount(int totalAfterDiscount) {
        this.totalAfterDiscount = totalAfterDiscount;
    }

}