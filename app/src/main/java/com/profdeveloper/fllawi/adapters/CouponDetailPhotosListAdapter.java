package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.MediaFullScreenActvity;
import com.profdeveloper.fllawi.model.AccommodationDetails.Gallery;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

public class CouponDetailPhotosListAdapter extends RecyclerView.Adapter<CouponDetailPhotosListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<com.profdeveloper.fllawi.model.couponDetails.Gallery> photosCouponList = new ArrayList<>();

    public CouponDetailPhotosListAdapter(Context context, ArrayList<com.profdeveloper.fllawi.model.couponDetails.Gallery> detailsPhotosList) {
        this.context = context;
        photosCouponList = detailsPhotosList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel_photos, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final com.profdeveloper.fllawi.model.couponDetails.Gallery photo = photosCouponList.get(position);

        Glide.with(context).load(Utility.BASE_GALLERY_URL+"/"+photo.getImage())
                .into(holder.ivService);
        holder.progressBar.setVisibility(View.GONE);

/*        ImageLoader.getInstance().loadImage(Utility.BASE_GALLERY_URL+"/"+photo.getImage(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                holder.ivService.setImageBitmap(loadedImage);
                holder.progressBar.setVisibility(View.GONE);
            }
        });*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MediaFullScreenActvity.class);
                intent.putExtra(AppConstant.EXT_HOTEL_IMAGE,photo.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photosCouponList.size();
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
