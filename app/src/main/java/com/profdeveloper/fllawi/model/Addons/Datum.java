package com.profdeveloper.fllawi.model.Addons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Datum implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("accomodation_id")
    @Expose
    private int accomodationId;
    @SerializedName("inclusion_id")
    @Expose
    private int inclusionId;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("inclusion")
    @Expose
    private Inclusion inclusion;
    @SerializedName("quantity")
    @Expose
    private String quantity = "0";
    @SerializedName("subTotal")
    @Expose
    private String subTotal = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccomodationId() {
        return accomodationId;
    }

    public void setAccomodationId(int accomodationId) {
        this.accomodationId = accomodationId;
    }

    public int getInclusionId() {
        return inclusionId;
    }

    public void setInclusionId(int inclusionId) {
        this.inclusionId = inclusionId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public Inclusion getInclusion() {
        return inclusion;
    }

    public void setInclusion(Inclusion inclusion) {
        this.inclusion = inclusion;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }
}