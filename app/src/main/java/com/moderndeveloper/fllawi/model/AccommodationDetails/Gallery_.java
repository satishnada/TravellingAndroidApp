package com.moderndeveloper.fllawi.model.AccommodationDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Gallery_ implements Serializable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("accomodation_id")
    @Expose
    private int accomodationId;
    @SerializedName("is_default")
    @Expose
    private int isDefault;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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