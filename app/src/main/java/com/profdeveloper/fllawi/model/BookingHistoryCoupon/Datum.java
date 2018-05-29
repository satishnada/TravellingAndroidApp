package com.profdeveloper.fllawi.model.BookingHistoryCoupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("internal_ref_no")
    @Expose
    private String internalRefNo;
    @SerializedName("gateway_ref_no")
    @Expose
    private String gatewayRefNo;
    @SerializedName("coupon_id")
    @Expose
    private Integer couponId;
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
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("applied_coupon")
    @Expose
    private String appliedCoupon;
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
    private Boolean paymentGatewayResponseData;
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
    @SerializedName("coupon")
    @Expose
    private Coupon coupon;
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

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getAppliedCoupon() {
        return appliedCoupon;
    }

    public void setAppliedCoupon(String appliedCoupon) {
        this.appliedCoupon = appliedCoupon;
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

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

}