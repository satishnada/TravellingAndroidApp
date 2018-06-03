package com.profdeveloper.fllawi.model.TopDestination;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.profdeveloper.fllawi.model.ThingToDoDetails.Gallery;
import com.profdeveloper.fllawi.model.ThingToDoDetails.Price;

import java.util.List;

public class ArrTopPackage {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("main_category_id")
    @Expose
    private Integer mainCategoryId;
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
    @SerializedName("unit")
    @Expose
    private Integer unit;
    @SerializedName("seat")
    @Expose
    private Integer seat;
    @SerializedName("weekend_one")
    @Expose
    private Integer weekendOne;
    @SerializedName("weekend_two")
    @Expose
    private Integer weekendTwo;
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
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
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
    @SerializedName("gallery")
    @Expose
    private List<Gallery> gallery = null;
    @SerializedName("price")
    @Expose
    private List<Price> price = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("why_you")
    @Expose
    private String whyYou;
    @SerializedName("additional_information")
    @Expose
    private String additionalInformation;
    @SerializedName("other_rules")
    @Expose
    private Object otherRules;
    @SerializedName("departure_point")
    @Expose
    private String departurePoint;
    @SerializedName("reporting_time")
    @Expose
    private String reportingTime;
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

    public Integer getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(Integer mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
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

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Integer getWeekendOne() {
        return weekendOne;
    }

    public void setWeekendOne(Integer weekendOne) {
        this.weekendOne = weekendOne;
    }

    public Integer getWeekendTwo() {
        return weekendTwo;
    }

    public void setWeekendTwo(Integer weekendTwo) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

    public List<Price> getPrice() {
        return price;
    }

    public void setPrice(List<Price> price) {
        this.price = price;
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

    public String getWhyYou() {
        return whyYou;
    }

    public void setWhyYou(String whyYou) {
        this.whyYou = whyYou;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Object getOtherRules() {
        return otherRules;
    }

    public void setOtherRules(Object otherRules) {
        this.otherRules = otherRules;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(String reportingTime) {
        this.reportingTime = reportingTime;
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