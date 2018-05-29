package com.profdeveloper.fllawi.model.BookingHistoryCoupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingHistoryDetailCouponRequestResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("booking")
    @Expose
    private Booking booking;
    @SerializedName("bank_receipt_path")
    @Expose
    private String bankReceiptPath;
    @SerializedName("arr_cancellation_details")
    @Expose
    private ArrCancellationDetails arrCancellationDetails;
    @SerializedName("arr_cancelled_status")
    @Expose
    private List<Integer> arrCancelledStatus = null;

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

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getBankReceiptPath() {
        return bankReceiptPath;
    }

    public void setBankReceiptPath(String bankReceiptPath) {
        this.bankReceiptPath = bankReceiptPath;
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

}