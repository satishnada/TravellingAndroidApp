package com.profdeveloper.fllawi.model.ThingToDoDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Price implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("thing_to_do_id")
    @Expose
    private Integer thingToDoId;
    @SerializedName("date_from")
    @Expose
    private String dateFrom;
    @SerializedName("date_to")
    @Expose
    private String dateTo;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("type")
    @Expose
    private Integer type;
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

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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