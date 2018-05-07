package com.moderndeveloper.fllawi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserProfileDataRequestResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("arr_data")
    @Expose
    private ArrData arrData;
    @SerializedName("arr_country")
    @Expose
    private List<ArrCountry> arrCountry = null;
    @SerializedName("arr_payment_type")
    @Expose
    private List<ArrPaymentType> arrPaymentType = null;
    @SerializedName("arr_currency")
    @Expose
    private List<ArrCurrency> arrCurrency = null;
    @SerializedName("arr_functional_language")
    @Expose
    private List<ArrFunctionalLanguage> arrFunctionalLanguage = null;
    @SerializedName("arr_interest")
    @Expose
    private List<ArrInterest> arrInterest = null;
    @SerializedName("arr_business")
    @Expose
    private List<ArrBusiness> arrBusiness = null;

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

    public ArrData getArrData() {
        return arrData;
    }

    public void setArrData(ArrData arrData) {
        this.arrData = arrData;
    }

    public List<ArrCountry> getArrCountry() {
        return arrCountry;
    }

    public void setArrCountry(List<ArrCountry> arrCountry) {
        this.arrCountry = arrCountry;
    }

    public List<ArrPaymentType> getArrPaymentType() {
        return arrPaymentType;
    }

    public void setArrPaymentType(List<ArrPaymentType> arrPaymentType) {
        this.arrPaymentType = arrPaymentType;
    }

    public List<ArrCurrency> getArrCurrency() {
        return arrCurrency;
    }

    public void setArrCurrency(List<ArrCurrency> arrCurrency) {
        this.arrCurrency = arrCurrency;
    }

    public List<ArrFunctionalLanguage> getArrFunctionalLanguage() {
        return arrFunctionalLanguage;
    }

    public void setArrFunctionalLanguage(List<ArrFunctionalLanguage> arrFunctionalLanguage) {
        this.arrFunctionalLanguage = arrFunctionalLanguage;
    }

    public List<ArrInterest> getArrInterest() {
        return arrInterest;
    }

    public void setArrInterest(List<ArrInterest> arrInterest) {
        this.arrInterest = arrInterest;
    }

    public List<ArrBusiness> getArrBusiness() {
        return arrBusiness;
    }

    public void setArrBusiness(List<ArrBusiness> arrBusiness) {
        this.arrBusiness = arrBusiness;
    }

}