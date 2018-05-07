package com.moderndeveloper.fllawi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.activities.HotelDetailsActivity;
import com.moderndeveloper.fllawi.adapters.HotelDetailPhotosListAdapter;
import com.moderndeveloper.fllawi.adapters.HotelReviewsListAdapter;
import com.moderndeveloper.fllawi.model.AccommodationDetails.ArrReview;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.Utility;

import java.util.ArrayList;

public class HotelDetailReviewFragment extends Fragment {

    private RecyclerView rvReviews;
    private HotelReviewsListAdapter hotelReviewsListAdapter;
    private LinearLayout llContinueBooking;
    private ArrayList<ArrReview> reviewList = new ArrayList<>();
    private RatingBar rattingAllReview;
    private String pricePerNight = "";
    private TextView tvPrice,tvReviewsCount;

    public HotelDetailReviewFragment() {
    }

    public static Fragment getInstance(ArrayList<ArrReview> reviews,String pricePerNight){
        HotelDetailReviewFragment reviewFragment = new HotelDetailReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_HOTEL_REVIEWS,reviews);
        bundle.putString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT,pricePerNight);
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

        Bundle bundle = getArguments();
        if (bundle != null){
            reviewList = (ArrayList<ArrReview>) bundle.getSerializable(AppConstant.EXT_HOTEL_REVIEWS);
            pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);
        }

        if (reviewList != null){
            rvReviews = view.findViewById(R.id.rvReviews);
            tvPrice = view.findViewById(R.id.tvPrice);
            tvPrice.setText("$ "+pricePerNight);
            rvReviews.setLayoutManager(Utility.getLayoutManager(getActivity()));
            hotelReviewsListAdapter = new HotelReviewsListAdapter(getActivity(),reviewList);
            rvReviews.setAdapter(hotelReviewsListAdapter);
            tvReviewsCount.setText(reviewList.size()+" Reviews");
        }

        llContinueBooking = view.findViewById(R.id.llContinueBooking);

        llContinueBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HotelDetailsActivity)getActivity()).onBookingClick();
            }
        });

        return view;
    }

}
