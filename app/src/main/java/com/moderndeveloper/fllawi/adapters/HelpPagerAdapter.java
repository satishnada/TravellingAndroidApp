package com.moderndeveloper.fllawi.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.moderndeveloper.fllawi.R;

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
            tvTitle.setText("Be Inspired");
            tvDescription.setText("we bring you daily travel inspiration through carefully curated galleries,news and stories about trending travel destinations.");
        }else if (position == 1){
            tvTitle.setText("Discover Places");
            tvDescription.setText("Browse our in-depth travel information for great ideas and smart travel tips that will have you feeling like a local in no time.");
        }else {
            tvTitle.setText("Start the Journey");
            tvDescription.setText("find editor-curated itineraries, amazing travel deals, the best travel agents in the world,and more.");
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
