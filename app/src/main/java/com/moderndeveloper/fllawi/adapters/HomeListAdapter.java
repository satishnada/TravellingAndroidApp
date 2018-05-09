package com.moderndeveloper.fllawi.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moderndeveloper.fllawi.activities.SearchResultActivity;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.model.SearchHotel.Datum;
import com.moderndeveloper.fllawi.utils.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Datum> searchResultList;

    public HomeListAdapter(Context context,ArrayList<Datum> searchList) {
        this.context = context;
        searchResultList = searchList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Datum datum = searchResultList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SearchResultActivity)context).onItemClick(datum);
            }
        });

        ImageLoader.getInstance().loadImage(Utility.BASE_URL+"/"+searchResultList.get(position).getImage(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                holder.ivHotel.setImageBitmap(loadedImage);
                holder.progressBar.setVisibility(View.GONE);
            }
        });

        //ImageLoader.getInstance().displayImage(Utility.BASE_URL+"/"+searchResultList.get(position).getImage(),holder.ivHotel);
        holder.tvHotelAddress.setText(datum.getAddress());
        holder.rattingBarReview.setRating(datum.getAvrageUserRatting());
        holder.tvReviewCount.setText(datum.getTotalReview()+" Reviews");
        holder.tvFullAddress.setText(datum.getAddress());
        holder.tvHotelName.setText(datum.getTitle());
        holder.tvHotelPrice.setText("SAR "+datum.getMaxPrice()+" Per unit");
        holder.tvFullAddress.setText(datum.getDescription());
    }

    @Override
    public int getItemCount() {
        return searchResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       private TextView tvHotelAddress,tvReviewCount,tvHotelName,tvFullAddress,tvHotelPrice;
       private ImageView ivHotel;
       private RatingBar rattingBarReview;
       private ProgressBar progressBar;

        public ViewHolder(View v) {
            super(v);
            ivHotel = v.findViewById(R.id.ivHotel);
            tvHotelAddress = v.findViewById(R.id.tvHotelAddress);
            tvReviewCount = v.findViewById(R.id.tvReviewCount);
            rattingBarReview = v.findViewById(R.id.rattingBarReview);
            tvHotelName = v.findViewById(R.id.tvHotelName);
            tvFullAddress = v.findViewById(R.id.tvFullAddress);
            tvHotelPrice = v.findViewById(R.id.tvHotelPrice);
            progressBar = v.findViewById(R.id.progressBar);
        }
    }

}
