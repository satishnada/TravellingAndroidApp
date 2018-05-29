package com.profdeveloper.fllawi.model.BookingHistoryThingToDo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingHistoryDetailThingToDoRequestResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("addon_total_sum")
    @Expose
    private int addonTotalSum;
    @SerializedName("thingtodo_booking")
    @Expose
    private ThingtodoBooking thingtodoBooking;
    @SerializedName("booking")
    @Expose
    private Booking booking;
    @SerializedName("thingtodo_booking_addons")
    @Expose
    private List<ThingtodoBookingAddon> thingtodoBookingAddons = null;
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

    public int getAddonTotalSum() {
        return addonTotalSum;
    }

    public void setAddonTotalSum(int addonTotalSum) {
        this.addonTotalSum = addonTotalSum;
    }

    public ThingtodoBooking getThingtodoBooking() {
        return thingtodoBooking;
    }

    public void setThingtodoBooking(ThingtodoBooking thingtodoBooking) {
        this.thingtodoBooking = thingtodoBooking;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public List<ThingtodoBookingAddon> getThingtodoBookingAddons() {
        return thingtodoBookingAddons;
    }

    public void setThingtodoBookingAddons(List<ThingtodoBookingAddon> thingtodoBookingAddons) {
        this.thingtodoBookingAddons = thingtodoBookingAddons;
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