package com.profdeveloper.fllawi.model.BookingHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArrAccomodationBooking implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("internal_ref_no")
    @Expose
    private String internalRefNo;
    @SerializedName("gateway_ref_no")
    @Expose
    private String gatewayRefNo;
    @SerializedName("accomodation_id")
    @Expose
    private int accomodationId;
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
    private double discountInPercent;
    @SerializedName("discount_in_amount")
    @Expose
    private double discountInAmount;
    @SerializedName("tax_in_percent")
    @Expose
    private double taxInPercent;
    @SerializedName("tax_in_amount")
    @Expose
    private double taxInAmount;
    @SerializedName("adults_qty")
    @Expose
    private int adultsQty;
    @SerializedName("kids_qty")
    @Expose
    private int kidsQty;
    @SerializedName("applied_coupon")
    @Expose
    private String appliedCoupon;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("cancel_refund_applicable")
    @Expose
    private double cancelRefundApplicable;
    @SerializedName("cancellation_charge")
    @Expose
    private double cancellationCharge;
    @SerializedName("refund_amount")
    @Expose
    private double refundAmount;
    @SerializedName("cancellation_amount")
    @Expose
    private double cancellationAmount;
    @SerializedName("cancellation_duration")
    @Expose
    private double cancellationDuration;
    @SerializedName("no_refund_duration")
    @Expose
    private double noRefundDuration;
    @SerializedName("type")
    @Expose
    private int type;
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
    private String internalFailureReason;
    @SerializedName("payment_gateway_request_data")
    @Expose
    private String paymentGatewayRequestData;
    @SerializedName("payment_gateway_response_data")
    @Expose
    private Boolean paymentGatewayResponseData;
    @SerializedName("bank_receipt")
    @Expose
    private String bankReceipt;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("accomodation")
    @Expose
    private Accomodation accomodation;

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

    public int getAccomodationId() {
        return accomodationId;
    }

    public void setAccomodationId(int accomodationId) {
        this.accomodationId = accomodationId;
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

    public double getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(double discountInPercent) {
        this.discountInPercent = discountInPercent;
    }

    public double getDiscountInAmount() {
        return discountInAmount;
    }

    public void setDiscountInAmount(double discountInAmount) {
        this.discountInAmount = discountInAmount;
    }

    public double getTaxInPercent() {
        return taxInPercent;
    }

    public void setTaxInPercent(double taxInPercent) {
        this.taxInPercent = taxInPercent;
    }

    public double getTaxInAmount() {
        return taxInAmount;
    }

    public void setTaxInAmount(double taxInAmount) {
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public double getCancelRefundApplicable() {
        return cancelRefundApplicable;
    }

    public void setCancelRefundApplicable(double cancelRefundApplicable) {
        this.cancelRefundApplicable = cancelRefundApplicable;
    }

    public double getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(double cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public double getCancellationAmount() {
        return cancellationAmount;
    }

    public void setCancellationAmount(double cancellationAmount) {
        this.cancellationAmount = cancellationAmount;
    }

    public double getCancellationDuration() {
        return cancellationDuration;
    }

    public void setCancellationDuration(double cancellationDuration) {
        this.cancellationDuration = cancellationDuration;
    }

    public double getNoRefundDuration() {
        return noRefundDuration;
    }

    public void setNoRefundDuration(double noRefundDuration) {
        this.noRefundDuration = noRefundDuration;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getInternalFailureReason() {
        return internalFailureReason;
    }

    public void setInternalFailureReason(String internalFailureReason) {
        this.internalFailureReason = internalFailureReason;
    }

    public String getPaymentGatewayRequestData() {
        return paymentGatewayRequestData;
    }

    public void setPaymentGatewayRequestData(String paymentGatewayRequestData) {
        this.paymentGatewayRequestData = paymentGatewayRequestData;
    }

    public Boolean getPaymentGatewayResponseData() {
        return paymentGatewayResponseData;
    }

    public void setPaymentGatewayResponseData(Boolean paymentGatewayResponseData) {
        this.paymentGatewayResponseData = paymentGatewayResponseData;
    }

    public String getBankReceipt() {
        return bankReceipt;
    }

    public void setBankReceipt(String bankReceipt) {
        this.bankReceipt = bankReceipt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
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

    public Accomodation getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(Accomodation accomodation) {
        this.accomodation = accomodation;
    }

}