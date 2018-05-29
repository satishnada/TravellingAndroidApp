package com.profdeveloper.fllawi.model.BookingHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Datum implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("internal_ref_no")
    @Expose
    private String internalRefNo;
    @SerializedName("gateway_ref_no")
    @Expose
    private String gatewayRefNo;
    @SerializedName("accomodation_id")
    @Expose
    private Integer accomodationId;
    @SerializedName("host_id")
    @Expose
    private Integer hostId;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
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
    private Integer adultsQty;
    @SerializedName("kids_qty")
    @Expose
    private Integer kidsQty;
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
    private Integer cancelRefundApplicable;
    @SerializedName("cancellation_charge")
    @Expose
    private Integer cancellationCharge;
    @SerializedName("refund_amount")
    @Expose
    private String refundAmount;
    @SerializedName("cancellation_amount")
    @Expose
    private String cancellationAmount;
    @SerializedName("cancellation_duration")
    @Expose
    private Integer cancellationDuration;
    @SerializedName("no_refund_duration")
    @Expose
    private Integer noRefundDuration;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("payment_mode")
    @Expose
    private Integer paymentMode;
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
    private String paymentGatewayResponseData;
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
    @SerializedName("accomodation")
    @Expose
    private Accomodation accomodation;
    @SerializedName("gallery")
    @Expose
    private List<Gallery> gallery = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAccomodationId() {
        return accomodationId;
    }

    public void setAccomodationId(Integer accomodationId) {
        this.accomodationId = accomodationId;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
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

    public Integer getAdultsQty() {
        return adultsQty;
    }

    public void setAdultsQty(Integer adultsQty) {
        this.adultsQty = adultsQty;
    }

    public Integer getKidsQty() {
        return kidsQty;
    }

    public void setKidsQty(Integer kidsQty) {
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

    public Integer getCancelRefundApplicable() {
        return cancelRefundApplicable;
    }

    public void setCancelRefundApplicable(Integer cancelRefundApplicable) {
        this.cancelRefundApplicable = cancelRefundApplicable;
    }

    public Integer getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(Integer cancellationCharge) {
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

    public Integer getCancellationDuration() {
        return cancellationDuration;
    }

    public void setCancellationDuration(Integer cancellationDuration) {
        this.cancellationDuration = cancellationDuration;
    }

    public Integer getNoRefundDuration() {
        return noRefundDuration;
    }

    public void setNoRefundDuration(Integer noRefundDuration) {
        this.noRefundDuration = noRefundDuration;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Integer paymentMode) {
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

    public String getPaymentGatewayResponseData() {
        return paymentGatewayResponseData;
    }

    public void setPaymentGatewayResponseData(String paymentGatewayResponseData) {
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

    public Accomodation getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(Accomodation accomodation) {
        this.accomodation = accomodation;
    }

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

}