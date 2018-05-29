package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.AddonsListActivity;
import com.profdeveloper.fllawi.model.Addons.Datum;

import java.util.ArrayList;

public class AddonsBillingInfoListAdapter extends RecyclerView.Adapter<AddonsBillingInfoListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Datum> addonsList;

    public AddonsBillingInfoListAdapter(Context context, ArrayList<Datum> addonList) {
        this.context = context;
        addonsList = addonList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addons_billing_info, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Datum datum = addonsList.get(position);
        int quantiry = 0;
        if (addonsList.get(position).getQuantity() != null &&
                addonsList.get(position).getQuantity().length() > 0){
             quantiry = Integer.parseInt(addonsList.get(position).getQuantity());
        }

        holder.tvAddonsName.setText(addonsList.get(position).getInclusion().getTitle()+" : "+ quantiry);
        holder.tvAddonsAmount.setText("SAR "+addonsList.get(position).getSubTotal());

    }

    @Override
    public int getItemCount() {
        return addonsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAddonsAmount;
        private TextView tvAddonsName;

        public ViewHolder(View v) {
            super(v);
            tvAddonsAmount = v.findViewById(R.id.tvAddonsAmount);
            tvAddonsName = v.findViewById(R.id.tvAddonsName);
        }
    }

}
