package com.moderndeveloper.fllawi.model.AccommodationDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AccomodationRoom implements Serializable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("accomodation_id")
    @Expose
    private int accomodationId;
    @SerializedName("no_of_rooms")
    @Expose
    private int noOfRooms;
    @SerializedName("no_of_bedrooms")
    @Expose
    private int noOfBedrooms;
    @SerializedName("no_of_beds")
    @Expose
    private int noOfBeds;
    @SerializedName("no_of_bathrooms")
    @Expose
    private int noOfBathrooms;
    @SerializedName("no_of_living_rooms")
    @Expose
    private int noOfLivingRooms;
    @SerializedName("no_of_guest_rooms")
    @Expose
    private int noOfGuestRooms;
    @SerializedName("no_of_balcony")
    @Expose
    private int noOfBalcony;
    @SerializedName("no_of_roof")
    @Expose
    private int noOfRoof;
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

    public int getAccomodationId() {
        return accomodationId;
    }

    public void setAccomodationId(int accomodationId) {
        this.accomodationId = accomodationId;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public int getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public void setNoOfBedrooms(int noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public int getNoOfBeds() {
        return noOfBeds;
    }

    public void setNoOfBeds(int noOfBeds) {
        this.noOfBeds = noOfBeds;
    }

    public int getNoOfBathrooms() {
        return noOfBathrooms;
    }

    public void setNoOfBathrooms(int noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }

    public int getNoOfLivingRooms() {
        return noOfLivingRooms;
    }

    public void setNoOfLivingRooms(int noOfLivingRooms) {
        this.noOfLivingRooms = noOfLivingRooms;
    }

    public int getNoOfGuestRooms() {
        return noOfGuestRooms;
    }

    public void setNoOfGuestRooms(int noOfGuestRooms) {
        this.noOfGuestRooms = noOfGuestRooms;
    }

    public int getNoOfBalcony() {
        return noOfBalcony;
    }

    public void setNoOfBalcony(int noOfBalcony) {
        this.noOfBalcony = noOfBalcony;
    }

    public int getNoOfRoof() {
        return noOfRoof;
    }

    public void setNoOfRoof(int noOfRoof) {
        this.noOfRoof = noOfRoof;
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