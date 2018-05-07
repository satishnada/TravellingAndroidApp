package com.moderndeveloper.fllawi.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.model.Coupons.GetCouponRequestResponse;
import com.moderndeveloper.fllawi.model.SearchHotel.SearchRequestResponse;
import com.moderndeveloper.fllawi.model.SearchHotel.SearchResultMainData;
import com.moderndeveloper.fllawi.retrofit.WebServiceCaller;
import com.moderndeveloper.fllawi.retrofit.WebUtility;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.PreferenceData;
import com.moderndeveloper.fllawi.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends BaseActivity {

    private View view;
    private TextView tvSearch;
    private SimpleDateFormat dateFormatter;
    private TextView tvCheckInDate, tvCheckOutDate;
    private EditText edtNumberOfRoom, edtNumberMember, edtLocation, edtCouponName;
    private String minPrice = "100";
    private String maxPrice = "10000";
    private LinearLayout llLocation, llCheckIn, llCheckOut, llRoom, llCoupon;
    private int isFrom = 1;
    private CheckBox checkBoxTickets, checkBoxCoupons;
    private boolean isTicketSelected = true;
    private boolean isCouponSelected = false;

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_search, llContainer);
        tvSearch = view.findViewById(R.id.tvSearch);
        tvSearch.setOnClickListener(this);

        llLocation = view.findViewById(R.id.llLocation);
        llCheckIn = view.findViewById(R.id.llCheckIn);
        llCheckOut = view.findViewById(R.id.llCheckOut);
        llRoom = view.findViewById(R.id.llRoom);

        llCoupon = view.findViewById(R.id.llCoupon);
        checkBoxCoupons = view.findViewById(R.id.checkBoxCoupons);
        checkBoxTickets = view.findViewById(R.id.checkBoxTickets);
        edtCouponName = view.findViewById(R.id.edtCouponName);

        tvCheckInDate = view.findViewById(R.id.tvCheckInDate);
        tvCheckOutDate = view.findViewById(R.id.tvCheckOutDate);
        edtNumberOfRoom = view.findViewById(R.id.edtNumberOfRoom);
        edtNumberMember = view.findViewById(R.id.edtNumberMember);
        edtLocation = view.findViewById(R.id.edtLocation);

        checkBoxTickets.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isTicketSelected = isChecked;
            }
        });

        checkBoxCoupons.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCouponSelected = isChecked;
            }
        });

        tvCheckInDate.setOnClickListener(this);
        tvCheckOutDate.setOnClickListener(this);
        isHomeRunning = false;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
        }

        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            llLocation.setVisibility(View.VISIBLE);
            llCheckIn.setVisibility(View.VISIBLE);
            llCheckOut.setVisibility(View.VISIBLE);
            llRoom.setVisibility(View.VISIBLE);
            llCoupon.setVisibility(View.GONE);
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            llCoupon.setVisibility(View.VISIBLE);
            llLocation.setVisibility(View.GONE);
            llCheckIn.setVisibility(View.GONE);
            llCheckOut.setVisibility(View.GONE);
            llRoom.setVisibility(View.GONE);
        }

    }

    @Override
    public void initialization() {
        tvTopTitle.setText(R.string.search);
        ivTopBack.setImageResource(R.drawable.ic_back_arrow);
        ivTopBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSearch:
                if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                    /* if (edtLocation.getText().toString().trim().length() == 0){
                    Utility.showError(getString(R.string.valid_location));
                } else*/
                    if (tvCheckInDate.getText().toString().trim().length() == 0) {
                        Utility.showError(getString(R.string.valid_check_in_date));
                    } else if (tvCheckOutDate.getText().toString().trim().length() == 0) {
                        Utility.showError(getString(R.string.valid_checkout_date));
                    } /*else if (edtNumberOfRoom.getText().toString().trim().length() == 0){
                    Utility.showError(getString(R.string.valid_room));
                } else if (edtNumberMember.getText().toString().trim().length() == 0){
                    Utility.showError(getString(R.string.valid_members));
                }*/ else {
                        PreferenceData.setFromDate(tvCheckInDate.getText().toString());
                        PreferenceData.setToDate(tvCheckOutDate.getText().toString());
                        PreferenceData.setMember(edtNumberMember.getText().toString());
                        PreferenceData.setRoom(edtNumberOfRoom.getText().toString());
                        searchAccommodation();
                    }
                } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                    /*if (edtCouponName.getText().toString().trim().length() == 0) {
                        Utility.showError("Enter name");
                    }*/
                    searchCoupon();

                } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                    /*if (edtCouponName.getText().toString().trim().length() == 0) {
                        Utility.showError("Enter name");
                    }*/
                    searchCoupon();

                }
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
            case R.id.ivTopBack:
                onBackPressed();
                break;
        }
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
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTime().getTime());
            } else {
                Utility.showError(getString(R.string.valid_check_out_date));
            }
        }

        if (datePickerDialog.getWindow() != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void searchAccommodation() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                Date checkInDate = dateFormatter.parse(tvCheckInDate.getText().toString());
                Date checkOutDate = dateFormatter.parse(tvCheckOutDate.getText().toString());

                SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                String checkInDateStr = dateFormatter1.format(checkInDate.getTime());
                String checkOutDateStr = dateFormatter1.format(checkOutDate.getTime());

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<SearchRequestResponse> call = service.searchAccommodation(WebUtility.SEARCH_ACCOMMODATION +
                        "?from_date=" + checkInDateStr
                        + "&to_date=" + checkOutDateStr
                        + "&no_room=" + edtNumberOfRoom.getText().toString().trim()
                        + "&no_member=" + edtNumberMember.getText().toString().trim()
                        + "&sortBy[name]=asc"
                        + "&sortBy[price]=asc"
                        + "&filterBy[category][]=1"
                        + "&filterBy[subcategory][]=2"
                        + "filterBy[min_price]=" + minPrice
                        + "&filterBy[max_price]=" + maxPrice
                        + "&q=" + edtLocation.getText().toString().trim()
                );
                call.enqueue(new Callback<SearchRequestResponse>() {
                    @Override
                    public void onResponse(Call<SearchRequestResponse> call, Response<SearchRequestResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (response.body() != null) {
                                    if (response.body().getSearchResultMainData() != null) {
                                        if (response.body().getSearchResultMainData().getSearchHotelList() != null && response.body().getSearchResultMainData().getSearchHotelList().getData().size() == 0) {
                                            Utility.showError("No Search Data Found");
                                        } else {
                                            Utility.BASE_URL = response.body().getImageUrl();
                                            Intent searchHotel = new Intent(mActivity, SearchResultActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable(AppConstant.EXT_SEARCH_DATA, response.body());
                                            searchHotel.putExtras(bundle);
                                            startActivity(searchHotel);
                                            finish();
                                            goNext();
                                        }
                                    } else {
                                        Utility.showError("No Search Data Found");
                                    }
                                } else {
                                    Utility.showError("No Search Data Found");
                                }

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(getResources().getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<SearchRequestResponse> call, Throwable t) {
                        Utility.log("" + t.getMessage());
                        hideProgress();
                        Utility.showError(t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchCoupon() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<GetCouponRequestResponse> call = service.searchCoupon(WebUtility.SEARCH_COUPON +
                        "q=&filterBy[min_price]=0&filterBy[Bmax_price]=10000&filterBy[star_rating]=1&sortBy[price]=asc&sortBy[name]=asc");
                call.enqueue(new Callback<GetCouponRequestResponse>() {
                    @Override
                    public void onResponse(Call<GetCouponRequestResponse> call, Response<GetCouponRequestResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (response.body() != null) {
                                    if (response.body().getData() != null) {
                                        if (response.body().getData().getObjData() != null && response.body().getData().getObjData().getData().size() == 0) {
                                            Utility.showError("No Search Data Found");
                                        } else {
                                           /* Utility.BASE_URL = response.body().getImageUrl();
                                            Intent searchHotel = new Intent(mActivity, SearchResultActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable(AppConstant.EXT_SEARCH_DATA, response.body());
                                            searchHotel.putExtras(bundle);
                                            startActivity(searchHotel);
                                            finish();
                                            goNext();*/
                                        }
                                    } else {
                                        Utility.showError("No Search Data Found");
                                    }
                                } else {
                                    Utility.showError("No Search Data Found");
                                }

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(getResources().getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<GetCouponRequestResponse> call, Throwable t) {
                        Utility.log("" + t.getMessage());
                        hideProgress();
                        Utility.showError(t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchThingToDo() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<GetCouponRequestResponse> call = service.searchCoupon(WebUtility.SEARCH_COUPON +
                        "q=&filterBy[min_price]=0&filterBy[Bmax_price]=10000&filterBy[star_rating]=1&sortBy[price]=asc&sortBy[name]=asc");
                call.enqueue(new Callback<GetCouponRequestResponse>() {
                    @Override
                    public void onResponse(Call<GetCouponRequestResponse> call, Response<GetCouponRequestResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (response.body() != null) {
                                    if (response.body().getData() != null) {
                                        if (response.body().getData().getObjData() != null && response.body().getData().getObjData().getData().size() == 0) {
                                            Utility.showError("No Search Data Found");
                                        } else {
                                           /* Utility.BASE_URL = response.body().getImageUrl();
                                            Intent searchHotel = new Intent(mActivity, SearchResultActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable(AppConstant.EXT_SEARCH_DATA, response.body());
                                            searchHotel.putExtras(bundle);
                                            startActivity(searchHotel);
                                            finish();
                                            goNext();*/
                                        }
                                    } else {
                                        Utility.showError("No Search Data Found");
                                    }
                                } else {
                                    Utility.showError("No Search Data Found");
                                }

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(getResources().getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<GetCouponRequestResponse> call, Throwable t) {
                        Utility.log("" + t.getMessage());
                        hideProgress();
                        Utility.showError(t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        goPrevious();
    }
}
