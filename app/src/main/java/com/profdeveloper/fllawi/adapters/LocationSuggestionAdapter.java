package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.SearchActivity;
import com.profdeveloper.fllawi.model.SuggestLocationRequestResponse;

import java.util.ArrayList;

public class LocationSuggestionAdapter extends ArrayAdapter<SuggestLocationRequestResponse> {

    Context mContext;
    ArrayList<SuggestLocationRequestResponse> locationList;

    public LocationSuggestionAdapter(Context mContext, ArrayList<SuggestLocationRequestResponse> data) {
        super(mContext, 0, data);
        this.mContext = mContext;
        this.locationList = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_common_list,parent,false);
            }

            TextView textViewItem = (TextView) convertView.findViewById(R.id.tvItemName);
            RadioButton radioButton = convertView.findViewById(R.id.radioSelect);
            radioButton.setVisibility(View.GONE);
            textViewItem.setText(locationList.get(position).getValue());

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;

    }
}