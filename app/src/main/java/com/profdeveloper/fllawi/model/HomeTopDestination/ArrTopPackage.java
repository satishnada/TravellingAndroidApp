package com.profdeveloper.fllawi.model.HomeTopDestination;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.profdeveloper.fllawi.model.couponDetails.Gallery;

import java.util.List;

public class ArrTopPackage {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("main_category_id")
    @Expose
    private int mainCategoryId;
    @SerializedName("category_id")
    @Expose
    private int categoryId;
    @SerializedName("subcategory_id")
    @Expose
    private int subcategoryId;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("type")
    @Expose
    private int type;
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
    private double reservationAmount;
    @SerializedName("unit")
    @Expose
    private double unit;
    @SerializedName("seat")
    @Expose
    private int seat;
    @SerializedName("weekend_one")
    @Expose
    private int weekendOne;
    @SerializedName("weekend_two")
    @Expose
    private int weekendTwo;
    @SerializedName("bird_discount_before")
    @Expose
    private double birdDiscountBefore;
    @SerializedName("bird_discount")
    @Expose
    private double birdDiscount;
    @SerializedName("cancel_refund_applicable")
    @Expose
    private double cancelRefundApplicable;
    @SerializedName("cancellation_charge")
    @Expose
    private double cancellationCharge;
    @SerializedName("quantity")
    @Expose
    private double quantity;
    @SerializedName("cancellation_duration")
    @Expose
    private double cancellationDuration;
    @SerializedName("no_refund_duration")
    @Expose
    private double noRefundDuration;
    @SerializedName("contact_email")
    @Expose
    private String contactEmail;
    @SerializedName("contact_mobile")
    @Expose
    private String contactMobile;
    @SerializedName("is_active")
    @Expose
    private int isActive;
    @SerializedName("is_top_destination")
    @Expose
    private int isTopDestination;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("gallery")
    @Expose
    private List<Gallery_> gallery = null;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(int mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    public double getReservationAmount() {
        return reservationAmount;
    }

    public void setReservationAmount(int reservationAmount) {
        this.reservationAmount = reservationAmount;
    }

    public double getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getWeekendOne() {
        return weekendOne;
    }

    public void setWeekendOne(int weekendOne) {
        this.weekendOne = weekendOne;
    }

    public int getWeekendTwo() {
        return weekendTwo;
    }

    public void setWeekendTwo(int weekendTwo) {
        this.weekendTwo = weekendTwo;
    }

    public double getBirdDiscountBefore() {
        return birdDiscountBefore;
    }

    public void setBirdDiscountBefore(double birdDiscountBefore) {
        this.birdDiscountBefore = birdDiscountBefore;
    }

    public double getBirdDiscount() {
        return birdDiscount;
    }

    public void setBirdDiscount(double birdDiscount) {
        this.birdDiscount = birdDiscount;
    }

    public double getCancelRefundApplicable() {
        return cancelRefundApplicable;
    }

    public void setCancelRefundApplicable(double cancelRefundApplicable) {
        this.cancelRefundApplicable = cancelRefundApplicable;
    }

    public double getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(double cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getCancellationDuration() {
        return cancellationDuration;
    }

    public void setCancellationDuration(double cancellationDuration) {
        this.cancellationDuration = cancellationDuration;
    }

    public double getNoRefundDuration() {
        return noRefundDuration;
    }

    public void setNoRefundDuration(double noRefundDuration) {
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsTopDestination() {
        return isTopDestination;
    }

    public void setIsTopDestination(int isTopDestination) {
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

    public List<Gallery_> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery_> gallery) {
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