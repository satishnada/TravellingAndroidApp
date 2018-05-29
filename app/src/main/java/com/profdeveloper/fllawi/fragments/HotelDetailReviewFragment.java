package com.profdeveloper.fllawi.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.HotelDetailsActivity;
import com.profdeveloper.fllawi.activities.SignInActivity;
import com.profdeveloper.fllawi.adapters.HotelReviewsListAdapter;
import com.profdeveloper.fllawi.model.ArrReview;
import com.profdeveloper.fllawi.model.CalculatedPriceRequestResponse;
import com.profdeveloper.fllawi.model.CommonRequestResponse;
import com.profdeveloper.fllawi.model.LoginRequestResponse;
import com.profdeveloper.fllawi.model.Reviews.ReviewsRequestResponse;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.retrofit.WebUtility;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.SharedPreferenceUtil;
import com.profdeveloper.fllawi.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelDetailReviewFragment extends Fragment {

    private RecyclerView rvReviews;
    private HotelReviewsListAdapter hotelReviewsListAdapter;
    private LinearLayout llContinueBooking;
    private ArrayList<ArrReview> reviewAccommodationList = new ArrayList<>();
    private RatingBar rattingAllReview;
    private String pricePerNight = "";
    private TextView tvPrice, tvReviewsCount, tvAddRatting;
    private int isFrom = 1;
    private String id;

    public HotelDetailReviewFragment() {
    }

    public static Fragment getInstance(int isFrom,String id, ArrayList<ArrReview> reviews, String pricePerNight) {
        HotelDetailReviewFragment reviewFragment = new HotelDetailReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_IS_FROM, isFrom);
        bundle.putSerializable(AppConstant.EXT_ACCOMMODATION_ID, id);
        bundle.putSerializable(AppConstant.EXT_HOTEL_REVIEWS, reviews);
        bundle.putString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT, pricePerNight);
        reviewFragment.setArguments(bundle);
        return reviewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotel_review, container, false);

        tvReviewsCount = view.findViewById(R.id.tvReviewsCount);
        rvReviews = view.findViewById(R.id.rvReviews);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvAddRatting = view.findViewById(R.id.tvAddRatting);
        rvReviews.setLayoutManager(Utility.getLayoutManager(getActivity()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
            id = bundle.getString(AppConstant.EXT_ACCOMMODATION_ID);
            reviewAccommodationList = (ArrayList<ArrReview>) bundle.getSerializable(AppConstant.EXT_HOTEL_REVIEWS);
            if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);
                if (reviewAccommodationList != null) {
                    tvPrice.setText("$ " + pricePerNight);
                    hotelReviewsListAdapter = new HotelReviewsListAdapter(getActivity(), reviewAccommodationList);
                    rvReviews.setAdapter(hotelReviewsListAdapter);
                    tvReviewsCount.setText(reviewAccommodationList.size() + " Reviews");
                }
            } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);
                if (reviewAccommodationList != null) {
                    tvPrice.setText("$ " + pricePerNight);
                    hotelReviewsListAdapter = new HotelReviewsListAdapter(getActivity(), reviewAccommodationList);
                    rvReviews.setAdapter(hotelReviewsListAdapter);
                    tvReviewsCount.setText(reviewAccommodationList.size() + " Reviews");
                }
            } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);
                if (reviewAccommodationList != null) {
                    tvPrice.setText("$ " + pricePerNight);
                    hotelReviewsListAdapter = new HotelReviewsListAdapter(getActivity(), reviewAccommodationList);
                    rvReviews.setAdapter(hotelReviewsListAdapter);
                    tvReviewsCount.setText(reviewAccommodationList.size() + " Reviews");
                }
            }
        }

        llContinueBooking = view.findViewById(R.id.llContinueBooking);

        llContinueBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HotelDetailsActivity) getActivity()).onBookingClick(isFrom);
            }
        });

        tvAddRatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddReviewDialog();
            }
        });

        return view;
    }

    public void openAddReviewDialog() {
        final Dialog popupWindowDialog = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar);

        final View layout = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_review, null);
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
        TextView tvNo = layout.findViewById(R.id.tvNo);

        tvPostReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (evFullName.getText().toString().trim().length() == 0) {
                    Utility.showError("Please enter your full name");
                } else if (evReviewMessage.getText().toString().trim().length() == 0) {
                    Utility.showError("Please enter your message");
                } else if (ratingBarReviewStar.getRating() == 0) {
                    Utility.showError("Please select ratting");
                } else {
                    popupWindowDialog.dismiss();
                   if (isFrom == AppConstant.IS_FROM_ACCOMMODATION){
                       addAccommodationReview(ratingBarReviewStar.getRating()+"",evFullName.getText().toString().trim(),evReviewMessage.getText().toString().trim());
                   }else if (isFrom == AppConstant.IS_FROM_COUPON){
                       addCouponReview(ratingBarReviewStar.getRating()+"",evFullName.getText().toString().trim(),evReviewMessage.getText().toString().trim());
                   }else if (isFrom == AppConstant.IS_FROM_THING_TO_DO){
                       addThingToDoReview(ratingBarReviewStar.getRating()+"",evFullName.getText().toString().trim(),evReviewMessage.getText().toString().trim());
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

    private void getAccommodationReviewList() {
        try {
            if (!Utility.isNetworkAvailable(getActivity())) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(getActivity());

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<ReviewsRequestResponse> call = service.getAccommodationReviewList(Utility.getLocale(),WebUtility.ACCOMMODATION_REVIEW_LIST
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
            if (!Utility.isNetworkAvailable(getActivity())) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(getActivity());

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<ReviewsRequestResponse> call = service.getAccommodationReviewList(Utility.getLocale(),WebUtility.THING_TO_DO_REVIEW_LIST
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
            if (!Utility.isNetworkAvailable(getActivity())) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(getActivity());

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<ReviewsRequestResponse> call = service.getAccommodationReviewList(Utility.getLocale(),WebUtility.COUPON_REVIEW_LIST
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

    private void addAccommodationReview(String rating, String name, String message) {
        try {
            if (!Utility.isNetworkAvailable(getActivity())) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(getActivity());
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.addReviewAccommodation(Utility.getLocale(),
                        PreferenceData.getUserData().getId() + "",
                        id, rating, message, name);
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
            if (!Utility.isNetworkAvailable(getActivity())) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(getActivity());
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.addReviewCoupon(Utility.getLocale(),
                        PreferenceData.getUserData().getId() +"",
                        id, rating, message, name);
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
            if (!Utility.isNetworkAvailable(getActivity())) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(getActivity());
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.addReviewThingToDo(Utility.getLocale(),
                        PreferenceData.getUserData().getId() +"",
                        id, rating, message, name);
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

}
