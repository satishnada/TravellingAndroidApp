package com.profdeveloper.fllawi.model.ThingToDoSearch;

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
    private Integer totalCount;
    @SerializedName("max_price")
    @Expose
    private Integer maxPrice;
    @SerializedName("min_price")
    @Expose
    private Integer minPrice;
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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

}