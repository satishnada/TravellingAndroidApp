package com.profdeveloper.fllawi.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.customviews.TypeFace;
import com.google.gson.Gson;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.adapters.OrderHistoryAccommodationAdapter;
import com.profdeveloper.fllawi.adapters.OrderHistoryCouponAdapter;
import com.profdeveloper.fllawi.adapters.OrderHistoryThingToDoAdapter;
import com.profdeveloper.fllawi.fragments.HotelDetailHostFragment;
import com.profdeveloper.fllawi.fragments.HotelDetailInfoFragment;
import com.profdeveloper.fllawi.fragments.HotelDetailPhotosFragment;
import com.profdeveloper.fllawi.fragments.HotelDetailReviewFragment;
import com.profdeveloper.fllawi.model.BookingHistory.BookingHistoryDetailRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistory.BookingHistoryRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistoryCoupon.BookingHistoryCouponRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistoryThingToDo.BookingHistoryThingToDoRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistoryThingToDo.Datum;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingHistoryActivity extends BaseActivity {

    private static final String TAG = BookingHistoryActivity.class.getSimpleName();
    private View view;
    private RecyclerView recyclerBookingHistory;
    private TextView tvNoData;
    private OrderHistoryAccommodationAdapter orderHistoryAdapter;
    private OrderHistoryThingToDoAdapter orderHistoryThingToDoAdapter;
    private OrderHistoryCouponAdapter orderHistoryCouponAdapter;

    private ArrayList<com.profdeveloper.fllawi.model.BookingHistory.Datum> orderHistoryList = new ArrayList<>();
    private ArrayList<Datum> orderHistoryThingToDoList = new ArrayList<>();
    private ArrayList<com.profdeveloper.fllawi.model.BookingHistoryCoupon.Datum> orderHistoryCouponList = new ArrayList<>();
    private int sltType = 0; // Pending booking default.
    private int serviceType = 1; //Accommodation 1 , ThingToDo 2 , Coupon 3
    private TabLayout topMenuLayout;
    private int sltPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_booking_history_list, llContainer);
        recyclerBookingHistory = (RecyclerView) view.findViewById(R.id.recyclerBookingHistory);
        tvNoData = (TextView) view.findViewById(R.id.tvNoData);

        topMenuLayout = (TabLayout) view.findViewById(R.id.tlTopMenu);
        topMenuLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        topMenuLayout.addTab(topMenuLayout.newTab().setText(R.string.accommodation), 0);
        topMenuLayout.addTab(topMenuLayout.newTab().setText(R.string.thing_to_do), 1);
        topMenuLayout.addTab(topMenuLayout.newTab().setText(R.string.coupon), 2);
        topMenuLayout.setTabTextColors(mActivity.getResources().getColor(R.color.dark_gray), mActivity.getResources().getColor(R.color.app_blue));

        topMenuLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                sltPosition = tab.getPosition();
                sltType = 0;
                if (tab.getPosition() == 0) {
                    getAccommodationHistoryList();
                } else if (tab.getPosition() == 1) {
                    getThingToDoHistoryList();
                } else if (tab.getPosition() == 2) {
                    getCouponHistoryList();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getAccommodationHistoryList();
        changeTabsFont();

    }

    @Override
    public void initialization() {
        tvTopTitle.setText(R.string.booking_history);
        ivTopBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_top_menu));
        ivTopSearch.setImageDrawable(getResources().getDrawable(R.drawable.ic_filter_history));
        ivTopSearch.setVisibility(View.VISIBLE);
        checkLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivTopBack:
                closeSideMenu();
                break;
            case R.id.ivTopSearch:
                showFilterPopupMenu(ivTopSearch);
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void getAccommodationHistoryList() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                //Call<BookingHistoryRequestResponse> call = service.getBookingHistoryList(Utility.getLocale(),sltType+"",PreferenceData.getUserData().getId()+"");
                Call<BookingHistoryRequestResponse> call = service.getBookingHistoryList(Utility.getLocale(), sltType + "", PreferenceData.getUserData().getId() + "");

                call.enqueue(new Callback<BookingHistoryRequestResponse>() {
                    @Override
                    public void onResponse(Call<BookingHistoryRequestResponse> call, Response<BookingHistoryRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                orderHistoryList.clear();
                                Utility.BASE_GALLERY_URL = response.body().getImage_url();
                                orderHistoryList.addAll(response.body().getObjAccomodationBooking().getData());

                                if (orderHistoryList != null && !orderHistoryList.isEmpty()) {
                                    recyclerBookingHistory.setVisibility(View.VISIBLE);
                                    tvNoData.setVisibility(View.GONE);

                                    recyclerBookingHistory.setHasFixedSize(true);
                                    recyclerBookingHistory.setLayoutManager(new LinearLayoutManager(BookingHistoryActivity.this, LinearLayoutManager.VERTICAL, false));
                                    orderHistoryAdapter = new OrderHistoryAccommodationAdapter(BookingHistoryActivity.this, orderHistoryList);
                                    recyclerBookingHistory.setAdapter(orderHistoryAdapter);

                                } else {
                                    recyclerBookingHistory.setVisibility(View.GONE);
                                    tvNoData.setVisibility(View.VISIBLE);
                                }

                            } else {
                                Utility.showError(response.body().getMsg());
                                recyclerBookingHistory.setVisibility(View.GONE);
                                tvNoData.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                            recyclerBookingHistory.setVisibility(View.GONE);
                            tvNoData.setVisibility(View.VISIBLE);
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<BookingHistoryRequestResponse> call, Throwable t) {
                        Utility.log("" + t.getMessage());
                        recyclerBookingHistory.setVisibility(View.GONE);
                        tvNoData.setVisibility(View.VISIBLE);
                        Utility.hideProgress();
                        Utility.showError(t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getThingToDoHistoryList() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                //Call<BookingHistoryRequestResponse> call = service.getBookingHistoryList(Utility.getLocale(),sltType+"",PreferenceData.getUserData().getId()+"");
                Call<BookingHistoryThingToDoRequestResponse> call = service.getBookingHistoryThingToDoList(Utility.getLocale(), sltType + "", PreferenceData.getUserData().getId() + "");

                call.enqueue(new Callback<BookingHistoryThingToDoRequestResponse>() {
                    @Override
                    public void onResponse(Call<BookingHistoryThingToDoRequestResponse> call, Response<BookingHistoryThingToDoRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                orderHistoryThingToDoList.clear();
                                Utility.BASE_GALLERY_URL = response.body().getImage_url();
                                orderHistoryThingToDoList.addAll(response.body().getObjThingtodoBooking().getData());

                                if (orderHistoryThingToDoList != null && !orderHistoryThingToDoList.isEmpty()) {
                                    recyclerBookingHistory.setVisibility(View.VISIBLE);
                                    tvNoData.setVisibility(View.GONE);

                                    recyclerBookingHistory.setHasFixedSize(true);
                                    recyclerBookingHistory.setLayoutManager(new LinearLayoutManager(BookingHistoryActivity.this, LinearLayoutManager.VERTICAL, false));
                                    orderHistoryThingToDoAdapter = new OrderHistoryThingToDoAdapter(BookingHistoryActivity.this, orderHistoryThingToDoList);
                                    recyclerBookingHistory.setAdapter(orderHistoryThingToDoAdapter);

                                } else {
                                    recyclerBookingHistory.setVisibility(View.GONE);
                                    tvNoData.setVisibility(View.VISIBLE);
                                }

                            } else {
                                Utility.showError(response.body().getMsg());
                                recyclerBookingHistory.setVisibility(View.GONE);
                                tvNoData.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                            recyclerBookingHistory.setVisibility(View.GONE);
                            tvNoData.setVisibility(View.VISIBLE);
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<BookingHistoryThingToDoRequestResponse> call, Throwable t) {
                        Utility.log("" + t.getMessage());
                        recyclerBookingHistory.setVisibility(View.GONE);
                        tvNoData.setVisibility(View.VISIBLE);
                        Utility.hideProgress();
                        Utility.showError(t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCouponHistoryList() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                //Call<BookingHistoryRequestResponse> call = service.getBookingHistoryList(Utility.getLocale(),sltType+"",PreferenceData.getUserData().getId()+"");
                Call<BookingHistoryCouponRequestResponse> call = service.getBookingHistoryCouponList(Utility.getLocale(), sltType + "", PreferenceData.getUserData().getId() + "");

                call.enqueue(new Callback<BookingHistoryCouponRequestResponse>() {
                    @Override
                    public void onResponse(Call<BookingHistoryCouponRequestResponse> call, Response<BookingHistoryCouponRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                orderHistoryCouponList.clear();
                                Utility.BASE_GALLERY_URL = response.body().getImage_url();
                                orderHistoryCouponList.addAll(response.body().getObjCouponBooking().getData());

                                if (orderHistoryCouponList != null && !orderHistoryCouponList.isEmpty()) {
                                    recyclerBookingHistory.setVisibility(View.VISIBLE);
                                    tvNoData.setVisibility(View.GONE);

                                    recyclerBookingHistory.setHasFixedSize(true);
                                    recyclerBookingHistory.setLayoutManager(new LinearLayoutManager(BookingHistoryActivity.this, LinearLayoutManager.VERTICAL, false));
                                    orderHistoryCouponAdapter = new OrderHistoryCouponAdapter(BookingHistoryActivity.this, orderHistoryCouponList);
                                    recyclerBookingHistory.setAdapter(orderHistoryCouponAdapter);

                                } else {
                                    recyclerBookingHistory.setVisibility(View.GONE);
                                    tvNoData.setVisibility(View.VISIBLE);
                                }

                            } else {
                                Utility.showError(response.body().getMsg());
                                recyclerBookingHistory.setVisibility(View.GONE);
                                tvNoData.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                            recyclerBookingHistory.setVisibility(View.GONE);
                            tvNoData.setVisibility(View.VISIBLE);
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<BookingHistoryCouponRequestResponse> call, Throwable t) {
                        Utility.log("" + t.getMessage());
                        recyclerBookingHistory.setVisibility(View.GONE);
                        tvNoData.setVisibility(View.VISIBLE);
                        Utility.hideProgress();
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
        Intent intent = new Intent(mActivity, HomeActivity.class);
        startActivity(intent);
        finish();
        goPrevious();
    }

    public void onHistoryItemClick(String id) {
        //TODO need to handle multiple type with
        Intent intent = new Intent(BookingHistoryActivity.this, BookingHistoryDetailActivity.class);
        if (sltPosition == 0) { // Accommodation
            intent.putExtra(AppConstant.EXT_IS_FROM, AppConstant.IS_FROM_ACCOMMODATION);
        } else if (sltPosition == 1) { // Thing to do
            intent.putExtra(AppConstant.EXT_IS_FROM, AppConstant.IS_FROM_THING_TO_DO);
        } else if (sltPosition == 2) { // Coupon
            intent.putExtra(AppConstant.EXT_IS_FROM, AppConstant.IS_FROM_COUPON);
        }
        intent.putExtra(AppConstant.EXT_BOOKING_ID, id);
        startActivity(intent);
        goNext();
    }

    private void showFilterPopupMenu(final View view2) {
        try {
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.popup_booking_filter, null, false);

            TextView tvPastBooking = layout.findViewById(R.id.tvPast);
            TextView tvPending = layout.findViewById(R.id.tvPending);
            TextView tvApproved = layout.findViewById(R.id.tvApproved);

            if (sltType == 2) {
                tvApproved.setTextColor(mActivity.getResources().getColor(R.color.black_dark));
                tvPastBooking.setTextColor(mActivity.getResources().getColor(R.color.app_blue));
                tvPending.setTextColor(mActivity.getResources().getColor(R.color.black_dark));
            } else if (sltType == 0) {
                tvPastBooking.setTextColor(mActivity.getResources().getColor(R.color.black_dark));
                tvPending.setTextColor(mActivity.getResources().getColor(R.color.app_blue));
                tvApproved.setTextColor(mActivity.getResources().getColor(R.color.black_dark));
            } else if (sltType == 1) {
                tvPending.setTextColor(mActivity.getResources().getColor(R.color.black_dark));
                tvPastBooking.setTextColor(mActivity.getResources().getColor(R.color.black_dark));
                tvApproved.setTextColor(mActivity.getResources().getColor(R.color.app_blue));
            }

            final PopupWindow popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }

            tvPastBooking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    sltType = 2;
                    getNewList();
                }
            });

            tvPending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    sltType = 0;
                    getNewList();
                }
            });

            tvApproved.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    sltType = 1;
                    getNewList();
                }
            });

            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            final View view1 = popupWindow.getContentView();
            view1.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            //popupWindow.showAsDropDown(view2, 0, -(view2.getHeight() + view1.getMeasuredHeight()));
            popupWindow.showAsDropDown(view2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getNewList() {
        if (sltPosition == 0) { // ACCOMMODATION
            getAccommodationHistoryList();
        } else if (sltPosition == 1) { // THING TO DO
            getThingToDoHistoryList();
        } else if (sltPosition == 2) {
            getCouponHistoryList();  // COUPON
        }
    }

    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup) topMenuLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    Typeface tf = Typeface.createFromAsset(getAssets(), TypeFace.RomanTF);
                    ((TextView) tabViewChild).setTypeface(tf);
                    ((TextView) tabViewChild).setTextSize(getResources().getDimension(R.dimen._16sdp));
                }
            }
        }
    }

}
