package com.profdeveloper.fllawi.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.adapters.TimeSlotAdapter;
import com.profdeveloper.fllawi.model.ArrCalculationBreakDown;
import com.profdeveloper.fllawi.model.CalculatedPriceRequestResponse;
import com.profdeveloper.fllawi.model.CommonRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDoPriceCalcu.ThingToDoCalculatePriceRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDoTimeSlot.ArrThingsToDoSlot;
import com.profdeveloper.fllawi.model.ThingToDoTimeSlot.ThingToDoTimeSlotRequestResponse;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.retrofit.WebUtility;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookHotelActivity extends BaseActivity {

    private String TAG = BookHotelActivity.class.getSimpleName();
    private View view;
    private TextView tvBookNow, tvCheckInDate, tvCheckOutDate, tvAdultCount,
            tvPriceBefore, tvAppliedDiscount, tvPriceAfter, tvKidsCount,
            tvQuantity, tvKidsQ, tvHotelName, tvHotelAmount, tvCheckIn;
    private ImageView ivPlushAdult, ivMinusAdult, ivPlushKids, ivMinusKids,
            ivPlushQ, ivMinusQ, ivHotelPic;
    private SimpleDateFormat dateFormatter;
    private int adultCount = 1;
    private int kidsCount = 0;
    private int QuantityCount = 1;
    private String accommodationId = "";
    private ArrCalculationBreakDown calculationBreakDown;
    private int isFrom = 1;
    private LinearLayout llAccommodationDetails, llCouponDetails, llCheckOut;
    private RatingBar ratingBarHotelStar;

    private String name, imageUrl, amount, ratting, subName;
    private String checkInDateStr = "";
    private String checkOutDateStr = "";
    private boolean isThingTODoFirstTime = false;
    private Spinner spinnerTimeSlot;
    private TextView tvSltTimeSlot;
    private LinearLayout llTimeSlot;
    private TimeSlotAdapter timeSlotAdapter;
    private ArrThingsToDoSlot sltTimeSlot;
    private ArrayList<ArrThingsToDoSlot> thingsToDoTimeSlots = new ArrayList<>();
    private com.profdeveloper.fllawi.model.ThingToDoPriceCalcu.ArrCalculationBreakDown calculationBreakDownThingToDo;
    private Date checkInDate;
    private Date checkOutDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_book_hotel, llContainer);
        mActivity = this;

        spinnerTimeSlot = view.findViewById(R.id.spinnerTimeSlot);
        tvSltTimeSlot = view.findViewById(R.id.tvSltTimeSlot);
        llTimeSlot = view.findViewById(R.id.llTimeSlot);

        tvCheckInDate = view.findViewById(R.id.tvCheckInDate);
        tvCheckIn = view.findViewById(R.id.tvCheckIn);
        tvCheckOutDate = view.findViewById(R.id.tvCheckOutDate);
        tvKidsCount = view.findViewById(R.id.tvKidsCount);
        tvAdultCount = view.findViewById(R.id.tvAdultCount);
        tvAppliedDiscount = view.findViewById(R.id.tvAppliedDiscount);
        tvPriceAfter = view.findViewById(R.id.tvPriceAfter);
        ivPlushAdult = view.findViewById(R.id.ivPlushAdult);
        tvPriceBefore = view.findViewById(R.id.tvPriceBefore);
        ivMinusAdult = view.findViewById(R.id.ivMinusAdult);
        ivPlushKids = view.findViewById(R.id.ivPlushKids);
        ivMinusKids = view.findViewById(R.id.ivMinusKids);
        tvBookNow = view.findViewById(R.id.tvBookNow);
        llAccommodationDetails = view.findViewById(R.id.llAccommodationDetails);
        llCheckOut = view.findViewById(R.id.llCheckOut);
        tvQuantity = view.findViewById(R.id.tvQuantity);
        llCouponDetails = view.findViewById(R.id.llCouponDetails);
        tvKidsQ = view.findViewById(R.id.tvKidsQ);
        ivPlushQ = view.findViewById(R.id.ivPlushQ);
        ivMinusQ = view.findViewById(R.id.ivMinusQ);
        ivHotelPic = view.findViewById(R.id.ivHotelPic);
        tvHotelName = view.findViewById(R.id.tvHotelName);
        tvHotelAmount = view.findViewById(R.id.tvHotelAmount);
        ratingBarHotelStar = view.findViewById(R.id.ratingBarHotelStar);


        timeSlotAdapter = new TimeSlotAdapter(mActivity, thingsToDoTimeSlots);
        spinnerTimeSlot.setAdapter(timeSlotAdapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
        }

        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            accommodationId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
            name = bundle.getString(AppConstant.EXT_HOTEL_NAME);
            imageUrl = bundle.getString(AppConstant.EXT_HOTEL_IMAGE);
            ratting = bundle.getString(AppConstant.EXT_HOTEL_RATTING);
            amount = bundle.getString(AppConstant.EXT_HOTEL_AMOUNT);
            checkInDateStr = bundle.getString(AppConstant.FROM_DATE);
            checkOutDateStr = bundle.getString(AppConstant.TO_DATE);

            llAccommodationDetails.setVisibility(View.VISIBLE);
            /*ImageLoader.getInstance().loadImage(Utility.BASE_GALLERY_URL + "/" + imageUrl, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    ivHotelPic.setImageBitmap(loadedImage);
                    //progressBar.setVisibility(View.GONE);
                }
            });*/
            tvHotelName.setText(name);
            tvHotelAmount.setText(getString(R.string.per_night) + " " + amount);
            try {
                if (ratting.length() > 0) {
                    ratingBarHotelStar.setRating(Float.parseFloat(ratting));
                } else {
                    ratingBarHotelStar.setRating(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            llCouponDetails.setVisibility(View.GONE);
            tvCheckOutDate.setText(checkOutDateStr);
            tvCheckInDate.setText(checkInDateStr);
            tvCheckIn.setText(getString(R.string.check_in));
            llCheckOut.setVisibility(View.VISIBLE);
            llTimeSlot.setVisibility(View.GONE);
            getPrice(false);
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            accommodationId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
            name = bundle.getString(AppConstant.EXT_HOTEL_NAME);
            imageUrl = bundle.getString(AppConstant.EXT_HOTEL_IMAGE);
            ratting = bundle.getString(AppConstant.EXT_HOTEL_RATTING);
            amount = bundle.getString(AppConstant.EXT_HOTEL_AMOUNT);
            checkInDateStr = bundle.getString(AppConstant.FROM_DATE);

          /*  ImageLoader.getInstance().loadImage(Utility.BASE_GALLERY_URL + "/" + imageUrl, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    ivHotelPic.setImageBitmap(loadedImage);
                    //progressBar.setVisibility(View.GONE);
                }
            });*/
            tvCheckIn.setText(R.string.scheduled_date);
            tvCheckInDate.setText(checkInDateStr);
            tvHotelName.setText(name);
            tvHotelAmount.setText(getString(R.string.per_night) + " " + amount);
            try {
                if (ratting.length() > 0) {
                    ratingBarHotelStar.setRating(Float.parseFloat(ratting));
                } else {
                    ratingBarHotelStar.setRating(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            llAccommodationDetails.setVisibility(View.VISIBLE);
            llCouponDetails.setVisibility(View.GONE);
            llCheckOut.setVisibility(View.GONE);
            llTimeSlot.setVisibility(View.VISIBLE);

            if (checkInDateStr != null && checkInDateStr.trim().toString().length() > 0) {
                getThingToDoTimeSlot();
            } else {
                Utility.showError(getString(R.string.valid_scheduled_date));
            }
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            accommodationId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
            name = bundle.getString(AppConstant.EXT_HOTEL_NAME);
            imageUrl = bundle.getString(AppConstant.EXT_HOTEL_IMAGE);
            ratting = bundle.getString(AppConstant.EXT_HOTEL_RATTING);
            amount = bundle.getString(AppConstant.EXT_HOTEL_AMOUNT);
            tvKidsQ.setText("1");

           /* ImageLoader.getInstance().loadImage(Utility.BASE_GALLERY_URL + "/" + imageUrl, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    ivHotelPic.setImageBitmap(loadedImage);
                    //progressBar.setVisibility(View.GONE);
                }
            });

            ImageLoader.getInstance().loadImage(Utility.BASE_GALLERY_URL + "/" + imageUrl, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    ivHotelPic.setImageBitmap(loadedImage);
                    //progressBar.setVisibility(View.GONE);
                }
            });*/
            tvHotelName.setText(name);
            tvHotelAmount.setText(amount);
            try {
                if (ratting.length() > 0) {
                    ratingBarHotelStar.setRating(Float.parseFloat(ratting));
                } else {
                    ratingBarHotelStar.setRating(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            llCouponDetails.setVisibility(View.VISIBLE);
            llAccommodationDetails.setVisibility(View.GONE);
            llTimeSlot.setVisibility(View.GONE);
            getCouponPrice();
        }

    /*    Calendar newDate = Calendar.getInstance();
        newDate.setTime(new Date());
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvCheckInDate.setText(dateFormatter.format(newDate.getTime()));
        newDate.add(Calendar.DAY_OF_MONTH,1);;
        tvCheckOutDate.setText(dateFormatter.format(newDate.getTime()));*/

        tvSltTimeSlot.setOnClickListener(this);
        tvBookNow.setOnClickListener(this);
        tvCheckInDate.setOnClickListener(this);
        tvCheckOutDate.setOnClickListener(this);
        ivPlushAdult.setOnClickListener(this);
        ivMinusAdult.setOnClickListener(this);
        ivMinusKids.setOnClickListener(this);
        ivPlushKids.setOnClickListener(this);
        ivPlushQ.setOnClickListener(this);
        ivMinusQ.setOnClickListener(this);
        isHomeRunning = false;

        spinnerTimeSlot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: Time Slot " + position);

                sltTimeSlot = thingsToDoTimeSlots.get(position);
                tvSltTimeSlot.setText(thingsToDoTimeSlots.get(position).getStartTime() + " - " + thingsToDoTimeSlots.get(position).getEndTime());
                if (isThingTODoFirstTime) {
                    if (tvCheckInDate.getText().toString().trim().length() > 0) {
                        getThingToDoPrice(false);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void initialization() {
        ivTopBack.setImageResource(R.drawable.ic_back_arrow);
        tvTopTitle.setBackgroundResource(0);
        tvTopTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        tvTopTitle.setText(R.string.your_selection);
        tvTopTitle.setVisibility(View.VISIBLE);
        ivTopSearch.setVisibility(View.GONE);
        checkLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSltTimeSlot:
                spinnerTimeSlot.performClick();
                break;
            case R.id.ivMinusAdult:
                if (adultCount != 1) {
                    adultCount = adultCount - 1;
                }
                tvAdultCount.setText(adultCount + "");

                if (tvCheckInDate.getText().toString().trim().length() > 0 &&
                        tvCheckOutDate.getText().toString().trim().length() > 0 && adultCount != 0) {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        getPrice(false);
                    }
                } else if (tvCheckInDate.getText().toString().trim().length() > 0 && adultCount != 0) {
                    if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        getThingToDoPrice(true);
                    }
                }
                break;
            case R.id.ivMinusKids:
                if (kidsCount != 0) {
                    kidsCount = kidsCount - 1;
                }
                tvKidsCount.setText(kidsCount + "");
                if (tvCheckInDate.getText().toString().trim().length() > 0 &&
                        tvCheckOutDate.getText().toString().trim().length() > 0) {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        getPrice(false);
                    }
                } else if (tvCheckInDate.getText().toString().trim().length() > 0) {
                    if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        getThingToDoPrice(true);
                    }
                }
                break;
            case R.id.ivPlushAdult:
                adultCount = adultCount + 1;
                tvAdultCount.setText(adultCount + "");
                if (tvCheckInDate.getText().toString().trim().length() > 0 &&
                        tvCheckOutDate.getText().toString().trim().length() > 0) {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        getPrice(false);
                    }
                } else if (tvCheckInDate.getText().toString().trim().length() > 0) {
                    if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        getThingToDoPrice(true);
                    }
                }
                break;
            case R.id.ivPlushKids:
                kidsCount = kidsCount + 1;
                tvKidsCount.setText(kidsCount + "");
                if (tvCheckInDate.getText().toString().trim().length() > 0 &&
                        tvCheckOutDate.getText().toString().trim().length() > 0) {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        getPrice(false);
                    }
                } else if (tvCheckInDate.getText().toString().trim().length() > 0) {
                    if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        getThingToDoPrice(true);
                    }
                }
                break;
            case R.id.ivPlushQ:
                QuantityCount = QuantityCount + 1;
                tvKidsQ.setText(QuantityCount + "");
                getCouponPrice();
                break;
            case R.id.ivMinusQ:
                if (QuantityCount != 1) {
                    QuantityCount = QuantityCount - 1;
                    getCouponPrice();
                }
                tvKidsQ.setText(QuantityCount + "");
                break;
            case R.id.ivTopBack:
                onBackPressed();
                break;
            case R.id.tvCheckInDate:
                showDatePickerDialog(true);
                break;
            case R.id.tvCheckOutDate:
                if (tvCheckInDate.getText().toString().length() == 0) {
                    Utility.showError(getString(R.string.valid_check_out_date));
                } else {
                    showDatePickerDialog(false);
                }
                break;
            case R.id.tvBookNow:
                if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                    if (tvCheckInDate.getText().toString().trim().length() > 0 &&
                            tvCheckOutDate.getText().toString().trim().length() > 0) {
                        if (calculationBreakDown != null) {
                            if (calculationBreakDown.getTotalBeforeDiscount() == 0) {
                                Utility.showError(getString(R.string.valid_date));
                            } else {
                                Intent intent = new Intent(mActivity, AddonsListActivity.class);
                                intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID, accommodationId);
                                intent.putExtra(AppConstant.EXT_IS_FROM, isFrom);
                                intent.putExtra(AppConstant.EXT_FROM_DATE, checkInDateStr);
                                intent.putExtra(AppConstant.EXT_TO_DATE, checkOutDateStr);
                                intent.putExtra(AppConstant.EXT_ADULTS, adultCount + "");
                                intent.putExtra(AppConstant.EXT_KIDS, kidsCount + "");
                                intent.putExtra(AppConstant.EXT_NAME, name);
                                startActivity(intent);
                                goNext();
                            }
                        } else {
                            getPrice(true);
                        }
                    }else{
                        Utility.showError(getString(R.string.valid_scheduled_date));
                    }
                } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                    if (tvCheckInDate.getText().toString().trim().length() > 0 && sltTimeSlot != null){
                        if (calculationBreakDownThingToDo != null) {
                            if (calculationBreakDownThingToDo.getTotalBeforeDiscount() == 0) {
                                Utility.showError(getString(R.string.valid_date));
                            } else {
                                Intent intent = new Intent(mActivity, AddonsListActivity.class);
                                intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID, accommodationId);
                                intent.putExtra(AppConstant.EXT_IS_FROM, isFrom);
                                intent.putExtra(AppConstant.EXT_FROM_DATE, checkInDateStr);
                                intent.putExtra(AppConstant.EXT_ADULTS, adultCount + "");
                                intent.putExtra(AppConstant.EXT_KIDS, kidsCount + "");
                                intent.putExtra(AppConstant.EXT_NAME, name);
                                intent.putExtra(AppConstant.EXT_START_TIME, sltTimeSlot.getStartTime());
                                intent.putExtra(AppConstant.EXT_END_TIME, sltTimeSlot.getEndTime());
                                intent.putExtra(AppConstant.EXT_TIME_SLOT_ID, sltTimeSlot.getId() + "");
                                startActivity(intent);
                                goNext();
                            }
                        } else {
                            getThingToDoPrice(true);
                        }
                    }else{
                        Utility.showError(getString(R.string.valid_scheduled_date));
                    }

                } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                    if (calculationBreakDown != null) {
                        if (calculationBreakDown.getTotalBeforeDiscount() == 0) {
                            Utility.showError(getString(R.string.valid_date));
                        } else {
                            Intent intent = new Intent(mActivity, BillingInfoActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt(AppConstant.EXT_IS_FROM, isFrom);
                            bundle.putString(AppConstant.EXT_ACCOMMODATION_ID, accommodationId);
                            bundle.putString(AppConstant.EXT_NAME, name);
                            bundle.putString(AppConstant.EXT_SUB_NAME, amount);
                            bundle.putString(AppConstant.EXT_COUPON_QUANTITY, QuantityCount + "");
                            bundle.putSerializable(AppConstant.EXT_ACCOMMODATION_BOOKING, calculationBreakDown);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            goNext();
                        }
                    } else {
                        getCouponPrice();
                    }
                }
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void getPrice(final boolean isFromBookNow) {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                if (tvCheckInDate.getText().toString().trim().length() > 0) {
                    checkInDate = dateFormatter.parse(tvCheckInDate.getText().toString().trim());
                    checkInDateStr = dateFormatter1.format(checkInDate.getTime());
                }

                if (tvCheckOutDate.getText().toString().trim().length() > 0) {
                    checkOutDate = dateFormatter.parse(tvCheckOutDate.getText().toString().trim());
                    checkOutDateStr = dateFormatter1.format(checkOutDate.getTime());
                }

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CalculatedPriceRequestResponse> call = service.getCalculatedPrice(Utility.getLocale(), WebUtility.ACCOMMODATION_CALCULATE_PRICE
                        + "accomodation_id=" +
                        accommodationId + "&from_date=" + checkInDateStr +
                        "&to_date=" + checkOutDateStr + "&adults=" + adultCount + "&kids=" + kidsCount);
                call.enqueue(new Callback<CalculatedPriceRequestResponse>() {
                    @Override
                    public void onResponse(Call<CalculatedPriceRequestResponse> call, Response<CalculatedPriceRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                calculationBreakDown = response.body().getArrCalculationBreakDown();

                                tvPriceBefore.setText(getString(R.string.sar) + " " + calculationBreakDown.getTotalBeforeDiscount());
                                tvAppliedDiscount.setText(getString(R.string.sar) + " " + calculationBreakDown.getDiscountAmount());
                                tvPriceAfter.setText(getString(R.string.sar) + " " + calculationBreakDown.getTotalAfterDiscount());

                                /*if (isFromBookNow){
                                    Intent intent = new Intent(mActivity, AddonsListActivity.class);
                                    intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID,accommodationId);
                                    intent.putExtra(AppConstant.EXT_IS_FROM,isFrom);
                                    intent.putExtra(AppConstant.EXT_FROM_DATE,checkInDateStr);
                                    intent.putExtra(AppConstant.EXT_TO_DATE,checkOutDateStr);
                                    intent.putExtra(AppConstant.EXT_ADULTS,adultCount+"");
                                    intent.putExtra(AppConstant.EXT_KIDS,kidsCount+"");
                                    intent.putExtra(AppConstant.EXT_NAME,name);
                                    startActivity(intent);
                                    goNext();
                                }*/
                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<CalculatedPriceRequestResponse> call, Throwable t) {
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


    private void getThingToDoPrice(final boolean isFromBookNow) {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

                if (tvCheckInDate.getText().toString().trim().length() > 0) {
                    Date checkInDate = dateFormatter.parse(tvCheckInDate.getText().toString().trim());
                    checkInDateStr = dateFormatter1.format(checkInDate.getTime());
                }

//                Date checkOutDate = dateFormatter.parse(tvCheckOutDate.getText().toString().trim());
//                checkOutDateStr = dateFormatter1.format(checkOutDate.getTime());

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<ThingToDoCalculatePriceRequestResponse> call = service.getThingToDoCalculatePrice(Utility.getLocale(), WebUtility.THING_TO_DO_CALCULATE_PRICE + "thing_to_do_id=" +
                        accommodationId + "&schedule_date=" + checkInDateStr
                        + "&schedule_time_id=" + sltTimeSlot.getId()
                        + "&adults=" + adultCount
                        + "&kids=" + kidsCount
                        + "&schedule_type=0");
                call.enqueue(new Callback<ThingToDoCalculatePriceRequestResponse>() {
                    @Override
                    public void onResponse(Call<ThingToDoCalculatePriceRequestResponse> call, Response<ThingToDoCalculatePriceRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                calculationBreakDownThingToDo = response.body().getArrCalculationBreakDown();

                                if (calculationBreakDownThingToDo != null) {
                                    tvPriceBefore.setText(getString(R.string.sar) + " " + calculationBreakDownThingToDo.getTotalBeforeDiscount());
                                    tvAppliedDiscount.setText(getString(R.string.sar) + " " + calculationBreakDownThingToDo.getDiscountAmount());
                                    tvPriceAfter.setText(getString(R.string.sar) + " " + calculationBreakDownThingToDo.getTotalAfterDiscount());
                                }

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

    private void getThingToDoTimeSlot() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

                if (tvCheckInDate.getText().toString().trim().length() > 0) {
                    Date checkInDate = dateFormatter.parse(tvCheckInDate.getText().toString().trim());
                    checkInDateStr = dateFormatter1.format(checkInDate.getTime());
                }

                // Date checkOutDate = dateFormatter.parse(tvCheckOutDate.getText().toString().trim());
                // checkOutDateStr = dateFormatter1.format(checkOutDate.getTime());

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<ThingToDoTimeSlotRequestResponse> call = service.getThingToDoTimeSlot(Utility.getLocale(), WebUtility.THING_TO_DO_TIME_SLOT + "schedule_date=" + checkInDateStr + "&thing_to_do_id=" + accommodationId);
                call.enqueue(new Callback<ThingToDoTimeSlotRequestResponse>() {
                    @Override
                    public void onResponse(Call<ThingToDoTimeSlotRequestResponse> call, Response<ThingToDoTimeSlotRequestResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                thingsToDoTimeSlots = (ArrayList<ArrThingsToDoSlot>) response.body().getArrThingsToDoSlots();
                                timeSlotAdapter = new TimeSlotAdapter(mActivity, thingsToDoTimeSlots);
                                spinnerTimeSlot.setAdapter(timeSlotAdapter);
                                if (thingsToDoTimeSlots != null && !thingsToDoTimeSlots.isEmpty()) {
                                    if (!isThingTODoFirstTime) {
                                        isThingTODoFirstTime = true;
                                    }
                                    llTimeSlot.setVisibility(View.VISIBLE);
                                } else {
                                    llTimeSlot.setVisibility(View.GONE);
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
                    public void onFailure(Call<ThingToDoTimeSlotRequestResponse> call, Throwable t) {
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

    private void getCouponPrice() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CalculatedPriceRequestResponse> call = service.getCalculatedCouponPrice(Utility.getLocale(), WebUtility.COUPON_CALCULATE_PRICE + "coupon_id=" +
                        accommodationId + "&qty=" + QuantityCount + "");
                call.enqueue(new Callback<CalculatedPriceRequestResponse>() {
                    @Override
                    public void onResponse(Call<CalculatedPriceRequestResponse> call, Response<CalculatedPriceRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                calculationBreakDown = response.body().getArrCalculationBreakDown();

                                tvPriceBefore.setText(getString(R.string.sar) + " " + calculationBreakDown.getTotalBeforeDiscount());
                                tvAppliedDiscount.setText(getString(R.string.sar) + " " + calculationBreakDown.getDiscountAmount());
                                tvPriceAfter.setText(getString(R.string.sar) + " " + calculationBreakDown.getTotalAfterDiscount());

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<CalculatedPriceRequestResponse> call, Throwable t) {
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

    private void showDatePickerDialog(final boolean isFromCheckInData) {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                if (isFromCheckInData) {
                    tvCheckInDate.setText(dateFormatter.format(newDate.getTime()));
                    if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        getThingToDoTimeSlot();
                    }
                } else {
                    tvCheckOutDate.setText(dateFormatter.format(newDate.getTime()));
                    getPrice(false);
                }
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setTitle(getString(R.string.select_date));
        if (isFromCheckInData) {
            datePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
        } else {
            Date date = null;
            try {
                if (tvCheckInDate.getText().toString().length() > 0) {
                    date = dateFormatter.parse(tvCheckInDate.getText().toString());
                } else {
                    //Utility.showError(getString(R.string.valid_check_out_date));
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
                //Utility.showError(getString(R.string.valid_check_out_date));
            }
        }

        if (datePickerDialog.getWindow() != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
