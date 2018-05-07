package com.moderndeveloper.fllawi.model.SearchHotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchRequestResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("data")
    @Expose
    private SearchResultMainData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SearchResultMainData getSearchResultMainData() {
        return data;
    }

    public void setSearchResultMainData(SearchResultMainData searchResultMainData) {
        this.data = searchResultMainData;
    }

}