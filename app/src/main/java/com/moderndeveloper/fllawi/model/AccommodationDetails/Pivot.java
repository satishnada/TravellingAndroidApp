package com.moderndeveloper.fllawi.model.AccommodationDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pivot implements Serializable{

    @SerializedName("accomodation_id")
    @Expose
    private int accomodationId;
    @SerializedName("amenity_id")
    @Expose
    private int amenityId;
    @SerializedName("quantity")
    @Expose
    private int quantity;

    public int getAccomodationId() {
        return accomodationId;
    }

    public void setAccomodationId(int accomodationId) {
        this.accomodationId = accomodationId;
    }

    public int getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(int amenityId) {
        this.amenityId = amenityId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}