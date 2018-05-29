package com.profdeveloper.fllawi.model.ThingToDoPriceCalcu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ThingToDoCalculatePriceRequestResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("arr_calculation_break_down")
    @Expose
    private ArrCalculationBreakDown arrCalculationBreakDown;
    @SerializedName("arr_schedule_time")
    @Expose
    private ArrScheduleTime arrScheduleTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrCalculationBreakDown getArrCalculationBreakDown() {
        return arrCalculationBreakDown;
    }

    public void setArrCalculationBreakDown(ArrCalculationBreakDown arrCalculationBreakDown) {
        this.arrCalculationBreakDown = arrCalculationBreakDown;
    }

    public ArrScheduleTime getArrScheduleTime() {
        return arrScheduleTime;
    }

    public void setArrScheduleTime(ArrScheduleTime arrScheduleTime) {
        this.arrScheduleTime = arrScheduleTime;
    }

}