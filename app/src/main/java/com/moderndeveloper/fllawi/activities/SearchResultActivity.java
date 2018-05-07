package com.moderndeveloper.fllawi.activities;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.customviews.RecyclerViewEmptySupport;
import com.moderndeveloper.BaseActivity;

import com.moderndeveloper.fllawi.adapters.HomeListAdapter;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.model.SearchHotel.ArrMainCategory;
import com.moderndeveloper.fllawi.model.SearchHotel.ArrSubCategory;
import com.moderndeveloper.fllawi.model.SearchHotel.Datum;
import com.moderndeveloper.fllawi.model.SearchHotel.SearchRequestResponse;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.Utility;

import java.util.ArrayList;

public class SearchResultActivity extends BaseActivity {

    private View view;
    private RecyclerViewEmptySupport recyclerHome;
    private HomeListAdapter homeListAdapter;
    private TextView tvSort, tvFilter;
    private LinearLayout llSort, llFilter;
    private SearchRequestResponse searchResponseData;
    private ArrayList<Datum> searchHotelListData = new ArrayList<>();
    private ArrayList<ArrMainCategory> categoryList = new ArrayList<>();
    private ArrayList<ArrSubCategory> subCategoryList = new ArrayList<>();
    private int RESULT_FILTER_CODE = 12;
    private int RESULT_SORT_CODE = 15;

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
                searchResponseData = (SearchRequestResponse) bundle.getSerializable(AppConstant.EXT_SEARCH_DATA);
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        isHomeRunning = false;

        //TODO need to implement search
        //etTopSearch

    }

    public void onItemClick(Datum searchResultData) {
        Intent intent = new Intent(mActivity, HotelDetailsActivity.class);
        intent.putExtra(AppConstant.EXT_HOTEL_NAME,searchResultData.getTitle());
        intent.putExtra(AppConstant.EXT_HOTEL_IMAGE,searchResultData.getImage());
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
        ivTopSearch.setVisibility(View.VISIBLE);
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
