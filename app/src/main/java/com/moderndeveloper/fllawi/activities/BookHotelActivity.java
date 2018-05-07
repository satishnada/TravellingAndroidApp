package com.moderndeveloper.fllawi.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.model.ArrCalculationBreakDown;
import com.moderndeveloper.fllawi.model.CalculatedPriceRequestResponse;
import com.moderndeveloper.fllawi.model.CommonRequestResponse;
import com.moderndeveloper.fllawi.retrofit.WebServiceCaller;
import com.moderndeveloper.fllawi.retrofit.WebUtility;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookHotelActivity extends BaseActivity {

    private View view;
    private TextView tvBookNow, tvCheckInDate, tvCheckOutDate, tvAdultCount,
            tvPriceBefore, tvAppliedDiscount, tvPriceAfter, tvKidsCount;
    private ImageView ivPlushAdult, ivMinusAdult, ivPlushKids, ivMinusKids;
    private SimpleDateFormat dateFormatter;
    private int adultCount = 0;
    private int kidsCount = 0;
    private String accommodationId = "";
    private ArrCalculationBreakDown calculationBreakDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_book_hotel, llContainer);
        mActivity = this;

        tvCheckInDate = view.findViewById(R.id.tvCheckInDate);
        tvCheckOutDate = view.findViewById(R.id.tvCheckOutDate);
        tvKidsCount = view.findViewById(R.id.tvKidsCount);
        tvAdultCount = view.findViewById(R.id.tvAdultCount);
        tvAppliedDiscount = view.findViewById(R.id.tvAppliedDiscount);
        tvPriceAfter = view.findViewById(R.id.tvPriceAfter);
        ivPlushAdult = view.findViewById(R.id.ivPlushAdult);
        tvPriceBefore = view.findViewById(R.id.tvPriceBefore);
        ivMinusAdult = view.findViewById(R.id.ivMinusAdult);
        ivPlushKids = view.findViewById(R.id.ivPlushKids);
        ivMinusKids = view.findViewById(R.id.ivMinusKids);
        tvBookNow = view.findViewById(R.id.tvBookNow);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            accommodationId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
        }

    /*    Calendar newDate = Calendar.getInstance();
        newDate.setTime(new Date());
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvCheckInDate.setText(dateFormatter.format(newDate.getTime()));
        newDate.add(Calendar.DAY_OF_MONTH,1);;
        tvCheckOutDate.setText(dateFormatter.format(newDate.getTime()));*/

        tvBookNow.setOnClickListener(this);
        tvCheckInDate.setOnClickListener(this);
        tvCheckOutDate.setOnClickListener(this);
        ivPlushAdult.setOnClickListener(this);
        ivMinusAdult.setOnClickListener(this);
        ivMinusKids.setOnClickListener(this);
        ivPlushKids.setOnClickListener(this);
        isHomeRunning = false;
    }

    @Override
    public void initialization() {
        ivTopBack.setImageResource(R.drawable.ic_back_arrow);
        tvTopTitle.setBackgroundResource(0);
        tvTopTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        tvTopTitle.setText(R.string.your_selection);
        tvTopTitle.setVisibility(View.VISIBLE);
        ivTopSearch.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivMinusAdult:
                if (adultCount != 0){
                    adultCount = adultCount - 1;
                }
                tvAdultCount.setText(adultCount + "");
                break;
            case R.id.ivMinusKids:
                if (kidsCount != 0){
                    kidsCount = kidsCount - 1;
                }
                tvKidsCount.setText(kidsCount + "");
                break;
            case R.id.ivPlushAdult:
                adultCount = adultCount + 1;
                tvAdultCount.setText(adultCount + "");
                break;
            case R.id.ivPlushKids:
                kidsCount = kidsCount + 1;
                tvKidsCount.setText(kidsCount + "");
                break;
            case R.id.ivTopBack:
                onBackPressed();
                break;
            case R.id.tvCheckInDate:
                showDatePickerDialog(true);
                break;
            case R.id.tvCheckOutDate:
                if (tvCheckInDate.getText().toString().length() == 0) {
                    Utility.showError(getString(R.string.valid_check_out_date));
                } else {
                    showDatePickerDialog(false);
                }
                break;
            case R.id.tvBookNow:
                openPaymentMethodSelectionDialog();
                break;
            default:
                super.onClick(v);
                break;
        }
    }


    private void getPrice() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CalculatedPriceRequestResponse> call = service.getCalculatedPrice(WebUtility.ACCOMMODATION_CALCULATE_PRICE + "accomodation_id="+
                        accommodationId + "&from_date=" + tvCheckInDate.getText().toString().trim() +
                        "&to_date=" + tvCheckOutDate.getText().toString().trim() + "&adults="+adultCount+"&kids="+kidsCount);
                call.enqueue(new Callback<CalculatedPriceRequestResponse>() {
                    @Override
                    public void onResponse(Call<CalculatedPriceRequestResponse> call, Response<CalculatedPriceRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                calculationBreakDown = response.body().getArrCalculationBreakDown();

                                tvPriceBefore.setText("SAR "+calculationBreakDown.getTotalBeforeDiscount());
                                tvAppliedDiscount.setText("SAR "+calculationBreakDown.getDiscountAmount());
                                tvPriceAfter.setText("SAR "+calculationBreakDown.getTotalAfterDiscount());

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<CalculatedPriceRequestResponse> call, Throwable t) {
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



    private void openPaymentMethodSelectionDialog() {
        final Dialog popupWindowDialog = new Dialog(mActivity, android.R.style.Theme_Black_NoTitleBar);

        final View layout = LayoutInflater.from(this).inflate(R.layout.dialog_image_options, null);
        popupWindowDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (popupWindowDialog.getWindow() != null) {
            popupWindowDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        }
        popupWindowDialog.setContentView(layout);
        popupWindowDialog.setCancelable(false);

        ImageView ivClose = (ImageView) layout.findViewById(R.id.ivClose);
        TextView tvTakePhoto = layout.findViewById(R.id.tvTakePhoto);
        TextView tvLibrary = layout.findViewById(R.id.tvLibrary);

        tvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDialog.dismiss();
                Intent intent = new Intent(mActivity, BillingInfoActivity.class);
                startActivity(intent);
                goNext();
            }
        });

        tvLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDialog.dismiss();
                Intent intent = new Intent(mActivity, BillingInfoActivity.class);
                startActivity(intent);
                goNext();
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDialog.dismiss();
            }
        });

        popupWindowDialog.show();
    }

    private void showDatePickerDialog(final boolean isFromCheckInData) {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                if (isFromCheckInData) {
                    tvCheckInDate.setText(dateFormatter.format(newDate.getTime()));
                } else {
                    tvCheckOutDate.setText(dateFormatter.format(newDate.getTime()));
                    getPrice();
                }
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setTitle(getString(R.string.select_date));
        if (isFromCheckInData) {
            datePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
        } else {
            Date date = null;
            try {
                if (tvCheckInDate.getText().toString().length() > 0) {
                    date = dateFormatter.parse(tvCheckInDate.getText().toString());
                } else {
                    Utility.showError(getString(R.string.valid_check_out_date));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (date != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DAY_OF_MONTH,1);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTime().getTime());
            } else {
                Utility.showError(getString(R.string.valid_check_out_date));
            }
        }

        if (datePickerDialog.getWindow() != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        goPrevious();
        finish();
    }
}
