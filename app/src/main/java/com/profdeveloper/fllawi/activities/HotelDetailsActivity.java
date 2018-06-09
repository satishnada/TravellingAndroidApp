package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.customviews.TypeFace;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.fragments.HotelDetailInfoFragment;
import com.profdeveloper.fllawi.fragments.HotelDetailHostFragment;
import com.profdeveloper.fllawi.fragments.HotelDetailPhotosFragment;
import com.profdeveloper.fllawi.fragments.HotelDetailReviewFragment;
import com.profdeveloper.fllawi.model.AccommodationDetails.AccommodationDetailsRequestResponse;
import com.profdeveloper.fllawi.model.ArrReview;
import com.profdeveloper.fllawi.model.AccommodationDetails.Data;
import com.profdeveloper.fllawi.model.AccommodationDetails.Gallery;
import com.profdeveloper.fllawi.model.AccommodationDetails.HostProvider;
import com.profdeveloper.fllawi.model.ThingToDoDetails.ThingToDoDetailRequestResponse;
import com.profdeveloper.fllawi.model.couponDetails.GetCouponDetailsRequestResponse;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.retrofit.WebUtility;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelDetailsActivity extends BaseActivity {

    private View view;
    private TabLayout topMenuLayout;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_toolbar;
    private ImageView ivTopMenu, ivTopSearch, ivPlacePin, ivTopDetails;
    private ProgressBar progressBar;
    private LinearLayout llDetails;
    private String latitude = "", longitude = "", location = "";
    private String hotelId = "1";
    private TextView tvHotelName, tvHotelStartFrom;

    private FragmentTransaction fragmentTransaction;
    private HotelDetailInfoFragment hotelDetailInfoFragment;
    private HotelDetailReviewFragment hotelDetailReviewFragment;
    private HotelDetailPhotosFragment detailPhotosFragment;
    private HotelDetailHostFragment detailHostFragment;

    private ArrayList<ArrReview> reviewList = new ArrayList<>();
    private ArrayList<Gallery> photosList = new ArrayList<>();
    private ArrayList<com.profdeveloper.fllawi.model.couponDetails.Gallery> photosCouponList = new ArrayList<>();
    private ArrayList<com.profdeveloper.fllawi.model.ThingToDoDetails.Gallery> photosThingToDoList = new ArrayList<>();

    private HostProvider hostProvider;
    private com.profdeveloper.fllawi.model.couponDetails.HostProvider hostCouponProvider;
    private com.profdeveloper.fllawi.model.ThingToDoDetails.HostProvider hostThingToDOProvider;
    private Data hotelDetail;
    private com.profdeveloper.fllawi.model.couponDetails.Data couponDetail;
    private com.profdeveloper.fllawi.model.ThingToDoDetails.Data thingToDoDetail;
    private String pricePerNight = "";
    private String detailId = "";
    private String hotelTopImageUrl = "";
    private String hotelName = "";
    private int isFrom = 0;
    private String fromDate = "";
    private String toDate = "";
    private String scheduledDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_hotel_detail_second, llContainer);
        mActivity = this;

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        collapsing_toolbar = view.findViewById(R.id.collapsing_toolbar);
        tvHotelName = view.findViewById(R.id.tvHotelName);
        tvHotelStartFrom = view.findViewById(R.id.tvHotelStartFrom);
        llDetails = view.findViewById(R.id.llDetails);
        ivTopMenu = view.findViewById(R.id.ivTopMenu);
        ivTopSearch = view.findViewById(R.id.ivTopSearch);
        ivPlacePin = view.findViewById(R.id.ivPlacePin);
        ivTopDetails = view.findViewById(R.id.ivTopDetails);
        progressBar = view.findViewById(R.id.progressBar);
        ivPlacePin.setOnClickListener(this);
        ivTopMenu.setOnClickListener(this);
        ivTopSearch.setOnClickListener(this);

        toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        isHomeRunning = false;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hotelTopImageUrl = bundle.getString(AppConstant.EXT_HOTEL_IMAGE);
            hotelName = bundle.getString(AppConstant.EXT_HOTEL_NAME);
            hotelId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);

            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
            if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                fromDate = bundle.getString(AppConstant.FROM_DATE);
                toDate = bundle.getString(AppConstant.TO_DATE);
                getAccommodationDetails();
            } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                scheduledDate = bundle.getString(AppConstant.FROM_DATE);
                getThingToDoDetails();
            } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                fromDate = bundle.getString(AppConstant.FROM_DATE);
                toDate = bundle.getString(AppConstant.TO_DATE);
                getCouponDetails();
            } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {

            } else if (isFrom == AppConstant.IS_FROM_EVENT) {

            }
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        getSupportActionBar().setTitle("");

        topMenuLayout = (TabLayout) view.findViewById(R.id.tlTopMenu);
        topMenuLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        topMenuLayout.addTab(topMenuLayout.newTab().setText(R.string.info), 0);
        topMenuLayout.addTab(topMenuLayout.newTab().setText(R.string.photo), 1);
        topMenuLayout.addTab(topMenuLayout.newTab().setText(R.string.host), 2);
        topMenuLayout.addTab(topMenuLayout.newTab().setText(R.string.review), 3);
        topMenuLayout.setTabTextColors(mActivity.getResources().getColor(R.color.dark_gray), mActivity.getResources().getColor(R.color.app_blue));

        topMenuLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        hotelDetailInfoFragment = (HotelDetailInfoFragment) HotelDetailInfoFragment.getInstance(isFrom, hotelDetail);
                        fragmentTransaction.replace(R.id.llDetails, hotelDetailInfoFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        hotelDetailInfoFragment = (HotelDetailInfoFragment) HotelDetailInfoFragment.getInstance(isFrom, thingToDoDetail);
                        fragmentTransaction.replace(R.id.llDetails, hotelDetailInfoFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        hotelDetailInfoFragment = (HotelDetailInfoFragment) HotelDetailInfoFragment.getInstance(isFrom, couponDetail);
                        fragmentTransaction.replace(R.id.llDetails, hotelDetailInfoFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                } else if (tab.getPosition() == 1) {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        detailPhotosFragment = (HotelDetailPhotosFragment) HotelDetailPhotosFragment.getInstance(isFrom, hotelDetail, pricePerNight);
                        fragmentTransaction.replace(R.id.llDetails, detailPhotosFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        detailPhotosFragment = (HotelDetailPhotosFragment) HotelDetailPhotosFragment.getInstance(isFrom, couponDetail, pricePerNight);
                        fragmentTransaction.replace(R.id.llDetails, detailPhotosFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        detailPhotosFragment = (HotelDetailPhotosFragment) HotelDetailPhotosFragment.getInstance(isFrom, thingToDoDetail, pricePerNight);
                        fragmentTransaction.replace(R.id.llDetails, detailPhotosFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                } else if (tab.getPosition() == 2) {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        detailHostFragment = (HotelDetailHostFragment) HotelDetailHostFragment.getInstance(isFrom, hostProvider, pricePerNight);
                        fragmentTransaction.replace(R.id.llDetails, detailHostFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        detailHostFragment = (HotelDetailHostFragment) HotelDetailHostFragment.getInstance(isFrom, hostThingToDOProvider, pricePerNight);
                        fragmentTransaction.replace(R.id.llDetails, detailHostFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        detailHostFragment = (HotelDetailHostFragment) HotelDetailHostFragment.getInstance(isFrom, hostCouponProvider, pricePerNight);
                        fragmentTransaction.replace(R.id.llDetails, detailHostFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                } else if (tab.getPosition() == 3) {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        hotelDetailReviewFragment = (HotelDetailReviewFragment) HotelDetailReviewFragment.getInstance(isFrom, hotelId, reviewList, pricePerNight);
                        fragmentTransaction.replace(R.id.llDetails, hotelDetailReviewFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        hotelDetailReviewFragment = (HotelDetailReviewFragment) HotelDetailReviewFragment.getInstance(isFrom, hotelId, reviewList, pricePerNight);
                        fragmentTransaction.replace(R.id.llDetails, hotelDetailReviewFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        hotelDetailReviewFragment = (HotelDetailReviewFragment) HotelDetailReviewFragment.getInstance(isFrom, hotelId, reviewList, pricePerNight);
                        fragmentTransaction.replace(R.id.llDetails, hotelDetailReviewFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        topMenuLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        changeTabsFont();
        ivTopBack.setOnClickListener(this);

    }

    public void onBookingClick(int isFrom) {
        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            bookNow(AppConstant.IS_FROM_ACCOMMODATION);
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            bookNow(AppConstant.IS_FROM_THING_TO_DO);
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            bookNow(AppConstant.IS_FROM_COUPON);
        } else if (isFrom == AppConstant.IS_FROM_EVENT) {

        } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {

        }
    }

    private void bookNow(int isFrom) {
        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            Intent intent = new Intent(mActivity, BookHotelActivity.class);
            intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID, detailId);
            intent.putExtra(AppConstant.EXT_IS_FROM, isFrom);
            intent.putExtra(AppConstant.EXT_FROM_DATE, fromDate);
            intent.putExtra(AppConstant.EXT_TO_DATE, toDate);
            intent.putExtra(AppConstant.EXT_HOTEL_NAME, hotelName);
            intent.putExtra(AppConstant.EXT_HOTEL_IMAGE, hotelTopImageUrl);
            intent.putExtra(AppConstant.EXT_HOTEL_AMOUNT, tvHotelStartFrom.getText().toString());
            intent.putExtra(AppConstant.EXT_HOTEL_RATTING, "3");
            startActivity(intent);
            goNext();
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            Intent intent = new Intent(mActivity, BookHotelActivity.class);
            intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID, detailId);
            intent.putExtra(AppConstant.EXT_IS_FROM, isFrom);
            intent.putExtra(AppConstant.EXT_FROM_DATE, scheduledDate);
            intent.putExtra(AppConstant.EXT_HOTEL_NAME, hotelName);
            intent.putExtra(AppConstant.EXT_HOTEL_IMAGE, hotelTopImageUrl);
            intent.putExtra(AppConstant.EXT_HOTEL_AMOUNT, tvHotelStartFrom.getText().toString());
            intent.putExtra(AppConstant.EXT_HOTEL_RATTING, "3");
            startActivity(intent);
            goNext();
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            Intent intent = new Intent(mActivity, BookHotelActivity.class);
            intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID, detailId);
            intent.putExtra(AppConstant.EXT_IS_FROM, isFrom);
            intent.putExtra(AppConstant.EXT_HOTEL_NAME, hotelName);
            intent.putExtra(AppConstant.EXT_HOTEL_IMAGE, hotelTopImageUrl);
            intent.putExtra(AppConstant.EXT_HOTEL_AMOUNT, tvHotelStartFrom.getText().toString());
            intent.putExtra(AppConstant.EXT_HOTEL_RATTING, "3");
            startActivity(intent);
            goNext();
        } else if (isFrom == AppConstant.IS_FROM_EVENT) {

        } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {

        }
    }

    @Override
    public void initialization() {
        hideTopBar();
        ivTopBack.setImageResource(R.drawable.ic_back_arrow);
        tvTopTitle.setBackgroundResource(0);
        tvTopTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        tvTopTitle.setText(R.string.details);
        tvTopTitle.setVisibility(View.VISIBLE);
        ivTopSearch.setVisibility(View.INVISIBLE);
        checkLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivTopMenu:
                onBackPressed();
                break;
            case R.id.ivTopSearch:
                Intent searchIntent = new Intent(mActivity, SearchActivity.class);
                startActivity(searchIntent);
                goNext();
                break;
            case R.id.ivPlacePin:
                if (latitude != null && longitude != null && latitude.length() > 0 && longitude.length() > 0 && location != null && location.length() > 0) {
                    Intent mapIntent = new Intent(mActivity, HotelDetailMapActivity.class);
                    mapIntent.putExtra(AppConstant.EXT_LATITUDE, latitude);
                    mapIntent.putExtra(AppConstant.EXT_LONGITUDE, longitude);
                    mapIntent.putExtra(AppConstant.EXT_MAP_LOCATION, location);
                    startActivity(mapIntent);
                    goNext();
                }
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void setAccommodationDetails(AccommodationDetailsRequestResponse data) {
        hotelDetail = data.getData();
        detailId = hotelDetail.getArrData().getId() + "";
        hostProvider = data.getData().getArrData().getHostProvider();
        latitude = hotelDetail.getArrData().getLat();
        longitude = hotelDetail.getArrData().getLng();
        location = hotelDetail.getArrData().getAddress();
        tvHotelName.setText(hotelName);
        tvHotelStartFrom.setText(getString(R.string.startfrom) + hotelDetail.getPrice());
        photosList = (ArrayList<Gallery>) hotelDetail.getArrData().getGallery();
        reviewList = (ArrayList<ArrReview>) hotelDetail.getArrReview();
        pricePerNight = hotelDetail.getPrice() + "";
        Utility.BASE_GALLERY_URL = data.getImageUrl();
        Utility.BASE_URL = data.getProfileImageUrl();
        ImageLoader.getInstance().loadImage(Utility.BASE_GALLERY_URL + "/" + hotelTopImageUrl, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                ivTopDetails.setImageBitmap(loadedImage);
                progressBar.setVisibility(View.GONE);
            }
        });

        hotelDetailInfoFragment = (HotelDetailInfoFragment) HotelDetailInfoFragment.getInstance(isFrom, hotelDetail);
        fragmentTransaction.replace(R.id.llDetails, hotelDetailInfoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setThingToDoDetails(ThingToDoDetailRequestResponse data) {
        thingToDoDetail = data.getData();
        detailId = thingToDoDetail.getArrData().getId() + "";
        hostThingToDOProvider = data.getData().getArrData().getHostProvider();
        latitude = thingToDoDetail.getArrData().getLat();
        longitude = thingToDoDetail.getArrData().getLng();
        location = thingToDoDetail.getArrData().getAddress();
        tvHotelName.setText(hotelName);
        tvHotelStartFrom.setText(getString(R.string.startfrom) + thingToDoDetail.getPrice());
        photosThingToDoList = (ArrayList<com.profdeveloper.fllawi.model.ThingToDoDetails.Gallery>) data.getData().getArrData().getGallery();
        reviewList = (ArrayList<ArrReview>) thingToDoDetail.getArrReview();
        pricePerNight = thingToDoDetail.getPrice() + "";
        Utility.BASE_GALLERY_URL = data.getImageUrl();
        Utility.BASE_URL = data.getProfileImageUrl();
        ImageLoader.getInstance().loadImage(Utility.BASE_GALLERY_URL + "/" + hotelTopImageUrl, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                ivTopDetails.setImageBitmap(loadedImage);
                progressBar.setVisibility(View.GONE);
            }
        });

        hotelDetailInfoFragment = (HotelDetailInfoFragment) HotelDetailInfoFragment.getInstance(isFrom, thingToDoDetail);
        fragmentTransaction.replace(R.id.llDetails, hotelDetailInfoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setCouponDetails(GetCouponDetailsRequestResponse data) {
        couponDetail = data.getData();
        detailId = couponDetail.getArrData().getId() + "";
        hostCouponProvider = couponDetail.getArrData().getHostProvider();
        latitude = couponDetail.getArrData().getLat();
        longitude = couponDetail.getArrData().getLng();
        location = couponDetail.getArrData().getAddress();
        tvHotelName.setText(hotelName);
        tvHotelStartFrom.setText(couponDetail.getArrData().getDiscount() + " % Discount on SAR " + couponDetail.getArrData().getBaseAmount() + " Per unit");
        photosCouponList = (ArrayList<com.profdeveloper.fllawi.model.couponDetails.Gallery>) couponDetail.getArrData().getGallery();
        reviewList = (ArrayList<ArrReview>) couponDetail.getArrReview();
        pricePerNight = couponDetail.getArrData().getBaseAmount() + "";
        Utility.BASE_GALLERY_URL = data.getImageUrl();
        ImageLoader.getInstance().loadImage(Utility.BASE_GALLERY_URL + "/" + hotelTopImageUrl, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                ivTopDetails.setImageBitmap(loadedImage);
                progressBar.setVisibility(View.GONE);
            }
        });

        hotelDetailInfoFragment = (HotelDetailInfoFragment) HotelDetailInfoFragment.getInstance(isFrom, couponDetail);
        fragmentTransaction.replace(R.id.llDetails, hotelDetailInfoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void getAccommodationDetails() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<AccommodationDetailsRequestResponse> call = service.getAccommodationDetails(Utility.getLocale(), WebUtility.GET_DETAILS + hotelId);
                call.enqueue(new Callback<AccommodationDetailsRequestResponse>() {
                    @Override
                    public void onResponse(Call<AccommodationDetailsRequestResponse> call, Response<AccommodationDetailsRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                AccommodationDetailsRequestResponse detailsRequestResponse = response.body();
                                setAccommodationDetails(detailsRequestResponse);

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<AccommodationDetailsRequestResponse> call, Throwable t) {
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

    private void getThingToDoDetails() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<ThingToDoDetailRequestResponse> call = service.getThingToDoDetails(Utility.getLocale(), WebUtility.GET_THING_TO_DO_DETAILS + hotelId);
                call.enqueue(new Callback<ThingToDoDetailRequestResponse>() {
                    @Override
                    public void onResponse(Call<ThingToDoDetailRequestResponse> call, Response<ThingToDoDetailRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                ThingToDoDetailRequestResponse detailsRequestResponse = response.body();
                                setThingToDoDetails(detailsRequestResponse);

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<ThingToDoDetailRequestResponse> call, Throwable t) {
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

    private void getCouponDetails() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<GetCouponDetailsRequestResponse> call = service.getCouponDetails(Utility.getLocale(), WebUtility.GET_COUPON_DETAILS + hotelId);
                call.enqueue(new Callback<GetCouponDetailsRequestResponse>() {
                    @Override
                    public void onResponse(Call<GetCouponDetailsRequestResponse> call, Response<GetCouponDetailsRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                GetCouponDetailsRequestResponse detailsRequestResponse = response.body();
                                setCouponDetails(detailsRequestResponse);

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<GetCouponDetailsRequestResponse> call, Throwable t) {
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

    /*  private void getAccommodationReviewList() {
          try {
              if (!Utility.isNetworkAvailable(mActivity)) {
                  Utility.showError(getString(R.string.no_internet_connection));
              } else {
                  Utility.showProgress(mActivity);
                  WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                  Call<AccommodationDetailsRequestResponse> call = service.getAccommodationReviewList(WebUtility.GET_DETAILS + hotelId);
                  call.enqueue(new Callback<AccommodationDetailsRequestResponse>() {
                      @Override
                      public void onResponse(Call<AccommodationDetailsRequestResponse> call, Response<AccommodationDetailsRequestResponse> response) {

                          if (response.isSuccessful()) {
                              if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                  AccommodationDetailsRequestResponse detailsRequestResponse = response.body();
                                  setAccommodationDetails(detailsRequestResponse);

                              } else {
                                  Utility.showError(response.body().getMsg());
                              }
                          } else {
                              Utility.showError(response.body().getMsg());
                          }
                          Utility.hideProgress();
                      }

                      @Override
                      public void onFailure(Call<AccommodationDetailsRequestResponse> call, Throwable t) {
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
  */
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
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        //Intent searchIntent = new Intent(HotelDetailsActivity.this,SearchActivity.class);
        //searchIntent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_ACCOMMODATION);
        //startActivity(searchIntent);
        finish();
    }
}
