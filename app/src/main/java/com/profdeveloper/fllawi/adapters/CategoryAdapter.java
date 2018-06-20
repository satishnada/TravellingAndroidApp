package com.profdeveloper.fllawi.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.model.SearchHotel.ArrMainCategory;
import com.profdeveloper.fllawi.model.ThingToDo.Datum;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Activity mActivity;
    private ArrayList<Datum> categoryList = new ArrayList<>();
    private ArrayList<ArrMainCategory> categoryPackageList = new ArrayList<>();
    private ViewHolder holder;
    private LayoutInflater inflater;
    private int type = 0;

    public CategoryAdapter(Activity mActivity, ArrayList<Datum> catList) {
        this.mActivity = mActivity;
        this.categoryList = catList;
        inflater = LayoutInflater.from(mActivity);
    }

    public CategoryAdapter(Activity mActivity, int type, ArrayList<ArrMainCategory> categoryPackageList) {
        this.mActivity = mActivity;
        this.categoryPackageList = categoryPackageList;
        inflater = LayoutInflater.from(mActivity);
        this.type = type;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_categories_spinner, null);
            initView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (type == 0){
            Datum areaResponse = categoryList.get(position);
            holder.txtName.setText(areaResponse.getTitle());
        }else if (type == 1){
            ArrMainCategory areaResponse = categoryPackageList.get(position);
            holder.txtName.setText(areaResponse.getTitle());
        }

        return convertView;
    }

    private void initView(View convertView) {
        holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
    }

    public class ViewHolder {
        protected TextView txtName;
    }
}
