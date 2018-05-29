package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.OnCommonAdapterItemClickListener;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.ProfileActivity;
import com.profdeveloper.fllawi.model.ArrCountry;
import com.profdeveloper.fllawi.model.ArrCurrency;
import com.profdeveloper.fllawi.model.ArrFunctionalLanguage;
import com.profdeveloper.fllawi.model.ArrInterest;
import com.profdeveloper.fllawi.model.ArrPaymentType;
import com.profdeveloper.fllawi.model.SearchHotel.ArrMainCategory;
import com.profdeveloper.fllawi.model.SearchHotel.ArrSubCategory;

import java.util.ArrayList;

public class CommonListAdapter extends RecyclerView.Adapter<CommonListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Object> itemList = new ArrayList<>();
    private ArrCountry country = null;
    private ArrCurrency currency = null;
    private ArrFunctionalLanguage language = null;
    private ArrPaymentType paymentType = null;
    private ArrInterest interest = null;
    private ArrMainCategory mainCategory = null;
    private ArrSubCategory subCategory = null;
    private int selectedPosition = 0;

    private int type = 0;
    private OnCommonAdapterItemClickListener onItemClick;

    public CommonListAdapter(Context context,int type,ArrayList<Object> itemList,OnCommonAdapterItemClickListener onClick) {
        this.context = context;
        this.type = type;
        onItemClick = onClick;
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (type == 0){ //Country
            country = (ArrCountry) itemList.get(position);
            holder.tvItemName.setText(country.getTitle().toString());
            if (position == selectedPosition){
                holder.radioSelect.setChecked(true);
            }else{
                holder.radioSelect.setChecked(false);
            }
        }else if (type == 1){ // Language
            language = (ArrFunctionalLanguage) itemList.get(position);
            holder.tvItemName.setText(language.getTitle().toString());
            if (position == selectedPosition){
                holder.radioSelect.setChecked(true);
            }else{
                holder.radioSelect.setChecked(false);
            }
        }else if (type == 2){ // Currency
            currency = (ArrCurrency) itemList.get(position);
            holder.tvItemName.setText(currency.getTitle().toString());
            if (position == selectedPosition){
                holder.radioSelect.setChecked(true);
            }else{
                holder.radioSelect.setChecked(false);
            }
        }else if (type == 3){ // Interest Multiple selection
            interest = (ArrInterest) itemList.get(position);
            holder.tvItemName.setText(interest.getTitle().toString());
            if (position == selectedPosition){
                holder.radioSelect.setChecked(true);
            }else{
                holder.radioSelect.setChecked(false);
            }
        }else if (type ==4){ // Payment Type
            paymentType = (ArrPaymentType)itemList.get(position);
            holder.tvItemName.setText(paymentType.getTitle().toString());
            if (position == selectedPosition){
                holder.radioSelect.setChecked(true);
            }else{
                holder.radioSelect.setChecked(false);
            }
        } else if (type == 6){ // Category Type
            mainCategory = (ArrMainCategory) itemList.get(position);
            holder.tvItemName.setText(mainCategory.getTitle().toString());
            if (position == selectedPosition){
                holder.radioSelect.setChecked(true);
            }else{
                holder.radioSelect.setChecked(false);
            }
        }else if (type == 7){ // Sub Category Type
            subCategory = (ArrSubCategory) itemList.get(position);
            holder.tvItemName.setText(subCategory.getTitle().toString());
            if (position == selectedPosition){
                holder.radioSelect.setChecked(true);
            }else{
                holder.radioSelect.setChecked(false);
            }
        }

        holder.radioSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.performClick();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                if (type == 0){ //Country
                    onItemClick.onCommonItemClickListener(type,position,country);
                }else if (type == 1){ // Language
                    language = (ArrFunctionalLanguage) itemList.get(position);
                    Log.i("CommonListAdapter", "onClick: Language "+language.getTitle());
                    onItemClick.onCommonItemClickListener(type,position,language);
                }else if (type == 2){ // Currency
                    currency = (ArrCurrency) itemList.get(position);
                    currency.setSelectedPosition(position);
                    onItemClick.onCommonItemClickListener(type,position,currency);
                }else if (type == 3){ // Interest Multiple selection
                    interest = (ArrInterest) itemList.get(position);
                    interest.setSelectedPosition(position);
                    onItemClick.onCommonItemClickListener(type,position,interest);
                }else if (type ==4){ // Payment Type
                    paymentType = (ArrPaymentType)itemList.get(position);
                    paymentType.setSelectedPosition(position);
                    onItemClick.onCommonItemClickListener(type,position,paymentType);
                }else if (type ==6){ // Payment Type
                    mainCategory = (ArrMainCategory) itemList.get(position);
                    //paymentType.setSelectedPosition(position);
                    onItemClick.onCommonItemClickListener(type,position,mainCategory);
                }else if (type ==7){ // Payment Type
                    //paymentType.setSelectedPosition(position);
                    subCategory = (ArrSubCategory) itemList.get(position);
                    onItemClick.onCommonItemClickListener(type,position,subCategory);
                }
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItemName;
        private RadioButton radioSelect;

        public ViewHolder(View v) {
            super(v);
            radioSelect = v.findViewById(R.id.radioSelect);
            tvItemName = v.findViewById(R.id.tvItemName);
        }
    }



}
