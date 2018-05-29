package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.profdeveloper.fllawi.activities.HomeActivity;
import com.profdeveloper.fllawi.R;

import java.util.ArrayList;

public class OffersAndPriceListAdapter extends RecyclerView.Adapter<OffersAndPriceListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> offerAndPriceList = new ArrayList<>();

    public OffersAndPriceListAdapter(Context context,ArrayList<String> offerAndPriceList) {
        this.context = context;
        this.offerAndPriceList = offerAndPriceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offers_prices, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.ratingBarHotel.setRating(4);
        //holder.ivHotel
        //holder.tvFullAddress.setText();
        holder.tvHotelName.setText(offerAndPriceList.get(position));
        holder.tvOffer.setText("SAR 32"+ position +"5");
        holder.tvReviewCount.setText(position+"34 "+ context.getString(R.string.reviews));
        if (position == 0){
            holder.ivHotel.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dummy_offere));
        }else if (position == 1){
            holder.ivHotel.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dummy_destinations2));
        }else if (position == 2){
            holder.ivHotel.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dummy_destinations3));
        }else if (position == 3){
            holder.ivHotel.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dummy_home));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((HomeActivity)context).onItemClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return offerAndPriceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivHotel;
        private TextView tvOffer,tvReviewCount,tvHotelName,tvFullAddress;
        private RatingBar ratingBarHotel;

        public ViewHolder(View v) {
            super(v);
            ivHotel = v.findViewById(R.id.ivHotel);
            tvOffer = v.findViewById(R.id.tvOffer);
            tvReviewCount = v.findViewById(R.id.tvReviewCount);
            ratingBarHotel = v.findViewById(R.id.ratingBarHotel);
            tvHotelName = v.findViewById(R.id.tvHotelName);
            tvFullAddress = v.findViewById(R.id.tvFullAddress);
        }
    }

}
