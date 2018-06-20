package com.profdeveloper.fllawi.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.customviews.TypeFace;
import com.google.gson.Gson;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.adapters.AllowedListAdapter;
import com.profdeveloper.fllawi.adapters.CouponDetailPhotosListAdapter;
import com.profdeveloper.fllawi.adapters.FacilitiesListAdapter;
import com.profdeveloper.fllawi.adapters.HotelDetailPhotosListAdapter;
import com.profdeveloper.fllawi.adapters.HotelReviewsListAdapter;
import com.profdeveloper.fllawi.adapters.IncludeListAdapter;
import com.profdeveloper.fllawi.adapters.NotAllowedListAdapter;
import com.profdeveloper.fllawi.adapters.NotIncludeListAdapter;
import com.profdeveloper.fllawi.adapters.PlacesListAdapter;
import com.profdeveloper.fllawi.adapters.RequireListAdapter;
import com.profdeveloper.fllawi.adapters.ThingTodoDetailPhotosListAdapter;
import com.profdeveloper.fllawi.model.AccommodationDetails.AccommodationDetailsRequestResponse;
import com.profdeveloper.fllawi.model.AccommodationDetails.Amenity;
import com.profdeveloper.fllawi.model.ArrReview;
import com.profdeveloper.fllawi.model.AccommodationDetails.Data;
import com.profdeveloper.fllawi.model.AccommodationDetails.Gallery;
import com.profdeveloper.fllawi.model.AccommodationDetails.HostProvider;
import com.profdeveloper.fllawi.model.CommonRequestResponse;
import com.profdeveloper.fllawi.model.Package.PackageDetaisRequestResponse;
import com.profdeveloper.fllawi.model.Reviews.ReviewsRequestResponse;
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
import java.util.List;

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
    private com.profdeveloper.fllawi.model.ThingToDoDetails.Data packageDetail;
    private String pricePerNight = "";
    private String detailId = "";
    private String hotelTopImageUrl = "";
    private String hotelName = "";
    private int isFrom = 0;
    private String fromDate = "";
    private String toDate = "";
    private String scheduledDate = "";

    // INFORMATION VIEW
    private RecyclerView rvRoomFacilities, rvAllowed, rvPlaces,rvNotAllowed, rvInclude, rvNotInclude, rvRequirement;
    private GridLayoutManager gridLayoutManager;
    private FacilitiesListAdapter facilitiesListAdapter;
    private LinearLayout llContinueBooking;
    private Data accommodationDetail;
    private ArrayList<Amenity> amenitiesList = new ArrayList<>();
    private List<String> allowList = new ArrayList<>();
    private TextView tvDescriptionText, tvPrice, tvCheckInOutTime, tvCheckInOutTimeDetail,
            tvCancellationPolicyDetail, tvNoDataFoundPlaces,tvNoDataFoundFacility,
            tvNoDataFoundAllowed, tvNoDataFoundNotAllowed, tvNoDataFoundInclude,
            tvNoDataFoundNotInclude, tvNoDataFoundRequirement, tvWhyWithUsDetail, tvWhyWithUs,
            tvAdditionalInfoDetails, tvOtherRuleDetails, tvBooking, tvPerNight;
    private LinearLayout llCheckInOutTime, llCancellationPolicy,llPlaces,
            llAllowed, llNotAllowed, llInclude, llNotInclude, llRequirement;
    private LinearLayout llRoomsAndFacility, llWhyWithUs, llAdditionalInfo, llOtherRule;
    private View viewDividerRoomAndFacility, viewCheckInOut, viewWhyWithUse,
            viewRequire, viewAdditionalInfo, viewAllowDivider, viewNotAllowDivider;

    //Photo view
    private RecyclerView rvHotelPhotos;
    private HotelDetailPhotosListAdapter photosListAdapter;
    private ThingTodoDetailPhotosListAdapter photosThingToDoListAdapter;
    private CouponDetailPhotosListAdapter photosCouponListAdapter;

    private HostProvider hostDetail;
    private com.profdeveloper.fllawi.model.couponDetails.HostProvider hostCouponDetail;
    private com.profdeveloper.fllawi.model.ThingToDoDetails.HostProvider hostThingToDoDetail;
    private ImageView ivProfilePic;
    private TextView tvHostName;
    private TextView tvHostContact;
    private ProgressBar progressBarHost;

    private final int LOGIN_REQUEST = 1001;
    private RecyclerView rvReviews;
    private HotelReviewsListAdapter hotelReviewsListAdapter;
    private RatingBar rattingAllReview;
    private TextView tvReviewsCount, tvAddRatting;
    private String id;
    private String reviewName = "";
    private String reviewMessage = "";
    private float reviewRatting = 0;
    private String rattingAvg = "";

    private ViewGroup viewInformation;
    private ViewGroup viewPhoto;
    private ViewGroup viewHostInfo;
    private ViewGroup viewReviewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_hotel_detail_second, llContainer);
        mActivity = this;

        collapsing_toolbar = view.findViewById(R.id.collapsing_toolbar);
        tvHotelName = view.findViewById(R.id.tvHotelName);
        tvHotelStartFrom = view.findViewById(R.id.tvHotelStartFrom);
        ivTopMenu = view.findViewById(R.id.ivTopMenu);
        ivTopSearch = view.findViewById(R.id.ivTopSearch);
        ivPlacePin = view.findViewById(R.id.ivPlacePin);
        ivTopDetails = view.findViewById(R.id.ivTopDetails);
        progressBar = view.findViewById(R.id.progressBar);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvBooking = view.findViewById(R.id.tvBooking);
        llContinueBooking = view.findViewById(R.id.llContinueBooking);
        llPlaces = view.findViewById(R.id.llPlaces);
        tvPerNight = view.findViewById(R.id.tvPerNight);

        viewInformation = view.findViewById(R.id.ViewInformation);
        viewPhoto = view.findViewById(R.id.ViewPhoto);
        viewHostInfo = view.findViewById(R.id.ViewHostInfo);
        viewReviewInfo = view.findViewById(R.id.ViewReviewInfo);

        ivPlacePin.setOnClickListener(this);
        ivTopMenu.setOnClickListener(this);
        ivTopSearch.setOnClickListener(this);
        llContinueBooking.setOnClickListener(this);

        toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        isHomeRunning = false;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hotelTopImageUrl = bundle.getString(AppConstant.EXT_HOTEL_IMAGE);
            hotelName = bundle.getString(AppConstant.EXT_HOTEL_NAME);
            hotelId = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
            rattingAvg = bundle.getString(AppConstant.EXT_AVG_RATTING);

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

            } else if (isFrom == AppConstant.IS_FROM_PACKAGE){
                scheduledDate = bundle.getString(AppConstant.FROM_DATE);
                getPackageDetails();
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
                    viewInformation.setVisibility(View.VISIBLE);
                    viewPhoto.setVisibility(View.GONE);
                    viewHostInfo.setVisibility(View.GONE);
                    viewReviewInfo.setVisibility(View.GONE);
                } else if (tab.getPosition() == 1) {
                    viewInformation.setVisibility(View.GONE);
                    viewPhoto.setVisibility(View.VISIBLE);
                    viewHostInfo.setVisibility(View.GONE);
                    viewReviewInfo.setVisibility(View.GONE);
                } else if (tab.getPosition() == 2) {
                    viewInformation.setVisibility(View.GONE);
                    viewPhoto.setVisibility(View.GONE);
                    viewHostInfo.setVisibility(View.VISIBLE);
                    viewReviewInfo.setVisibility(View.GONE);
                } else if (tab.getPosition() == 3) {
                    viewInformation.setVisibility(View.GONE);
                    viewPhoto.setVisibility(View.GONE);
                    viewHostInfo.setVisibility(View.GONE);
                    viewReviewInfo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Information tab view
        setInformationView();

        //Photo tab view
        setPhotoView();

        //Host tab view
        setHostView();

        setReviewView();

        topMenuLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        changeTabsFont();
        ivTopBack.setOnClickListener(this);

    }

    public void onBookingClick() {
        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            bookNow(AppConstant.IS_FROM_ACCOMMODATION);
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            bookNow(AppConstant.IS_FROM_THING_TO_DO);
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            bookNow(AppConstant.IS_FROM_COUPON);
        } else if (isFrom == AppConstant.IS_FROM_EVENT) {

        } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {

        } else if (isFrom == AppConstant.IS_FROM_PACKAGE) {
            bookNow(AppConstant.IS_FROM_PACKAGE);
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
            intent.putExtra(AppConstant.EXT_HOTEL_AMOUNT, hotelDetail.getPrice()+"");
            intent.putExtra(AppConstant.EXT_HOTEL_RATTING, rattingAvg+"");
            startActivity(intent);
            goNext();
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            Intent intent = new Intent(mActivity, BookHotelActivity.class);
            intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID, detailId);
            intent.putExtra(AppConstant.EXT_IS_FROM, isFrom);
            intent.putExtra(AppConstant.EXT_FROM_DATE, scheduledDate);
            intent.putExtra(AppConstant.EXT_HOTEL_NAME, hotelName);
            intent.putExtra(AppConstant.EXT_HOTEL_IMAGE, hotelTopImageUrl);
            intent.putExtra(AppConstant.EXT_HOTEL_AMOUNT, thingToDoDetail.getPrice()+""); //tvHotelStartFrom.getText().toString()
            intent.putExtra(AppConstant.EXT_HOTEL_RATTING, rattingAvg+"");
            startActivity(intent);
            goNext();
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            Intent intent = new Intent(mActivity, BookHotelActivity.class);
            intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID, detailId);
            intent.putExtra(AppConstant.EXT_IS_FROM, isFrom);
            intent.putExtra(AppConstant.EXT_HOTEL_NAME, hotelName);
            intent.putExtra(AppConstant.EXT_HOTEL_IMAGE, hotelTopImageUrl);
            intent.putExtra(AppConstant.EXT_HOTEL_AMOUNT, tvHotelStartFrom.getText().toString());
            intent.putExtra(AppConstant.EXT_HOTEL_RATTING, rattingAvg+"");
            startActivity(intent);
            goNext();
        } else if (isFrom == AppConstant.IS_FROM_EVENT) {

        } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {

        } else if (isFrom == AppConstant.IS_FROM_PACKAGE) {
            Intent intent = new Intent(mActivity, BookHotelActivity.class);
            intent.putExtra(AppConstant.EXT_ACCOMMODATION_ID, detailId);
            intent.putExtra(AppConstant.EXT_IS_FROM, isFrom);
            intent.putExtra(AppConstant.EXT_FROM_DATE, scheduledDate);
            intent.putExtra(AppConstant.EXT_HOTEL_NAME, hotelName);
            intent.putExtra(AppConstant.EXT_HOTEL_IMAGE, hotelTopImageUrl);
            intent.putExtra(AppConstant.EXT_HOTEL_AMOUNT, packageDetail.getPrice()+""); //tvHotelStartFrom.getText().toString()
            intent.putExtra(AppConstant.EXT_HOTEL_RATTING, rattingAvg+"");
            startActivity(intent);
            goNext();
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
                    mapIntent.putExtra(AppConstant.EXT_MAP_LOCATION, hotelName + "\n" + location + "\n" + tvHotelStartFrom.getText().toString());
                    startActivity(mapIntent);
                    goNext();
                }
                break;
            case R.id.llContinueBooking:
                onBookingClick();
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
        tvHotelStartFrom.setText(getString(R.string.startfrom) + " " + getString(R.string.sar) + " " + hotelDetail.getPrice());
        photosList = (ArrayList<Gallery>) hotelDetail.getArrData().getGallery();
        reviewList = (ArrayList<ArrReview>) hotelDetail.getArrReview();
        pricePerNight = hotelDetail.getPrice() + "";
        Utility.BASE_GALLERY_URL = data.getImageUrl();
        Utility.BASE_URL = data.getProfileImageUrl();

        Glide.with(this).load(Utility.BASE_GALLERY_URL + "/" + hotelTopImageUrl)
                .into(ivTopDetails);
        progressBar.setVisibility(View.GONE);

/*        mImageLoader.loadImage(Utility.BASE_GALLERY_URL + "/" + hotelTopImageUrl, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                ivTopDetails.setImageBitmap(loadedImage);
                progressBar.setVisibility(View.GONE);
            }
        });*/

        hostDetail = hotelDetail.getArrData().getHostProvider();

        tvPrice.setText(getString(R.string.sar) + " " + pricePerNight);

        setInformationData();

        setPhotoData();

        setHostViewData();

        setReviewViewData();

    }

    private void setThingToDoDetails(ThingToDoDetailRequestResponse data) {
        thingToDoDetail = data.getData();
        detailId = thingToDoDetail.getArrData().getId() + "";
        hostThingToDOProvider = data.getData().getArrData().getHostProvider();
        latitude = thingToDoDetail.getArrData().getLat();
        longitude = thingToDoDetail.getArrData().getLng();
        location = thingToDoDetail.getArrData().getAddress();
        tvHotelName.setText(hotelName);
        tvHotelStartFrom.setText(getString(R.string.startfrom) + " " + getString(R.string.sar) + " " + thingToDoDetail.getPrice());
        photosThingToDoList = (ArrayList<com.profdeveloper.fllawi.model.ThingToDoDetails.Gallery>) data.getData().getArrData().getGallery();
        reviewList = (ArrayList<ArrReview>) thingToDoDetail.getArrReview();
        pricePerNight = thingToDoDetail.getPrice() + "";
        Utility.BASE_GALLERY_URL = data.getImageUrl();
        Utility.BASE_URL = data.getProfileImageUrl();
        mImageLoader.loadImage(Utility.BASE_GALLERY_URL + "/" + hotelTopImageUrl, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                ivTopDetails.setImageBitmap(loadedImage);
                progressBar.setVisibility(View.GONE);
            }
        });

        tvPrice.setText(getString(R.string.sar) + " " + pricePerNight);
        hostThingToDoDetail = thingToDoDetail.getArrData().getHostProvider();

        setInformationData();

        setPhotoData();

        setHostViewData();

        setReviewViewData();

    }

    private void setPackageDetails(PackageDetaisRequestResponse data) {
        packageDetail = data.getData();
        detailId = packageDetail.getArrData().getId() + "";
        hostThingToDOProvider = data.getData().getArrData().getHostProvider();
        latitude = packageDetail.getArrData().getLat();
        longitude = packageDetail.getArrData().getLng();
        location = packageDetail.getArrData().getAddress();
        tvHotelName.setText(hotelName);
        tvHotelStartFrom.setText(getString(R.string.startfrom) + " " + getString(R.string.sar) + " " + packageDetail.getPrice());
        photosThingToDoList = (ArrayList<com.profdeveloper.fllawi.model.ThingToDoDetails.Gallery>) data.getData().getArrData().getGallery();
        reviewList = (ArrayList<ArrReview>) packageDetail.getArrReview();
        pricePerNight = packageDetail.getPrice() + "";
        Utility.BASE_GALLERY_URL = data.getImageUrl();
        Utility.BASE_URL = data.getProfileImageUrl();
        mImageLoader.loadImage(Utility.BASE_GALLERY_URL + "/" + hotelTopImageUrl, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                ivTopDetails.setImageBitmap(loadedImage);
                progressBar.setVisibility(View.GONE);
            }
        });

        tvPrice.setText(getString(R.string.sar) + " " + pricePerNight);
        hostThingToDoDetail = packageDetail.getArrData().getHostProvider();

        setInformationData();

        setPhotoData();

        setHostViewData();

        setReviewViewData();

    }

    private void setCouponDetails(GetCouponDetailsRequestResponse data) {
        couponDetail = data.getData();
        detailId = couponDetail.getArrData().getId() + "";
        hostCouponProvider = couponDetail.getArrData().getHostProvider();
        latitude = couponDetail.getArrData().getLat();
        longitude = couponDetail.getArrData().getLng();
        location = couponDetail.getArrData().getAddress();
        tvHotelName.setText(hotelName);
        tvHotelStartFrom.setText(couponDetail.getArrData().getDiscount() + " " + getString(R.string.discount_on) + " " + getString(R.string.sar) +" "+ couponDetail.getArrData().getBaseAmount() + " " + getString(R.string.per_unit));
        photosCouponList = (ArrayList<com.profdeveloper.fllawi.model.couponDetails.Gallery>) couponDetail.getArrData().getGallery();
        reviewList = (ArrayList<ArrReview>) couponDetail.getArrReview();
        pricePerNight = couponDetail.getArrData().getBaseAmount() + " ";
        Utility.BASE_GALLERY_URL = data.getImageUrl();
        mImageLoader.loadImage(Utility.BASE_GALLERY_URL + "/" + hotelTopImageUrl, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                ivTopDetails.setImageBitmap(loadedImage);
                progressBar.setVisibility(View.GONE);
            }
        });

        tvPrice.setText(getString(R.string.sar) + " " + pricePerNight);
        hostCouponDetail = couponDetail.getArrData().getHostProvider();

        setInformationData();

        setPhotoData();

        setHostViewData();

        setReviewViewData();

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

    private void getPackageDetails() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<PackageDetaisRequestResponse> call = service.getPackageDetails(Utility.getLocale(), WebUtility.GET_PACKAGE_DETAILS + hotelId);
                call.enqueue(new Callback<PackageDetaisRequestResponse>() {
                    @Override
                    public void onResponse(Call<PackageDetaisRequestResponse> call, Response<PackageDetaisRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                PackageDetaisRequestResponse detailsRequestResponse = response.body();
                                setPackageDetails(detailsRequestResponse);

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<PackageDetaisRequestResponse> call, Throwable t) {
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

    private void setInformationView() {
        rvRoomFacilities = viewInformation.findViewById(R.id.rvRoomFacilities);
        tvDescriptionText = viewInformation.findViewById(R.id.tvDescriptionText);
        llCheckInOutTime = viewInformation.findViewById(R.id.llCheckInOutTime);
        viewCheckInOut = viewInformation.findViewById(R.id.viewCheckInOut);
        llRoomsAndFacility = viewInformation.findViewById(R.id.llRoomsAndFacility);
        viewDividerRoomAndFacility = viewInformation.findViewById(R.id.viewDividerRoomAndFacility);

        tvCheckInOutTime = viewInformation.findViewById(R.id.tvCheckInOutTime);
        tvCheckInOutTimeDetail = viewInformation.findViewById(R.id.tvCheckInOutTimeDetail);
        tvNoDataFoundAllowed = viewInformation.findViewById(R.id.tvNoDataFoundAllowed);
        tvNoDataFoundNotAllowed = viewInformation.findViewById(R.id.tvNoDataFoundNotAllowed);
        tvNoDataFoundNotInclude = viewInformation.findViewById(R.id.tvNoDataFoundNotInclude);
        tvNoDataFoundInclude = viewInformation.findViewById(R.id.tvNoDataFoundInclude);
        tvNoDataFoundRequirement = viewInformation.findViewById(R.id.tvNoDataFoundRequirement);
        tvWhyWithUs = viewInformation.findViewById(R.id.tvWhyWithUs);
        tvWhyWithUsDetail = viewInformation.findViewById(R.id.tvWhyWithUsDetail);
        viewWhyWithUse = viewInformation.findViewById(R.id.viewWhyWithUse);
        viewRequire = viewInformation.findViewById(R.id.viewRequire);
        viewAdditionalInfo = viewInformation.findViewById(R.id.viewAdditionalInfo);
        llAdditionalInfo = viewInformation.findViewById(R.id.llAdditionalInfo);
        tvAdditionalInfoDetails = viewInformation.findViewById(R.id.tvAdditionalInfoDetails);
        llOtherRule = viewInformation.findViewById(R.id.llOtherRule);
        tvOtherRuleDetails = viewInformation.findViewById(R.id.tvOtherRuleDetails);

        llWhyWithUs = viewInformation.findViewById(R.id.llWhyWithUs);
        llCancellationPolicy = viewInformation.findViewById(R.id.llCancellationPolicy);
        llAllowed = viewInformation.findViewById(R.id.llAllowed);
        llNotAllowed = viewInformation.findViewById(R.id.llNotAllowed);
        llInclude = viewInformation.findViewById(R.id.llInclude);
        llNotInclude = viewInformation.findViewById(R.id.llNotInclude);
        llRequirement = viewInformation.findViewById(R.id.llRequirement);
        viewAllowDivider = viewInformation.findViewById(R.id.viewAllowDivider);
        viewNotAllowDivider = viewInformation.findViewById(R.id.viewNotAllowDivider);

        rvAllowed = viewInformation.findViewById(R.id.rvAllowed);
        rvPlaces = viewInformation.findViewById(R.id.rvPlaces);
        rvNotAllowed = viewInformation.findViewById(R.id.rvNotAllowed);
        rvInclude = viewInformation.findViewById(R.id.rvInclude);
        rvNotInclude = viewInformation.findViewById(R.id.rvNotInclude);
        rvRequirement = viewInformation.findViewById(R.id.rvRequirement);

        tvNoDataFoundPlaces = viewInformation.findViewById(R.id.tvNoDataFoundPlaces);
        tvCancellationPolicyDetail = viewInformation.findViewById(R.id.tvCancellationPolicyDetail);
        tvNoDataFoundFacility = viewInformation.findViewById(R.id.tvNoDataFoundFacility);
    }

    private void setPhotoView() {
        rvHotelPhotos = viewPhoto.findViewById(R.id.rvHotelPhotos);
        gridLayoutManager = new GridLayoutManager(this, 3);
        rvHotelPhotos.setLayoutManager(gridLayoutManager);
    }

    private void setHostView() {
        ivProfilePic = viewHostInfo.findViewById(R.id.ivProfilePic);
        tvHostName = viewHostInfo.findViewById(R.id.tvHostName);
        tvHostContact = viewHostInfo.findViewById(R.id.tvHostContact);
        progressBarHost = viewHostInfo.findViewById(R.id.progressBar);
    }

    private void setReviewView() {
        tvReviewsCount = viewReviewInfo.findViewById(R.id.tvReviewsCount);
        rvReviews = viewReviewInfo.findViewById(R.id.rvReviews);
        tvAddRatting = viewReviewInfo.findViewById(R.id.tvAddRatting);
        rvReviews.setLayoutManager(Utility.getLayoutManager(this));

        tvAddRatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddReviewDialog();
            }
        });
    }

    private void setReviewViewData() {
        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            if (reviewList != null) {
                hotelReviewsListAdapter = new HotelReviewsListAdapter(this, reviewList);
                rvReviews.setAdapter(hotelReviewsListAdapter);
                tvReviewsCount.setText(reviewList.size() + getString(R.string.reviews));
            }
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            if (reviewList != null) {
                hotelReviewsListAdapter = new HotelReviewsListAdapter(this, reviewList);
                rvReviews.setAdapter(hotelReviewsListAdapter);
                tvReviewsCount.setText(reviewList.size() + getString(R.string.reviews));
            }
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            if (reviewList != null) {
                hotelReviewsListAdapter = new HotelReviewsListAdapter(this, reviewList);
                rvReviews.setAdapter(hotelReviewsListAdapter);
                tvReviewsCount.setText(reviewList.size() + getString(R.string.reviews));
            }
        }  else if (isFrom == AppConstant.IS_FROM_PACKAGE) {
            if (reviewList != null) {
                hotelReviewsListAdapter = new HotelReviewsListAdapter(this, reviewList);
                rvReviews.setAdapter(hotelReviewsListAdapter);
                tvReviewsCount.setText(reviewList.size() + getString(R.string.reviews));
            }
        }
    }

    private void setHostViewData() {
        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            if (hostDetail != null) {
                mImageLoader.loadImage(Utility.BASE_URL + "/" + hostDetail.getProfileImage(), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        ivProfilePic.setImageBitmap(loadedImage);
                        progressBarHost.setVisibility(View.GONE);
                    }
                });

                if (hostDetail.getFirstName() != null && hostDetail.getLastName() != null) {
                    tvHostName.setText(hostDetail.getFirstName() + " " + hostDetail.getLastName());
                } else {
                    tvHostName.setText("");
                }

                if (hostDetail.getContactNo() != null) {
                    tvHostContact.setText(hostDetail.getContactNo());
                } else {
                    tvHostContact.setText("");
                }
            }
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            if (hostThingToDoDetail != null) {
                mImageLoader.loadImage(Utility.BASE_URL + "/" + hostThingToDoDetail.getProfileImage(), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        ivProfilePic.setImageBitmap(loadedImage);
                        progressBarHost.setVisibility(View.GONE);
                    }
                });

                if (hostThingToDoDetail.getFirstName() != null && hostThingToDoDetail.getLastName() != null) {
                    tvHostName.setText(hostThingToDoDetail.getFirstName() + " " + hostThingToDoDetail.getLastName());
                } else {
                    tvHostName.setText("");
                }

                if (hostThingToDoDetail.getContactNo() != null) {
                    tvHostContact.setText(hostThingToDoDetail.getContactNo());
                } else {
                    tvHostContact.setText("");
                }
            }
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            if (hostCouponDetail != null) {
                mImageLoader.loadImage(Utility.BASE_URL + "/" + hostCouponDetail.getProfileImage(), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        ivProfilePic.setImageBitmap(loadedImage);
                        progressBarHost.setVisibility(View.GONE);
                    }
                });

                if (hostCouponDetail.getFirstName() != null && hostCouponDetail.getLastName() != null) {
                    tvHostName.setText(hostCouponDetail.getFirstName() + " " + hostCouponDetail.getLastName());
                } else {
                    tvHostName.setText("");
                }

                if (hostCouponDetail.getContactNo() != null) {
                    tvHostContact.setText(hostCouponDetail.getContactNo());
                } else {
                    tvHostContact.setText("");
                }
            }
        }else if (isFrom == AppConstant.IS_FROM_PACKAGE) {
            if (hostThingToDoDetail != null) {
                mImageLoader.loadImage(Utility.BASE_URL + "/" + hostThingToDoDetail.getProfileImage(), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        ivProfilePic.setImageBitmap(loadedImage);
                        progressBarHost.setVisibility(View.GONE);
                    }
                });

                if (hostThingToDoDetail.getFirstName() != null && hostThingToDoDetail.getLastName() != null) {
                    tvHostName.setText(hostThingToDoDetail.getFirstName() + " " + hostThingToDoDetail.getLastName());
                } else {
                    tvHostName.setText("");
                }

                if (hostThingToDoDetail.getContactNo() != null) {
                    tvHostContact.setText(hostThingToDoDetail.getContactNo());
                } else {
                    tvHostContact.setText("");
                }
            }
        }
    }

    private void setPhotoData() {
        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            if (photosList != null && !photosList.isEmpty()) {
                photosListAdapter = new HotelDetailPhotosListAdapter(this, photosList);
                rvHotelPhotos.setAdapter(photosListAdapter);
            }
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            if (photosCouponList != null && !photosCouponList.isEmpty()) {
                photosCouponListAdapter = new CouponDetailPhotosListAdapter(this, photosCouponList);
                rvHotelPhotos.setAdapter(photosCouponListAdapter);
            }
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            if (photosThingToDoList != null && !photosThingToDoList.isEmpty()) {
                photosThingToDoListAdapter = new ThingTodoDetailPhotosListAdapter(this, photosThingToDoList);
                rvHotelPhotos.setAdapter(photosThingToDoListAdapter);
            }
        } else if (isFrom == AppConstant.IS_FROM_PACKAGE) {
            if (photosThingToDoList != null && !photosThingToDoList.isEmpty()) {
                photosThingToDoListAdapter = new ThingTodoDetailPhotosListAdapter(this, photosThingToDoList);
                rvHotelPhotos.setAdapter(photosThingToDoListAdapter);
            }
        }
    }

    private void setInformationData() {
        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            accommodationDetail = hotelDetail;
            setAccommodationInfoData();
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            setThingToDoDetails();
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            setCouponDetails();
        } else if (isFrom == AppConstant.IS_FROM_PACKAGE){
            setPackageDetails();
        }
    }

    private void setAccommodationInfoData() {

        llWhyWithUs.setVisibility(View.GONE);
        viewWhyWithUse.setVisibility(View.GONE);
        viewRequire.setVisibility(View.GONE);
        llAdditionalInfo.setVisibility(View.GONE);
        viewAdditionalInfo.setVisibility(View.GONE);
        llOtherRule.setVisibility(View.GONE);

        if (accommodationDetail != null) {
            amenitiesList = (ArrayList<Amenity>) accommodationDetail.getArrData().getAmenities();
            allowList = accommodationDetail.getArrAllowable();
            pricePerNight = accommodationDetail.getPrice() + "";
            tvPrice.setText(getString(R.string.sar) + " " + accommodationDetail.getPrice() + "");
            tvDescriptionText.setText(accommodationDetail.getArrData().getDescription());
            tvCheckInOutTimeDetail.setText(getString(R.string.check_in_time) + " " + accommodationDetail.getArrData().getAccomodationRule().getCheckIn() + "\n" + getString(R.string.check_out_time) + " " + accommodationDetail.getArrData().getAccomodationRule().getCheckOut());
            String cancellationPolicy = getString(R.string.cancellation_price) + " " + accommodationDetail.getArrData().getCancellationCharge() + getString(R.string.before_hours) + accommodationDetail.getArrData().getCancellationDuration() + " " + getString(R.string.no_refund_before_hours) + " " + accommodationDetail.getArrData().getNoRefundDuration();
            tvCancellationPolicyDetail.setText(cancellationPolicy);

            //Facility and Rooms
            if (accommodationDetail.getArrData().getAmenities() != null && !accommodationDetail.getArrData().getAmenities().isEmpty()) {
                rvRoomFacilities.setVisibility(View.VISIBLE);
                tvNoDataFoundFacility.setVisibility(View.GONE);
                ViewGroup.LayoutParams params = rvRoomFacilities.getLayoutParams();
                if (amenitiesList.size() == 1) {
                    params.height = (int) (getResources().getDimension(R.dimen._24sdp) * amenitiesList.size());
                } else if (amenitiesList.size() % 2 != 0) {
                    params.height = (int) (getResources().getDimension(R.dimen._17sdp) * amenitiesList.size());
                } else {
                    params.height = (int) (getResources().getDimension(R.dimen._15sdp) * amenitiesList.size());
                }
                rvRoomFacilities.setLayoutParams(params);
                gridLayoutManager = new GridLayoutManager(this, 2);
                rvRoomFacilities.setLayoutManager(gridLayoutManager);
                facilitiesListAdapter = new FacilitiesListAdapter(this, amenitiesList);
                rvRoomFacilities.setAdapter(facilitiesListAdapter);
            } else {
                rvRoomFacilities.setVisibility(View.GONE);
                tvNoDataFoundFacility.setVisibility(View.VISIBLE);
            }

            //Allowed List Data
            if (accommodationDetail.getArrAllowable() != null && !accommodationDetail.getArrAllowable().isEmpty()) {
                rvAllowed.setVisibility(View.VISIBLE);
                tvNoDataFoundAllowed.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvAllowed.getLayoutParams();
                rvAllowed.setLayoutParams(parms);
                if (accommodationDetail.getArrAllowable().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._24sdp) * accommodationDetail.getArrAllowable().size());
                } else if (accommodationDetail.getArrAllowable().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._17sdp) * accommodationDetail.getArrAllowable().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * accommodationDetail.getArrAllowable().size());
                }
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvAllowed.setLayoutManager(gridLayoutManager);
                AllowedListAdapter allowedListAdapter = new AllowedListAdapter(this, accommodationDetail.getArrAllowable());
                rvAllowed.setAdapter(allowedListAdapter);
            } else {
                rvAllowed.setVisibility(View.GONE);
                tvNoDataFoundAllowed.setVisibility(View.VISIBLE);
            }

            //Not Allowed List Data
            if (accommodationDetail.getArrNotAllowable() != null && !accommodationDetail.getArrNotAllowable().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotAllowed.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvNotInclude.getLayoutParams();
                if (accommodationDetail.getArrNotAllowable().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * accommodationDetail.getArrNotAllowable().size());
                    rvNotAllowed.setLayoutParams(parms);
                } else if (accommodationDetail.getArrNotAllowable().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * accommodationDetail.getArrNotAllowable().size());
                    rvNotAllowed.setLayoutParams(parms);
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * accommodationDetail.getArrNotAllowable().size());
                    rvNotAllowed.setLayoutParams(parms);
                }

                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvNotAllowed.setLayoutManager(gridLayoutManager);
                NotAllowedListAdapter notAllowedListAdapter = new NotAllowedListAdapter(this, accommodationDetail.getArrNotAllowable());
                rvNotAllowed.setAdapter(notAllowedListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotAllowed.setVisibility(View.VISIBLE);
            }

            //Include List Data
            if (accommodationDetail.getArrIncluded() != null && !accommodationDetail.getArrIncluded().isEmpty()) {
                rvInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundInclude.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvInclude.getLayoutParams();
                if (accommodationDetail.getArrIncluded().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * accommodationDetail.getArrIncluded().size());
                } else if (accommodationDetail.getArrIncluded().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * accommodationDetail.getArrIncluded().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * accommodationDetail.getArrIncluded().size());
                }
                rvInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvInclude.setLayoutManager(gridLayoutManager);
                IncludeListAdapter includeListAdapter = new IncludeListAdapter(this, accommodationDetail.getArrIncluded());
                rvInclude.setAdapter(includeListAdapter);
            } else {
                rvInclude.setVisibility(View.GONE);
                tvNoDataFoundInclude.setVisibility(View.VISIBLE);
            }

            //Not Include List Data
            if (accommodationDetail.getArrNotIncluded() != null && !accommodationDetail.getArrNotIncluded().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotInclude.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvNotInclude.getLayoutParams();
                if (accommodationDetail.getArrNotIncluded().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * accommodationDetail.getArrNotIncluded().size());
                } else if (accommodationDetail.getArrNotIncluded().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * accommodationDetail.getArrNotIncluded().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * accommodationDetail.getArrNotIncluded().size());
                }
                rvNotInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvNotInclude.setLayoutManager(gridLayoutManager);
                NotIncludeListAdapter notIncludeListAdapter = new NotIncludeListAdapter(this, accommodationDetail.getArrNotIncluded());
                rvNotInclude.setAdapter(notIncludeListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }

            //Require List Data
            if (accommodationDetail.getArrRequirment() != null && !accommodationDetail.getArrRequirment().isEmpty()) {
                rvRequirement.setVisibility(View.VISIBLE);
                tvNoDataFoundRequirement.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvRequirement.getLayoutParams();
                if (accommodationDetail.getArrRequirment().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * accommodationDetail.getArrRequirment().size());
                } else if (accommodationDetail.getArrRequirment().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * accommodationDetail.getArrRequirment().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * accommodationDetail.getArrRequirment().size());
                }
                rvRequirement.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvRequirement.setLayoutManager(gridLayoutManager);
                RequireListAdapter requireListAdapter = new RequireListAdapter(this, accommodationDetail.getArrRequirment());
                rvRequirement.setAdapter(requireListAdapter);
            } else {
                rvRequirement.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setThingToDoDetails() {
        if (thingToDoDetail != null) {
            allowList = thingToDoDetail.getArrAllowable();
            pricePerNight = thingToDoDetail.getPrice() + "";
            tvPrice.setText(getString(R.string.sar) + " " + thingToDoDetail.getPrice() + "");
            tvDescriptionText.setText(thingToDoDetail.getArrData().getDescription());
            //tvCheckInOutTimeDetail.setText("Check in Time : " + thingToDoDetail.getArrData().getAccomodationRule().getCheckIn() + "\nCheck out Time : " + thingToDoDetail.getArrData().getAccomodationRule().getCheckOut());
            String cancellationPolicy = getString(R.string.cancellation_price) + thingToDoDetail.getArrData().getCancellationCharge() + " " + getString(R.string.before_hours) + " " + thingToDoDetail.getArrData().getCancellationDuration() + " " + getString(R.string.no_refund_before_hours) + " " + thingToDoDetail.getArrData().getNoRefundDuration();
            tvCancellationPolicyDetail.setText(cancellationPolicy);

            if (thingToDoDetail.getArrData().getAdditionalInformation() != null) {
                llAdditionalInfo.setVisibility(View.VISIBLE);
                tvAdditionalInfoDetails.setText(thingToDoDetail.getArrData().getAdditionalInformation());
            } else {
                llAdditionalInfo.setVisibility(View.GONE);
            }

            if (thingToDoDetail.getArrData().getOtherRules() != null) {
                tvOtherRuleDetails.setText(thingToDoDetail.getArrData().getOtherRules() + "");
            } else {
                tvOtherRuleDetails.setText(R.string.no_rules);
            }

            llRoomsAndFacility.setVisibility(View.GONE);
            viewDividerRoomAndFacility.setVisibility(View.GONE);

            llCheckInOutTime.setVisibility(View.GONE);
            viewCheckInOut.setVisibility(View.GONE);

            llWhyWithUs.setVisibility(View.VISIBLE);
            tvWhyWithUsDetail.setText(thingToDoDetail.getArrData().getWhyYou());
            viewWhyWithUse.setVisibility(View.VISIBLE);

            //Allowed List Data
            if (thingToDoDetail.getArrAllowable() != null && !thingToDoDetail.getArrAllowable().isEmpty()) {
                rvAllowed.setVisibility(View.VISIBLE);
                tvNoDataFoundAllowed.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvAllowed.getLayoutParams();
                if (thingToDoDetail.getArrAllowable().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * thingToDoDetail.getArrAllowable().size());
                } else if (thingToDoDetail.getArrAllowable().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * thingToDoDetail.getArrAllowable().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * thingToDoDetail.getArrAllowable().size());
                }
                rvAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvAllowed.setLayoutManager(gridLayoutManager);
                AllowedListAdapter allowedListAdapter = new AllowedListAdapter(this, thingToDoDetail.getArrAllowable());
                rvAllowed.setAdapter(allowedListAdapter);
            } else {
                rvAllowed.setVisibility(View.GONE);
                tvNoDataFoundAllowed.setVisibility(View.VISIBLE);
            }

            //Not Allowed List Data
            if (thingToDoDetail.getArrNotAllowable() != null && !thingToDoDetail.getArrNotAllowable().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotAllowed.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvNotInclude.getLayoutParams();
                if (thingToDoDetail.getArrNotAllowable().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * thingToDoDetail.getArrNotAllowable().size());
                } else if (thingToDoDetail.getArrNotAllowable().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * thingToDoDetail.getArrNotAllowable().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * thingToDoDetail.getArrNotAllowable().size());
                }
                rvNotAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvNotAllowed.setLayoutManager(gridLayoutManager);
                NotAllowedListAdapter notAllowedListAdapter = new NotAllowedListAdapter(this, thingToDoDetail.getArrNotAllowable());
                rvNotAllowed.setAdapter(notAllowedListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotAllowed.setVisibility(View.VISIBLE);
            }

            //Include List Data
            if (thingToDoDetail.getArrIncluded() != null && !thingToDoDetail.getArrIncluded().isEmpty()) {
                rvInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundInclude.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvInclude.getLayoutParams();
                if (thingToDoDetail.getArrIncluded().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * thingToDoDetail.getArrIncluded().size());
                } else if (thingToDoDetail.getArrIncluded().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * thingToDoDetail.getArrIncluded().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * thingToDoDetail.getArrIncluded().size());
                }
                rvInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvInclude.setLayoutManager(gridLayoutManager);
                IncludeListAdapter includeListAdapter = new IncludeListAdapter(this, thingToDoDetail.getArrIncluded());
                rvInclude.setAdapter(includeListAdapter);
            } else {
                rvInclude.setVisibility(View.GONE);
                tvNoDataFoundInclude.setVisibility(View.VISIBLE);
            }

            //Not Include List Data
            if (thingToDoDetail.getArrNotIncluded() != null && !thingToDoDetail.getArrNotIncluded().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotInclude.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvNotInclude.getLayoutParams();
                if (thingToDoDetail.getArrNotIncluded().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * thingToDoDetail.getArrNotIncluded().size());
                } else if (thingToDoDetail.getArrNotIncluded().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * thingToDoDetail.getArrNotIncluded().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * thingToDoDetail.getArrNotIncluded().size());
                }
                rvNotInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvNotInclude.setLayoutManager(gridLayoutManager);
                NotIncludeListAdapter notIncludeListAdapter = new NotIncludeListAdapter(this, thingToDoDetail.getArrNotIncluded());
                rvNotInclude.setAdapter(notIncludeListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }

            //Require List Data
            if (thingToDoDetail.getArrRequirment() != null && !thingToDoDetail.getArrRequirment().isEmpty()) {
                rvRequirement.setVisibility(View.VISIBLE);
                tvNoDataFoundRequirement.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvRequirement.getLayoutParams();
                if (thingToDoDetail.getArrRequirment().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * thingToDoDetail.getArrRequirment().size());
                } else if (thingToDoDetail.getArrRequirment().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * thingToDoDetail.getArrRequirment().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * thingToDoDetail.getArrRequirment().size());
                }
                rvRequirement.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvRequirement.setLayoutManager(gridLayoutManager);
                RequireListAdapter requireListAdapter = new RequireListAdapter(this, thingToDoDetail.getArrRequirment());
                rvRequirement.setAdapter(requireListAdapter);
            } else {
                rvRequirement.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setCouponDetails() {
        if (couponDetail != null) {
            tvDescriptionText.setText(couponDetail.getArrData().getDescription());
            allowList = couponDetail.getArrData().getArrAllowable();
            llCancellationPolicy.setVisibility(View.GONE);
            llCheckInOutTime.setVisibility(View.GONE);
            viewCheckInOut.setVisibility(View.GONE);
            llRoomsAndFacility.setVisibility(View.GONE);
            llAdditionalInfo.setVisibility(View.GONE);
            viewAdditionalInfo.setVisibility(View.GONE);
            llOtherRule.setVisibility(View.GONE);
            llWhyWithUs.setVisibility(View.GONE);
            viewWhyWithUse.setVisibility(View.GONE);

 /*           llAllowed.setVisibility(View.GONE);
            viewAllowDivider.setVisibility(View.GONE);
            llNotAllowed.setVisibility(View.GONE);
            viewNotAllowDivider.setVisibility(View.GONE);*/
            //Allowed List Data
            if (couponDetail.getArrData().getArrAllowable() != null && !couponDetail.getArrData().getArrAllowable().isEmpty()) {
                rvAllowed.setVisibility(View.VISIBLE);
                tvNoDataFoundAllowed.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvAllowed.getLayoutParams();
                if (allowList.size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * allowList.size());
                } else if (allowList.size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * allowList.size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * allowList.size());
                }
                rvAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvAllowed.setLayoutManager(gridLayoutManager);
                AllowedListAdapter allowedListAdapter = new AllowedListAdapter(this, allowList);
                rvAllowed.setAdapter(allowedListAdapter);
            } else {
                rvAllowed.setVisibility(View.GONE);
                tvNoDataFoundAllowed.setVisibility(View.VISIBLE);
            }

            //Not Allowed List Data
            if (couponDetail.getArrData().getArrNotAllowable() != null && !couponDetail.getArrData().getArrNotAllowable().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotAllowed.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvNotInclude.getLayoutParams();
                if (couponDetail.getArrData().getArrNotAllowable().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * couponDetail.getArrData().getArrNotAllowable().size());
                } else if (couponDetail.getArrData().getArrNotAllowable().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * couponDetail.getArrData().getArrNotAllowable().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * couponDetail.getArrData().getArrNotAllowable().size());
                }
                rvNotAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvNotAllowed.setLayoutManager(gridLayoutManager);
                NotAllowedListAdapter notAllowedListAdapter = new NotAllowedListAdapter(this, couponDetail.getArrData().getArrNotAllowable());
                rvNotAllowed.setAdapter(notAllowedListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotAllowed.setVisibility(View.VISIBLE);
            }

            //Include List Data
            if (couponDetail.getArrData().getArrIncluded() != null && !couponDetail.getArrData().getArrIncluded().isEmpty()) {
                rvInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundInclude.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                if (couponDetail.getArrData().getArrIncluded().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * couponDetail.getArrData().getArrIncluded().size());
                } else if (couponDetail.getArrData().getArrIncluded().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * couponDetail.getArrData().getArrIncluded().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * couponDetail.getArrData().getArrIncluded().size());
                }
                rvInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvInclude.setLayoutManager(gridLayoutManager);
                IncludeListAdapter includeListAdapter = new IncludeListAdapter(this, couponDetail.getArrData().getArrIncluded());
                rvInclude.setAdapter(includeListAdapter);
            } else {
                rvInclude.setVisibility(View.GONE);
                tvNoDataFoundInclude.setVisibility(View.VISIBLE);
            }

            //Not Include List Data
            rvNotInclude.setVisibility(View.GONE);
            tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            /*if (couponDetail.getArrData().getArrNotIncluded() != null && !couponDetail.getArrNotIncluded().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotInclude.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * couponDetail.getArrNotIncluded().size());
                rvNotInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvNotInclude.setLayoutManager(gridLayoutManager);
                NotIncludeListAdapter notIncludeListAdapter = new NotIncludeListAdapter(getActivity(), couponDetail.getArrNotIncluded());
                rvNotInclude.setAdapter(notIncludeListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }*/

            //Require List Data
            rvRequirement.setVisibility(View.GONE);
            tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            /*if (couponDetail.getArrRequirment() != null && !couponDetail.getArrRequirment().isEmpty()) {
                rvRequirement.setVisibility(View.VISIBLE);
                tvNoDataFoundRequirement.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * couponDetail.getArrRequirment().size());
                rvRequirement.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvRequirement.setLayoutManager(gridLayoutManager);
                RequireListAdapter requireListAdapter = new RequireListAdapter(getActivity(), couponDetail.getArrRequirment());
                rvRequirement.setAdapter(requireListAdapter);
            } else {
                rvRequirement.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }*/
        }
    }

    private void setPackageDetails() {
        if (packageDetail != null) {
            allowList = packageDetail.getArrAllowable();
            pricePerNight = packageDetail.getPrice() + "";
            tvPrice.setText(getString(R.string.sar) + " " + packageDetail.getPrice() + "");
            tvDescriptionText.setText(packageDetail.getArrData().getDescription());
            //tvCheckInOutTimeDetail.setText("Check in Time : " + packageDetail.getArrData().getAccomodationRule().getCheckIn() + "\nCheck out Time : " + packageDetail.getArrData().getAccomodationRule().getCheckOut());
            String cancellationPolicy = getString(R.string.cancellation_price) + packageDetail.getArrData().getCancellationCharge() + " " + getString(R.string.before_hours) + " " + packageDetail.getArrData().getCancellationDuration() + " " + getString(R.string.no_refund_before_hours) + " " + packageDetail.getArrData().getNoRefundDuration();
            tvCancellationPolicyDetail.setText(cancellationPolicy);

            if (packageDetail.getArrData().getAdditionalInformation() != null) {
                llAdditionalInfo.setVisibility(View.VISIBLE);
                tvAdditionalInfoDetails.setText(packageDetail.getArrData().getAdditionalInformation());
            } else {
                llAdditionalInfo.setVisibility(View.GONE);
            }

            if (packageDetail.getArrData().getOtherRules() != null) {
                tvOtherRuleDetails.setText(packageDetail.getArrData().getOtherRules() + "");
            } else {
                tvOtherRuleDetails.setText(R.string.no_rules);
            }

            llRoomsAndFacility.setVisibility(View.GONE);
            viewDividerRoomAndFacility.setVisibility(View.GONE);

            llCheckInOutTime.setVisibility(View.GONE);
            viewCheckInOut.setVisibility(View.GONE);

            llWhyWithUs.setVisibility(View.VISIBLE);
            tvWhyWithUsDetail.setText(packageDetail.getArrData().getWhyYou());
            viewWhyWithUse.setVisibility(View.VISIBLE);

            llPlaces.setVisibility(View.VISIBLE);

            //Places List Data
            if (packageDetail.getArrData().getPlaces() != null && !packageDetail.getArrData().getPlaces().isEmpty()) {
                rvPlaces.setVisibility(View.VISIBLE);
                tvNoDataFoundPlaces.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvPlaces.getLayoutParams();
                if (packageDetail.getArrData().getPlaces().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * packageDetail.getArrData().getPlaces().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * packageDetail.getArrData().getPlaces().size());
                }
                rvPlaces.setLayoutParams(parms);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                rvPlaces.setLayoutManager(linearLayoutManager);
                PlacesListAdapter placesListAdapter = new PlacesListAdapter(this,packageDetail.getArrData().getPlaces());
                rvPlaces.setAdapter(placesListAdapter);
            } else {
                rvPlaces.setVisibility(View.GONE);
                tvNoDataFoundPlaces.setVisibility(View.VISIBLE);
            }

            //Allowed List Data
            if (packageDetail.getArrAllowable() != null && !packageDetail.getArrAllowable().isEmpty()) {
                rvAllowed.setVisibility(View.VISIBLE);
                tvNoDataFoundAllowed.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvAllowed.getLayoutParams();
                if (packageDetail.getArrAllowable().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * packageDetail.getArrAllowable().size());
                } else if (packageDetail.getArrAllowable().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * packageDetail.getArrAllowable().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * packageDetail.getArrAllowable().size());
                }
                rvAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvAllowed.setLayoutManager(gridLayoutManager);
                AllowedListAdapter allowedListAdapter = new AllowedListAdapter(this, packageDetail.getArrAllowable());
                rvAllowed.setAdapter(allowedListAdapter);
            } else {
                rvAllowed.setVisibility(View.GONE);
                tvNoDataFoundAllowed.setVisibility(View.VISIBLE);
            }

            //Not Allowed List Data
            if (packageDetail.getArrNotAllowable() != null && !packageDetail.getArrNotAllowable().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotAllowed.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvNotInclude.getLayoutParams();
                if (packageDetail.getArrNotAllowable().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * packageDetail.getArrNotAllowable().size());
                } else if (packageDetail.getArrNotAllowable().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * packageDetail.getArrNotAllowable().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * packageDetail.getArrNotAllowable().size());
                }
                rvNotAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvNotAllowed.setLayoutManager(gridLayoutManager);
                NotAllowedListAdapter notAllowedListAdapter = new NotAllowedListAdapter(this, packageDetail.getArrNotAllowable());
                rvNotAllowed.setAdapter(notAllowedListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotAllowed.setVisibility(View.VISIBLE);
            }

            //Include List Data
            if (packageDetail.getArrIncluded() != null && !packageDetail.getArrIncluded().isEmpty()) {
                rvInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundInclude.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvInclude.getLayoutParams();
                if (packageDetail.getArrIncluded().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * packageDetail.getArrIncluded().size());
                } else if (packageDetail.getArrIncluded().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * packageDetail.getArrIncluded().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * packageDetail.getArrIncluded().size());
                }
                rvInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvInclude.setLayoutManager(gridLayoutManager);
                IncludeListAdapter includeListAdapter = new IncludeListAdapter(this, packageDetail.getArrIncluded());
                rvInclude.setAdapter(includeListAdapter);
            } else {
                rvInclude.setVisibility(View.GONE);
                tvNoDataFoundInclude.setVisibility(View.VISIBLE);
            }

            //Not Include List Data
            if (packageDetail.getArrNotIncluded() != null && !packageDetail.getArrNotIncluded().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotInclude.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvNotInclude.getLayoutParams();
                if (packageDetail.getArrNotIncluded().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * packageDetail.getArrNotIncluded().size());
                } else if (packageDetail.getArrNotIncluded().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * packageDetail.getArrNotIncluded().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * packageDetail.getArrNotIncluded().size());
                }
                rvNotInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvNotInclude.setLayoutManager(gridLayoutManager);
                NotIncludeListAdapter notIncludeListAdapter = new NotIncludeListAdapter(this, packageDetail.getArrNotIncluded());
                rvNotInclude.setAdapter(notIncludeListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }

            //Require List Data
            if (packageDetail.getArrRequirment() != null && !packageDetail.getArrRequirment().isEmpty()) {
                rvRequirement.setVisibility(View.VISIBLE);
                tvNoDataFoundRequirement.setVisibility(View.GONE);
                ViewGroup.LayoutParams parms = rvRequirement.getLayoutParams();
                if (packageDetail.getArrRequirment().size() == 1) {
                    parms.height = (int) (getResources().getDimension(R.dimen._25sdp) * packageDetail.getArrRequirment().size());
                } else if (packageDetail.getArrRequirment().size() % 2 != 0) {
                    parms.height = (int) (getResources().getDimension(R.dimen._20sdp) * packageDetail.getArrRequirment().size());
                } else {
                    parms.height = (int) (getResources().getDimension(R.dimen._15sdp) * packageDetail.getArrRequirment().size());
                }
                rvRequirement.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                rvRequirement.setLayoutManager(gridLayoutManager);
                RequireListAdapter requireListAdapter = new RequireListAdapter(this, packageDetail.getArrRequirment());
                rvRequirement.setAdapter(requireListAdapter);
            } else {
                rvRequirement.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }
        }
    }

    public void openAddReviewDialog() {
        final Dialog popupWindowDialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar);

        final View layout = LayoutInflater.from(this).inflate(R.layout.dialog_add_review, null);
        popupWindowDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (popupWindowDialog.getWindow() != null) {
            popupWindowDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        }
        popupWindowDialog.setContentView(layout);
        popupWindowDialog.setCancelable(false);

        ImageView ivClose = (ImageView) layout.findViewById(R.id.ivClose);
        final EditText evFullName = layout.findViewById(R.id.evFullName);
        final EditText evReviewMessage = layout.findViewById(R.id.evReviewMessage);
        final RatingBar ratingBarReviewStar = layout.findViewById(R.id.ratingBarReviewStar);
        TextView tvPostReview = layout.findViewById(R.id.tvPostReview);

        tvPostReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (evFullName.getText().toString().trim().length() == 0) {
                    Utility.showError(getString(R.string.valid_full_name));
                } else if (evReviewMessage.getText().toString().trim().length() == 0) {
                    Utility.showError(getString(R.string.valid_message));
                } else if (ratingBarReviewStar.getRating() == 0) {
                    Utility.showError(getString(R.string.valid_ratting));
                } else {
                    popupWindowDialog.dismiss();

                    reviewName = evFullName.getText().toString().trim();
                    reviewMessage = evReviewMessage.getText().toString().trim();
                    reviewRatting = ratingBarReviewStar.getRating();

                    if (!PreferenceData.isLogin()) {
                        Intent intent = new Intent(HotelDetailsActivity.this, SignInActivity.class);
                        intent.putExtra(AppConstant.EXT_IS_FROM, AppConstant.IS_FROM_ADD_REVIEW);
                        startActivityForResult(intent, LOGIN_REQUEST);
                    } else {
                        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                            addAccommodationReview(ratingBarReviewStar.getRating() + "", evFullName.getText().toString().trim(), evReviewMessage.getText().toString().trim());
                        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                            addCouponReview(ratingBarReviewStar.getRating() + "", evFullName.getText().toString().trim(), evReviewMessage.getText().toString().trim());
                        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                            addThingToDoReview(ratingBarReviewStar.getRating() + "", evFullName.getText().toString().trim(), evReviewMessage.getText().toString().trim());
                        }
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

    private void addAccommodationReview(String rating, String name, String message) {
        try {
            if (!Utility.isNetworkAvailable(this)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.addReviewAccommodation(Utility.getLocale(),
                        PreferenceData.getUserData().getId() + "",
                        hotelId, rating, message, name);
                call.enqueue(new Callback<CommonRequestResponse>() {
                    @Override
                    public void onResponse(Call<CommonRequestResponse> call, Response<CommonRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                Utility.showError(response.body().getMessage());

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

    private void addCouponReview(String rating, String name, String message) {
        try {
            if (!Utility.isNetworkAvailable(this)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.addReviewCoupon(Utility.getLocale(),
                        PreferenceData.getUserData().getId() + "",
                        hotelId, rating, message, name);
                call.enqueue(new Callback<CommonRequestResponse>() {
                    @Override
                    public void onResponse(Call<CommonRequestResponse> call, Response<CommonRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                Utility.showError(response.body().getMessage());

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

    private void addThingToDoReview(String rating, String name, String message) {
        try {
            if (!Utility.isNetworkAvailable(this)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.addReviewThingToDo(Utility.getLocale(),
                        PreferenceData.getUserData().getId() + "",
                        hotelId, rating, message, name);
                call.enqueue(new Callback<CommonRequestResponse>() {
                    @Override
                    public void onResponse(Call<CommonRequestResponse> call, Response<CommonRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                Utility.showError(response.body().getMessage());

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

    private void addPackageReview(String rating, String name, String message) {
        try {
            if (!Utility.isNetworkAvailable(this)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.addPackage(Utility.getLocale(),
                        PreferenceData.getUserData().getId() + "",
                        hotelId, rating, message, name);
                call.enqueue(new Callback<CommonRequestResponse>() {
                    @Override
                    public void onResponse(Call<CommonRequestResponse> call, Response<CommonRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                                Utility.showError(response.body().getMessage());

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

    private void getAccommodationReviewList() {
        try {
            if (!Utility.isNetworkAvailable(this)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<ReviewsRequestResponse> call = service.getAccommodationReviewList(Utility.getLocale(), WebUtility.ACCOMMODATION_REVIEW_LIST
                        + "accomodation_id=" + id);
                call.enqueue(new Callback<ReviewsRequestResponse>() {
                    @Override
                    public void onResponse(Call<ReviewsRequestResponse> call, Response<ReviewsRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {



                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<ReviewsRequestResponse> call, Throwable t) {
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

    private void getThingToDoReviewList() {
        try {
            if (!Utility.isNetworkAvailable(this)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<ReviewsRequestResponse> call = service.getAccommodationReviewList(Utility.getLocale(), WebUtility.THING_TO_DO_REVIEW_LIST
                        + "thingtodo_id=" + id);
                call.enqueue(new Callback<ReviewsRequestResponse>() {
                    @Override
                    public void onResponse(Call<ReviewsRequestResponse> call, Response<ReviewsRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {


                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<ReviewsRequestResponse> call, Throwable t) {
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

    private void getCouponReviewList() {
        try {
            if (!Utility.isNetworkAvailable(this)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<ReviewsRequestResponse> call = service.getAccommodationReviewList(Utility.getLocale(), WebUtility.COUPON_REVIEW_LIST
                        + "coupon_id=" + id);
                call.enqueue(new Callback<ReviewsRequestResponse>() {
                    @Override
                    public void onResponse(Call<ReviewsRequestResponse> call, Response<ReviewsRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {


                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(response.body().getMsg());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<ReviewsRequestResponse> call, Throwable t) {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int isResultFrom = 0;
        if (requestCode == LOGIN_REQUEST) {
            if (data != null) {
                isResultFrom = data.getIntExtra(AppConstant.EXT_IS_FROM, 0);
                if (isResultFrom == AppConstant.IS_FROM_ADD_REVIEW) {
                    if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                        addAccommodationReview(reviewRatting + "", reviewName, reviewMessage);
                    } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                        addCouponReview(reviewRatting + "", reviewName, reviewMessage);
                    } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                        addThingToDoReview(reviewRatting + "", reviewName, reviewMessage);
                    } else if (isFrom == AppConstant.IS_FROM_EVENT) {

                    } else if (isFrom == AppConstant.IS_FROM_TRANSPORTATION) {

                    } else if (isFrom == AppConstant.IS_FROM_PACKAGE) {
                        addPackageReview(reviewRatting + "", reviewName, reviewMessage);
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
