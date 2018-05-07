package com.moderndeveloper.fllawi.fragments;

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

import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.activities.HotelDetailsActivity;
import com.moderndeveloper.fllawi.adapters.HotelDetailPhotosListAdapter;
import com.moderndeveloper.fllawi.adapters.HotelReviewsListAdapter;
import com.moderndeveloper.fllawi.model.AccommodationDetails.HostProvider;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class HotelDetailHostFragment extends Fragment {

    private LinearLayout llContinueBooking;
    private HostProvider hostDetail;
    private String pricePerNight = "";
    private TextView tvPrice;

    public static Fragment getInstance(HostProvider hostDetail,String pricePerNight){
        HotelDetailHostFragment hostFragment = new HotelDetailHostFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_HOTEL_HOST,hostDetail);
        bundle.putString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT,pricePerNight);
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
        if (bundle != null){
            hostDetail = (HostProvider) bundle.getSerializable(AppConstant.EXT_HOTEL_HOST);
            pricePerNight = bundle.getString(AppConstant.EXT_HOTEL_PRICE_PER_NIGHT);

            tvPrice.setText("$ "+pricePerNight);

            if (hostDetail != null){
                ImageLoader.getInstance().loadImage(Utility.BASE_URL+"/"+hostDetail.getProfileImage(), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        ivProfilePic.setImageBitmap(loadedImage);
                        progressBar.setVisibility(View.GONE);
                    }
                });

                if (hostDetail.getFirstName() != null && hostDetail.getLastName() != null){
                    tvHostName.setText(hostDetail.getFirstName() + hostDetail.getLastName());
                }else{
                    tvHostName.setText("");
                }

                if (hostDetail.getContactNo() != null){
                    tvHostContact.setText(hostDetail.getContactNo());
                }else{
                    tvHostContact.setText("");
                }

            }

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
