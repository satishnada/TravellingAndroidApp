package com.profdeveloper.fllawi.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.profdeveloper.fllawi.R;

public class HelpPagerAdapter extends PagerAdapter {
    private Context mActivity;
    private int currentPos = -1;
    private int[] helpImagesList;

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

        imgHelpImage.setImageResource(helpImagesList[position]);

        if (position == 0){
            tvTitle.setText(R.string.be_inspired);
            tvDescription.setText(R.string.be_inspired_description);
        }else if (position == 1){
            tvTitle.setText(R.string.discover_places);
            tvDescription.setText(R.string.discover_places_description);
        }else {
            tvTitle.setText(R.string.start_journey);
            tvDescription.setText(R.string.start_journey_description);
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
