package com.moderndeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;

public class BookingFeedbackActivity extends BaseActivity {

    private View view;
    private TextView tvPrintDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_booking_feedback, llContainer);
        mActivity = this;
        tvPrintDetails = view.findViewById(R.id.tvPrintDetails);
        tvPrintDetails.setOnClickListener(this);
        isHomeRunning = false;
    }

    @Override
    public void initialization() {
        ivTopBack.setImageResource(R.drawable.ic_back_arrow);
        tvTopTitle.setBackgroundResource(0);
        tvTopTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        tvTopTitle.setText("");
        tvTopTitle.setVisibility(View.INVISIBLE);
        ivTopSearch.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivTopBack:
                onBackPressed();
                break;
            case R.id.tvPrintDetails:
                Intent intent = new Intent(mActivity,HomeActivity.class);
                startActivity(intent);
                goPrevious();
                finish();
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
