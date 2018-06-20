package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.HomeActivity;
import com.profdeveloper.fllawi.activities.SearchActivity;

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

        if (position == 0) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen._74sdp), ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.llMainItem.setLayoutParams(params);
        } else if (position == 1) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen._65sdp), ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.llMainItem.setLayoutParams(params);
        } else if (position == 2) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen._42sdp), ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.llMainItem.setLayoutParams(params);
        } else if (position == 3) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen._75sdp), ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.llMainItem.setLayoutParams(params);
        } else if (position == 4) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen._42sdp), ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.llMainItem.setLayoutParams(params);
        } else if (position == 5) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen._65sdp), ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.llMainItem.setLayoutParams(params);
        }

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
        private LinearLayout llMainItem;

        public ViewHolder(View v) {
            super(v);
            ivService = (ImageView) v.findViewById(R.id.ivService);
            tvServiceName = v.findViewById(R.id.tvServiceName);
            llMainItem = v.findViewById(R.id.llMainItem);
        }
    }

}
