package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.model.Coupons.GetCouponRequestResponse;
import com.profdeveloper.fllawi.model.SearchHotel.SearchRequestResponse;
import com.profdeveloper.fllawi.model.SuggestLocationRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDoSearch.GetThingToDoRequestResponse;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.retrofit.WebUtility;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SortHotelActivity extends BaseActivity {

    private View view;
    private TextView tvApply, tvMinAmount, tvMaxAmount;
    private CrystalRangeSeekbar seekBar;
    private String minPrice = "0";
    private String maxPrice = "10000";
    private AutoCompleteTextView edtLocationSort;
    private ArrayList<SuggestLocationRequestResponse> suggestLocationList = new ArrayList<>();
    private int isFrom = 0;
    private CardView cardViewRatting;
    private String searchLocation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_sort_hotel, llContainer);
        mActivity = this;
        tvMinAmount = view.findViewById(R.id.tvMinAmount);
        tvMaxAmount = view.findViewById(R.id.tvMaxAmount);
        seekBar = view.findViewById(R.id.rangeSeekBar);
        cardViewRatting = view.findViewById(R.id.cardViewRatting);
        tvApply = view.findViewById(R.id.tvApply);
        edtLocationSort = view.findViewById(R.id.edtLocationSort);
        tvApply.setOnClickListener(this);
        isHomeRunning = false;

        tvMinAmount.setText(getString(R.string.sar) + " 0");
        tvMaxAmount.setText(getString(R.string.sar) + " 10000");
        seekBar.setMinValue(0);
        seekBar.setMaxValue(10000);

        seekBar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMinAmount.setText(getString(R.string.sar) + " " + String.valueOf(minValue));
                tvMaxAmount.setText(getString(R.string.sar) + " " + String.valueOf(maxValue));
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);

            if (isFrom == AppConstant.IS_FROM_COUPON){
                cardViewRatting.setVisibility(View.GONE);
            }
        }

        edtLocationSort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                searchLocation = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                suggestLocation(searchLocation);
            }
        });

    }

    @Override
    public void initialization() {
        tvTopTitle.setBackgroundResource(0);
        tvTopTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        tvTopTitle.setText(R.string.sort_by);
        ivTopBack.setVisibility(View.GONE);
        tvTopTitle.setVisibility(View.VISIBLE);
        ivTopSearch.setVisibility(View.GONE);

        tvLeftText.setText(R.string.cancel);
        tvRightText.setText(R.string.reset);
        tvLeftText.setVisibility(View.VISIBLE);
        tvRightText.setVisibility(View.VISIBLE);

        checkLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvApply:
                if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                    PreferenceData.setLocation(edtLocationSort.getText().toString().trim());
                    searchAccommodation();
                } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                    PreferenceData.setLocation(edtLocationSort.getText().toString().trim());
                    searchThingToDo();
                } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                    searchCoupon();
                }
                break;
            case R.id.tvRightText: // Reset
                tvMinAmount.setText(getString(R.string.sar) + " 0");
                tvMaxAmount.setText(getString(R.string.sar) + " 10000");
                seekBar.setMinValue(0);
                seekBar.setMaxValue(10000);
                break;
            case R.id.tvLeftText: // Cancel
                onBackPressed();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void searchAccommodation() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                String checkInDateStr = "";
                String checkOutDateStr = "";
                if (PreferenceData.getFromDate().length() > 0) {
                    Date checkInDate = dateFormatter.parse(PreferenceData.getFromDate());
                    Date checkOutDate = dateFormatter.parse(PreferenceData.getToDate());
                    checkInDateStr = dateFormatter1.format(checkInDate.getTime());
                    checkOutDateStr = dateFormatter1.format(checkOutDate.getTime());
                }

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<SearchRequestResponse> call = service.searchAccommodation(Utility.getLocale(), WebUtility.SEARCH_ACCOMMODATION +
                        "from_date=" + checkInDateStr
                        + "&to_date=" + checkOutDateStr
                        + "&no_room=" + PreferenceData.getRoom()
                        + "&no_member=" + PreferenceData.getMember()
                        + "filterBy[min_price]=" + minPrice
                        + "&filterBy[max_price]=" + maxPrice
                        + "&q=" + edtLocationSort.getText().toString().trim()
                );
                call.enqueue(new Callback<SearchRequestResponse>() {
                    @Override
                    public void onResponse(Call<SearchRequestResponse> call, Response<SearchRequestResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (response.body() != null) {
                                    if (response.body().getSearchResultMainData() != null) {
                                        if (response.body().getSearchResultMainData().getSearchHotelList() != null && response.body().getSearchResultMainData().getSearchHotelList().getData().size() == 0) {
                                            Utility.showError(getString(R.string.no_search_found));
                                        } else {
                                            Utility.BASE_URL = response.body().getImageUrl();
                                            Intent searchHotel = new Intent(mActivity, SearchResultActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt(AppConstant.EXT_IS_FROM,isFrom);
                                            bundle.putSerializable(AppConstant.EXT_SEARCH_DATA, response.body());
                                            searchHotel.putExtras(bundle);
                                            setResult(13, searchHotel);
                                            goPrevious();
                                            finish();
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

    private void searchThingToDo() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                String scheduleDateStr = "";
                if (PreferenceData.getScheduleDate().length() > 0) {
                    Date scheduleDate = dateFormatter.parse(PreferenceData.getScheduleDate());
                    scheduleDateStr = dateFormatter1.format(scheduleDate.getTime());
                }

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<GetThingToDoRequestResponse> call = service.searchThingToDo(
                        Utility.getLocale(), WebUtility.SEARCH_THING_TO_DO
                                + "q=" + edtLocationSort.getText().toString().trim()
                                + "&schedule_date=" + scheduleDateStr
                );

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
                                            bundle.putInt(AppConstant.EXT_IS_FROM,isFrom);
                                            bundle.putSerializable(AppConstant.EXT_SEARCH_DATA, response.body());
                                            searchHotel.putExtras(bundle);
                                            setResult(13, searchHotel);
                                            goPrevious();
                                            finish();
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

    private void searchCoupon() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<GetCouponRequestResponse> call = service.searchCoupon(
                        Utility.getLocale(), WebUtility.SEARCH_COUPON
                                + "q=" + edtLocationSort.getText().toString().trim()
                                + "&type=" + PreferenceData.getCoupon());
                call.enqueue(new Callback<GetCouponRequestResponse>() {
                    @Override
                    public void onResponse(Call<GetCouponRequestResponse> call, Response<GetCouponRequestResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (response.body() != null) {
                                    if (response.body().getData().getObjData() != null) {
                                        if (response.body().getData().getObjData() != null && response.body().getData().getObjData().getData().size() == 0) {
                                            Utility.showError(getString(R.string.no_search_found));
                                        } else {
                                            Utility.BASE_URL = response.body().getImageUrl();
                                            Intent searchHotel = new Intent(mActivity, SearchResultActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt(AppConstant.EXT_IS_FROM,isFrom);
                                            bundle.putSerializable(AppConstant.EXT_SEARCH_DATA, response.body());
                                            searchHotel.putExtras(bundle);
                                            setResult(13, searchHotel);
                                            goPrevious();
                                            finish();
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

    private void suggestLocation(final String search) {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                //Utility.showProgress(this);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<ResponseBody> call = null;

                if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                    call = service.getSuggestLocation(Utility.getLocale(), WebUtility.SUGGEST_ACCOMMODATION
                            + "term=" + search
                    );
                } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                    call = service.getSuggestLocation(Utility.getLocale(), WebUtility.SUGGEST_THING_TO_DO
                            + "term=" + search
                    );
                } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                    call = service.getSuggestLocation(Utility.getLocale(), WebUtility.SUGGEST_COUPON
                            + "term=" + search
                    );
                } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {
                    call = service.getSuggestLocation(Utility.getLocale(), WebUtility.SUGGEST_PACKAGE
                            + "term=" + search
                    );
                } else if (isFrom == AppConstant.IS_FROM_EVENT) {
                    call = service.getSuggestLocation(Utility.getLocale(), WebUtility.SUGGEST_EVENTS
                            + "term=" + search
                    );
                }

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                suggestLocationList.clear();
                                String[] arr = null;
                                if (response.body().toString().length() > 2){
                                    try {
                                        String resStr = response.body().string();
                                        JSONArray jsonArray = new JSONArray(resStr);
                                        arr = new String[jsonArray.length()];
                                        for (int i = 0;i<jsonArray.length();i++){
                                            SuggestLocationRequestResponse location = new SuggestLocationRequestResponse();
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            location.setId(jsonObject.getInt("id"));
                                            location.setLabel(jsonObject.getString("label"));
                                            location.setValue(jsonObject.getString("value"));
                                            suggestLocationList.add(location);
                                            arr[i] = jsonObject.getString("label");
                                        }
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }

                                    if (arr != null){
                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SortHotelActivity.this,android.R.layout.simple_dropdown_item_1line, arr);
                                        //LocationSuggestionAdapter adapter = new LocationSuggestionAdapter(SearchActivity.this,suggestLocationList);
                                        edtLocationSort.setAdapter(adapter);
                                    }
                                }

                            } else {
                                //Utility.showError(getString(R.string.no_search_data));
                            }
                        } else {
                            //Utility.showError(getResources().getString(R.string.message_something_wrong));
                        }
                        //Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
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
