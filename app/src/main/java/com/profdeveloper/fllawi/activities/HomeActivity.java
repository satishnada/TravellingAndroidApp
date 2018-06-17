package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.adapters.DestinationsListAdapter;
import com.profdeveloper.fllawi.adapters.HomeListAdapter;
import com.profdeveloper.fllawi.adapters.OffersAndPriceListAdapter;
import com.profdeveloper.fllawi.adapters.OurServicesListAdapter;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.model.AccommodationBooking.AccommodationBookingRequestResponse;
import com.profdeveloper.fllawi.model.HomeTopDestination.ArrTopCoupon;
import com.profdeveloper.fllawi.model.HomeTopDestination.TopDestinationRequestResponse;
import com.profdeveloper.fllawi.model.SearchHotel.Datum;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.retrofit.WebUtility;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    private View view;
    private RecyclerView rvOurServices, rvTopDestination, rvTopOffers;
    private HomeListAdapter homeListAdapter;
    private OurServicesListAdapter ourServicesListAdapter;
    private DestinationsListAdapter destinationsListAdapter;
    private OffersAndPriceListAdapter offersAndPriceListAdapter;
    private ImageView ivTopHomeBack, ivTopHomeSearch;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_toolbar;
    private TextView tvEnglishTop, tvAdditionalDiscount, tvEarnAndPay, tvFasterCheckout;
    private ImageView ivTopMenu, ivTopSearch;
    private TextView tvSeeAllDestination,tvNoDataTopDestination;

    private ArrayList<ArrTopCoupon> topCouponsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        mActivity = this;
        isHomeRunning = true;
        serAllView();
        setLanguage();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        view = LayoutInflater.from(this).inflate(R.layout.activity_home, llContainer);
    }

    private void serAllView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_home, llContainer);
        ivTopMenu = view.findViewById(R.id.ivTopMenu);
        ivTopSearch = view.findViewById(R.id.ivTopSearch);
        tvEnglishTop = view.findViewById(R.id.tvEnglishTop);
        tvSeeAllDestination = view.findViewById(R.id.tvSeeAllDestination);
        tvNoDataTopDestination = view.findViewById(R.id.tvNoDataTopDestination);

        tvAdditionalDiscount = view.findViewById(R.id.tvAdditionalDiscount);
        tvEarnAndPay = view.findViewById(R.id.tvEarnAndPay);
        tvFasterCheckout = view.findViewById(R.id.tvFasterCheckout);

        tvAdditionalDiscount.setText(getString(R.string.get_additional_ndiscounts));
        tvEarnAndPay.setText(getString(R.string.earn_and_pay));
        tvFasterCheckout.setText(getString(R.string.faster_checkout));

        tvEnglishTop.setOnClickListener(this);
        ivTopMenu.setOnClickListener(this);
        ivTopSearch.setOnClickListener(this);

        collapsing_toolbar = view.findViewById(R.id.collapsing_toolbar);
        toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(5);
        actionBar.setDisplayShowHomeEnabled(true);

        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        getSupportActionBar().setTitle("");

        rvOurServices = view.findViewById(R.id.rvOurServices);
        rvTopDestination = view.findViewById(R.id.rvTopDestination);
        rvTopOffers = view.findViewById(R.id.rvTopOffers);
        rvOurServices.setLayoutManager(Utility.getLayoutManagerHorizontal(mActivity));
        ourServicesListAdapter = new OurServicesListAdapter(mActivity);
        rvOurServices.setAdapter(ourServicesListAdapter);

        rvTopDestination.setLayoutManager(Utility.getLayoutManagerHorizontal(mActivity));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        rvTopOffers.setLayoutManager(gridLayoutManager);
        ArrayList<String> topOffers = new ArrayList<>();
        offersAndPriceListAdapter = new OffersAndPriceListAdapter(mActivity, topOffers);
        rvTopOffers.setAdapter(offersAndPriceListAdapter);

        getTopDestination();
    }

    private void setLanguage() {
        if (PreferenceData.getUserLanguage() != null) {
            if (PreferenceData.getUserLanguage().equalsIgnoreCase("ar")) {
                changeLanguage("ar");
                tvEnglishTop.setText("Eng");
            } else {
                tvEnglishTop.setText("عربي");
                changeLanguage("en");
            }
        } else {
            tvEnglishTop.setText("عربي");
            changeLanguage("en");
        }
    }

    private void setLanguageOnClick() {
        if (PreferenceData.getUserLanguage() != null) {
            if (PreferenceData.getUserLanguage().equalsIgnoreCase("ar")) {
                changeLanguage("en");
                PreferenceData.setUserLang("en");
                tvEnglishTop.setText("عربي");
            } else {
                tvEnglishTop.setText("Eng");
                PreferenceData.setUserLang("ar");
                changeLanguage("ar");
            }
        } else {
            PreferenceData.setUserLang("en");
            tvEnglishTop.setText("عربي");
            changeLanguage("en");
        }
    }

    public void onOurServiceItemClick(int position) {
        if (position == 0) { // Accommodation
            Intent searchIntent = new Intent(mActivity, SearchActivity.class);
            searchIntent.putExtra(AppConstant.EXT_IS_FROM, AppConstant.IS_FROM_ACCOMMODATION);
            startActivity(searchIntent);
            goNext();
        } else if (position == 1) { // Things to do
            Intent searchIntent = new Intent(mActivity, SearchActivity.class);
            searchIntent.putExtra(AppConstant.EXT_IS_FROM, AppConstant.IS_FROM_THING_TO_DO);
            startActivity(searchIntent);
            goNext();
        } else if (position == 2) { // Coupon
            Intent searchIntent = new Intent(mActivity, SearchActivity.class);
            searchIntent.putExtra(AppConstant.EXT_IS_FROM, AppConstant.IS_FROM_COUPON);
            startActivity(searchIntent);
            goNext();
        } else if (position == 3) { // Transportation
           /* Intent searchIntent = new Intent(mActivity, SearchActivity.class);
            searchIntent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_TRANSPORTATION);
            startActivity(searchIntent);
            goNext();*/
        } else if (position == 4) { // Event
         /*   Intent searchIntent = new Intent(mActivity, SearchActivity.class);
            searchIntent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_EVENT);
            startActivity(searchIntent);
            goNext();*/
        }
    }

    public void onAccommodationItemClick(Datum searchResultData) {
        Intent intent = new Intent(mActivity, HotelDetailsActivity.class);
        intent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_ACCOMMODATION);
        intent.putExtra(AppConstant.EXT_HOTEL_NAME,searchResultData.getTitle());
        intent.putExtra(AppConstant.EXT_HOTEL_IMAGE,searchResultData.getImage());
        intent.putExtra(AppConstant.EXT_FROM_DATE,"");
        intent.putExtra(AppConstant.EXT_TO_DATE,"");
        intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID,searchResultData.getId()+"");
        intent.putExtra(AppConstant.EXT_AVG_RATTING,searchResultData.getAvrageUserRatting()+"");
        startActivity(intent);
        goNext();
    }

    public void onCouponItemClick(ArrTopCoupon coupon) {
        Intent intent = new Intent(mActivity, HotelDetailsActivity.class);
        intent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_COUPON);
        intent.putExtra(AppConstant.EXT_HOTEL_NAME,coupon.getTitle());
        intent.putExtra(AppConstant.EXT_HOTEL_IMAGE,"");
        intent.putExtra(AppConstant.EXT_TO_DATE,"");
        intent.putExtra(AppConstant.EXT_FROM_DATE,"");
        intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID,coupon.getId()+"");
        intent.putExtra(AppConstant.EXT_AVG_RATTING,"0");
        startActivity(intent);
        goNext();
    }

    public void onThingToDoItemClick(com.profdeveloper.fllawi.model.ThingToDoSearch.Datum searchResultData) {
        Intent intent = new Intent(mActivity, HotelDetailsActivity.class);
        intent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_THING_TO_DO);
        intent.putExtra(AppConstant.EXT_HOTEL_NAME,searchResultData.getTitle());
        intent.putExtra(AppConstant.EXT_HOTEL_IMAGE,searchResultData.getImage());
        intent.putExtra(AppConstant.EXT_FROM_DATE,"");
        intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID,searchResultData.getId()+"");
        intent.putExtra(AppConstant.EXT_AVG_RATTING,searchResultData.getAvrageUserRating()+"");
        startActivity(intent);
        goNext();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Log.i("HomeActivity", "onResume: ");
            //serAllView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            Log.i("HomeActivity", "onRestart: ");
            recreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialization() {
        hideTopBar();
        checkLocal();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvEnglishTop:
                setLanguageOnClick();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
                serAllView();
                break;
            case R.id.ivTopMenu:
                closeSideMenu();
                break;
            case R.id.ivTopSearch:
                Intent searchIntent = new Intent(mActivity, SearchActivity.class);
                startActivity(searchIntent);
                goNext();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void getTopDestination() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<TopDestinationRequestResponse> call = service.getTopDestination(Utility.getLocale(), WebUtility.GET_HOME_TOP_DESTINATION);
                call.enqueue(new Callback<TopDestinationRequestResponse>() {
                    @Override
                    public void onResponse(Call<TopDestinationRequestResponse> call, Response<TopDestinationRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                topCouponsList.clear();
                                topCouponsList = (ArrayList<ArrTopCoupon>) response.body().getArrTopCoupon();
                                if (topCouponsList != null && !topCouponsList.isEmpty()){
                                    destinationsListAdapter = new DestinationsListAdapter(mActivity, topCouponsList);
                                    rvTopDestination.setAdapter(destinationsListAdapter);
                                    tvSeeAllDestination.setVisibility(View.VISIBLE);
                                    rvTopDestination.setVisibility(View.VISIBLE);
                                    tvNoDataTopDestination.setVisibility(View.GONE);
                                }else{
                                    tvNoDataTopDestination.setVisibility(View.VISIBLE);
                                    tvSeeAllDestination.setVisibility(View.GONE);
                                    rvTopDestination.setVisibility(View.GONE);
                                }
                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<TopDestinationRequestResponse> call, Throwable t) {
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


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(Gravity.START)) {
            drawer_layout.closeDrawer(Gravity.LEFT);
        } else {
            if (doubleBackToExitPressedOnce) {
                finish();
                goPrevious();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Utility.showError(getString(R.string.press_again_to_exit));
            //showSuccessSnackBar(getString(R.string.press_again_to_exit));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}
