package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.adapters.AddonsListAdapter;
import com.profdeveloper.fllawi.model.AccommodationBooking.AccommodationBookingRequestResponse;
import com.profdeveloper.fllawi.model.Addons.AddonsRequestResponse;
import com.profdeveloper.fllawi.model.Addons.Datum;
import com.profdeveloper.fllawi.model.Coupons.CouponRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDoPriceCalcu.ThingToDoCalculatePriceRequestResponse;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.retrofit.WebUtility;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddonsListActivity extends BaseActivity {
    private static final String TAG = AddonsListActivity.class.getSimpleName();
    private View view;
    private RecyclerView recyclerAddons;
    private TextView tvNoData, tvProcessFurther, tvApply;
    private AddonsListAdapter adapter;
    private ArrayList<Datum> addonsList = new ArrayList<>();
    private EditText edtCoupon;

    private String accommodationId = "21";
    private String fromDate = "2018-03-05";
    private String toDate = "2018-03-07";
    private String scheduleDate = "2018-03-05"; // ThingToDo scheduled Date.
    private String adults = "2";
    private String kids = "3";
    private String addonSltKey = "2";
    private String addonsValue = "2";
    private String couponAmount = "0";
    private String name = "";
    private int isFrom = 1;
    private String timeSlotId = "";
    private String startTIme = "";
    private String endTime = "";

    private AccommodationBookingRequestResponse accommodationBookingResponse;
    private ThingToDoCalculatePriceRequestResponse thingToDoBookingResponse;
    private String addonsParameters = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_addons_list, llContainer);
        recyclerAddons = (RecyclerView) view.findViewById(R.id.recyclerAddons);
        tvNoData = (TextView) view.findViewById(R.id.tvNoData);
        tvApply = view.findViewById(R.id.tvApply);
        tvProcessFurther = view.findViewById(R.id.tvProcessFurther);
        edtCoupon = view.findViewById(R.id.edtCoupon);

        recyclerAddons.setHasFixedSize(true);
        recyclerAddons.setLayoutManager(new LinearLayoutManager(AddonsListActivity.this, LinearLayoutManager.VERTICAL, false));
        adapter = new AddonsListAdapter(AddonsListActivity.this, addonsList);
        recyclerAddons.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
        }

        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION){
            accommodationId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
            fromDate = bundle.getString(AppConstant.EXT_FROM_DATE);
            toDate = bundle.getString(AppConstant.EXT_TO_DATE);
            adults = bundle.getString(AppConstant.EXT_ADULTS);
            kids = bundle.getString(AppConstant.EXT_KIDS);
            name = bundle.getString(AppConstant.EXT_NAME);
            getAddonsList(accommodationId);
        }else if (isFrom == AppConstant.IS_FROM_THING_TO_DO){
            accommodationId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
            fromDate = bundle.getString(AppConstant.EXT_FROM_DATE);
            adults = bundle.getString(AppConstant.EXT_ADULTS);
            kids = bundle.getString(AppConstant.EXT_KIDS);
            name = bundle.getString(AppConstant.EXT_NAME);
            startTIme = bundle.getString(AppConstant.EXT_START_TIME);
            endTime = bundle.getString(AppConstant.EXT_END_TIME);
            timeSlotId = bundle.getString(AppConstant.EXT_TIME_SLOT_ID);
            getAddonsList(accommodationId);
        }else if (isFrom == AppConstant.IS_FROM_COUPON){
            accommodationId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
            fromDate = bundle.getString(AppConstant.EXT_FROM_DATE);
            toDate = bundle.getString(AppConstant.EXT_TO_DATE);
            adults = bundle.getString(AppConstant.EXT_ADULTS);
            kids = bundle.getString(AppConstant.EXT_KIDS);
            name = bundle.getString(AppConstant.EXT_NAME);
            getAddonsList(accommodationId);
        }

        tvApply.setOnClickListener(this);
        tvProcessFurther.setOnClickListener(this);

    }

    @Override
    public void initialization() {
        tvTopTitle.setText(R.string.choose_addons);
        ivTopBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_back_arrow));
        checkLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivTopBack:
                onBackPressed();
                break;
            case R.id.tvApply:
                if (edtCoupon.getText().toString().trim().length() == 0) {
                    Utility.showError(getString(R.string.enter_coupon));
                } else {
                    applyCouponCode(true);
                }
                break;
            case R.id.tvProcessFurther:
                if (edtCoupon.getText().toString().trim().length() == 0) {
                    processFurther();
                }else{
                    applyCouponCode(false);
                }
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void processFurther(){
        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION){
            addonsParameters = "";
            if (adapter.getAddonsSelectedList() != null &&
                    !adapter.getAddonsSelectedList().isEmpty()) {
                for (int i = 0; i < adapter.getAddonsSelectedList().size(); i++) {
                    addonsParameters =  addonsParameters+"&addons[" + adapter.getAddonsSelectedList().get(i).getId() + "]=" + adapter.getAddonsSelectedList().get(i).getQuantity();
                }
            }
            AccommodationBookingCalculation();
        }else if (isFrom == AppConstant.IS_FROM_THING_TO_DO){
            addonsParameters = "";
            if (adapter.getAddonsSelectedList() != null &&
                    !adapter.getAddonsSelectedList().isEmpty()) {
                for (int i = 0; i < adapter.getAddonsSelectedList().size(); i++) {
                    addonsParameters =  addonsParameters+"&addons[" + adapter.getAddonsSelectedList().get(i).getId() + "]=" + adapter.getAddonsSelectedList().get(i).getQuantity();
                }
            }
            getThingToDoPrice();
        }else if (isFrom == AppConstant.IS_FROM_COUPON){
            addonsParameters = "";
            if (adapter.getAddonsSelectedList() != null &&
                    !adapter.getAddonsSelectedList().isEmpty()) {
                for (int i = 0; i < adapter.getAddonsSelectedList().size(); i++) {
                    addonsParameters =  addonsParameters+"&addons[" + adapter.getAddonsSelectedList().get(i).getId() + "]=" + adapter.getAddonsSelectedList().get(i).getQuantity();
                }
            }
            AccommodationBookingCalculation();
        }
    }

    private void applyCouponCode(final boolean isFromApply) {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CouponRequestResponse> call = service.applyCoupon(Utility.getLocale(),edtCoupon.getText().toString().trim());
                call.enqueue(new Callback<CouponRequestResponse>() {
                    @Override
                    public void onResponse(Call<CouponRequestResponse> call, Response<CouponRequestResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                if (isFromApply){
                                    Utility.showError(response.body().getMsg());
                                }else{
                                    processFurther();
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
                    public void onFailure(Call<CouponRequestResponse> call, Throwable t) {
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

    private void AccommodationBookingCalculation() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<AccommodationBookingRequestResponse> call = service.getAccommodationBookingCalculation(Utility.getLocale(),WebUtility.ACCOMMODATION_BOOKING_CALCULATION
                                + "accomodation_id=" + accommodationId
                                + "&from_date=" + fromDate
                                + "&to_date=" + toDate
                                + "&adults=" + adults
                                + "&kids=" + kids
                                + addonsParameters
                        /*+ "&applied_coupon[amount]="+couponAmount*/);
                call.enqueue(new Callback<AccommodationBookingRequestResponse>() {
                    @Override
                    public void onResponse(Call<AccommodationBookingRequestResponse> call, Response<AccommodationBookingRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                accommodationBookingResponse = response.body();

                                Intent intent = new Intent(mActivity, BillingInfoActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt(AppConstant.EXT_IS_FROM,isFrom);
                                bundle.putString(AppConstant.EXT_ACCOMMODATION_ID, accommodationId);
                                bundle.putString(AppConstant.EXT_FROM_DATE, fromDate);
                                bundle.putString(AppConstant.EXT_NAME,name);
                                bundle.putString(AppConstant.EXT_TO_DATE, toDate);
                                bundle.putString(AppConstant.EXT_ADULTS, adults);
                                bundle.putString(AppConstant.EXT_KIDS, kids);
                                bundle.putSerializable(AppConstant.EXT_ADDONS_SELECTED_LIST,adapter.getAddonsSelectedList());
                                bundle.putSerializable(AppConstant.EXT_ACCOMMODATION_BOOKING, accommodationBookingResponse.getArrCalculationBreakDown());
                                intent.putExtras(bundle);
                                startActivity(intent);
                                goNext();

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<AccommodationBookingRequestResponse> call, Throwable t) {
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

    private void getThingToDoPrice() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                Date checkInDate = dateFormatter.parse(fromDate);
//                Date checkOutDate = dateFormatter.parse(tvCheckOutDate.getText().toString().trim());

                SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                scheduleDate = dateFormatter1.format(checkInDate.getTime());
//                checkOutDateStr = dateFormatter1.format(checkOutDate.getTime());

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<ThingToDoCalculatePriceRequestResponse> call = service.getThingToDoCalculatePrice(Utility.getLocale(),WebUtility.THING_TO_DO_CALCULATE_PRICE
                        + "thing_to_do_id=" + accommodationId
                        + "&schedule_date=" + fromDate
                        + "&schedule_time_id=" + timeSlotId
                        + "&adults=" + adults
                        + "&kids=" + kids
                        + "&schedule_type=0"
                        + addonsParameters);
                call.enqueue(new Callback<ThingToDoCalculatePriceRequestResponse>() {
                    @Override
                    public void onResponse(Call<ThingToDoCalculatePriceRequestResponse> call, Response<ThingToDoCalculatePriceRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                thingToDoBookingResponse = response.body();
                                Intent intent = new Intent(mActivity, BillingInfoActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt(AppConstant.EXT_IS_FROM,isFrom);
                                bundle.putString(AppConstant.EXT_ACCOMMODATION_ID, accommodationId);
                                bundle.putString(AppConstant.EXT_FROM_DATE, fromDate);
                                bundle.putString(AppConstant.EXT_NAME,name);
                                bundle.putString(AppConstant.EXT_TO_DATE, toDate);
                                bundle.putString(AppConstant.EXT_ADULTS, adults);
                                bundle.putString(AppConstant.EXT_KIDS, kids);
                                bundle.putString(AppConstant.EXT_START_TIME, startTIme);
                                bundle.putString(AppConstant.EXT_END_TIME, endTime);
                                bundle.putSerializable(AppConstant.EXT_ADDONS_SELECTED_LIST,adapter.getAddonsSelectedList());
                                bundle.putSerializable(AppConstant.EXT_ACCOMMODATION_BOOKING, thingToDoBookingResponse.getArrCalculationBreakDown());
                                intent.putExtras(bundle);
                                startActivity(intent);
                                goNext();

                            } else {
                                //Utility.showError(response.body().get());
                            }
                        } else {
                            // Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<ThingToDoCalculatePriceRequestResponse> call, Throwable t) {
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

    private void CouponBookingCalculation() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<AccommodationBookingRequestResponse> call = service.getCouponBookingCalculation(Utility.getLocale(),WebUtility.THING_TO_DO_BOOKING_CALCULATION
                                + "thing_to_do_id=" + accommodationId
                                + "&schedule_date=" + fromDate
                                + "&schedule_time_id=" + ""
                                + "&adults=" + adults
                                + "&kids=" + kids
                                + addonsParameters
                        /*+ "&applied_coupon[amount]="+couponAmount*/);
                call.enqueue(new Callback<AccommodationBookingRequestResponse>() {
                    @Override
                    public void onResponse(Call<AccommodationBookingRequestResponse> call, Response<AccommodationBookingRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                accommodationBookingResponse = response.body();

                                Intent intent = new Intent(mActivity, BillingInfoActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt(AppConstant.EXT_IS_FROM,isFrom);
                                bundle.putString(AppConstant.EXT_ACCOMMODATION_ID, accommodationId);
                                bundle.putString(AppConstant.EXT_FROM_DATE, fromDate);
                                bundle.putString(AppConstant.EXT_NAME,name);
                                bundle.putString(AppConstant.EXT_TO_DATE, toDate);
                                bundle.putString(AppConstant.EXT_ADULTS, adults);
                                bundle.putString(AppConstant.EXT_KIDS, kids);
                                bundle.putSerializable(AppConstant.EXT_ADDONS_SELECTED_LIST,adapter.getAddonsSelectedList());
                                bundle.putSerializable(AppConstant.EXT_ACCOMMODATION_BOOKING, accommodationBookingResponse.getArrCalculationBreakDown());
                                intent.putExtras(bundle);
                                startActivity(intent);
                                goNext();

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<AccommodationBookingRequestResponse> call, Throwable t) {
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

    private void getAddonsList(String accommodationId) {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<AddonsRequestResponse> call = service.getAccommodationAddons(Utility.getLocale(),WebUtility.GET_ACCOMMODATION_ADDONS + accommodationId);
                call.enqueue(new Callback<AddonsRequestResponse>() {
                    @Override
                    public void onResponse(Call<AddonsRequestResponse> call, Response<AddonsRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                AddonsRequestResponse requestResponse = response.body();

                                addonsList.clear();
                                addonsList = (ArrayList<Datum>) requestResponse.getData();
                                if (!addonsList.isEmpty()) {
                                    tvNoData.setVisibility(View.GONE);
                                    recyclerAddons.setHasFixedSize(true);
                                    recyclerAddons.setLayoutManager(new LinearLayoutManager(AddonsListActivity.this, LinearLayoutManager.VERTICAL, false));
                                    adapter = new AddonsListAdapter(AddonsListActivity.this, addonsList);
                                    recyclerAddons.setAdapter(adapter);
                                    recyclerAddons.setVisibility(View.VISIBLE);
                                    recyclerAddons.setItemViewCacheSize(addonsList.size());
                                } else {
                                    recyclerAddons.setVisibility(View.GONE);
                                    tvNoData.setVisibility(View.VISIBLE);
                                    //tvNoData.setText(response.body().getMsg());
                                }

                            } else {
                                recyclerAddons.setVisibility(View.GONE);
                                tvNoData.setVisibility(View.VISIBLE);
                                //tvNoData.setText(response.body().getMsg());
                                //Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<AddonsRequestResponse> call, Throwable t) {
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

    @Override
    public void onBackPressed() {
        finish();
        // overridePendingTransition(0,0);
    }

    public void onAddonItemClick(Datum datum) {
        Log.i(TAG, "onAddonItemClick: ");

    }
}
