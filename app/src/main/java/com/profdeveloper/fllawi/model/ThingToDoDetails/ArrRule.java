package com.profdeveloper.fllawi.model.ThingToDoDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArrRule implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("thing_to_do_id")
    @Expose
    private Integer thingToDoId;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("child_age")
    @Expose
    private Integer childAge;
    @SerializedName("child_price")
    @Expose
    private Integer childPrice;
    @SerializedName("is_group")
    @Expose
    private Integer isGroup;
    @SerializedName("group_min_qty")
    @Expose
    private Integer groupMinQty;
    @SerializedName("group_max_qty")
    @Expose
    private Integer groupMaxQty;
    @SerializedName("group_price")
    @Expose
    private Integer groupPrice;
    @SerializedName("reporting_before_days")
    @Expose
    private Integer reportingBeforeDays;
    @SerializedName("max_advance_days")
    @Expose
    private Integer maxAdvanceDays;
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getChildAge() {
        return childAge;
    }

    public void setChildAge(Integer childAge) {
        this.childAge = childAge;
    }

    public Integer getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(Integer childPrice) {
        this.childPrice = childPrice;
    }

    public Integer getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Integer isGroup) {
        this.isGroup = isGroup;
    }

    public Integer getGroupMinQty() {
        return groupMinQty;
    }

    public void setGroupMinQty(Integer groupMinQty) {
        this.groupMinQty = groupMinQty;
    }

    public Integer getGroupMaxQty() {
        return groupMaxQty;
    }

    public void setGroupMaxQty(Integer groupMaxQty) {
        this.groupMaxQty = groupMaxQty;
    }

    public Integer getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(Integer groupPrice) {
        this.groupPrice = groupPrice;
    }

    public Integer getReportingBeforeDays() {
        return reportingBeforeDays;
    }

    public void setReportingBeforeDays(Integer reportingBeforeDays) {
        this.reportingBeforeDays = reportingBeforeDays;
    }

    public Integer getMaxAdvanceDays() {
        return maxAdvanceDays;
    }

    public void setMaxAdvanceDays(Integer maxAdvanceDays) {
        this.maxAdvanceDays = maxAdvanceDays;
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