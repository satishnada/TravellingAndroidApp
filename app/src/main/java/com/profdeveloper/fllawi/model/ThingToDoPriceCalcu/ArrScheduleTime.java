package com.profdeveloper.fllawi.model.ThingToDoPriceCalcu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArrScheduleTime implements Serializable {

    @SerializedName("schedule_start_time")
    @Expose
    private String scheduleStartTime;
    @SerializedName("schedule_end_time")
    @Expose
    private String scheduleEndTime;

    public String getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public String getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(String scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

}