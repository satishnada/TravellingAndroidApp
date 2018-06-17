package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.permissionManagerViews.PermissionManagerInstance;
import com.profdeveloper.fllawi.utils.PreferenceData;

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
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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
                        //if (PreferenceData.isLogin()) {
                            intent = new Intent(mActivity, HomeActivity.class);
                        /*} else {
                            intent = new Intent(mActivity, SignInActivity.class);
                        }*/
                } else {
                    intent = new Intent(mActivity, IntroductionActivity.class);
                }
                startActivity(intent);
                finish();
                goNext();
            }
        }, 1000);

    }

}
