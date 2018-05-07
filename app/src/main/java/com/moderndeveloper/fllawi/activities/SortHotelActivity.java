package com.moderndeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.model.SearchHotel.SearchRequestResponse;
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

public class SortHotelActivity extends BaseActivity {

    private View view;
    private TextView tvApply,tvMinAmount,tvMaxAmount;
    private CrystalRangeSeekbar seekBar;
    private String minPrice = "0";
    private String maxPrice = "10000";
    private EditText edtLocationSort;

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
        tvApply = view.findViewById(R.id.tvApply);
        edtLocationSort = view.findViewById(R.id.edtLocationSort);
        tvApply.setOnClickListener(this);
        isHomeRunning = false;

        tvMinAmount.setText("$0");
        tvMaxAmount.setText("$10000");
        seekBar.setMinValue(0);
        seekBar.setMaxValue(10000);

        seekBar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMinAmount.setText("$"+String.valueOf(minValue));
                tvMaxAmount.setText("$"+String.valueOf(maxValue));
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvApply:
                searchAccommodation();
                break;
            case R.id.tvRightText: // Reset
                tvMinAmount.setText("$0");
                tvMaxAmount.setText("$10000");
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
                Date checkInDate = dateFormatter.parse(PreferenceData.getFromDate());
                Date checkOutDate = dateFormatter.parse(PreferenceData.getToDate());

                SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                String checkInDateStr = dateFormatter1.format(checkInDate.getTime());
                String checkOutDateStr = dateFormatter1.format(checkOutDate.getTime());

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<SearchRequestResponse> call = service.searchAccommodation(WebUtility.SEARCH_ACCOMMODATION +
                        "?from_date=" +checkInDateStr
                        +"&to_date="+ checkOutDateStr
                        +"&no_room="+ PreferenceData.getRoom()
                        +"&no_member="+ PreferenceData.getMember()
                        +"&sortBy[name]=asc"
                        +"&sortBy[price]=asc"
                        +"&filterBy[category][]=1"
                        +"&filterBy[subcategory][]=2"
                        +"filterBy[min_price]="+minPrice
                        +"&filterBy[max_price]="+maxPrice
                        +"&q="+edtLocationSort.getText().toString().trim()
                );
                call.enqueue(new Callback<SearchRequestResponse>() {
                    @Override
                    public void onResponse(Call<SearchRequestResponse> call, Response<SearchRequestResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (response.body() != null){
                                    if (response.body().getSearchResultMainData() != null){
                                        if (response.body().getSearchResultMainData().getSearchHotelList() != null && response.body().getSearchResultMainData().getSearchHotelList().getData().size() == 0){
                                            Utility.showError("No Search Data Found");
                                        }else{
                                            Utility.BASE_URL = response.body().getImageUrl();
                                            Intent searchHotel = new Intent(mActivity,SearchResultActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable(AppConstant.EXT_SEARCH_DATA,response.body());
                                            searchHotel.putExtras(bundle);
                                            setResult(13,searchHotel);
                                            goPrevious();
                                            finish();
                                        }
                                    }else{
                                        Utility.showError("No Search Data Found");
                                    }
                                }else{
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


    @Override
    public void onBackPressed() {
        finish();
        goPrevious();
    }
}
