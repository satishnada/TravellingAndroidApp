package com.profdeveloper.fllawi.fragments;

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

import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.HotelDetailsActivity;
import com.profdeveloper.fllawi.adapters.CouponDetailPhotosListAdapter;
import com.profdeveloper.fllawi.adapters.HotelDetailPhotosListAdapter;
import com.profdeveloper.fllawi.adapters.ThingTodoDetailPhotosListAdapter;
import com.profdeveloper.fllawi.model.AccommodationDetails.Data;
import com.profdeveloper.fllawi.model.AccommodationDetails.Gallery;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.Utility;

import java.util.ArrayList;

public class HotelDetailPhotosFragment extends Fragment {

    private RecyclerView rvHotelPhotos;
    private GridLayoutManager gridLayoutManager;
    private HotelDetailPhotosListAdapter photosListAdapter;
    private ThingTodoDetailPhotosListAdapter photosThingToDoListAdapter;
    private CouponDetailPhotosListAdapter photosCouponListAdapter;
    private ArrayList<Gallery> photosList = new ArrayList<>();
    private ArrayList<com.profdeveloper.fllawi.model.couponDetails.Gallery> photosCouponList = new ArrayList<>();
    private ArrayList<com.profdeveloper.fllawi.model.ThingToDoDetails.Gallery> photosThingsTodoList = new ArrayList<>();
    private String pricePerNight = "";
    private TextView tvPrice;
    private LinearLayout llContinueBooking;
    private int isFrom = 1;

    public static Fragment getInstance(int isFrom, Data data, String pricePerNight) {
        HotelDetailPhotosFragment photosFragment = new HotelDetailPhotosFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_IS_FROM, isFrom);
        bundle.putSerializable(AppConstant.EXT_HOTEL_PHOTOS, data);
        bundle.putString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT, pricePerNight);
        photosFragment.setArguments(bundle);
        return photosFragment;
    }

    public static Fragment getInstance(int isFrom, com.profdeveloper.fllawi.model.couponDetails.Data couponDetails, String pricePerNight) {
        HotelDetailPhotosFragment photosFragment = new HotelDetailPhotosFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_IS_FROM, isFrom);
        bundle.putSerializable(AppConstant.EXT_HOTEL_PHOTOS, couponDetails);
        bundle.putString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT, pricePerNight);
        photosFragment.setArguments(bundle);
        return photosFragment;
    }

    public static Fragment getInstance(int isFrom, com.profdeveloper.fllawi.model.ThingToDoDetails.Data thingToDoDetail, String pricePerNight) {
        HotelDetailPhotosFragment photosFragment = new HotelDetailPhotosFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_IS_FROM, isFrom);
        bundle.putSerializable(AppConstant.EXT_HOTEL_PHOTOS, thingToDoDetail);
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

        tvPrice = view.findViewById(R.id.tvPrice);
        rvHotelPhotos = view.findViewById(R.id.rvHotelPhotos);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        rvHotelPhotos.setLayoutManager(gridLayoutManager);

        Bundle bundle = getArguments();
        try {
            if (bundle != null) {
                isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
                if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                    Data data = (Data) bundle.getSerializable(AppConstant.EXT_HOTEL_PHOTOS);
                    photosList = (ArrayList<Gallery>) data.getArrData().getGallery();
                    pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);
                } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                    com.profdeveloper.fllawi.model.ThingToDoDetails.Data data = (com.profdeveloper.fllawi.model.ThingToDoDetails.Data) bundle.getSerializable(AppConstant.EXT_HOTEL_PHOTOS);
                    photosThingsTodoList = (ArrayList<com.profdeveloper.fllawi.model.ThingToDoDetails.Gallery>) data.getArrData().getGallery();
                    pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);
                } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                    com.profdeveloper.fllawi.model.couponDetails.Data data = (com.profdeveloper.fllawi.model.couponDetails.Data) bundle.getSerializable(AppConstant.EXT_HOTEL_PHOTOS);
                    photosCouponList = (ArrayList<com.profdeveloper.fllawi.model.couponDetails.Gallery>) data.getArrData().getGallery();
                    pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
            if (photosList != null && !photosList.isEmpty()) {
                photosListAdapter = new HotelDetailPhotosListAdapter(getActivity(), photosList);
                rvHotelPhotos.setAdapter(photosListAdapter);
            }
        } else if (isFrom == AppConstant.IS_FROM_COUPON) {
            if (photosCouponList != null && !photosCouponList.isEmpty()) {
                photosCouponListAdapter = new CouponDetailPhotosListAdapter(getActivity(), photosCouponList);
                rvHotelPhotos.setAdapter(photosCouponListAdapter);
            }
        } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
            if (photosThingsTodoList != null && !photosThingsTodoList.isEmpty()) {
                photosThingToDoListAdapter = new ThingTodoDetailPhotosListAdapter(getActivity(), photosThingsTodoList);
                rvHotelPhotos.setAdapter(photosThingToDoListAdapter);
            }
        }

        tvPrice.setText("$ " + pricePerNight);

        llContinueBooking = view.findViewById(R.id.llContinueBooking);

        llContinueBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HotelDetailsActivity) getActivity()).onBookingClick(isFrom);
            }
        });

        return view;
    }

}
