package com.profdeveloper.fllawi.model.ThingToDoDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Gallery implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("thing_to_do_id")
    @Expose
    private Integer thingToDoId;
    @SerializedName("is_default")
    @Expose
    private Integer isDefault;
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

    public Integer getThingToDoId() {
        return thingToDoId;
    }

    public void setThingToDoId(Integer thingToDoId) {
        this.thingToDoId = thingToDoId;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
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