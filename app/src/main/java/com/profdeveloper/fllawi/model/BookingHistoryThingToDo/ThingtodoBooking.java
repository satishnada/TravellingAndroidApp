package com.profdeveloper.fllawi.model.BookingHistoryThingToDo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThingtodoBooking {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("internal_ref_no")
    @Expose
    private String internalRefNo;
    @SerializedName("gateway_ref_no")
    @Expose
    private String gatewayRefNo;
    @SerializedName("thing_to_do_id")
    @Expose
    private int thingToDoId;
    @SerializedName("host_id")
    @Expose
    private int hostId;
    @SerializedName("customer_id")
    @Expose
    private int customerId;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount;
    @SerializedName("discount_in_percent")
    @Expose
    private String discountInPercent;
    @SerializedName("discount_in_amount")
    @Expose
    private String discountInAmount;
    @SerializedName("tax_in_percent")
    @Expose
    private String taxInPercent;
    @SerializedName("tax_in_amount")
    @Expose
    private String taxInAmount;
    @SerializedName("adults_qty")
    @Expose
    private int adultsQty;
    @SerializedName("kids_qty")
    @Expose
    private int kidsQty;
    @SerializedName("applied_coupon")
    @Expose
    private String appliedCoupon;
    @SerializedName("schedule_date")
    @Expose
    private String scheduleDate;
    @SerializedName("schedule_start_time")
    @Expose
    private String scheduleStartTime;
    @SerializedName("schedule_end_time")
    @Expose
    private String scheduleEndTime;
    @SerializedName("schedule_type")
    @Expose
    private int scheduleType;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("cancel_refund_applicable")
    @Expose
    private int cancelRefundApplicable;
    @SerializedName("cancellation_charge")
    @Expose
    private int cancellationCharge;
    @SerializedName("refund_amount")
    @Expose
    private String refundAmount;
    @SerializedName("cancellation_amount")
    @Expose
    private String cancellationAmount;
    @SerializedName("cancellation_duration")
    @Expose
    private int cancellationDuration;
    @SerializedName("no_refund_duration")
    @Expose
    private int noRefundDuration;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("payment_mode")
    @Expose
    private int paymentMode;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("internal_failure_reason")
    @Expose
    private Object internalFailureReason;
    @SerializedName("payment_gateway_request_data")
    @Expose
    private Object paymentGatewayRequestData;
    @SerializedName("payment_gateway_response_data")
    @Expose
    private boolean paymentGatewayResponseData;
    @SerializedName("bank_receipt")
    @Expose
    private String bankReceipt;
    @SerializedName("notes")
    @Expose
    private Object notes;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("thingtodo")
    @Expose
    private Thingtodo thingtodo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInternalRefNo() {
        return internalRefNo;
    }

    public void setInternalRefNo(String internalRefNo) {
        this.internalRefNo = internalRefNo;
    }

    public String getGatewayRefNo() {
        return gatewayRefNo;
    }

    public void setGatewayRefNo(String gatewayRefNo) {
        this.gatewayRefNo = gatewayRefNo;
    }

    public int getThingToDoId() {
        return thingToDoId;
    }

    public void setThingToDoId(int thingToDoId) {
        this.thingToDoId = thingToDoId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(String discountInPercent) {
        this.discountInPercent = discountInPercent;
    }

    public String getDiscountInAmount() {
        return discountInAmount;
    }

    public void setDiscountInAmount(String discountInAmount) {
        this.discountInAmount = discountInAmount;
    }

    public String getTaxInPercent() {
        return taxInPercent;
    }

    public void setTaxInPercent(String taxInPercent) {
        this.taxInPercent = taxInPercent;
    }

    public String getTaxInAmount() {
        return taxInAmount;
    }

    public void setTaxInAmount(String taxInAmount) {
        this.taxInAmount = taxInAmount;
    }

    public int getAdultsQty() {
        return adultsQty;
    }

    public void setAdultsQty(int adultsQty) {
        this.adultsQty = adultsQty;
    }

    public int getKidsQty() {
        return kidsQty;
    }

    public void setKidsQty(int kidsQty) {
        this.kidsQty = kidsQty;
    }

    public String getAppliedCoupon() {
        return appliedCoupon;
    }

    public void setAppliedCoupon(String appliedCoupon) {
        this.appliedCoupon = appliedCoupon;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public String getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(String scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public int getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(int scheduleType) {
        this.scheduleType = scheduleType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCancelRefundApplicable() {
        return cancelRefundApplicable;
    }

    public void setCancelRefundApplicable(int cancelRefundApplicable) {
        this.cancelRefundApplicable = cancelRefundApplicable;
    }

    public int getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(int cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getCancellationAmount() {
        return cancellationAmount;
    }

    public void setCancellationAmount(String cancellationAmount) {
        this.cancellationAmount = cancellationAmount;
    }

    public int getCancellationDuration() {
        return cancellationDuration;
    }

    public void setCancellationDuration(int cancellationDuration) {
        this.cancellationDuration = cancellationDuration;
    }

    public int getNoRefundDuration() {
        return noRefundDuration;
    }

    public void setNoRefundDuration(int noRefundDuration) {
        this.noRefundDuration = noRefundDuration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(int paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Object getInternalFailureReason() {
        return internalFailureReason;
    }

    public void setInternalFailureReason(Object internalFailureReason) {
        this.internalFailureReason = internalFailureReason;
    }

    public Object getPaymentGatewayRequestData() {
        return paymentGatewayRequestData;
    }

    public void setPaymentGatewayRequestData(Object paymentGatewayRequestData) {
        this.paymentGatewayRequestData = paymentGatewayRequestData;
    }

    public boolean isPaymentGatewayResponseData() {
        return paymentGatewayResponseData;
    }

    public void setPaymentGatewayResponseData(boolean paymentGatewayResponseData) {
        this.paymentGatewayResponseData = paymentGatewayResponseData;
    }

    public String getBankReceipt() {
        return bankReceipt;
    }

    public void setBankReceipt(String bankReceipt) {
        this.bankReceipt = bankReceipt;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Thingtodo getThingtodo() {
        return thingtodo;
    }

    public void setThingtodo(Thingtodo thingtodo) {
        this.thingtodo = thingtodo;
    }

}