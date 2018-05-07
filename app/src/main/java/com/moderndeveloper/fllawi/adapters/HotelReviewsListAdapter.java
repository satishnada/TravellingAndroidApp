package com.moderndeveloper.fllawi.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.model.AccommodationDetails.ArrReview;
import com.moderndeveloper.fllawi.utils.PreferenceData;
import com.moderndeveloper.fllawi.utils.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HotelReviewsListAdapter extends RecyclerView.Adapter<HotelReviewsListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ArrReview> reviewList;
    private SimpleDateFormat dateFormatter;

    public HotelReviewsListAdapter(Context context, ArrayList<ArrReview> reviews) {
        this.context = context;
        this.reviewList = reviews;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel_reviews, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        ArrReview userReview = reviewList.get(position);

        ImageLoader.getInstance().loadImage(Utility.BASE_URL + "/" +userReview.getCustomer().getProfileImage(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                holder.ivReviewProfile.setImageBitmap(loadedImage);
            }
        });

        holder.tvReviewTitle.setText(userReview.getName());
        holder.tvUserReview.setText(userReview.getMessage());
        holder.rattingReview.setRating(userReview.getRating());
        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",Locale.US);
            Date reviewCreatedDate = format1.parse(userReview.getCreatedAt());
            String reviewDate = dateFormatter.format(reviewCreatedDate.getTime());
            holder.tvReviewTime.setText(reviewDate);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivReviewProfile;
        private TextView tvReviewTitle;
        private TextView tvReviewTime;
        private TextView tvUserReview;
        private RatingBar rattingReview;

        public ViewHolder(View v) {
            super(v);
            ivReviewProfile = v.findViewById(R.id.ivReviewProfile);
            tvReviewTitle = v.findViewById(R.id.tvReviewTitle);
            tvReviewTime = v.findViewById(R.id.tvReviewTime);
            tvUserReview = v.findViewById(R.id.tvUserReview);
            rattingReview = v.findViewById(R.id.rattingReview);
        }
    }

}
