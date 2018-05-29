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

import com.profdeveloper.fllawi.activities.HomeActivity;
import com.profdeveloper.fllawi.R;

import java.util.ArrayList;

public class DestinationsListAdapter extends RecyclerView.Adapter<DestinationsListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> topDestinationsList = new ArrayList<>();

    public DestinationsListAdapter(Context context,ArrayList<String> destinationsList) {
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
        //holder.ivDestination
        holder.tvHotelPackage.setText(position+"43 "+context.getString(R.string.packages));
        //holder.tvPlaceName.setText("");
        holder.tvStartingPrice.setText(R.string.start_from);

        if (position == 0){
            Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.ic_dummy_offere);
            RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(context.getResources(),icon);
            dr.setCornerRadius(1f);
            holder.ivDestination.setImageDrawable(dr);
        }else if (position == 1){
            Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.ic_dummy_home);
            RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(context.getResources(),icon);
            dr.setCornerRadius(1f);
            holder.ivDestination.setImageDrawable(dr);
        }else if (position == 2){
            Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.ic_dummy_destinations2);
            RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(context.getResources(),icon);
            dr.setCornerRadius(1f);
            holder.ivDestination.setImageDrawable(dr);
        }else if (position == 3){
            Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.ic_dummy_destinations);
            RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(context.getResources(),icon);
            dr.setCornerRadius(1f);
            holder.ivDestination.setImageDrawable(dr);
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
        return topDestinationsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivDestination;
        private TextView tvHotelPackage,tvPlaceName,tvStartingPrice;

        public ViewHolder(View v) {
            super(v);
            ivDestination = v.findViewById(R.id.ivDestination);
            tvHotelPackage = v.findViewById(R.id.tvHotelPackage);
            tvPlaceName = v.findViewById(R.id.tvPlaceName);
            tvStartingPrice = v.findViewById(R.id.tvStartingPrice);
        }
    }

}
