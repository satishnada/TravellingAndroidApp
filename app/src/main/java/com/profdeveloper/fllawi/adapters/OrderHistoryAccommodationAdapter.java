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

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.BookingHistoryActivity;
import com.profdeveloper.fllawi.model.BookingHistory.Accomodation;
import com.profdeveloper.fllawi.model.BookingHistory.Datum;
import com.profdeveloper.fllawi.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryAccommodationAdapter extends RecyclerView.Adapter<OrderHistoryAccommodationAdapter.ViewHolder> {

    private String TAG = OrderHistoryAccommodationAdapter.class.getSimpleName();
    private Context context;
    private List<Datum> orderHistoryList = new ArrayList<>();

    public OrderHistoryAccommodationAdapter(Context context, List<Datum> orderHistoryList) {
        this.context = context;
        this.orderHistoryList = orderHistoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking_history, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.i(TAG, "onBindViewHolder: AllowedList " + orderHistoryList.get(position));

        Accomodation accomodation = orderHistoryList.get(position).getAccomodation();

        ImageLoader.getInstance().loadImage(Utility.BASE_URL + "/" + "", new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                holder.ivOrder.setImageBitmap(loadedImage);
            }
        });

        holder.tvOrderId.setText("Order ID: FLW#" + accomodation.getId());
        holder.tvOrderTitle.setText(accomodation.getTitle());
        //holder.tvOrderValidDate.setText("Valid Till Date : "+orderHistoryList.get(position).getFromDate());
        if (orderHistoryList.get(position).getPaymentMode() == 1){
            holder.tvPaymentMethod.setText("Payment method: Paytab");
        }else{
            holder.tvPaymentMethod.setText("Payment method: Bank");
        }

        holder.tvFromDate.setText(orderHistoryList.get(position).getFromDate());
        holder.tvToDate.setText(orderHistoryList.get(position).getToDate());
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
        }
    }

}
