package com.profdeveloper.fllawi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArrCountry implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("prefix_code")
    @Expose
    private String prefixCode;
    @SerializedName("iso_code")
    @Expose
    private String isoCode;
    @SerializedName("is_active")
    @Expose
    private int isActive;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("locale")
    @Expose
    private String locale;

    private int selectedPosition;

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrefixCode() {
        return prefixCode;
    }

    public void setPrefixCode(String prefixCode) {
        this.prefixCode = prefixCode;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}