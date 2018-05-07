package com.moderndeveloper.fllawi.model.couponDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.moderndeveloper.fllawi.model.AccommodationDetails.Translation;

import java.io.Serializable;
import java.util.List;

public class ArrData implements Serializable {

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
    private float baseAmount;
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
    private int discount;
    @SerializedName("contact_email")
    @Expose
    private String contactEmail;
    @SerializedName("contact_mobile")
    @Expose
    private String contactMobile;
    @SerializedName("is_active")
    @Expose
    private int isActive;
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
    @SerializedName("translations")
    @Expose
    private List<Translation> translations = null;
    @SerializedName("host_provider")
    @Expose
    private HostProvider hostProvider;
    @SerializedName("coupon_rule")
    @Expose
    private Object couponRule;
    @SerializedName("inclusions")
    @Expose
    private List<Inclusion> inclusions = null;
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
    @SerializedName("arr_similar")
    @Expose
    private List<ArrSimilar> arrSimilar = null;
    @SerializedName("arr_review")
    @Expose
    private ArrReview arrReview;
    @SerializedName("arr_allowable")
    @Expose
    private List<String> arrAllowable = null;
    @SerializedName("arr_not_allowable")
    @Expose
    private List<Object> arrNotAllowable = null;
    @SerializedName("arr_included")
    @Expose
    private List<Object> arrIncluded = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(float baseAmount) {
        this.baseAmount = baseAmount;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
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

    public int getDiscountApplicable() {
        return discountApplicable;
    }

    public void setDiscountApplicable(int discountApplicable) {
        this.discountApplicable = discountApplicable;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsPubliclyAvailable() {
        return isPubliclyAvailable;
    }

    public void setIsPubliclyAvailable(int isPubliclyAvailable) {
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

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public HostProvider getHostProvider() {
        return hostProvider;
    }

    public void setHostProvider(HostProvider hostProvider) {
        this.hostProvider = hostProvider;
    }

    public Object getCouponRule() {
        return couponRule;
    }

    public void setCouponRule(Object couponRule) {
        this.couponRule = couponRule;
    }

    public List<Inclusion> getInclusions() {
        return inclusions;
    }

    public void setInclusions(List<Inclusion> inclusions) {
        this.inclusions = inclusions;
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

    public List<ArrSimilar> getArrSimilar() {
        return arrSimilar;
    }

    public void setArrSimilar(List<ArrSimilar> arrSimilar) {
        this.arrSimilar = arrSimilar;
    }

    public ArrReview getArrReview() {
        return arrReview;
    }

    public void setArrReview(ArrReview arrReview) {
        this.arrReview = arrReview;
    }

    public List<String> getArrAllowable() {
        return arrAllowable;
    }

    public void setArrAllowable(List<String> arrAllowable) {
        this.arrAllowable = arrAllowable;
    }

    public List<Object> getArrNotAllowable() {
        return arrNotAllowable;
    }

    public void setArrNotAllowable(List<Object> arrNotAllowable) {
        this.arrNotAllowable = arrNotAllowable;
    }

    public List<Object> getArrIncluded() {
        return arrIncluded;
    }

    public void setArrIncluded(List<Object> arrIncluded) {
        this.arrIncluded = arrIncluded;
    }

}