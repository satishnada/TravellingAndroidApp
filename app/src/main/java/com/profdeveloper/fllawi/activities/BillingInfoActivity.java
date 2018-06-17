package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.adapters.AddonsBillingInfoListAdapter;
import com.profdeveloper.fllawi.model.AccommodationBooking.AccommodationBookingRequestResponse;
import com.profdeveloper.fllawi.model.AccommodationBooking.ArrCalculationBreakDown;
import com.profdeveloper.fllawi.model.Addons.Datum;
import com.profdeveloper.fllawi.model.CalculatedPriceRequestResponse;
import com.profdeveloper.fllawi.model.CommonRequestResponse;
import com.profdeveloper.fllawi.model.InitPaymentRequestResponse;
import com.profdeveloper.fllawi.model.LoginUserData;
import com.profdeveloper.fllawi.model.PaymentResponse;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.retrofit.WebUtility;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import paytabs.project.PayTabActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BillingInfoActivity extends BaseActivity {

    private static final String TAG = BillingInfoActivity.class.getSimpleName();
    private final int paymentRequestCode = 100;
    private final int LOGIN_REQUEST = 1001;
    private View view;
    private TextView tvFromDate, tvAdult, tvToDate, tvKids, tvAddonsAvailable,
            tvDiscount, tvTotalPayableBefore, tvCouponAmount, tvTotalPayableAfter,
            tvPayViaBank, tvPayViaCredit, tvSubTotal, tvTitle, tvSubTotalText, tvAddress;
    private int isFrom = 0;
    private RecyclerView recyclerAddons;
    private AddonsBillingInfoListAdapter addonsBillingAdapter;
    private ArrayList<Datum> addonsList = new ArrayList<>();

    private LinearLayout llAddons, llTopInfo;

    private String accommodationId = "21";
    private String fromDate = "2018-03-05";
    private String toDate = "2018-03-07";
    private String adults = "2";
    private String kids = "3";
    private String addonSltKey = "2";
    private String addonsValue = "2";
    private String couponAmount = "0";
    private String name = "";
    private ArrCalculationBreakDown accommodationBookingResponse;
    private com.profdeveloper.fllawi.model.ThingToDoPriceCalcu.ArrCalculationBreakDown breakDown;
    private com.profdeveloper.fllawi.model.ArrCalculationBreakDown couponBreakDown;

    private String timeSlotId = "";

    private JsonObject addonsJson = new JsonObject();

    private String endTime = "";
    private String startTime = "";

    private String quantity = "";
    private String checkInDateStr = "", checkOutDateStr = "";

    private String orderId = "";  // order Id from initPayment API
    private String response = ""; // JSON from PayTab.
    private String result = "";   // Result Code from PayTab.
    private String orderInitAmount = "0"; // final Order Amount.
    private String paymentMode = "bank";
    private String addonsParameters = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_billing_info, llContainer);
        mActivity = this;
        tvFromDate = view.findViewById(R.id.tvFromDate);
        tvAdult = view.findViewById(R.id.tvAdult);
        tvToDate = view.findViewById(R.id.tvToDate);
        tvKids = view.findViewById(R.id.tvKids);
        tvSubTotal = view.findViewById(R.id.tvSubTotal);
        tvAddonsAvailable = view.findViewById(R.id.tvAddonsAvailable);
        tvDiscount = view.findViewById(R.id.tvDiscount);
        tvTotalPayableBefore = view.findViewById(R.id.tvTotalPayableBefore);
        tvCouponAmount = view.findViewById(R.id.tvCouponAmount);
        tvTotalPayableAfter = view.findViewById(R.id.tvTotalPayableAfter);
        tvPayViaBank = view.findViewById(R.id.tvPayViaBank);
        tvPayViaCredit = view.findViewById(R.id.tvPayViaCredit);
        tvTitle = view.findViewById(R.id.tvTitle);
        recyclerAddons = view.findViewById(R.id.recyclerAddons);
        recyclerAddons.setLayoutManager(Utility.getLayoutManager(mActivity));
        recyclerAddons.setHasFixedSize(true);

        llTopInfo = view.findViewById(R.id.llTopInfo);
        llAddons = view.findViewById(R.id.llAddons);
        tvSubTotalText = view.findViewById(R.id.tvSubTotalText);
        tvAddress = view.findViewById(R.id.tvAddress);

        tvPayViaBank.setOnClickListener(this);
        tvPayViaCredit.setOnClickListener(this);
        isHomeRunning = false;

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
        }

        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            accommodationId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
            fromDate = bundle.getString(AppConstant.EXT_FROM_DATE);
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
            toDate = bundle.getString(AppConstant.EXT_TO_DATE);
            adults = bundle.getString(AppConstant.EXT_ADULTS);
            kids = bundle.getString(AppConstant.EXT_KIDS);
            name = bundle.getString(AppConstant.EXT_NAME);
            addonsList = (ArrayList<Datum>) bundle.getSerializable(AppConstant.EXT_ADDONS_SELECTED_LIST);
            accommodationBookingResponse = (ArrCalculationBreakDown) bundle.getSerializable(AppConstant.EXT_ACCOMMODATION_BOOKING);

            if (accommodationBookingResponse != null) {
                if (accommodationBookingResponse != null) {
                    tvSubTotal.setText(getString(R.string.sar)+" " + accommodationBookingResponse.getSubTotal() + "");
                    tvTotalPayableBefore.setText(getString(R.string.sar)+" " + accommodationBookingResponse.getTotalBeforeDiscount() + "");
                    tvTotalPayableAfter.setText(getString(R.string.sar)+" " + accommodationBookingResponse.getTotalAfterDiscount() + "");
                    tvDiscount.setText(getString(R.string.sar)+" " + accommodationBookingResponse.getDiscountAmount() + "");
                    tvCouponAmount.setText(getString(R.string.sar)+" " + accommodationBookingResponse.getDiscountAmount());
                }
            }

            tvTitle.setText(name);
            tvFromDate.setText(fromDate);
            tvToDate.setText(toDate);
            tvAdult.setText(adults);
            tvKids.setText(kids);

            setAddonsList();
            recyclerAddons.setVisibility(View.VISIBLE);
            llAddons.setVisibility(View.VISIBLE);
            tvSubTotalText.setText(getString(R.string.sub_total));
            llTopInfo.setVisibility(View.VISIBLE);
            tvAddress.setVisibility(View.GONE);
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            recyclerAddons.setVisibility(View.GONE);
            llAddons.setVisibility(View.GONE);
            tvSubTotalText.setText(R.string.quantity);
            llTopInfo.setVisibility(View.GONE);
            tvAddress.setVisibility(View.VISIBLE);

            accommodationId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
            name = bundle.getString(AppConstant.EXT_NAME);
            couponAmount = bundle.getString(AppConstant.EXT_SUB_NAME);
            quantity = bundle.getString(AppConstant.EXT_COUPON_QUANTITY);
            couponBreakDown = (com.profdeveloper.fllawi.model.ArrCalculationBreakDown) bundle.getSerializable(AppConstant.EXT_ACCOMMODATION_BOOKING);

            tvSubTotal.setText(quantity);
            tvTitle.setText(name);
            tvAddress.setText(couponAmount);

            if (couponBreakDown != null) {
                if (couponBreakDown != null) {
                    tvTotalPayableBefore.setText(getString(R.string.sar)+" " + couponBreakDown.getTotalBeforeDiscount() + "");
                    tvTotalPayableAfter.setText(getString(R.string.sar)+" " + couponBreakDown.getTotalAfterDiscount() + "");
                    tvDiscount.setText(getString(R.string.sar)+" " + couponBreakDown.getDiscountAmount() + "");
                    tvCouponAmount.setText(getString(R.string.sar)+" " + couponBreakDown.getDiscountAmount());
                }

            }

        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            accommodationId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
            fromDate = bundle.getString(AppConstant.EXT_FROM_DATE);
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
            adults = bundle.getString(AppConstant.EXT_ADULTS);
            kids = bundle.getString(AppConstant.EXT_KIDS);
            name = bundle.getString(AppConstant.EXT_NAME);
            startTime = bundle.getString(AppConstant.EXT_START_TIME);
            endTime = bundle.getString(AppConstant.EXT_END_TIME);
            addonsList = (ArrayList<Datum>) bundle.getSerializable(AppConstant.EXT_ADDONS_SELECTED_LIST);
            breakDown = (com.profdeveloper.fllawi.model.ThingToDoPriceCalcu.ArrCalculationBreakDown) bundle.getSerializable(AppConstant.EXT_ACCOMMODATION_BOOKING);

            tvTitle.setText(name);
            tvFromDate.setText(fromDate);
            tvToDate.setText(toDate);
            tvAdult.setText(adults);
            tvKids.setText(kids);
            if (breakDown != null) {
                tvSubTotal.setText(getString(R.string.sar)+" " + breakDown.getSubTotal() + "");
                tvTotalPayableBefore.setText(getString(R.string.sar)+" " + breakDown.getTotalBeforeDiscount() + "");
                tvTotalPayableAfter.setText(getString(R.string.sar)+" " + breakDown.getTotalAfterDiscount() + "");
                tvDiscount.setText(getString(R.string.sar)+" " + breakDown.getDiscountAmount() + "");
                tvCouponAmount.setText(getString(R.string.sar)+" " + breakDown.getDiscountAmount());
            }
            setAddonsList();
            recyclerAddons.setVisibility(View.VISIBLE);
            llAddons.setVisibility(View.VISIBLE);
            tvSubTotalText.setText(getString(R.string.sub_total));
            llTopInfo.setVisibility(View.VISIBLE);
            tvAddress.setVisibility(View.GONE);
        } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {

        } else if (isFrom == AppConstant.IS_FROM_EVENT) {

        }

    }

    private void setAddonsList() {
        if (!addonsList.isEmpty()) {
            recyclerAddons.setVisibility(View.VISIBLE);
            addonsBillingAdapter = new AddonsBillingInfoListAdapter(mActivity, addonsList);
            recyclerAddons.setAdapter(addonsBillingAdapter);
            ViewGroup.LayoutParams params = recyclerAddons.getLayoutParams();
            params.height = (int) (getResources().getDimension(R.dimen._30sdp) * addonsList.size());
            recyclerAddons.setLayoutParams(params);
            tvAddonsAvailable.setText(R.string.as_follow);
            addonsJson = new JsonObject();
            addonsParameters = "";
            for (int i = 0; i < addonsList.size(); i++) {
                addonsParameters = addonsParameters + "&addons[" + addonsList.get(i).getId() + "]=" + addonsList.get(i).getQuantity();
                addonsJson.addProperty(addonsList.get(i).getId() + "", addonsList.get(i).getQuantity() + "");
            }
        } else {
            recyclerAddons.setVisibility(View.GONE);
            tvAddonsAvailable.setText(R.string.none);
        }
    }

    @Override
    public void initialization() {
        ivTopBack.setImageResource(R.drawable.ic_back_arrow);
        tvTopTitle.setBackgroundResource(0);
        tvTopTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        tvTopTitle.setText(R.string.billing_info);
        tvTopTitle.setVisibility(View.VISIBLE);
        ivTopSearch.setVisibility(View.GONE);
        checkLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivTopBack:
                onBackPressed();
                break;
            case R.id.tvPayViaBank:
                //startPaymentActivity();
                paymentMode = "bank";
                if (!PreferenceData.isLogin()) {
                    Intent intent = new Intent(mActivity, SignInActivity.class);
                    intent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_BOOKING);
                    startActivityForResult(intent, LOGIN_REQUEST);
                    goNext();
                } else {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        accommodationInitPayment();
                    } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        thingToDoInitPayment();
                    } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                        couponInitPayment();
                    }
                }
                break;
            case R.id.tvPayViaCredit:
                paymentMode = "paytab";
                if (!PreferenceData.isLogin()) {
                    Intent intent = new Intent(mActivity, SignInActivity.class);
                    intent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_BOOKING);
                    startActivityForResult(intent, LOGIN_REQUEST);
                    goNext();
                } else {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        accommodationInitPayment();
                    } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        thingToDoInitPayment();
                    } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                        couponInitPayment();
                    }
                }
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void accommodationInitPayment() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);

                try {
                    checkInDateStr = fromDate;
                    checkOutDateStr = toDate;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JsonObject calculationBreakDown = new JsonObject();
                calculationBreakDown.addProperty("discount_amount", accommodationBookingResponse.getTotalAfterDiscount());
                calculationBreakDown.addProperty("discount_percent_final", accommodationBookingResponse.getDiscountAmount());
                calculationBreakDown.addProperty("total_after_discount", accommodationBookingResponse.getTotalBeforeDiscount());

                Log.i(TAG, "JSON: " + calculationBreakDown);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<InitPaymentRequestResponse> call = service.accommodationInitPayment(Utility.getLocale(),
                        paymentMode,
                        accommodationId,
                        adults,
                        kids,
                        checkInDateStr,
                        checkOutDateStr,
                        accommodationBookingResponse.getTotalAfterDiscount() + "",
                        accommodationBookingResponse.getDiscountPercent() + "",
                        accommodationBookingResponse.getDiscountAmount() + "",
                        PreferenceData.getUserData().getId(),
                        addonsParameters,
                        "", "",
                        "", "");
                call.enqueue(new Callback<InitPaymentRequestResponse>() {
                    @Override
                    public void onResponse(Call<InitPaymentRequestResponse> call, Response<InitPaymentRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (paymentMode.equalsIgnoreCase("bank")) {
                                    orderId = response.body().getData().getOrderId();
                                    orderInitAmount = response.body().getData().getAmount();
                                    Utility.showError(response.body().getMsg());
                                    goToHome();
                                } else {
                                    orderId = response.body().getData().getOrderId();
                                    orderInitAmount = response.body().getData().getAmount();
                                    startPaymentActivity();
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
                    public void onFailure(Call<InitPaymentRequestResponse> call, Throwable t) {
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

    private void goToHome() {
        Intent intent = new Intent(BillingInfoActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
        goPrevious();
    }


    private void thingToDoInitPayment() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);

                JsonObject appliedCouponJson = new JsonObject();
                appliedCouponJson.addProperty("coupon_code_row_id", "");
                appliedCouponJson.addProperty("is_applyied", false);

                JsonObject scheduleTimeJson = new JsonObject();
                scheduleTimeJson.addProperty("schedule_end_time", endTime);
                scheduleTimeJson.addProperty("schedule_start_time", startTime);

                JsonObject calculationBreakDownJson = new JsonObject();
                calculationBreakDownJson.addProperty("total_after_discount", breakDown.getTotalAfterDiscount() + "");

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("payment_mode", paymentMode);
                jsonObject.addProperty("thing_to_do_id", accommodationId);
                jsonObject.addProperty("user_id", accommodationId);
                jsonObject.addProperty("schedule_date", fromDate);
                jsonObject.addProperty("schedule_type", "0");
                jsonObject.addProperty("adults", adults);
                jsonObject.addProperty("kids", kids);

                jsonObject.addProperty("addons[19]", 2);

                //jsonObject.addProperty("addons",addonsJson.toString());

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<InitPaymentRequestResponse> call = service.thingToDoInitPayment(
                        Utility.getLocale(),
                        paymentMode,
                        accommodationId,
                        adults,
                        kids,
                        fromDate,
                        "0",
                        PreferenceData.getUserData().getId(),
                        "1",
                        breakDown.getTotalAfterDiscount() + "",
                        startTime,
                        endTime
                );
/*
calculationBreakDownJson.toString(),
                        scheduleTimeJson.toString(),
                        appliedCouponJson.toString(), "", ""*/

                call.enqueue(new Callback<InitPaymentRequestResponse>() {
                    @Override
                    public void onResponse(Call<InitPaymentRequestResponse> call, Response<InitPaymentRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (paymentMode.equalsIgnoreCase("bank")) {
                                    orderId = response.body().getData().getOrderId();
                                    orderInitAmount = response.body().getData().getAmount();
                                    Utility.showError(response.body().getMsg());
                                    goToHome();
                                } else {
                                    orderId = response.body().getData().getOrderId();
                                    orderInitAmount = response.body().getData().getAmount();
                                    startPaymentActivity();
                                }

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<InitPaymentRequestResponse> call, Throwable t) {
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

    private void couponInitPayment() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
            /*    JsonObject calculationBreakDown = new JsonObject();
                calculationBreakDown.addProperty("discount_amount", couponBreakDown.getTotalAfterDiscount());
                calculationBreakDown.addProperty("discount_percent_final", couponBreakDown.getDiscountAmount());
                calculationBreakDown.addProperty("total_after_discount", couponBreakDown.getTotalBeforeDiscount());

                Log.i(TAG, "JSON: " + calculationBreakDown);*/
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<InitPaymentRequestResponse> call = service.couponInitPayment(Utility.getLocale(), paymentMode,
                        accommodationId,
                        quantity,
                        couponBreakDown.getTotalAfterDiscount() + "",
                        couponBreakDown.getDiscountAmount() + "",
                        couponBreakDown.getTotalBeforeDiscount() + "",
                        PreferenceData.getUserData().getId());

                call.enqueue(new Callback<InitPaymentRequestResponse>() {
                    @Override
                    public void onResponse(Call<InitPaymentRequestResponse> call, Response<InitPaymentRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                if (paymentMode.equalsIgnoreCase("bank")) {
                                    orderId = response.body().getData().getOrderId();
                                    orderInitAmount = response.body().getData().getAmount();
                                    Utility.showError(response.body().getMsg());
                                    goToHome();
                                } else {
                                    orderId = response.body().getData().getOrderId();
                                    orderInitAmount = response.body().getData().getAmount();
                                    startPaymentActivity();
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
                    public void onFailure(Call<InitPaymentRequestResponse> call, Throwable t) {
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

    private void accommodationUpdateBookingPayment(PaymentResponse paymentResponse) {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);

                if (paymentResponse != null) {
                    response = new Gson().toJson(paymentResponse);
                }

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.accommodationUpdateBooking(Utility.getLocale(), orderId, response, result);

                call.enqueue(new Callback<CommonRequestResponse>() {
                    @Override
                    public void onResponse(Call<CommonRequestResponse> call, Response<CommonRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                Utility.showError(response.body().getMessage());
                                goToHome();
                            } else {
                                Utility.showError(response.body().getMessage());
                            }
                        } else {
                            Utility.showError(response.body().getMessage());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<CommonRequestResponse> call, Throwable t) {
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

    private void thingToDoUpdateBookingPayment(PaymentResponse paymentResponse) {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);

                if (paymentResponse != null) {
                    response = new Gson().toJson(paymentResponse);
                }

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.thingToDoUpdateBooking(Utility.getLocale(), orderId, response, result);

                call.enqueue(new Callback<CommonRequestResponse>() {
                    @Override
                    public void onResponse(Call<CommonRequestResponse> call, Response<CommonRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                Utility.showError(response.body().getMessage());
                                goToHome();
                            } else {
                                Utility.showError(response.body().getMessage());
                            }
                        } else {
                            Utility.showError(response.body().getMessage());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<CommonRequestResponse> call, Throwable t) {
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

    private void couponUpdateBookingPayment(PaymentResponse paymentResponse) {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);

                if (paymentResponse != null) {
                    response = new Gson().toJson(paymentResponse);
                }

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.couponUpdateBooking(Utility.getLocale(), orderId, response, result);

                call.enqueue(new Callback<CommonRequestResponse>() {
                    @Override
                    public void onResponse(Call<CommonRequestResponse> call, Response<CommonRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                Utility.showError(response.body().getMessage());
                                goToHome();
                            } else {
                                Utility.showError(response.body().getMessage());
                            }
                        } else {
                            Utility.showError(getResources().getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<CommonRequestResponse> call, Throwable t) {
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
        goPrevious();
        finish();
    }

    private void startPaymentActivity() {

        LoginUserData userData = PreferenceData.getUserData();

        Intent in = new Intent(BillingInfoActivity.this, PayTabActivity.class);
        in.putExtra("pt_merchant_email", "nawafalhazmii@hotmail.com");//this a demo account for testing the sdk
        in.putExtra("pt_secret_key", "0mjuW4M0thacJ1IOigDx3JSnXOvmoH4o3nT0rG1VXb8GdRuv883bSVyPJ4b7LgwPDpP56Q1329Jg1eM9nPkgKT5uYhCJx8zbyphE"); //Add your Secret Key Here
        //in.putExtra("pt_secret_key", "oIUhj8mssa9rTWRAqHg4P9ECOcfs35lsOgJ7p6ARgJjaFbK6S1aIbOlZ1As5GNxu4hCtnclEWEOCPzIIBSrMGMMImeN22kx6C9zZ"); //Add your Secret Key Here
        in.putExtra("pt_transaction_title", name);
        in.putExtra("pt_amount", orderInitAmount + "");
        in.putExtra("pt_currency_code", "USD"); //Use Standard 3 character ISO
        in.putExtra("pt_shared_prefs_name", AppConstant.PAY_TAB_RESPONSE);
        in.putExtra("pt_customer_email", userData.getEmail());
        in.putExtra("pt_customer_phone_number", "9537299596");
        in.putExtra("pt_order_id", orderId);
        in.putExtra("pt_product_name", name);
        in.putExtra("pt_timeout_in_seconds", "10000"); //Optional //Billing Address
        in.putExtra("pt_address_billing", userData.getAddress());
        in.putExtra("pt_city_billing", "Ahmedabad");
        in.putExtra("pt_state_billing", "Gujarat");
        in.putExtra("pt_country_billing", "IND");
        in.putExtra("pt_postal_code_billing", "00973"); //Put Country Phone code if Postal code not available '00973' //Shipping Address
        in.putExtra("pt_address_shipping", userData.getAddress());
        in.putExtra("pt_city_shipping", "Ahmedabad");
        in.putExtra("pt_state_shipping", "Gujarat");
        in.putExtra("pt_country_shipping", "IND");
        in.putExtra("pt_postal_code_shipping", "00973"); //Put Country Phone code if Postal code not available '00973'
        startActivityForResult(in, paymentRequestCode);
        goNext();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int isResultFrom = 0;
        if (requestCode == LOGIN_REQUEST) {
                if (data != null) {
                    isResultFrom = data.getIntExtra(AppConstant.EXT_IS_FROM, 0);
                    if (isResultFrom == AppConstant.IS_FROM_BOOKING) {
                        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                            accommodationInitPayment();
                        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                            thingToDoInitPayment();
                        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                            couponInitPayment();
                        } else if (isFrom == AppConstant.IS_FROM_EVENT) {

                        } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {

                        }
                    }
                }
        } else {
            SharedPreferences shared_prefs = getSharedPreferences(AppConstant.PAY_TAB_RESPONSE, MODE_PRIVATE);
            String pt_response_code = shared_prefs.getString("pt_response_code", "");
            String pt_transaction_id = shared_prefs.getString("pt_transaction_id", "");
            Log.i(TAG, "onActivityResult: CODE " + pt_response_code + " ID " + pt_transaction_id);

            if (pt_response_code != null && pt_response_code.length() > 0) {

                PaymentResponse paymentResponse = new PaymentResponse();
                paymentResponse.setPt_response_code(pt_response_code);
                paymentResponse.setPt_transaction_id(pt_transaction_id);
                paymentResponse.setPt_description("Approved");
                result = pt_response_code;
                if (result.equalsIgnoreCase("100")) {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        accommodationUpdateBookingPayment(paymentResponse);
                    } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        thingToDoUpdateBookingPayment(paymentResponse);
                    } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                        couponUpdateBookingPayment(paymentResponse);
                    }
                } else {
                    Utility.showError(getString(R.string.message_something_wrong));
                }
            } else {
                Utility.showError(getString(R.string.message_something_wrong));
            }
        }
    }
}
