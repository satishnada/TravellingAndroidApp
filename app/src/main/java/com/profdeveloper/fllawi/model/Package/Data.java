package com.profdeveloper.fllawi.model.Package;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.profdeveloper.fllawi.model.SearchHotel.ArrMainCategory;
import com.profdeveloper.fllawi.model.SearchHotel.ArrSubCategory;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    @SerializedName("arr_main_category")
    @Expose
    private List<ArrMainCategory> arrMainCategory = null;
    @SerializedName("arr_sub_category")
    @Expose
    private List<ArrSubCategory> arrSubCategory = null;
    @SerializedName("obj_data")
    @Expose
    private ObjData objData;
    @SerializedName("total_count")
    @Expose
    private double totalCount;
    @SerializedName("max_price")
    @Expose
    private double maxPrice;
    @SerializedName("min_price")
    @Expose
    private double minPrice;
    @SerializedName("search_time")
    @Expose
    private String searchTime;

    public List<ArrMainCategory> getArrMainCategory() {
        return arrMainCategory;
    }

    public void setArrMainCategory(List<ArrMainCategory> arrMainCategory) {
        this.arrMainCategory = arrMainCategory;
    }

    public List<ArrSubCategory> getArrSubCategory() {
        return arrSubCategory;
    }

    public void setArrSubCategory(List<ArrSubCategory> arrSubCategory) {
        this.arrSubCategory = arrSubCategory;
    }

    public ObjData getObjData() {
        return objData;
    }

    public void setObjData(ObjData objData) {
        this.objData = objData;
    }

    public double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(double totalCount) {
        this.totalCount = totalCount;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

}