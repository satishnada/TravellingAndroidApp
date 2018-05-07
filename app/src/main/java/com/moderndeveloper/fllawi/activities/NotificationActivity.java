package com.moderndeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.adapters.NotificationAdapter;

public class NotificationActivity extends BaseActivity {
    private View view;
    private RecyclerView recyclerNotification;
    private TextView tvNoData;
    private NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_notification, llContainer);
        recyclerNotification = (RecyclerView) view.findViewById(R.id.recyclerNotification);
        tvNoData=(TextView)view.findViewById(R.id.tvNoData);

        recyclerNotification.setHasFixedSize(true);
        recyclerNotification.setLayoutManager(new LinearLayoutManager(NotificationActivity.this, LinearLayoutManager.VERTICAL, false));
        adapter = new NotificationAdapter(NotificationActivity.this);
        recyclerNotification.setAdapter(adapter);
    }

    @Override
    public void initialization() {
        tvTopTitle.setText(getString(R.string.notifications));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                super.onClick(v);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(mActivity, HomeActivity.class);
        startActivity(intent);
        finish();
        goPrevious();
    }
}
