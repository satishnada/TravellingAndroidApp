package com.moderndeveloper.fllawi.model.SearchHotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SearchResultMainData implements Serializable {

    @SerializedName("arr_main_category")
    @Expose
    private List<ArrMainCategory> arr_main_category = null;
    @SerializedName("arr_sub_category")
    @Expose
    private List<ArrSubCategory> arr_sub_category = null;
    @SerializedName("arr_data")
    @Expose
    private List<ArrDatum> arrData = null;
    @SerializedName("obj_data")
    @Expose
    private SearchHotelListData obj_data;
    @SerializedName("arr_booked_accomodation_id")
    @Expose
    private List<Integer> arrBookedAccomodationId = null;
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

    public List<ArrMainCategory> getCategory() {
        return arr_main_category;
    }

    public void setArrMainCategory(List<ArrMainCategory> arrMainCategory) {
        this.arr_main_category = arrMainCategory;
    }

    public List<ArrSubCategory> getSubCategory() {
        return arr_sub_category;
    }

    public void setArrSubCategory(List<ArrSubCategory> arrSubCategory) {
        this.arr_sub_category = arrSubCategory;
    }

    public List<ArrDatum> getArrData() {
        return arrData;
    }

    public void setArrData(List<ArrDatum> arrData) {
        this.arrData = arrData;
    }

    public SearchHotelListData getSearchHotelList() {
        return obj_data;
    }

    public void setSearchHotelListData(SearchHotelListData searchHotelListData) {
        this.obj_data = searchHotelListData;
    }

    public List<Integer> getArrBookedAccomodationId() {
        return arrBookedAccomodationId;
    }

    public void setArrBookedAccomodationId(List<Integer> arrBookedAccomodationId) {
        this.arrBookedAccomodationId = arrBookedAccomodationId;
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