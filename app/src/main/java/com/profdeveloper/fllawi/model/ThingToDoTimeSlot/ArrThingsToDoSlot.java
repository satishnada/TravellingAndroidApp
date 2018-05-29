package com.profdeveloper.fllawi.model.ThingToDoTimeSlot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArrThingsToDoSlot implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("thing_to_do_id")
    @Expose
    private int thingToDoId;
    @SerializedName("thing_to_do_schedule_meta_id")
    @Expose
    private int thingToDoScheduleMetaId;
    @SerializedName("thing_to_do_schedule_id")
    @Expose
    private int thingToDoScheduleId;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
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

    public int getThingToDoScheduleMetaId() {
        return thingToDoScheduleMetaId;
    }

    public void setThingToDoScheduleMetaId(int thingToDoScheduleMetaId) {
        this.thingToDoScheduleMetaId = thingToDoScheduleMetaId;
    }

    public int getThingToDoScheduleId() {
        return thingToDoScheduleId;
    }

    public void setThingToDoScheduleId(int thingToDoScheduleId) {
        this.thingToDoScheduleId = thingToDoScheduleId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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