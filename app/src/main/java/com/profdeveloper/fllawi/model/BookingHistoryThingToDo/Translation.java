package com.profdeveloper.fllawi.model.BookingHistoryThingToDo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translation {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("thing_to_do_id")
    @Expose
    private int thingToDoId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("duration")
    @Expose
    private String duration;
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
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThingToDoId() {
        return thingToDoId;
    }

    public void setThingToDoId(int thingToDoId) {
        this.thingToDoId = thingToDoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

}