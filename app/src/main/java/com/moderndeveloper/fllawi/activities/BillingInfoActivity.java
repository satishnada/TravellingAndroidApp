package com.moderndeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;


public class BillingInfoActivity extends BaseActivity {

    private View view;
    private TextView tvContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_billing_info, llContainer);
        mActivity = this;
        tvContinue = view.findViewById(R.id.tvContinue);
        tvContinue.setOnClickListener(this);
        isHomeRunning = false;
    }

    @Override
    public void initialization() {
        ivTopBack.setImageResource(R.drawable.ic_back_arrow);
        tvTopTitle.setBackgroundResource(0);
        tvTopTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        tvTopTitle.setText(R.string.billing_info);
        tvTopTitle.setVisibility(View.VISIBLE);
        ivTopSearch.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivTopBack:
                onBackPressed();
                break;
            case R.id.tvContinue:
                Intent intent = new Intent(mActivity,BookingFeedbackActivity.class);
                startActivity(intent);
                finish();
                goNext();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        goPrevious();
        finish();
    }
}
