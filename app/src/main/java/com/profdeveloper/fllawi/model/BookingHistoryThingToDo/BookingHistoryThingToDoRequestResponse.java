package com.profdeveloper.fllawi.model.BookingHistoryThingToDo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingHistoryThingToDoRequestResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("obj_thingtodo_booking")
    @Expose
    private ObjThingtodoBooking objThingtodoBooking;

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

    public ObjThingtodoBooking getObjThingtodoBooking() {
        return objThingtodoBooking;
    }

    public void setObjThingtodoBooking(ObjThingtodoBooking objThingtodoBooking) {
        this.objThingtodoBooking = objThingtodoBooking;
    }

}