package com.moderndeveloper.fllawi.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.adapters.HelpPagerAdapter;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.SharedPreferenceUtil;

public class IntroductionActivity extends BaseActivity {

    private View view;
    private ViewPager viewPager;
    private LinearLayout circles;
    private int no_pages = 3;
    private HelpPagerAdapter adapter;
    private int[] helpImagesList = {R.drawable.intro_first, R.drawable.intro_second, R.drawable.intro_third};
    private TextView tvLogIn, tvSkip;
    private boolean isFromMyProfile = false;

    @Override
    public void setLayoutView() {

        view = LayoutInflater.from(this).inflate(R.layout.activity_introduction, llContainer);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        circles = (LinearLayout) view.findViewById(R.id.circles);
        tvSkip = (TextView) view.findViewById(R.id.tvSkip);
        tvLogIn = (TextView) view.findViewById(R.id.tvLogIn);

        tvLogIn.setOnClickListener(this);
        tvSkip.setOnClickListener(this);
        isHomeRunning = false;

    }

    @Override
    public void initialization() {
        hideTopBar();
        hideTopShadow();

        adapter = new HelpPagerAdapter(IntroductionActivity.this, helpImagesList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                adapter.setCurrentItem(position);
            }

            @Override
            public void onPageSelected(int position) {
                setIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        buildCircles();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLogIn:
                gotoLogin();
                break;
            case R.id.tvSkip:
                //gotoHome();
                gotoLogin();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        goPrevious();
    }

    private void gotoLogin() {
        SharedPreferenceUtil.putValue(AppConstant.isIntroductionVisitComplete, true);
        Intent intent = new Intent(mActivity, SignInActivity.class);
        startActivity(intent);
        goNext();
        finish();
    }

    private void gotoHome() {
        SharedPreferenceUtil.putValue(AppConstant.isIntroductionVisitComplete, true);
        Intent intent = new Intent(mActivity, HomeActivity.class);
        startActivity(intent);
        goNext();
        finish();
    }

    private void buildCircles() {
        try {
            circles.removeAllViews();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        float scale = getResources().getDisplayMetrics().density;
        int padding = (int) (5 * scale + 0.5f);

        for (int i = 0; i < no_pages; i++) {
            ImageView circle = new ImageView(this);
            circle.setImageResource(R.drawable.helper_selected_button);
            circle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            circle.setAdjustViewBounds(true);
            circle.setPadding(padding, 0, padding, 0);
            circles.addView(circle);
        }

        setIndicator(0);
    }

    private void setIndicator(int index) {
        if (index < no_pages) {
            for (int i = 0; i < no_pages; i++) {
                ImageView circle = (ImageView) circles.getChildAt(i);
                if (i == index) {
                    circle.setImageResource(R.drawable.helper_selected_button);
                } else {
                    circle.setImageResource(R.drawable.helper_deselected_button);
                }
            }
        }
    }

}
