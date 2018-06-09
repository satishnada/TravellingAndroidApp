package com.profdeveloper.fllawi.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.adapters.CategoryAdapter;
import com.profdeveloper.fllawi.adapters.CommonListAdapter;
import com.profdeveloper.fllawi.adapters.SubCategoryAdapter;
import com.profdeveloper.fllawi.model.Coupons.GetCouponRequestResponse;
import com.profdeveloper.fllawi.model.SearchHotel.SearchRequestResponse;
import com.profdeveloper.fllawi.model.SearchHotel.SearchResultMainData;
import com.profdeveloper.fllawi.model.ThingToDo.ChildCategory;
import com.profdeveloper.fllawi.model.ThingToDo.Datum;
import com.profdeveloper.fllawi.model.ThingToDo.GetThingToDoCategoryRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDoSearch.GetThingToDoRequestResponse;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.retrofit.WebUtility;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends BaseActivity {

    private String TAG = SearchActivity.class.getSimpleName();
    private int DATE_PICKER_TYPE_CHECK_OUT = 1;
    private int DATE_PICKER_TYPE_CHECK_IN = 2;
    private int DATE_PICKER_TYPE_SCHEDULE = 3;
    private View view;
    private Button btnSearch;
    private SimpleDateFormat dateFormatter;
    private TextView tvCheckInDate, tvCheckOutDate,
            tvScheduleDate, tvCategory, tvSubCategory;
    private EditText edtNumberOfRoom, edtNumberMember,
            edtLocation, edtCouponName;
    private String minPrice = "";
    private String maxPrice = "";
    private LinearLayout llLocation, llCheckIn, llCheckOut, llRoom,
            llCoupon, llScheduleDate;
    private int isFrom = 1;
    private RelativeLayout rlCategory, rlSubCategory;
    private CheckBox checkBoxTickets, checkBoxCoupons;
    private boolean isTicketSelected = true;
    private boolean isCouponSelected = false;
    private ArrayList<Datum> categoryList = new ArrayList<>();
    private ArrayList<ChildCategory> subCategoryList = new ArrayList<>();
    private Spinner spinnerCategory, spinnerSubCategory;
    private CategoryAdapter categoryAdapter;
    private SubCategoryAdapter subCategoryAdapter;
    private String couponType = "";
    private String sltSubCategoryId = "";
    private String sltCategoryId = "";
    private String checkInDateStr = "";
    private String checkOutDateStr = "";

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_search, llContainer);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        llScheduleDate = view.findViewById(R.id.llScheduleDate);
        tvScheduleDate = view.findViewById(R.id.tvScheduleDate);
        rlCategory = view.findViewById(R.id.rlCategory);
        tvCategory = view.findViewById(R.id.tvCategory);
        tvSubCategory = view.findViewById(R.id.tvSubCategory);
        rlSubCategory = view.findViewById(R.id.rlSubCategory);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);
        spinnerSubCategory = view.findViewById(R.id.spinnerSubCategory);

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
                checkBoxCoupons.setChecked(false);
                if (isChecked) {
                    couponType = "1";
                }
            }
        });

        checkBoxCoupons.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCouponSelected = isChecked;
                checkBoxTickets.setChecked(false);
                if (isChecked) {
                    couponType = "2";
                }
            }
        });

        tvCheckInDate.setOnClickListener(this);
        tvCheckOutDate.setOnClickListener(this);
        tvScheduleDate.setOnClickListener(this);
        tvCategory.setOnClickListener(this);
        tvSubCategory.setOnClickListener(this);
        isHomeRunning = false;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
        }

        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            llLocation.setVisibility(View.VISIBLE);
            llCheckIn.setVisibility(View.VISIBLE);
            llCheckOut.setVisibility(View.VISIBLE);
            llScheduleDate.setVisibility(View.GONE);
            llRoom.setVisibility(View.VISIBLE);
            llCoupon.setVisibility(View.GONE);
            rlCategory.setVisibility(View.GONE);
            rlSubCategory.setVisibility(View.GONE);
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            llCoupon.setVisibility(View.VISIBLE);
            llScheduleDate.setVisibility(View.GONE);
            llLocation.setVisibility(View.GONE);
            llCheckIn.setVisibility(View.GONE);
            llCheckOut.setVisibility(View.GONE);
            llRoom.setVisibility(View.GONE);
            rlCategory.setVisibility(View.GONE);
            rlSubCategory.setVisibility(View.GONE);
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            rlCategory.setVisibility(View.VISIBLE);
            rlSubCategory.setVisibility(View.VISIBLE);
            llLocation.setVisibility(View.VISIBLE);
            llScheduleDate.setVisibility(View.VISIBLE);
            llCheckIn.setVisibility(View.GONE);
            llCheckOut.setVisibility(View.GONE);
            llRoom.setVisibility(View.GONE);
            getThingToDoCategoryList();
        } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {

        } else if (isFrom == AppConstant.IS_FROM_EVENT) {

        }

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: Category " + position);
                if (position == 0) {
                    tvCategory.setText(R.string.select_category);
                    tvCategory.setTextColor(getResources().getColor(R.color.dark_blue));
                    tvSubCategory.setText(R.string.select_sub_category);
                    tvSubCategory.setTextColor(getResources().getColor(R.color.dark_blue));
                    sltCategoryId = "";
                    sltSubCategoryId = "";
                } else {
                    sltCategoryId = categoryList.get(position).getId()+"";
                    tvCategory.setText(categoryList.get(position).getTitle());
                    tvCategory.setTextColor(getResources().getColor(R.color.white));
                    sltSubCategoryId = "";
                    tvSubCategory.setText(R.string.select_category);
                    tvSubCategory.setTextColor(getResources().getColor(R.color.dark_blue));
                }
                subCategoryList.clear();
                ChildCategory childCategory = new ChildCategory();
                childCategory.setTitle("None");
                subCategoryList.add(0, childCategory);
                if (categoryList.get(position).getChildCategory() != null) {
                    for (ChildCategory childCat : categoryList.get(position).getChildCategory()) {
                        subCategoryList.add(childCat);
                    }
                }
                if (subCategoryList.size() < 1) {
                    tvSubCategory.setText(R.string.select_category);
                    tvSubCategory.setTextColor(getResources().getColor(R.color.dark_blue));
                    sltSubCategoryId = "";
                }
                if (subCategoryAdapter != null) {
                    subCategoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: Sub Category " + position);

                if (position == 0) {
                    sltSubCategoryId = "";
                    tvSubCategory.setText(R.string.select_category);
                    tvSubCategory.setTextColor(getResources().getColor(R.color.dark_blue));
                } else {
                    sltSubCategoryId = subCategoryList.get(position).getId()+"";
                    tvSubCategory.setText(subCategoryList.get(position).getTitle());
                    tvSubCategory.setTextColor(getResources().getColor(R.color.white));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void initialization() {
        tvTopTitle.setText(R.string.search);
        ivTopBack.setImageResource(R.drawable.ic_back_arrow);
        ivTopBack.setVisibility(View.VISIBLE);
        checkLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                    if (tvCheckInDate.getText().toString().trim().length() == 0) {
                        Utility.showError(getString(R.string.valid_check_in_date));
                    } else if (tvCheckOutDate.getText().toString().trim().length() == 0) {
                        Utility.showError(getString(R.string.valid_checkout_date));
                    } else {
                        PreferenceData.setFromDate(tvCheckInDate.getText().toString());
                        PreferenceData.setToDate(tvCheckOutDate.getText().toString());
                        PreferenceData.setMember(edtNumberMember.getText().toString());
                        PreferenceData.setRoom(edtNumberOfRoom.getText().toString());
                        searchAccommodation();
                    }
                } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                    searchThingToDo();
                } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                    searchCoupon();
                }
                break;
            case R.id.tvCategory:
                if (categoryList.size() > 0) {
                    spinnerCategory.performClick();
                } else {
                    Utility.showError(getString(R.string.cat_not_available));
                }
                break;
            case R.id.tvSubCategory:
                if (subCategoryList.size() > 1) {
                    spinnerSubCategory.performClick();
                } else {
                    Utility.showError(getString(R.string.valid_sub_category));
                }
                break;
            case R.id.tvScheduleDate:
                showDatePickerDialog(DATE_PICKER_TYPE_SCHEDULE);
                break;
            case R.id.tvCheckInDate:
                showDatePickerDialog(DATE_PICKER_TYPE_CHECK_IN);
                break;
            case R.id.tvCheckOutDate:
                if (tvCheckInDate.getText().toString().length() == 0) {
                    Utility.showError(getString(R.string.valid_check_out_date));
                } else {
                    showDatePickerDialog(DATE_PICKER_TYPE_CHECK_OUT);
                }
                break;
            case R.id.ivTopBack:
                onBackPressed();
                break;
        }
    }

    private void showDatePickerDialog(final int isFromCheckInData) {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                if (isFromCheckInData == DATE_PICKER_TYPE_CHECK_IN) {
                    tvCheckInDate.setText(dateFormatter.format(newDate.getTime()));
                } else if (isFromCheckInData == DATE_PICKER_TYPE_CHECK_OUT) {
                    tvCheckOutDate.setText(dateFormatter.format(newDate.getTime()));
                } else if (isFromCheckInData == DATE_PICKER_TYPE_SCHEDULE) {
                    tvScheduleDate.setText(dateFormatter.format(newDate.getTime()));
                }
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setTitle(getString(R.string.select_date));
        if (isFromCheckInData == DATE_PICKER_TYPE_CHECK_IN) {
            datePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
        } else if (isFromCheckInData == DATE_PICKER_TYPE_CHECK_OUT) {
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
        } else if (isFromCheckInData == DATE_PICKER_TYPE_SCHEDULE) {
            datePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
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
                checkInDateStr = dateFormatter1.format(checkInDate.getTime());
                checkOutDateStr = dateFormatter1.format(checkOutDate.getTime());

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<SearchRequestResponse> call = service.searchAccommodation(Utility.getLocale(),WebUtility.SEARCH_ACCOMMODATION
                        + "from_date=" + checkInDateStr
                        + "&to_date=" + checkOutDateStr
                        + "&no_room=" + edtNumberOfRoom.getText().toString().trim()
                        + "&no_member=" + edtNumberMember.getText().toString().trim()
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
                                            Utility.showError(getString(R.string.no_search_data));
                                        } else {
                                            Utility.BASE_URL = response.body().getImageUrl();
                                            Intent searchHotel = new Intent(mActivity, SearchResultActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt(AppConstant.EXT_IS_FROM, AppConstant.IS_FROM_ACCOMMODATION);
                                            bundle.putString(AppConstant.EXT_FROM_DATE,tvCheckInDate.getText().toString());
                                            bundle.putString(AppConstant.EXT_TO_DATE,tvCheckOutDate.getText().toString());
                                            bundle.putSerializable(AppConstant.EXT_SEARCH_DATA, response.body());
                                            searchHotel.putExtras(bundle);
                                            startActivity(searchHotel);
                                            goNext();
                                        }
                                    } else {
                                        Utility.showError(getString(R.string.no_search_data));
                                    }
                                } else {
                                    Utility.showError(getString(R.string.no_search_data));
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
                Call<GetCouponRequestResponse> call = service.searchCoupon(Utility.getLocale(),WebUtility.SEARCH_COUPON
                        + "q=" + edtCouponName.getText().toString().trim()
                        + "&type=" + couponType);
                call.enqueue(new Callback<GetCouponRequestResponse>() {
                    @Override
                    public void onResponse(Call<GetCouponRequestResponse> call, Response<GetCouponRequestResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (response.body() != null) {
                                    if (response.body().getData() != null) {
                                        if (response.body().getData().getObjData() != null && response.body().getData().getObjData().getData().size() == 0) {
                                            Utility.showError(getString(R.string.no_search_found));
                                        } else {
                                            Utility.BASE_URL = response.body().getImageUrl();
                                            Intent searchHotel = new Intent(mActivity, SearchResultActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt(AppConstant.EXT_IS_FROM, AppConstant.IS_FROM_COUPON);
                                            bundle.putSerializable(AppConstant.EXT_SEARCH_DATA, response.body());
                                            searchHotel.putExtras(bundle);
                                            startActivity(searchHotel);
                                            goNext();
                                        }
                                    } else {
                                        Utility.showError(getString(R.string.no_search_found));
                                    }
                                } else {
                                    Utility.showError(getString(R.string.no_search_found));
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
                String checkInDateStr = "";
                if (tvScheduleDate.getText().toString().trim().length() > 0) {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                    Date checkInDate = dateFormatter.parse(tvScheduleDate.getText().toString());

                    SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    checkInDateStr = dateFormatter1.format(checkInDate.getTime());
                }

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<GetThingToDoRequestResponse> call = service.searchThingToDo(Utility.getLocale(),WebUtility.SEARCH_THING_TO_DO
                        + "q=" + edtLocation.getText().toString().trim()
                        + "&schedule_date=" + checkInDateStr
                        + "&filterBy[category][]=" + sltCategoryId
                        + "&filterBy[subcategory][]=" + sltSubCategoryId);
                call.enqueue(new Callback<GetThingToDoRequestResponse>() {
                    @Override
                    public void onResponse(Call<GetThingToDoRequestResponse> call, Response<GetThingToDoRequestResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (response.body() != null) {
                                    if (response.body().getData() != null) {
                                        if (response.body().getData().getObjData() != null && response.body().getData().getObjData().getData().size() == 0) {
                                            Utility.showError(getString(R.string.no_search_found));
                                        } else {
                                            Utility.BASE_URL = response.body().getImageUrl();
                                            Intent searchHotel = new Intent(mActivity, SearchResultActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt(AppConstant.EXT_IS_FROM, AppConstant.IS_FROM_THING_TO_DO);
                                            bundle.putString(AppConstant.EXT_FROM_DATE,tvScheduleDate.getText().toString());
                                            bundle.putString(AppConstant.EXT_CATEGORY,sltCategoryId);
                                            bundle.putString(AppConstant.EXT_SUB_CATEGORY,sltSubCategoryId);
                                            bundle.putSerializable(AppConstant.EXT_SEARCH_DATA, response.body());
                                            searchHotel.putExtras(bundle);
                                            startActivity(searchHotel);
                                            goNext();
                                        }
                                    } else {
                                        Utility.showError(getString(R.string.no_search_found));
                                    }
                                } else {
                                    Utility.showError(getString(R.string.no_search_found));
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
                    public void onFailure(Call<GetThingToDoRequestResponse> call, Throwable t) {
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

    private void getThingToDoCategoryList() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<GetThingToDoCategoryRequestResponse> call = service.getThingToDoCategory(Utility.getLocale(),WebUtility.BASE_URL + WebUtility.GET_THING_TO_DO_CATEGORY);
                call.enqueue(new Callback<GetThingToDoCategoryRequestResponse>() {
                    @Override
                    public void onResponse(Call<GetThingToDoCategoryRequestResponse> call, Response<GetThingToDoCategoryRequestResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (response.body() != null) {
                                    if (response.body().getData() != null) {
                                        categoryList.clear();
                                        Datum category = new Datum();
                                        category.setTitle(getString(R.string.none));
                                        categoryList.add(0, category);
                                        for (Datum datum : response.body().getData()) {
                                            categoryList.add(datum);
                                        }
                                        categoryAdapter = new CategoryAdapter(SearchActivity.this, categoryList);
                                        spinnerCategory.setAdapter(categoryAdapter);

                                        subCategoryAdapter = new SubCategoryAdapter(SearchActivity.this, subCategoryList);
                                        spinnerSubCategory.setAdapter(subCategoryAdapter);
                                    }
                                } else {
                                    Utility.showError(getString(R.string.no_search_found));
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
                    public void onFailure(Call<GetThingToDoCategoryRequestResponse> call, Throwable t) {
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
    }
}
