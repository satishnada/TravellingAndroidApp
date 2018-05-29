package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.profdeveloper.fllawi.R;
import java.util.ArrayList;
import java.util.List;

public class AllowedListAdapter extends RecyclerView.Adapter<AllowedListAdapter.ViewHolder> {

    private String TAG = AllowedListAdapter.class.getSimpleName();
    private Context context;
    private List<String> allowedList = new ArrayList<>();

    public AllowedListAdapter(Context context, List<String> allowedList) {
        this.context = context;
        this.allowedList = allowedList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allowed, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.i(TAG, "onBindViewHolder: AllowedList "+allowedList.get(position));
     /*   ImageLoader.getInstance().loadImage(Utility.BASE_URL+"/"+amenitiesList.get(position).get, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                holder.ivFacility.setImageBitmap(loadedImage);
            }
        });*/

        holder.tvServiceName.setText(allowedList.get(position));
    }

    @Override
    public int getItemCount() {
        return allowedList.size();
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