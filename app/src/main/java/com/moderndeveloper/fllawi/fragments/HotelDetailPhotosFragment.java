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
import android.widget.TextView;

import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.activities.HotelDetailsActivity;
import com.moderndeveloper.fllawi.adapters.HotelDetailPhotosListAdapter;
import com.moderndeveloper.fllawi.adapters.HotelReviewsListAdapter;
import com.moderndeveloper.fllawi.model.AccommodationDetails.Gallery;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.Utility;

import java.util.ArrayList;

public class HotelDetailPhotosFragment extends Fragment {

    private RecyclerView rvHotelPhotos;
    private GridLayoutManager gridLayoutManager;
    private HotelDetailPhotosListAdapter photosListAdapter;
    private ArrayList<Gallery> photosList = new ArrayList<>();
    private String pricePerNight = "";
    private TextView tvPrice;
    private LinearLayout llContinueBooking;

    public static Fragment getInstance(ArrayList<Gallery> photosList, String pricePerNight) {
        HotelDetailPhotosFragment photosFragment = new HotelDetailPhotosFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_HOTEL_PHOTOS, photosList);
        bundle.putString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT, pricePerNight);
        photosFragment.setArguments(bundle);
        return photosFragment;
    }

    public HotelDetailPhotosFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotel_photos, container, false);

        Bundle bundle = getArguments();
        try {
            if (bundle != null) {
                photosList = (ArrayList<Gallery>) bundle.getSerializable(AppConstant.EXT_HOTEL_PHOTOS);
                pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (photosList != null && !photosList.isEmpty()) {
            tvPrice = view.findViewById(R.id.tvPrice);
            rvHotelPhotos = view.findViewById(R.id.rvHotelPhotos);
            gridLayoutManager = new GridLayoutManager(getActivity(), 3);
            rvHotelPhotos.setLayoutManager(gridLayoutManager);
            photosListAdapter = new HotelDetailPhotosListAdapter(getActivity(), photosList);
            rvHotelPhotos.setAdapter(photosListAdapter);

            tvPrice.setText("$ " + pricePerNight);
        }

        llContinueBooking = view.findViewById(R.id.llContinueBooking);

        llContinueBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HotelDetailsActivity) getActivity()).onBookingClick();
            }
        });

        return view;
    }

}
