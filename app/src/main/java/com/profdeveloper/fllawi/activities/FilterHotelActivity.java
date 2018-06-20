package com.profdeveloper.fllawi.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.OnCommonAdapterItemClickListener;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.adapters.CommonListAdapter;
import com.profdeveloper.fllawi.model.Coupons.GetCouponRequestResponse;
import com.profdeveloper.fllawi.model.SearchHotel.ArrMainCategory;
import com.profdeveloper.fllawi.model.SearchHotel.ArrSubCategory;
import com.profdeveloper.fllawi.model.SearchHotel.SearchRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDoSearch.GetThingToDoRequestResponse;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.retrofit.WebUtility;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterHotelActivity extends BaseActivity implements OnCommonAdapterItemClickListener {

    private View view;
    private CrystalRangeSeekbar seekBar;
    private TextView tvApplyFilter, tvMinAmount, tvMaxAmount, tvHotelCategory, tvSubCategoryTitle;
    private String minPrice = "0";
    private String maxPrice = "10000";
    private RelativeLayout rlSubCategory, rlCategory;
    private ArrayList<ArrMainCategory> categoryList = new ArrayList<>();
    private ArrayList<ArrSubCategory> subCategoryList = new ArrayList<>();
    private ArrMainCategory mainCategory = null;
    private ArrSubCategory subCategory = null;
    private int sltType = 6;
    private String sltCategoryId = "";
    private String sltSubCategoryId = "";
    private Date checkInDate;
    private Date checkOutDate;
    private String checkInDateStr = "";
    private Date checkOutDateParm;
    private String checkOutDateStr = "";
    private CardView cardViewCategory;
    private int isFrom = 0;
    public static int selectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_filter_hotel, llContainer);
        mActivity = this;
        tvMinAmount = view.findViewById(R.id.tvMinAmount);
        tvMaxAmount = view.findViewById(R.id.tvMaxAmount);
        seekBar = view.findViewById(R.id.rangeSeekBar);
        cardViewCategory = view.findViewById(R.id.cardViewCategory);
        tvHotelCategory = view.findViewById(R.id.tvHotelCategory);
        tvSubCategoryTitle = view.findViewById(R.id.tvSubCategoryTitle);
        tvApplyFilter = view.findViewById(R.id.tvApplyFilter);
        tvApplyFilter.setOnClickListener(this);
        isHomeRunning = false;

        rlCategory = view.findViewById(R.id.rlCategory);
        rlSubCategory = view.findViewById(R.id.rlSubCategory);
        rlCategory.setOnClickListener(this);
        rlSubCategory.setOnClickListener(this);

        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);

                if (isFrom == AppConstant.IS_FROM_COUPON){
                    cardViewCategory.setVisibility(View.GONE);
                }

                categoryList = (ArrayList<ArrMainCategory>) bundle.getSerializable(AppConstant.EXT_CATEGORY);
                subCategoryList = (ArrayList<ArrSubCategory>) bundle.getSerializable(AppConstant.EXT_SUB_CATEGORY);
            }

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

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialization() {
        tvTopTitle.setBackgroundResource(0);
        tvTopTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        tvTopTitle.setText(R.string.filter);
        tvTopTitle.setVisibility(View.VISIBLE);
        ivTopSearch.setVisibility(View.GONE);
        ivTopBack.setVisibility(View.GONE);

        tvLeftText.setText(R.string.cancel);
        tvRightText.setText(R.string.reset);
        tvLeftText.setVisibility(View.VISIBLE);
        tvRightText.setVisibility(View.VISIBLE);
        tvLeftText.setOnClickListener(this);
        tvRightText.setOnClickListener(this);
        checkLocal();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rlCategory:
                selectedPosition = 0;
                sltType = 6;
                if (categoryList != null && !categoryList.isEmpty()) {
                    ArrayList<Object> objectArrayList = new ArrayList<>();
                    ArrMainCategory category = new ArrMainCategory();
                    category.setId(0);
                    category.setTitle(getString(R.string.all));
                    objectArrayList.add(0,category);
                    for (Object obj : categoryList) {
                        objectArrayList.add(obj);
                    }
                    CommonListAdapter commonListAdapter = new CommonListAdapter(FilterHotelActivity.this, 6, objectArrayList, this);
                    openItemSelectList(this, commonListAdapter);
                }
                break;
            case R.id.rlSubCategory:
                sltType = 7;
                selectedPosition = 0;
                if (subCategoryList != null && !subCategoryList.isEmpty()) {
                    ArrayList<Object> objectArrayList = new ArrayList<>();
                    ArrSubCategory category = new ArrSubCategory();
                    category.setId(0);
                    category.setTitle(getString(R.string.all));
                    objectArrayList.add(0,category);
                    for (Object obj : subCategoryList) {
                        objectArrayList.add(obj);
                    }
                    CommonListAdapter commonListAdapter = new CommonListAdapter(FilterHotelActivity.this, 7, objectArrayList, this);
                    openItemSelectList(this, commonListAdapter);
                }
                break;
            case R.id.tvApplyFilter:
                if (isFrom == AppConstant.IS_FROM_ACCOMMODATION){
                    searchAccommodation();
                }else if (isFrom == AppConstant.IS_FROM_THING_TO_DO){
                    searchThingToDo();
                }else if (isFrom == AppConstant.IS_FROM_COUPON){
                    searchCoupon();
                }else if (isFrom == AppConstant.IS_FROM_PACKAGE){
                    searchPackage();
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

                if (PreferenceData.getFromDate().length() > 0){
                    checkInDate = dateFormatter.parse(PreferenceData.getFromDate());
                    checkInDateStr = dateFormatter1.format(checkInDate.getTime());
                }

                if (PreferenceData.getToDate().length() > 0){
                    checkOutDateParm = dateFormatter.parse(PreferenceData.getToDate());
                    checkOutDateStr = dateFormatter1.format(checkOutDateParm.getTime());
                }

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<SearchRequestResponse> call = service.searchAccommodation(Utility.getLocale(), WebUtility.SEARCH_ACCOMMODATION +
                        "from_date=" + checkInDateStr
                        + "&to_date=" + checkOutDateStr
                        + "&no_room=" + PreferenceData.getRoom()
                        + "&no_member=" + PreferenceData.getMember()
                        + "&filterBy[category][]=" + sltCategoryId
                        + "&filterBy[subcategory][]=" + sltSubCategoryId
                        + "filterBy[min_price]=" + minPrice
                        + "&filterBy[max_price]=" + maxPrice
                        + "&q="+PreferenceData.getLocation()
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
                                            bundle.putSerializable(AppConstant.EXT_SEARCH_DATA, response.body());
                                            searchHotel.putExtras(bundle);
                                            setResult(12, searchHotel);
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
                                + "q=" + PreferenceData.getLocation()
                                + "&schedule_date=" + scheduleDateStr
                                + "filterBy[min_price]=" + minPrice
                                + "&filterBy[max_price]=" + maxPrice
                                + "&filterBy[category][]" + sltCategoryId
                                + "&filterBy[subcategory][]" + sltSubCategoryId
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
                                            setResult(12, searchHotel);
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
                                + "q=" + PreferenceData.getLocation()
                                + "&type=" + PreferenceData.getCoupon()
                                + "filterBy[min_price]=" + minPrice
                                + "&filterBy[max_price]=" + maxPrice
                );
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
                                            setResult(12, searchHotel);
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

    private void searchPackage() {
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
                        Utility.getLocale(), WebUtility.GET_PACKAGE
                                + "q=" + PreferenceData.getLocation()
                                + "&schedule_date=" + scheduleDateStr
                                + "filterBy[min_price]=" + minPrice
                                + "&filterBy[max_price]=" + maxPrice
                                + "&filterBy[category][]" + sltCategoryId
                                + "&filterBy[subcategory][]" + sltSubCategoryId
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
                                            setResult(12, searchHotel);
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


    public void openItemSelectList(Activity context, CommonListAdapter arrayListAdapter) {
        final Dialog popupWindowDialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar);

        final View layout = LayoutInflater.from(context).inflate(R.layout.dialog_select_item, null);
        popupWindowDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (popupWindowDialog.getWindow() != null) {
            popupWindowDialog.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.transparent)));
        }
        popupWindowDialog.setContentView(layout);
        popupWindowDialog.setCancelable(false);

        RecyclerView rvSelectItem = layout.findViewById(R.id.rvSelectItem);
        rvSelectItem.setLayoutManager(Utility.getLayoutManager(context));
        rvSelectItem.setAdapter(arrayListAdapter);
        ImageView ivClose = (ImageView) layout.findViewById(R.id.ivClose);
        TextView tvSubmit = layout.findViewById(R.id.tvSubmit);

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDialog.dismiss();
                if (selectedPosition != 0){
                    if (sltType == 6) {
                        tvHotelCategory.setText(mainCategory.getTitle());
                        sltCategoryId = mainCategory.getId()+"";
                    } else {
                        tvSubCategoryTitle.setText(subCategory.getTitle());
                        sltSubCategoryId = subCategory.getId()+"";
                    }
                }else{
                    if (sltType == 6){
                        tvHotelCategory.setText(getString(R.string.all));
                        sltCategoryId = "";
                    }else {
                        tvSubCategoryTitle.setText(getString(R.string.all));
                        sltSubCategoryId = "";
                    }
                }
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

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onCommonItemClickListener(int type, int position, Object object) {
        selectedPosition = position;
        sltType = type;
        if (position != 0){
            if (type == 6) {
                mainCategory = (ArrMainCategory) object;
            } else {
                subCategory = (ArrSubCategory) object;
            }
        }else{
            mainCategory = new ArrMainCategory();
            subCategory = new ArrSubCategory();
        }
    }
}
