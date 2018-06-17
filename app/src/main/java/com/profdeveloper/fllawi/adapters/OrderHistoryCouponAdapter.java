package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.BookingHistoryActivity;

import com.profdeveloper.fllawi.model.BookingHistoryCoupon.Coupon;
import com.profdeveloper.fllawi.model.BookingHistoryCoupon.Datum;
import com.profdeveloper.fllawi.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryCouponAdapter extends RecyclerView.Adapter<OrderHistoryCouponAdapter.ViewHolder> {

    private String TAG = OrderHistoryCouponAdapter.class.getSimpleName();
    private Context context;
    private List<Datum> orderHistoryList = new ArrayList<>();
    private ImageLoader mImageLoader;

    public OrderHistoryCouponAdapter(Context context, List<Datum> orderHistoryList) {
        this.context = context;
        this.orderHistoryList = orderHistoryList;
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking_history_coupon, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.i(TAG, "onBindViewHolder: AllowedList " + orderHistoryList.get(position));

        Coupon coupon = orderHistoryList.get(position).getCoupon();

        if (orderHistoryList.get(0).getGallery() != null && !orderHistoryList.get(0).getGallery().isEmpty()){
            String imageUrl = orderHistoryList.get(0).getGallery().get(0).getImage();

            Glide.with(context).load(Utility.BASE_URL + "/" + imageUrl)
                    .into(holder.ivOrder);

            /*mImageLoader.loadImage(Utility.BASE_URL + "/" + imageUrl, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    holder.ivOrder.setImageBitmap(loadedImage);
                }
            });*/
        }

        holder.tvOrderId.setText(R.string.order_id_flw + coupon.getId());
        holder.tvOrderTitle.setText(coupon.getTitle());
        holder.tvQty.setText(coupon.getQty()+"");
        holder.tvValidDate.setText(context.getString(R.string.valid_till_date)+coupon.getValidTillDate());
        if (orderHistoryList.get(position).getPaymentMode() == 1){
            holder.tvPaymentMethod.setText(R.string.payment_method_paytab);
        }else{
            holder.tvPaymentMethod.setText(R.string.payment_method_bank);
        }

        holder.tvFromDate.setText(coupon.getFromDate());
        holder.tvToDate.setText(coupon.getToDate());
        if (orderHistoryList.get(position).getCreatedAt() != null && orderHistoryList.get(position).getCreatedAt().length() > 0){
            holder.tvOrderDateFinal.setText(orderHistoryList.get(position).getCreatedAt().substring(0,11));
        }
        holder.tvTotalPrice.setText(orderHistoryList.get(position).getTotalAmount()+ " SAR");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BookingHistoryActivity) context).onHistoryItemClick(orderHistoryList.get(position).getId() + "");
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivOrder;
        private TextView tvOrderId;
        private TextView tvOrderTitle;
        private TextView tvPaymentMethod;
        private TextView tvFromDate;
        private TextView tvToDate;
        private TextView tvOrderDateFinal;
        private TextView tvTotalPrice;
        private TextView tvValidDate;
        private TextView tvQty;

        public ViewHolder(View v) {
            super(v);
            ivOrder = (ImageView) v.findViewById(R.id.ivOrder);
            tvOrderId = (TextView) v.findViewById(R.id.tvOrderId);
            tvOrderTitle = (TextView) v.findViewById(R.id.tvOrderTitle);
            tvPaymentMethod = (TextView) v.findViewById(R.id.tvPaymentMethod);
            tvFromDate = (TextView) v.findViewById(R.id.tvFromDate);
            tvToDate = (TextView) v.findViewById(R.id.tvToDate);
            tvTotalPrice = (TextView) v.findViewById(R.id.tvTotalPrice);
            tvOrderDateFinal = v.findViewById(R.id.tvOrderDateFinal);
            tvValidDate = v.findViewById(R.id.tvValidDate);
            tvQty = v.findViewById(R.id.tvQty);
        }
    }

}
