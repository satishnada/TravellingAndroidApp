package com.profdeveloper.fllawi.model.AccommodationBooking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AccommodationBookingRequestResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("arr_calculation_break_down")
    @Expose
    private ArrCalculationBreakDown arrCalculationBreakDown;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrCalculationBreakDown getArrCalculationBreakDown() {
        return arrCalculationBreakDown;
    }

    public void setArrCalculationBreakDown(ArrCalculationBreakDown arrCalculationBreakDown) {
        this.arrCalculationBreakDown = arrCalculationBreakDown;
    }

}