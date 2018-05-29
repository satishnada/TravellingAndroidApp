package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.customviews.RecyclerViewEmptySupport;
import com.profdeveloper.BaseActivity;

import com.profdeveloper.fllawi.adapters.CouponListAdapter;
import com.profdeveloper.fllawi.adapters.HomeListAdapter;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.adapters.ThingToDoListAdapter;
import com.profdeveloper.fllawi.model.Coupons.GetCouponRequestResponse;
import com.profdeveloper.fllawi.model.SearchHotel.ArrMainCategory;
import com.profdeveloper.fllawi.model.SearchHotel.ArrSubCategory;
import com.profdeveloper.fllawi.model.SearchHotel.Datum;
import com.profdeveloper.fllawi.model.SearchHotel.SearchRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDo.GetThingToDoCategoryRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDoSearch.GetThingToDoRequestResponse;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.Utility;

import java.util.ArrayList;

public class SearchResultActivity extends BaseActivity {

    private View view;
    private RecyclerView recyclerHome;
    private HomeListAdapter homeListAdapter;
    private TextView tvSort, tvFilter;
    private LinearLayout llSort, llFilter;
    private SearchRequestResponse searchResponseData;

    private GetCouponRequestResponse couponResponseData;
    private CouponListAdapter couponListAdapter;
    private ArrayList<Datum> searchHotelListData = new ArrayList<>();

    private GetThingToDoRequestResponse thingToDoResponseData;
    private ThingToDoListAdapter thingToDoListAdapter;
    private ArrayList<ArrMainCategory> categoryThingToDoList = new ArrayList<>();
    private ArrayList<ArrSubCategory> subCategoryThingToDoList = new ArrayList<>();

    private ArrayList<com.profdeveloper.fllawi.model.Coupons.Datum> searchCouponListData = new ArrayList<>();
    private ArrayList<com.profdeveloper.fllawi.model.ThingToDoSearch.Datum> searchThingToDoListData = new ArrayList<>();
    private ArrayList<ArrMainCategory> categoryList = new ArrayList<>();
    private ArrayList<ArrSubCategory> subCategoryList = new ArrayList<>();
    private int RESULT_FILTER_CODE = 12;
    private int RESULT_SORT_CODE = 15;
    private int isFrom = 0;
    private String fromDate = "";
    private String toDate = "";

    private String categoryId = "";
    private String subCategoryId = "";
    private String scheduledDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_search_result, llContainer);
        mActivity = this;
        recyclerHome = view.findViewById(R.id.recyclerHome);
        recyclerHome.setLayoutManager(Utility.getLayoutManager(mActivity));

        tvSort = view.findViewById(R.id.tvSort);
        tvFilter = view.findViewById(R.id.tvFilter);
        llSort = view.findViewById(R.id.llSort);
        llFilter = view.findViewById(R.id.llFilter);

        tvSort.setOnClickListener(this);
        tvFilter.setOnClickListener(this);
        llFilter.setOnClickListener(this);
        llSort.setOnClickListener(this);
        ivTopSearch.setOnClickListener(this);
        ivTopBack.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        try {
            if (bundle != null) {

                isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);

                if (isFrom == AppConstant.IS_FROM_ACCOMMODATION){

                    searchResponseData = (SearchRequestResponse) bundle.getSerializable(AppConstant.EXT_SEARCH_DATA);
                    searchHotelListData = (ArrayList<Datum>) searchResponseData.getSearchResultMainData().getSearchHotelList().getData();

                    fromDate = bundle.getString(AppConstant.FROM_DATE);
                    toDate = bundle.getString(AppConstant.TO_DATE);

                    if (searchResponseData.getSearchResultMainData().getCategory() != null) {
                        categoryList = (ArrayList<ArrMainCategory>) searchResponseData.getSearchResultMainData().getCategory();
                    }

                    if (searchResponseData.getSearchResultMainData().getSubCategory() != null) {
                        subCategoryList = (ArrayList<ArrSubCategory>) searchResponseData.getSearchResultMainData().getSubCategory();
                    }

                    homeListAdapter = new HomeListAdapter(mActivity, searchHotelListData);
                    recyclerHome.setAdapter(homeListAdapter);

                } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO){

                    categoryId = bundle.getString(AppConstant.EXT_CATEGORY);
                    subCategoryId = bundle.getString(AppConstant.EXT_SUB_CATEGORY);
                    scheduledDate = bundle.getString(AppConstant.EXT_FROM_DATE);

                    thingToDoResponseData = (GetThingToDoRequestResponse) bundle.getSerializable(AppConstant.EXT_SEARCH_DATA);

                    if (thingToDoResponseData.getData().getArrMainCategory() != null) {
                        categoryList = (ArrayList<ArrMainCategory>) thingToDoResponseData.getData().getArrMainCategory();
                    }

                    if (thingToDoResponseData.getData().getArrSubCategory() != null) {
                        subCategoryList = (ArrayList<ArrSubCategory>) thingToDoResponseData.getData().getArrSubCategory();
                    }

                    searchThingToDoListData = (ArrayList<com.profdeveloper.fllawi.model.ThingToDoSearch.Datum>) thingToDoResponseData.getData().getObjData().getData();

                    if (thingToDoResponseData.getData().getArrMainCategory() != null) {
                        categoryThingToDoList = (ArrayList<ArrMainCategory>) thingToDoResponseData.getData().getArrMainCategory();
                    }

                    if (thingToDoResponseData.getData().getArrSubCategory() != null) {
                        subCategoryThingToDoList = (ArrayList<ArrSubCategory>) thingToDoResponseData.getData().getArrSubCategory();
                    }

                    thingToDoListAdapter = new ThingToDoListAdapter(mActivity,searchThingToDoListData);
                    recyclerHome.setAdapter(thingToDoListAdapter);
                } else if (isFrom == AppConstant.IS_FROM_COUPON){
                    couponResponseData = (GetCouponRequestResponse) bundle.getSerializable(AppConstant.EXT_SEARCH_DATA);
                    searchCouponListData = (ArrayList<com.profdeveloper.fllawi.model.Coupons.Datum>) couponResponseData.getData().getObjData().getData();
                    couponListAdapter = new CouponListAdapter(mActivity,searchCouponListData);
                    recyclerHome.setAdapter(couponListAdapter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        isHomeRunning = false;

        //TODO need to implement search
        //etTopSearch

    }

    public void onItemClick(Datum searchResultData) {
        Intent intent = new Intent(mActivity, HotelDetailsActivity.class);
        intent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_ACCOMMODATION);
        intent.putExtra(AppConstant.EXT_HOTEL_NAME,searchResultData.getTitle());
        intent.putExtra(AppConstant.EXT_HOTEL_IMAGE,searchResultData.getImage());
        intent.putExtra(AppConstant.EXT_FROM_DATE,fromDate);
        intent.putExtra(AppConstant.EXT_TO_DATE,toDate);
        intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID,searchResultData.getId()+"");
        startActivity(intent);
        goNext();
        finish();
    }

    public void onCouponItemClick(com.profdeveloper.fllawi.model.Coupons.Datum searchResultData) {
        Intent intent = new Intent(mActivity, HotelDetailsActivity.class);
        intent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_COUPON);
        intent.putExtra(AppConstant.EXT_HOTEL_NAME,searchResultData.getTitle());
        intent.putExtra(AppConstant.EXT_HOTEL_IMAGE,searchResultData.getImage());
        intent.putExtra(AppConstant.EXT_TO_DATE,toDate);
        intent.putExtra(AppConstant.EXT_FROM_DATE,fromDate);
        intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID,searchResultData.getId()+"");
        startActivity(intent);
        goNext();
        finish();
    }

    public void onThingToDoItemClick(com.profdeveloper.fllawi.model.ThingToDoSearch.Datum searchResultData) {
        Intent intent = new Intent(mActivity, HotelDetailsActivity.class);
        intent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_THING_TO_DO);
        intent.putExtra(AppConstant.EXT_HOTEL_NAME,searchResultData.getTitle());
        intent.putExtra(AppConstant.EXT_HOTEL_IMAGE,searchResultData.getImage());
        intent.putExtra(AppConstant.EXT_FROM_DATE,scheduledDate);
        intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID,searchResultData.getId()+"");
        startActivity(intent);
        goNext();
        finish();
    }

    @Override
    public void initialization() {
        ivTopBack.setImageResource(R.drawable.ic_back_arrow);
        tvTopTitle.setBackgroundResource(0);
        tvTopTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        tvTopTitle.setText(R.string.search_result);
        tvTopTitle.setVisibility(View.VISIBLE);
        ivTopSearch.setVisibility(View.GONE);
        checkLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llSort:
                Intent sortIntent = new Intent(mActivity, SortHotelActivity.class);
                startActivityForResult(sortIntent, RESULT_SORT_CODE);
                goNext();
                break;
            case R.id.llFilter:
                Intent filterIntent = new Intent(mActivity, FilterHotelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(AppConstant.EXT_CATEGORY,categoryList);
                bundle.putSerializable(AppConstant.EXT_SUB_CATEGORY,subCategoryList);
                filterIntent.putExtras(bundle);
                startActivityForResult(filterIntent, RESULT_FILTER_CODE);
                goNext();
                break;
            case R.id.ivTopSearch:
                Intent searchIntent = new Intent(mActivity, SearchActivity.class);
                startActivity(searchIntent);
                goNext();
                finish();
                break;
            case R.id.ivTopBack:
                Intent homeIntent = new Intent(mActivity, HomeActivity.class);
                startActivity(homeIntent);
                finish();
                goPrevious();
                break;
            case R.id.tvSort:
                llSort.performClick();
                break;
            case R.id.tvFilter:
                llFilter.performClick();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void setListData(SearchRequestResponse searchResponseData) {
        searchResponseData = (SearchRequestResponse) searchResponseData;
        searchHotelListData = (ArrayList<Datum>) searchResponseData.getSearchResultMainData().getSearchHotelList().getData();

        if (searchResponseData.getSearchResultMainData().getCategory() != null) {
            categoryList = (ArrayList<ArrMainCategory>) searchResponseData.getSearchResultMainData().getCategory();
        }

        if (searchResponseData.getSearchResultMainData().getSubCategory() != null) {
            subCategoryList = (ArrayList<ArrSubCategory>) searchResponseData.getSearchResultMainData().getSubCategory();
        }

        homeListAdapter = new HomeListAdapter(mActivity, searchHotelListData);
        recyclerHome.setAdapter(homeListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_FILTER_CODE && requestCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                SearchRequestResponse requestResponse = (SearchRequestResponse) bundle.getSerializable(AppConstant.EXT_SEARCH_DATA);
                setListData(requestResponse);
            } else { // SORT CODE
                Bundle bundle = data.getExtras();
                SearchRequestResponse requestResponse = (SearchRequestResponse) bundle.getSerializable(AppConstant.EXT_SEARCH_DATA);
                setListData(requestResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(mActivity, HomeActivity.class);
        startActivity(homeIntent);
        finish();
        goPrevious();
    }
}
