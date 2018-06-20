package com.profdeveloper.fllawi.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.model.SearchHotel.ArrSubCategory;
import com.profdeveloper.fllawi.model.ThingToDo.ChildCategory;
import com.profdeveloper.fllawi.model.ThingToDo.Datum;

import java.util.ArrayList;

public class SubCategoryAdapter extends BaseAdapter {
    private Activity mActivity;
    private ArrayList<ChildCategory> subCategoryList = new ArrayList<>();
    private ArrayList<ArrSubCategory> subCategoryPackageList = new ArrayList<>();
    private ViewHolder holder;
    private LayoutInflater inflater;
    private int type = 0;

    public SubCategoryAdapter(Activity mActivity, ArrayList<ChildCategory> catList) {
        this.mActivity = mActivity;
        this.subCategoryList = catList;
        inflater = LayoutInflater.from(mActivity);
    }

    public SubCategoryAdapter(Activity mActivity,int type ,ArrayList<ArrSubCategory> catPackageList) {
        this.mActivity = mActivity;
        this.subCategoryPackageList = catPackageList;
        inflater = LayoutInflater.from(mActivity);
        this.type = type;
    }

    @Override
    public int getCount() {
        return subCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return subCategoryList.get(position);
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
            ChildCategory areaResponse = subCategoryList.get(position);
            holder.txtName.setText(areaResponse.getTitle());
        }else if (type == 1){
            ArrSubCategory areaResponse = subCategoryPackageList.get(position);
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
