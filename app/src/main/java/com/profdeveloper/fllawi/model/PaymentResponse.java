package com.profdeveloper.fllawi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentResponse {
    @SerializedName("pt_transaction_id")
    @Expose
    private String pt_transaction_id;

    @SerializedName("pt_response_code")
    @Expose
    private String pt_response_code;

    @SerializedName("pt_description")
    @Expose
    private String pt_description;

    public String getPt_transaction_id() {
        return pt_transaction_id;
    }

    public void setPt_transaction_id(String pt_transaction_id) {
        this.pt_transaction_id = pt_transaction_id;
    }

    public String getPt_response_code() {
        return pt_response_code;
    }

    public void setPt_response_code(String pt_response_code) {
        this.pt_response_code = pt_response_code;
    }

    public String getPt_description() {
        return pt_description;
    }

    public void setPt_description(String pt_description) {
        this.pt_description = pt_description;
    }

}
