package com.profdeveloper.fllawi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonRequestResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}