package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.SearchResultActivity;
import com.profdeveloper.fllawi.model.Package.PackageDatum;
import com.profdeveloper.fllawi.model.ThingToDoSearch.Datum;
import com.profdeveloper.fllawi.utils.Utility;

import java.util.ArrayList;

public class PackageListAdapter extends RecyclerView.Adapter<PackageListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PackageDatum> searchResultList;
    private ImageLoader mImageLoader;

    public PackageListAdapter(Context context, ArrayList<PackageDatum> searchList) {
        this.context = context;
        searchResultList = searchList;
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thing_todo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final PackageDatum datum = searchResultList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SearchResultActivity)context).onPackageItemClick(datum);
            }
        });

        holder.progressBar.setVisibility(View.GONE);
        Glide.with(context).load(Utility.BASE_URL+"/"+searchResultList.get(position).getImage())
                .into(holder.ivHotel);

/*        mImageLoader.loadImage(Utility.BASE_URL+"/"+searchResultList.get(position).getImage(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.ivHotel.setImageBitmap(loadedImage);
                holder.progressBar.setVisibility(View.GONE);
            }
        });*/

        //ImageLoader.getInstance().displayImage(Utility.BASE_URL+"/"+searchResultList.get(position).getImage(),holder.ivHotel);
        holder.tvHotelAddress.setText(datum.getAddress());
        if (datum.getAvrageUserRating() != null && datum.getAvrageUserRating().length() > 0){
            holder.rattingBarReview.setRating(Float.parseFloat(datum.getAvrageUserRating()));
        }

        //holder.tvReviewCount.setText(datum.getTotalReview()+" Reviews");
        holder.tvFullAddress.setText(datum.getAddress());
        holder.tvHotelName.setText(datum.getTitle());
        holder.tvHotelPrice.setText(context.getString(R.string.sar)+" "+datum.getMaxPrice()+" "+context.getString(R.string.per_unit));
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
