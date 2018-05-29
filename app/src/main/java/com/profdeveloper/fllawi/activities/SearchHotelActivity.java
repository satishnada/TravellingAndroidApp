package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;

public class SearchHotelActivity extends BaseActivity {

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_search_result, llContainer);
        mActivity = this;
    }

    @Override
    public void initialization() {
        ivTopBack.setImageResource(R.drawable.ic_back_arrow);
        tvTopTitle.setBackgroundResource(0);
        tvTopTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        tvTopTitle.setText(R.string.search);
        tvTopTitle.setVisibility(View.VISIBLE);
        ivTopSearch.setVisibility(View.VISIBLE);
        isHomeRunning = false;
        checkLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivTopBack:
                closeSideMenu();
                break;
            case R.id.ivTopSearch:
                Intent searchIntent = new Intent(mActivity,SearchActivity.class);
                startActivity(searchIntent);
                goNext();
                finish();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent searchIntent = new Intent(mActivity,HomeActivity.class);
        startActivity(searchIntent);
        finish();
        goPrevious();
    }
}
