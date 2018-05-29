package com.profdeveloper.fllawi.model.BookingHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BookingHistoryDetailRequestResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("arr_accomodation_booking")
    @Expose
    private ArrAccomodationBooking arrAccomodationBooking;
    @SerializedName("addon_total_sum")
    @Expose
    private Integer addonTotalSum;
    @SerializedName("arr_addons")
    @Expose
    private List<Object> arrAddons = null;
    @SerializedName("arr_cancellation_details")
    @Expose
    private ArrCancellationDetails arrCancellationDetails;
    @SerializedName("arr_cancelled_status")
    @Expose
    private List<Integer> arrCancelledStatus = null;
    @SerializedName("bank_receipt_path")
    @Expose
    private String bankReceiptPath;

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

    public ArrAccomodationBooking getArrAccomodationBooking() {
        return arrAccomodationBooking;
    }

    public void setArrAccomodationBooking(ArrAccomodationBooking arrAccomodationBooking) {
        this.arrAccomodationBooking = arrAccomodationBooking;
    }

    public Integer getAddonTotalSum() {
        return addonTotalSum;
    }

    public void setAddonTotalSum(Integer addonTotalSum) {
        this.addonTotalSum = addonTotalSum;
    }

    public List<Object> getArrAddons() {
        return arrAddons;
    }

    public void setArrAddons(List<Object> arrAddons) {
        this.arrAddons = arrAddons;
    }

    public ArrCancellationDetails getArrCancellationDetails() {
        return arrCancellationDetails;
    }

    public void setArrCancellationDetails(ArrCancellationDetails arrCancellationDetails) {
        this.arrCancellationDetails = arrCancellationDetails;
    }

    public List<Integer> getArrCancelledStatus() {
        return arrCancelledStatus;
    }

    public void setArrCancelledStatus(List<Integer> arrCancelledStatus) {
        this.arrCancelledStatus = arrCancelledStatus;
    }

    public String getBankReceiptPath() {
        return bankReceiptPath;
    }

    public void setBankReceiptPath(String bankReceiptPath) {
        this.bankReceiptPath = bankReceiptPath;
    }

}