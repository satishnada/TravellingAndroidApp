package com.moderndeveloper.fllawi.model.AccommodationDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ArrData implements Serializable{

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
    @SerializedName("reservation_amount")
    @Expose
    private int reservationAmount;
    @SerializedName("weekend_one")
    @Expose
    private double weekendOne;
    @SerializedName("weekend_two")
    @Expose
    private double weekendTwo;
    @SerializedName("bird_discount_before")
    @Expose
    private int birdDiscountBefore;
    @SerializedName("bird_discount")
    @Expose
    private int birdDiscount;
    @SerializedName("cancel_refund_applicable")
    @Expose
    private int cancelRefundApplicable;
    @SerializedName("cancellation_charge")
    @Expose
    private int cancellationCharge;
    @SerializedName("cancellation_duration")
    @Expose
    private int cancellationDuration;
    @SerializedName("no_refund_duration")
    @Expose
    private int noRefundDuration;
    @SerializedName("contact_email")
    @Expose
    private String contactEmail;
    @SerializedName("contact_mobile")
    @Expose
    private String contactMobile;
    @SerializedName("is_active")
    @Expose
    private int isActive;
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
    @SerializedName("accomodation_room")
    @Expose
    private AccomodationRoom accomodationRoom;
    @SerializedName("accomodation_rule")
    @Expose
    private AccomodationRule accomodationRule;
    @SerializedName("amenities")
    @Expose
    private List<Amenity> amenities = null;
    @SerializedName("inclusions")
    @Expose
    private List<Inclusion> inclusions = null;
    @SerializedName("price")
    @Expose
    private List<Price> price = null;
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

    public int getReservationAmount() {
        return reservationAmount;
    }

    public void setReservationAmount(int reservationAmount) {
        this.reservationAmount = reservationAmount;
    }

    public double getWeekendOne() {
        return weekendOne;
    }

    public void setWeekendOne(double weekendOne) {
        this.weekendOne = weekendOne;
    }

    public double getWeekendTwo() {
        return weekendTwo;
    }

    public void setWeekendTwo(double weekendTwo) {
        this.weekendTwo = weekendTwo;
    }

    public int getBirdDiscountBefore() {
        return birdDiscountBefore;
    }

    public void setBirdDiscountBefore(int birdDiscountBefore) {
        this.birdDiscountBefore = birdDiscountBefore;
    }

    public int getBirdDiscount() {
        return birdDiscount;
    }

    public void setBirdDiscount(int birdDiscount) {
        this.birdDiscount = birdDiscount;
    }

    public int getCancelRefundApplicable() {
        return cancelRefundApplicable;
    }

    public void setCancelRefundApplicable(int cancelRefundApplicable) {
        this.cancelRefundApplicable = cancelRefundApplicable;
    }

    public int getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(int cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }

    public int getCancellationDuration() {
        return cancellationDuration;
    }

    public void setCancellationDuration(int cancellationDuration) {
        this.cancellationDuration = cancellationDuration;
    }

    public int getNoRefundDuration() {
        return noRefundDuration;
    }

    public void setNoRefundDuration(int noRefundDuration) {
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

    public AccomodationRoom getAccomodationRoom() {
        return accomodationRoom;
    }

    public void setAccomodationRoom(AccomodationRoom accomodationRoom) {
        this.accomodationRoom = accomodationRoom;
    }

    public AccomodationRule getAccomodationRule() {
        return accomodationRule;
    }

    public void setAccomodationRule(AccomodationRule accomodationRule) {
        this.accomodationRule = accomodationRule;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<Inclusion> getInclusions() {
        return inclusions;
    }

    public void setInclusions(List<Inclusion> inclusions) {
        this.inclusions = inclusions;
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