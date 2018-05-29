package com.profdeveloper.fllawi.model.ThingToDoTimeSlot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ThingToDoTimeSlotRequestResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("arr_things_to_do_slots")
    @Expose
    private List<ArrThingsToDoSlot> arrThingsToDoSlots = null;

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

    public List<ArrThingsToDoSlot> getArrThingsToDoSlots() {
        return arrThingsToDoSlots;
    }

    public void setArrThingsToDoSlots(List<ArrThingsToDoSlot> arrThingsToDoSlots) {
        this.arrThingsToDoSlots = arrThingsToDoSlots;
    }

}