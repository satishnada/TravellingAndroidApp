package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.profdeveloper.fllawi.R;

public class HelpPagerAdapter extends PagerAdapter {
    private Context mActivity;
    private int currentPos = -1;
    private int[] helpImagesList;
    private int no_pages = 4;

    public HelpPagerAdapter(Context mActivity, int[] onboardinglist) {
        this.mActivity = mActivity;
        this.helpImagesList = onboardinglist;
    }

    public void setCurrentItem(int pos) {
        currentPos = pos;
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int position) {

        LayoutInflater inflater = LayoutInflater.from(mActivity);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_help_screen, viewGroup, false);
        ImageView imgHelpImage = (ImageView) layout.findViewById(R.id.imgHelpImage);
        TextView tvTitle = layout.findViewById(R.id.tvTitle);
        TextView tvDescription = layout.findViewById(R.id.tvDescription);
        LinearLayout circles = (LinearLayout) layout.findViewById(R.id.circles);

        imgHelpImage.setImageResource(helpImagesList[position]);

        if (position == 0){
            tvTitle.setText(R.string.discover_new);
            tvDescription.setText(R.string.discover_description);
        }else if (position == 1){
            tvTitle.setText(R.string.stay);
            tvDescription.setText(R.string.stay_description);
        }else if (position == 2){
            tvTitle.setText(R.string.sightseeing);
            tvDescription.setText(R.string.sightseeing_description);
        }else{
            tvTitle.setText(R.string.map_intro);
            tvDescription.setText(R.string.map_intro_description);
        }

        viewGroup.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup viewGroup, int position, Object view) {
        viewGroup.removeView((View) view);
    }

    @Override
    public int getCount() {
        return helpImagesList.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
