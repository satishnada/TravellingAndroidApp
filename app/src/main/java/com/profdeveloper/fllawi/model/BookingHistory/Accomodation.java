package com.profdeveloper.fllawi.model.BookingHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Accomodation implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("subcategory_id")
    @Expose
    private Integer subcategoryId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("reservation_amount")
    @Expose
    private Integer reservationAmount;
    @SerializedName("weekend_one")
    @Expose
    private Double weekendOne;
    @SerializedName("weekend_two")
    @Expose
    private Double weekendTwo;
    @SerializedName("bird_discount_before")
    @Expose
    private Integer birdDiscountBefore;
    @SerializedName("bird_discount")
    @Expose
    private Integer birdDiscount;
    @SerializedName("cancel_refund_applicable")
    @Expose
    private Integer cancelRefundApplicable;
    @SerializedName("cancellation_charge")
    @Expose
    private Integer cancellationCharge;
    @SerializedName("cancellation_duration")
    @Expose
    private Integer cancellationDuration;
    @SerializedName("no_refund_duration")
    @Expose
    private Integer noRefundDuration;
    @SerializedName("contact_email")
    @Expose
    private String contactEmail;
    @SerializedName("contact_mobile")
    @Expose
    private String contactMobile;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("is_top_destination")
    @Expose
    private Integer isTopDestination;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("other_rules")
    @Expose
    private Object otherRules;
    @SerializedName("contact_name")
    @Expose
    private String contactName;
    @SerializedName("contact_position")
    @Expose
    private String contactPosition;
    @SerializedName("locale")
    @Expose
    private String locale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getReservationAmount() {
        return reservationAmount;
    }

    public void setReservationAmount(Integer reservationAmount) {
        this.reservationAmount = reservationAmount;
    }

    public Double getWeekendOne() {
        return weekendOne;
    }

    public void setWeekendOne(Double weekendOne) {
        this.weekendOne = weekendOne;
    }

    public Double getWeekendTwo() {
        return weekendTwo;
    }

    public void setWeekendTwo(Double weekendTwo) {
        this.weekendTwo = weekendTwo;
    }

    public Integer getBirdDiscountBefore() {
        return birdDiscountBefore;
    }

    public void setBirdDiscountBefore(Integer birdDiscountBefore) {
        this.birdDiscountBefore = birdDiscountBefore;
    }

    public Integer getBirdDiscount() {
        return birdDiscount;
    }

    public void setBirdDiscount(Integer birdDiscount) {
        this.birdDiscount = birdDiscount;
    }

    public Integer getCancelRefundApplicable() {
        return cancelRefundApplicable;
    }

    public void setCancelRefundApplicable(Integer cancelRefundApplicable) {
        this.cancelRefundApplicable = cancelRefundApplicable;
    }

    public Integer getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(Integer cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }

    public Integer getCancellationDuration() {
        return cancellationDuration;
    }

    public void setCancellationDuration(Integer cancellationDuration) {
        this.cancellationDuration = cancellationDuration;
    }

    public Integer getNoRefundDuration() {
        return noRefundDuration;
    }

    public void setNoRefundDuration(Integer noRefundDuration) {
        this.noRefundDuration = noRefundDuration;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsTopDestination() {
        return isTopDestination;
    }

    public void setIsTopDestination(Integer isTopDestination) {
        this.isTopDestination = isTopDestination;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getOtherRules() {
        return otherRules;
    }

    public void setOtherRules(Object otherRules) {
        this.otherRules = otherRules;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}