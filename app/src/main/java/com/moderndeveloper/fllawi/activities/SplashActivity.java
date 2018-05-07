package com.moderndeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.permissionManagerViews.PermissionManagerInstance;
import com.moderndeveloper.fllawi.utils.PreferenceData;

public class SplashActivity extends BaseActivity {

    private View view;
    private PermissionManagerInstance mPermissionManagerInstance;

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_splash, llContainer);
        isHomeRunning = false;
    }

    @Override
    public void initialization() {

        hideTopBar();
        hideTopShadow();
//        mPermissionManagerInstance = new PermissionManagerInstance(this);
//        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        mPermissionManagerInstance.requestForPermissions(
//                permissions,
//                new PermissionManagerListener() {
//                    @Override
//                    public void permissionCallback(String[] permissions, Permission[] grantResults, boolean allGranted) {
//                        startApp();
//                    }
//                });

        startApp();
    }

    private void startApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (PreferenceData.isIntroductionVisitComplete()) {
                        if (PreferenceData.isLogin()) {
                            intent = new Intent(mActivity, HomeActivity.class);
                        } else {
                            intent = new Intent(mActivity, SignInActivity.class);
                        }
                } else {
                    intent = new Intent(mActivity, IntroductionActivity.class);
                }
                startActivity(intent);
                finish();
                goNext();
            }
        }, 2000);

    }

}
