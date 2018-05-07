package com.moderndeveloper.fllawi.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.activities.MediaFullScreenActvity;
import com.moderndeveloper.fllawi.model.AccommodationDetails.Gallery;
import com.moderndeveloper.fllawi.retrofit.WebUtility;
import com.moderndeveloper.fllawi.utils.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

public class HotelDetailPhotosListAdapter extends RecyclerView.Adapter<HotelDetailPhotosListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Gallery> photosList = new ArrayList<>();

    public HotelDetailPhotosListAdapter(Context context,ArrayList<Gallery> detailsPhotosList) {
        this.context = context;
        photosList = detailsPhotosList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel_photos, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Gallery photo = photosList.get(position);

        ImageLoader.getInstance().loadImage(Utility.BASE_GALLERY_URL+"/"+photo.getImage(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                holder.ivService.setImageBitmap(loadedImage);
                holder.progressBar.setVisibility(View.GONE);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MediaFullScreenActvity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivService;
        private ProgressBar progressBar;

        public ViewHolder(View v) {
            super(v);
            progressBar = v.findViewById(R.id.progressBar);
            ivService = (ImageView) v.findViewById(R.id.ivPhotos);
        }
    }

}
