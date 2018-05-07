package com.moderndeveloper.fllawi.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.activities.HomeActivity;
import com.moderndeveloper.fllawi.activities.SearchActivity;

public class OurServicesListAdapter extends RecyclerView.Adapter<OurServicesListAdapter.ViewHolder> {

    private Context context;
    private String[] serviceList = new String[4];
    private TypedArray serviceImg = null;

    public OurServicesListAdapter(Context context) {
        this.context = context;
        serviceList = context.getResources().getStringArray(R.array.our_services_str);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_our_services, parent, false);
        serviceImg = context.getResources().obtainTypedArray(R.array.our_services);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.ivService.setImageResource(serviceImg.getResourceId(position, -1));
        holder.tvServiceName.setText(serviceList[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) context).onOurServiceItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivService;
        private TextView tvServiceName;

        public ViewHolder(View v) {
            super(v);
            ivService = (ImageView) v.findViewById(R.id.ivService);
            tvServiceName = v.findViewById(R.id.tvServiceName);
        }
    }

}
