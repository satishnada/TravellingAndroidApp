package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.AddonsListActivity;
import com.profdeveloper.fllawi.activities.SearchResultActivity;
import com.profdeveloper.fllawi.model.Addons.Datum;

import java.util.ArrayList;

public class AddonsListAdapter extends RecyclerView.Adapter<AddonsListAdapter.ViewHolder> {

    private Context context;
    public ArrayList<Datum> addonsList;

    public AddonsListAdapter(Context context, ArrayList<Datum> addonList) {
        this.context = context;
        addonsList = addonList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addons, parent, false);
        return new ViewHolder(v,new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Datum datum = addonsList.get(position);

        holder.myCustomEditTextListener1.updatePosition(position);

        int quantity = 0;
        if (addonsList.get(position).getQuantity() != null &&
                addonsList.get(position).getQuantity().length() > 0){
            quantity = Integer.parseInt(addonsList.get(position).getQuantity());
        }

        if (addonsList.get(position).getSubTotal().length() > 0){
            holder.tvSubTotal.setText("SAR "+ addonsList.get(position).getSubTotal()+"");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddonsListActivity) context).onAddonItemClick(datum);
            }
        });

        holder.tvAddonsName.setText(datum.getInclusion().getTitle());
        holder.tvUnitPrice.setText("SAR "+datum.getPrice() + "");
        //holder.edtQuantity.setText(addonsList.get(holder.getAdapterPosition()).getQuantity());
        //holder.tvSubTotal.setText(datum.getSubTotal());
        //holder.tvKidsQ.setText(datum.getQuantity());

       /* holder.ivMinusQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float quantity = 0;
                if (datum.getQuantity().trim().length() > 0) {
                    quantity = Float.parseFloat(datum.getQuantity());
                    if (quantity > 0) {
                        quantity = quantity - 1;
                    }

                    float finalPrice = quantity * datum.getPrice();

                    holder.tvSubTotal.setText(finalPrice + "");
                }
            }
        });*/

      /*  holder.ivPlushQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float quantity = 0;
                if (datum.getQuantity().trim().length() > 0) {
                    quantity = Float.parseFloat(datum.getQuantity());
                    quantity = quantity + 1;
                    float finalPrice = quantity * datum.getPrice();
                    holder.tvSubTotal.setText(finalPrice + "");
                }
            }
        });*/

        //holder.edtQuantity.setText(datum.getQuantity());
        //holder.tvSubTotal.setText(datum.getSubTotal());

    }

    public  ArrayList<Datum> getAddonsSelectedList(){
        ArrayList<Datum> addonSelectedList = new ArrayList<>();
        for (int i=0;i<addonsList.size();i++){
            if (addonsList.get(i).getSubTotal().trim().length() > 0){
                if (Float.parseFloat(addonsList.get(i).getSubTotal().trim()) != 0){
                    addonSelectedList.add(addonsList.get(i));
                }
            }
        }

        return addonSelectedList;
    }

    @Override
    public int getItemCount() {
        return addonsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAddonsName;
        private TextView tvUnitPrice;
        private ImageView ivPlushQ;
        private ImageView ivMinusQ;
        private TextView tvKidsQ;
        private EditText edtQuantity;
        private TextView tvSubTotal;
        private MyCustomEditTextListener myCustomEditTextListener1;

        public ViewHolder(View v,MyCustomEditTextListener myCustomEditTextListener) {
            super(v);
            tvAddonsName = v.findViewById(R.id.tvAddonsName);
            tvUnitPrice = v.findViewById(R.id.tvUnitPrice);
            edtQuantity = v.findViewById(R.id.edtQuantity);
            tvSubTotal = v.findViewById(R.id.tvSubTotal);
            ivPlushQ = v.findViewById(R.id.ivPlushQ);
            ivMinusQ = v.findViewById(R.id.ivMinusQ);
            tvKidsQ = v.findViewById(R.id.tvKidsQ);
            myCustomEditTextListener1 = myCustomEditTextListener;
            edtQuantity.addTextChangedListener(myCustomEditTextListener1);
        }
    }

    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            String quantity = charSequence.toString();
            if (quantity.trim().length() > 0) {
                int quantityf = Integer.parseInt(quantity);
                float price = addonsList.get(position).getPrice();
                float subTotal = quantityf * price;
                addonsList.get(position).setSubTotal(subTotal + "");
                addonsList.get(position).setQuantity(quantityf+"");
            }else{
                //addonsList.get(position).setSubTotal("00.00");
                //addonsList.get(position).setQuantity("0");
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
            notifyItemChanged(position);
        }

    }
}
