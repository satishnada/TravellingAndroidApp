package com.profdeveloper.fllawi.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.HotelDetailsActivity;
import com.profdeveloper.fllawi.adapters.HotelDetailPhotosListAdapter;
import com.profdeveloper.fllawi.adapters.HotelReviewsListAdapter;
import com.profdeveloper.fllawi.model.AccommodationDetails.HostProvider;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class HotelDetailHostFragment extends Fragment {

    private LinearLayout llContinueBooking;
    private HostProvider hostDetail;
    private com.profdeveloper.fllawi.model.couponDetails.HostProvider hostCouponDetail;
    private com.profdeveloper.fllawi.model.ThingToDoDetails.HostProvider hostThingToDoDetail;
    private String pricePerNight = "";
    private TextView tvPrice;
    private int isFrom = 1;

    public static Fragment getInstance(int isFrom, HostProvider hostDetail, String pricePerNight) {
        HotelDetailHostFragment hostFragment = new HotelDetailHostFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_IS_FROM, isFrom);
        bundle.putSerializable(AppConstant.EXT_HOTEL_HOST, hostDetail);
        bundle.putString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT, pricePerNight);
        hostFragment.setArguments(bundle);
        return hostFragment;
    }

    public static Fragment getInstance(int isFrom, com.profdeveloper.fllawi.model.couponDetails.HostProvider hostDetail, String pricePerNight) {
        HotelDetailHostFragment hostFragment = new HotelDetailHostFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_IS_FROM, isFrom);
        bundle.putSerializable(AppConstant.EXT_HOTEL_HOST, hostDetail);
        bundle.putString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT, pricePerNight);
        hostFragment.setArguments(bundle);
        return hostFragment;
    }

    public static Fragment getInstance(int isFrom, com.profdeveloper.fllawi.model.ThingToDoDetails.HostProvider hostDetail, String pricePerNight) {
        HotelDetailHostFragment hostFragment = new HotelDetailHostFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_IS_FROM, isFrom);
        bundle.putSerializable(AppConstant.EXT_HOTEL_HOST, hostDetail);
        bundle.putString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT, pricePerNight);
        hostFragment.setArguments(bundle);
        return hostFragment;
    }

    public HotelDetailHostFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passenger_detail_info, container, false);

        final ImageView ivProfilePic = view.findViewById(R.id.ivProfilePic);
        TextView tvHostName = view.findViewById(R.id.tvHostName);
        TextView tvHostContact = view.findViewById(R.id.tvHostContact);
        final ProgressBar progressBar = view.findViewById(R.id.progressBar);
        tvPrice = view.findViewById(R.id.tvPrice);

        Bundle bundle = getArguments();
        if (bundle != null) {
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
            if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                hostDetail = (HostProvider) bundle.getSerializable(AppConstant.EXT_HOTEL_HOST);
                pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);
            } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
                hostThingToDoDetail = (com.profdeveloper.fllawi.model.ThingToDoDetails.HostProvider) bundle.getSerializable(AppConstant.EXT_HOTEL_HOST);
                pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);
            } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                hostCouponDetail = (com.profdeveloper.fllawi.model.couponDetails.HostProvider) bundle.getSerializable(AppConstant.EXT_HOTEL_HOST);
                pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);
            }

            tvPrice.setText("$ " + pricePerNight);


            if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                if (hostDetail != null){
                    ImageLoader.getInstance().loadImage(Utility.BASE_URL + "/" + hostDetail.getProfileImage(), new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            ivProfilePic.setImageBitmap(loadedImage);
                            progressBar.setVisibility(View.GONE);
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
                if (hostThingToDoDetail != null){
                    ImageLoader.getInstance().loadImage(Utility.BASE_URL + "/" + hostThingToDoDetail.getProfileImage(), new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            ivProfilePic.setImageBitmap(loadedImage);
                            progressBar.setVisibility(View.GONE);
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
                if (hostCouponDetail != null){
                    ImageLoader.getInstance().loadImage(Utility.BASE_URL + "/" + hostCouponDetail.getProfileImage(), new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            ivProfilePic.setImageBitmap(loadedImage);
                            progressBar.setVisibility(View.GONE);
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
            }
        }

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
