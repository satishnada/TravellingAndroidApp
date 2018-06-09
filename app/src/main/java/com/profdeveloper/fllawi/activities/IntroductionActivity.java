package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.adapters.HelpPagerAdapter;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.SharedPreferenceUtil;

public class IntroductionActivity extends BaseActivity {

    private View view;
    private ViewPager viewPager;
    private LinearLayout circles;
    private int no_pages = 4;
    private HelpPagerAdapter adapter;
    private int[] helpImagesList = {R.drawable.intro_first, R.drawable.intro_second, R.drawable.intro_third,R.drawable.intro_forth};
    private TextView tvLogIn, tvSkip;
    private boolean isFromMyProfile = false;

    @Override
    public void setLayoutView() {

        view = LayoutInflater.from(this).inflate(R.layout.activity_introduction, llContainer);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        circles = (LinearLayout) view.findViewById(R.id.circles);
        tvLogIn = (TextView) view.findViewById(R.id.tvLogIn);
        tvSkip = view.findViewById(R.id.tvSkip);
        tvSkip.setOnClickListener(this);
        tvLogIn.setOnClickListener(this);
        isHomeRunning = false;

    }

    @Override
    public void initialization() {
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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
        checkLocal();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLogIn:
                gotoLogin();
                break;
            case R.id.tvSkip:
                gotoHome();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        goPrevious();
    }

    private void gotoLogin() {
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
                    circle.setImageResource(R.drawable.ic_dot_red);
                } else {
                    circle.setImageResource(R.drawable.ic_dot_white);
                }
            }
        }
    }

}
