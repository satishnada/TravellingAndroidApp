package com.profdeveloper.fllawi.model.BookingHistoryThingToDo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThingtodoBookingAddon {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("thing_to_do_booking_id")
    @Expose
    private int thingToDoBookingId;
    @SerializedName("addon_id")
    @Expose
    private int addonId;
    @SerializedName("qty")
    @Expose
    private int qty;
    @SerializedName("base_amount")
    @Expose
    private String baseAmount;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("addon")
    @Expose
    private Addon addon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThingToDoBookingId() {
        return thingToDoBookingId;
    }

    public void setThingToDoBookingId(int thingToDoBookingId) {
        this.thingToDoBookingId = thingToDoBookingId;
    }

    public int getAddonId() {
        return addonId;
    }

    public void setAddonId(int addonId) {
        this.addonId = addonId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(String baseAmount) {
        this.baseAmount = baseAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
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

    public Addon getAddon() {
        return addon;
    }

    public void setAddon(Addon addon) {
        this.addon = addon;
    }

}