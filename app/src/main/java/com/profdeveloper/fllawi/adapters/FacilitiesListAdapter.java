package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.model.AccommodationDetails.Amenity;
import com.profdeveloper.fllawi.utils.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;


public class FacilitiesListAdapter extends RecyclerView.Adapter<FacilitiesListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Amenity> amenitiesList = new ArrayList<>();

    public FacilitiesListAdapter(Context context,ArrayList<Amenity> amenitiesList) {
        this.context = context;
        this.amenitiesList = amenitiesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_facilities, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Amenity amenity = amenitiesList.get(position);
        String amenityCount = "";
        if (amenity.getPivot() != null){
            amenityCount = amenity.getPivot().getQuantity()+"";
        }

     /*   ImageLoader.getInstance().loadImage(Utility.BASE_URL+"/"+amenitiesList.get(position).get, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                holder.ivFacility.setImageBitmap(loadedImage);
            }
        });*/

        holder.tvServiceName.setText(" "+amenityCount +" "+amenity.getTitle());
    }

    @Override
    public int getItemCount() {
        return amenitiesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivFacility;
        private TextView tvServiceName;

        public ViewHolder(View v) {
            super(v);
            ivFacility = (ImageView) v.findViewById(R.id.ivFacility);
            tvServiceName = v.findViewById(R.id.tvServiceName);
        }
    }

}
