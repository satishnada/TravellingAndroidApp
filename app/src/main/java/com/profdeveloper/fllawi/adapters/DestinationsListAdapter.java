package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.profdeveloper.fllawi.activities.HomeActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.model.HomeTopDestination.ArrTopCoupon;
import com.profdeveloper.fllawi.utils.Utility;

import java.util.ArrayList;

public class DestinationsListAdapter extends RecyclerView.Adapter<DestinationsListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ArrTopCoupon> topDestinationsList = new ArrayList<>();

    public DestinationsListAdapter(Context context,ArrayList<ArrTopCoupon> destinationsList) {
        this.context = context;
        topDestinationsList = destinationsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destinations, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final ArrTopCoupon coupon = topDestinationsList.get(position);
        holder.tvHotelName.setText(coupon.getTitle());
        holder.tvPlaceName.setText(coupon.getDiscountApplicable()+" "+context.getString(R.string.discount_on)+" "+ context.getString(R.string.SAR) +" " + coupon.getDiscount() +" "+ context.getString(R.string.per_unit));

/*        Glide.with(context).load(Utility.BASE_URL+"/"+topDestinationsList.get(position).getImage())
                .into(holder.ivDestination);*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)context).onCouponItemClick(coupon);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topDestinationsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivDestination;
        private TextView tvHotelName,tvPlaceName,tvStartingPrice;

        public ViewHolder(View v) {
            super(v);
            ivDestination = v.findViewById(R.id.ivDestination);
            tvHotelName = v.findViewById(R.id.tvHotelName);
            tvPlaceName = v.findViewById(R.id.tvPlaceName);
            tvStartingPrice = v.findViewById(R.id.tvStartingPrice);
        }
    }

}
