package com.profdeveloper.fllawi.model.BookingHistoryThingToDo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addon {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("thing_to_do_id")
    @Expose
    private int thingToDoId;
    @SerializedName("inclusion_id")
    @Expose
    private int inclusionId;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("inclusion")
    @Expose
    private Inclusion inclusion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThingToDoId() {
        return thingToDoId;
    }

    public void setThingToDoId(int thingToDoId) {
        this.thingToDoId = thingToDoId;
    }

    public int getInclusionId() {
        return inclusionId;
    }

    public void setInclusionId(int inclusionId) {
        this.inclusionId = inclusionId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

}