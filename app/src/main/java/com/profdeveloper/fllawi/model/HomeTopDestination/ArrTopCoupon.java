package com.profdeveloper.fllawi.model.HomeTopDestination;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.profdeveloper.fllawi.model.couponDetails.Gallery;

import java.util.List;

public class ArrTopCoupon {

    @SerializedName("id")
    @Expose
    private int id;
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
    @SerializedName("base_amount")
    @Expose
    private double baseAmount;
    @SerializedName("qty")
    @Expose
    private int qty;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("valid_till_date")
    @Expose
    private String validTillDate;
    @SerializedName("discount_applicable")
    @Expose
    private int discountApplicable;
    @SerializedName("discount")
    @Expose
    private double discount;
    @SerializedName("contact_email")
    @Expose
    private String contactEmail;
    @SerializedName("contact_mobile")
    @Expose
    private String contactMobile;
    @SerializedName("allowed_gender")
    @Expose
    private int allowedGender;
    @SerializedName("is_active")
    @Expose
    private int isActive;
    @SerializedName("is_top_destination")
    @Expose
    private int isTopDestination;
    @SerializedName("is_publicly_available")
    @Expose
    private int isPubliclyAvailable;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("gallery")
    @Expose
    private List<Gallery> gallery = null;
    @SerializedName("coupon_rule")
    @Expose
    private Object couponRule;
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

    public Double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
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

    public String getValidTillDate() {
        return validTillDate;
    }

    public void setValidTillDate(String validTillDate) {
        this.validTillDate = validTillDate;
    }

    public Integer getDiscountApplicable() {
        return discountApplicable;
    }

    public void setDiscountApplicable(Integer discountApplicable) {
        this.discountApplicable = discountApplicable;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
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

    public Integer getAllowedGender() {
        return allowedGender;
    }

    public void setAllowedGender(Integer allowedGender) {
        this.allowedGender = allowedGender;
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

    public Integer getIsPubliclyAvailable() {
        return isPubliclyAvailable;
    }

    public void setIsPubliclyAvailable(Integer isPubliclyAvailable) {
        this.isPubliclyAvailable = isPubliclyAvailable;
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

    public Object getCouponRule() {
        return couponRule;
    }

    public void setCouponRule(Object couponRule) {
        this.couponRule = couponRule;
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