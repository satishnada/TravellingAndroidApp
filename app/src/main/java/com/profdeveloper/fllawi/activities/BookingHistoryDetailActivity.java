package com.profdeveloper.fllawi.activities;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.adapters.OrderHistoryAccommodationAdapter;
import com.profdeveloper.fllawi.model.BookingHistory.BookingHistoryDetailRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistory.BookingHistoryRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistoryCoupon.BookingHistoryCouponRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistoryCoupon.BookingHistoryDetailCouponRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistoryThingToDo.BookingHistoryDetailThingToDoRequestResponse;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingHistoryDetailActivity extends BaseActivity {
    private static final String TAG = BookingHistoryDetailActivity.class.getSimpleName();
    private View view;
    private TextView tvNoData, tvTitle, tvPaymentStatus, tvValidDate, tvQty,
            tvFromDate, tvToDate, tvOrderDate, tvDescriptionText,
            tvTotalPayableAmount, tvDiscountApplied,tvScheduleDate,tvReceiptTitle,
            tvToDateLable,tvFromDateLable;
    private LinearLayout llValidDate, llQty,llFromToDate,llScheduleDate;
    private ImageView ivUploadReceipt;
    private int isFrom = 0;
    private String bookingId = "0";
    private BookingHistoryDetailRequestResponse bookingDetailAccommodation;
    private BookingHistoryDetailThingToDoRequestResponse bookingDetailThingToDoResponse;
    private BookingHistoryDetailCouponRequestResponse bookingDetailCouponResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_booking_info, llContainer);
        tvNoData = (TextView) view.findViewById(R.id.tvNoData);

        tvTitle = view.findViewById(R.id.tvTitle);
        tvPaymentStatus = view.findViewById(R.id.tvPaymentStatus);
        tvValidDate = view.findViewById(R.id.tvValidDate);
        llValidDate = view.findViewById(R.id.llValidDate);
        llScheduleDate = view.findViewById(R.id.llScheduleDate);
        llQty = view.findViewById(R.id.llQty);
        llFromToDate = view.findViewById(R.id.llFromToDate);
        tvQty = view.findViewById(R.id.tvQty);
        tvFromDate = view.findViewById(R.id.tvFromDate);
        tvToDate = view.findViewById(R.id.tvToDate);
        tvOrderDate = view.findViewById(R.id.tvOrderDate);
        tvDescriptionText = view.findViewById(R.id.tvDescriptionText);
        tvTotalPayableAmount = view.findViewById(R.id.tvTotalPayableAmount);
        tvDiscountApplied = view.findViewById(R.id.tvDiscountApplied);
        tvScheduleDate = view.findViewById(R.id.tvScheduleDate);
        tvReceiptTitle = view.findViewById(R.id.tvReceiptTitle);
        tvToDateLable = view.findViewById(R.id.tvToDateLable);
        ivUploadReceipt = view.findViewById(R.id.ivUploadReceipt);
        tvFromDateLable = view.findViewById(R.id.tvFromDateLable);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
            bookingId = bundle.getString(AppConstant.EXT_BOOKING_ID);
        }

        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            llValidDate.setVisibility(View.GONE);
            llQty.setVisibility(View.GONE);
            llScheduleDate.setVisibility(View.GONE);
            getHistoryAccommodationDetails();
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            llValidDate.setVisibility(View.GONE);
            llScheduleDate.setVisibility(View.VISIBLE);
            llQty.setVisibility(View.GONE);
            getHistoryThingToDoDetails();
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            llScheduleDate.setVisibility(View.GONE);
            llValidDate.setVisibility(View.VISIBLE);
            llQty.setVisibility(View.VISIBLE);
            getHistoryCouponDetails();
        } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {

        } else if (isFrom == AppConstant.IS_FROM_EVENT) {

        }

        //Utility.showError("Coming soon....");

    }

    @Override
    public void initialization() {
        tvTopTitle.setText(R.string.booking_info);
        ivTopBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_back_arrow));
        ivTopSearch.setVisibility(View.INVISIBLE);
        checkLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivTopBack:
                onBackPressed();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void setDetailData() {
        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {

            if (bookingDetailAccommodation != null){
                if (bookingDetailAccommodation.getArrAccomodationBooking().getPaymentMode() == 1){
                    tvReceiptTitle.setVisibility(View.GONE);
                    ivUploadReceipt.setVisibility(View.GONE);
                }else{
                    tvReceiptTitle.setVisibility(View.VISIBLE);
                    ivUploadReceipt.setVisibility(View.VISIBLE);
                }

                tvPaymentStatus.setText(getPaymentStatus(bookingDetailAccommodation.getArrAccomodationBooking().getStatus()));
                tvTitle.setText(bookingDetailAccommodation.getArrAccomodationBooking().getAccomodation().getTitle());
                tvDescriptionText.setText(bookingDetailAccommodation.getArrAccomodationBooking().getAccomodation().getDescription());
                tvFromDate.setText(bookingDetailAccommodation.getArrAccomodationBooking().getFromDate());
                tvToDate.setText(bookingDetailAccommodation.getArrAccomodationBooking().getToDate());
                tvOrderDate.setText(bookingDetailAccommodation.getArrAccomodationBooking().getCreatedAt().substring(0, 11));
                tvDiscountApplied.setText(bookingDetailAccommodation.getArrAccomodationBooking().getDiscountInAmount() + " SAR");
                tvTotalPayableAmount.setText(bookingDetailAccommodation.getArrAccomodationBooking().getTotalAmount() + " SAR");
            }

        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {

            if (bookingDetailThingToDoResponse != null){
                if (bookingDetailThingToDoResponse.getThingtodoBooking().getPaymentMode() == 1){
                    tvReceiptTitle.setVisibility(View.GONE);
                    ivUploadReceipt.setVisibility(View.GONE);
                }else{
                    tvReceiptTitle.setVisibility(View.VISIBLE);
                    ivUploadReceipt.setVisibility(View.VISIBLE);
                }
                tvTitle.setText(bookingDetailThingToDoResponse.getThingtodoBooking().getThingtodo().getTitle());
                tvDescriptionText.setText(bookingDetailThingToDoResponse.getThingtodoBooking().getThingtodo().getDescription());
                tvPaymentStatus.setText(getPaymentStatus(bookingDetailThingToDoResponse.getThingtodoBooking().getStatus()));
                tvScheduleDate.setText(bookingDetailThingToDoResponse.getThingtodoBooking().getScheduleDate());
                tvFromDateLable.setText(getString(R.string.from_time));
                tvToDateLable.setText(getString(R.string.to_time));
                tvFromDate.setText(bookingDetailThingToDoResponse.getThingtodoBooking().getScheduleStartTime());
                tvToDate.setText(bookingDetailThingToDoResponse.getThingtodoBooking().getScheduleEndTime());
                tvOrderDate.setText(bookingDetailThingToDoResponse.getThingtodoBooking().getCreatedAt().substring(0, 11));
                tvDiscountApplied.setText(bookingDetailThingToDoResponse.getThingtodoBooking().getDiscountInAmount() + "SAR");
                tvTotalPayableAmount.setText(bookingDetailThingToDoResponse.getThingtodoBooking().getTotalAmount() + "SAR");
            }

        } else if (isFrom == AppConstant.IS_FROM_COUPON) {

            if (bookingDetailCouponResponse != null){
                tvReceiptTitle.setVisibility(View.GONE);
                ivUploadReceipt.setVisibility(View.GONE);
                /*if (bookingDetailCouponResponse.getBooking().getPaymentMode() == 1){
                    tvReceiptTitle.setVisibility(View.GONE);
                    ivUploadReceipt.setVisibility(View.GONE);
                }else{
                    tvReceiptTitle.setVisibility(View.VISIBLE);
                    ivUploadReceipt.setVisibility(View.VISIBLE);
                }*/
                tvTitle.setText(bookingDetailCouponResponse.getBooking().getCoupon().getTitle());
                tvDescriptionText.setText(bookingDetailCouponResponse.getBooking().getCoupon().getDescription());
                tvPaymentStatus.setText(getPaymentStatus(bookingDetailCouponResponse.getBooking().getStatus()));
                tvValidDate.setText(bookingDetailCouponResponse.getBooking().getCoupon().getValidTillDate());
                tvFromDate.setText(bookingDetailCouponResponse.getBooking().getCoupon().getFromDate());
                tvToDate.setText(bookingDetailCouponResponse.getBooking().getCoupon().getToDate());
                tvOrderDate.setText(bookingDetailCouponResponse.getBooking().getCreatedAt().substring(0, 11));
                tvDiscountApplied.setText(bookingDetailCouponResponse.getBooking().getDiscountInAmount() + " SAR");
                tvTotalPayableAmount.setText(bookingDetailCouponResponse.getBooking().getTotalAmount() + " SAR");
            }

        } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {

        } else if (isFrom == AppConstant.IS_FROM_EVENT) {

        }
    }

    private void getHistoryAccommodationDetails() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<BookingHistoryDetailRequestResponse> call = service.getBookingHistoryDetails(Utility.getLocale(), PreferenceData.getUserData().getId() + "", bookingId);
                call.enqueue(new Callback<BookingHistoryDetailRequestResponse>() {
                    @Override
                    public void onResponse(Call<BookingHistoryDetailRequestResponse> call, Response<BookingHistoryDetailRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                bookingDetailAccommodation = response.body();
                                setDetailData();

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<BookingHistoryDetailRequestResponse> call, Throwable t) {
                        Utility.log("" + t.getMessage());
                        Utility.hideProgress();
                        Utility.showError(t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getHistoryThingToDoDetails() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<BookingHistoryDetailThingToDoRequestResponse> call = service.getBookingHistoryThingToDoDetails(Utility.getLocale(), PreferenceData.getUserData().getId() + "", bookingId);
                call.enqueue(new Callback<BookingHistoryDetailThingToDoRequestResponse>() {
                    @Override
                    public void onResponse(Call<BookingHistoryDetailThingToDoRequestResponse> call, Response<BookingHistoryDetailThingToDoRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                bookingDetailThingToDoResponse = response.body();
                                setDetailData();

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<BookingHistoryDetailThingToDoRequestResponse> call, Throwable t) {
                        Utility.log("" + t.getMessage());
                        Utility.hideProgress();
                        Utility.showError(t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getHistoryCouponDetails() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<BookingHistoryDetailCouponRequestResponse> call = service.getBookingHistoryCouponDetails(Utility.getLocale(), PreferenceData.getUserData().getId() + "", bookingId);
                call.enqueue(new Callback<BookingHistoryDetailCouponRequestResponse>() {
                    @Override
                    public void onResponse(Call<BookingHistoryDetailCouponRequestResponse> call, Response<BookingHistoryDetailCouponRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                bookingDetailCouponResponse = response.body();
                                setDetailData();

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<BookingHistoryDetailCouponRequestResponse> call, Throwable t) {
                        Utility.log("" + t.getMessage());
                        Utility.hideProgress();
                        Utility.showError(t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPaymentStatus(int status){
        if (status == 0){
            return "Pending";
        }else if (status == 1){
            return "Completed";
        }else if (status == 2){
            return "Internal failed";
        }else if (status == 3){
            return "External Failure";
        }else if (status == 4){
            return "Awaiting Bank Receipt Verification";
        }else if (status == 5){
            return "Cancelled By Customer";
        }else if (status == 6){
            return "Cancelled By Customer";
        }else if (status == 7){
            return "Cancelled By Host Provider";
        }else if (status == 8){
            return "Cancelled By Admin";
        }
        return "";
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
