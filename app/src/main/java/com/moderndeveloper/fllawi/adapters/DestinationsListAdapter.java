package com.moderndeveloper.fllawi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moderndeveloper.fllawi.activities.HomeActivity;
import com.moderndeveloper.fllawi.R;

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
        holder.tvHotelPackage.setText(position+"43"+"Packages");
        //holder.tvPlaceName.setText("");
        holder.tvStartingPrice.setText("Starting from SAR 2300");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)context).onItemClick();
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
