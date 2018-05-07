package com.moderndeveloper.fllawi.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

    private Context context;
    private String[] serviceList = new String[4];
    private TypedArray serviceImg = null;

    public MenuListAdapter(Context context) {
        this.context = context;
        serviceList = context.getResources().getStringArray(R.array.side_menu_str);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_side_menu, parent, false);
        serviceImg =  context.getResources().obtainTypedArray(R.array.side_menu);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position == 0){
            holder.ivService.setVisibility(View.INVISIBLE);
        } else{
            holder.ivService.setVisibility(View.VISIBLE);
            holder.ivService.setImageResource(serviceImg.getResourceId(position,-1));
        }

        holder.tvServiceName.setText(serviceList[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)context).setOnMenuItemClick(position);
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