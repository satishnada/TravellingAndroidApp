package com.moderndeveloper.fllawi.model.Coupons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    @SerializedName("arr_type")
    @Expose
    private ArrType arrType;
    @SerializedName("arr_data")
    @Expose
    private List<ArrDatum> arrData = null;
    @SerializedName("obj_data")
    @Expose
    private ObjData objData;
    @SerializedName("total_count")
    @Expose
    private int totalCount;
    @SerializedName("max_price")
    @Expose
    private int maxPrice;
    @SerializedName("min_price")
    @Expose
    private int minPrice;
    @SerializedName("search_time")
    @Expose
    private String searchTime;

    public ArrType getArrType() {
        return arrType;
    }

    public void setArrType(ArrType arrType) {
        this.arrType = arrType;
    }

    public List<ArrDatum> getArrData() {
        return arrData;
    }

    public void setArrData(List<ArrDatum> arrData) {
        this.arrData = arrData;
    }

    public ObjData getObjData() {
        return objData;
    }

    public void setObjData(ObjData objData) {
        this.objData = objData;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

}