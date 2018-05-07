package com.moderndeveloper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.customviews.snackBarCustomViews.SnackBarInstance;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.activities.ChangePasswordActivity;
import com.moderndeveloper.fllawi.activities.HomeActivity;
import com.moderndeveloper.fllawi.activities.NotificationActivity;
import com.moderndeveloper.fllawi.activities.ProfileActivity;
import com.moderndeveloper.fllawi.activities.SignInActivity;
import com.moderndeveloper.fllawi.activities.WebViewActivity;
import com.moderndeveloper.fllawi.enums.WEB_VIEW_CONTENT_TYPE;
import com.moderndeveloper.fllawi.permissionManagerViews.PermissionManagerInstance;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.PreferenceData;
import com.moderndeveloper.fllawi.utils.SharedPreferenceUtil;
import com.moderndeveloper.fllawi.utils.Utility;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public LinearLayout llContainer;
    public SnackBarInstance snackBarInstance;

    public LinearLayout llTop;
    private ImageView imgContainerShadow;
    public ImageView ivTopBack;
    public TextView tvTopTitle;
    public TextView tvTopLeftFirst;
    public TextView tvTopSkip;
    public ImageView ivTopSearch;
    public ImageView ivTopFilter;
    public TextView tvLeftText;
    public TextView tvRightText;
    public static boolean isHomeRunning = false;

    public RelativeLayout rlTopSearch;
    public EditText etTopSearch;
    public TextView tvTopSearchCancel;
    protected LinearLayout llBase;
    private ImageView ivCancel;

    private int selectedFooterColor;
    protected Activity mActivity = BaseActivity.this;
    private ProgressDialog mPd;
    protected PermissionManagerInstance mPermissionManagerInstance;

    public NavigationView nav_view;
    public DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        llContainer = (LinearLayout) findViewById(R.id.llContainer);

        mPd = Utility.initProgress(mActivity);
        mPermissionManagerInstance = new PermissionManagerInstance(this);

        nav_view = findViewById(R.id.nav_view);
        drawer_layout = findViewById(R.id.drawer_layout);

        //topbar
        llTop = (LinearLayout) findViewById(R.id.lnrTop);
        imgContainerShadow = (ImageView) findViewById(R.id.imgContainerShadow);
        ivTopBack = (ImageView) findViewById(R.id.ivTopBack);
        tvTopTitle = (TextView) findViewById(R.id.tvTopTitle);
        tvTopLeftFirst = (TextView) findViewById(R.id.tvTopLeftFirst);
        tvTopSkip = (TextView) findViewById(R.id.tvTopSkip);
        ivTopSearch = (ImageView) findViewById(R.id.ivTopSearch);
        ivTopFilter = (ImageView) findViewById(R.id.ivTopFilter);
        ivCancel = (ImageView) findViewById(R.id.ivCancel);
        // top search
        rlTopSearch = (RelativeLayout) findViewById(R.id.rlTopSearch);
        etTopSearch = (EditText) findViewById(R.id.edtTopSearch);
        tvTopSearchCancel = (TextView) findViewById(R.id.tvTopSearchCancel);

        tvLeftText = findViewById(R.id.tvLeftText);
        tvRightText = findViewById(R.id.tvRightText);

        snackBarInstance = new SnackBarInstance(this);
        snackBarInstance.setSnackBarDuration(Utility.SNACK_TIME);

        ivTopBack.setOnClickListener(this);
        tvTopSkip.setOnClickListener(this);
        ivCancel.setOnClickListener(this);
        tvTopLeftFirst.setOnClickListener(this);
        ivTopSearch.setOnClickListener(this);
        ivTopFilter.setOnClickListener(this);
        rlTopSearch.setOnClickListener(this);
        tvTopSearchCancel.setOnClickListener(this);
        tvLeftText.setOnClickListener(this);
        tvRightText.setOnClickListener(this);

        selectedFooterColor = ContextCompat.getColor(BaseActivity.this, R.color.app_blue);

        setLayoutView();
        initialization();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public abstract void setLayoutView();

    public abstract void initialization();

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ivTopBack:
                closeSideMenu();
                break;
            case R.id.llUserProfile:
                onClickUserProfile();
                break;
        }
    }

    public void closeSideMenu() {
        if (drawer_layout.isDrawerOpen(Gravity.START)) {
            drawer_layout.closeDrawer(Gravity.LEFT);
        } else {
            drawer_layout.openDrawer(Gravity.LEFT);
        }
    }

    public void setOnMenuItemClick(int position){
        closeSideMenu();
        if (position == 0){
            onClickHomeScreen();
        }else if (position == 1){
            onClickUserProfile();
        }else if (position == 2){
            Intent intent = new Intent(mActivity, WebViewActivity.class);
            intent.putExtra(AppConstant.EXT_CONTENT_TYPE, WEB_VIEW_CONTENT_TYPE.OVERVIEW.value);
            startActivity(intent);
            finish();
            overridePendingTransition(0,0);
        }else if (position == 3){
            Intent intent = new Intent(mActivity, NotificationActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0,0);
        }else if (position == 4){
            Intent intent = new Intent(mActivity, ChangePasswordActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0,0);
        }else if (position == 5){
            Intent intent = new Intent(mActivity, WebViewActivity.class);
            intent.putExtra(AppConstant.EXT_CONTENT_TYPE, WEB_VIEW_CONTENT_TYPE.PRIVACY_POLICY.value);
            startActivity(intent);
            finish();
            overridePendingTransition(0,0);
        }else if (position == 6){
            Intent intent = new Intent(mActivity, WebViewActivity.class);
            intent.putExtra(AppConstant.EXT_CONTENT_TYPE, WEB_VIEW_CONTENT_TYPE.TERMS_AND_CONDITIONS.value);
            startActivity(intent);
            finish();
            overridePendingTransition(0,0);
        }else if (position == 7){
            Intent intent = new Intent(mActivity, WebViewActivity.class);
            intent.putExtra(AppConstant.EXT_CONTENT_TYPE, WEB_VIEW_CONTENT_TYPE.HELP_AND_SUPPORT.value);
            startActivity(intent);
            finish();
            overridePendingTransition(0,0);
        }else if (position == 8){
           logoutAlert("",getString(R.string.confirm_logout));
        }
    }

    public void onClickHomeScreen() {
        if (!isHomeRunning) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(mActivity, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(0,0);
                }
            }, 100);
        }else{
            closeSideMenu();
        }
    }

    private void logoutAlert(String mTitle, String mMessage) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mActivity);
        alertDialogBuilder.setTitle(mTitle);
        alertDialogBuilder.setMessage(mMessage).setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        SharedPreferenceUtil.clear();
                        Intent intent = new Intent(mActivity, SignInActivity.class);
                        startActivity(intent);
                        finish();
                        goPrevious();
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Utility.alertButtonTextColor(alertDialog);
    }

    public void openUserConfirmLogoutDialog() {
        final Dialog popupWindowDialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar);

        final View layout = LayoutInflater.from(this).inflate(R.layout.dialog_confirm_user_logout, null);
        popupWindowDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (popupWindowDialog.getWindow() != null) {
            popupWindowDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        }
        popupWindowDialog.setContentView(layout);
        popupWindowDialog.setCancelable(false);

        ImageView ivClose = (ImageView) layout.findViewById(R.id.ivClose);
        TextView tvYes = layout.findViewById(R.id.tvYes);
        TextView tvNo = layout.findViewById(R.id.tvNo);

        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceUtil.clear();
                popupWindowDialog.dismiss();
                Intent intent = new Intent(mActivity, SignInActivity.class);
                startActivity(intent);
                finish();
                goPrevious();
            }
        });

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDialog.dismiss();
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDialog.dismiss();
            }
        });

        popupWindowDialog.show();
    }

    public void onClickUserProfile() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mActivity, ProfileActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0,0);
            }
        }, 100);
    }

    public void hideSoftKeyboard() {
        Utility.hideSoftKeyboard(mActivity);
    }

    public void showSuccessSnackBar(String message) {
        snackBarInstance.showSnackBar(tvTopTitle, message, R.color.snack_success, R.color.white);
    }

    public void showErrorSnackBar(String message) {
        snackBarInstance.showSnackBar(tvTopTitle, message, R.color.snack_error, R.color.white);
    }

    public void showNetworkErrorSnackBar() {
        snackBarInstance.showSnackBar(tvTopTitle, getString(R.string.no_internet_connection), R.color.snack_error, R.color.white);
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    public void hideTopBar() {
        llTop.setVisibility(View.GONE);
    }

    public void showTopBar() {
        llTop.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_IMPLICIT, 0);
    }

    public void hideTopShadow() {
        imgContainerShadow.setVisibility(View.GONE);
    }

    public void showProgress() {
        Utility.dialogDismiss(mActivity, mPd);
        Utility.dialogShow(mActivity, mPd);
    }

    public void hideProgress() {
        Utility.dialogDismiss(mActivity, mPd);
    }

    public void goNext() {
        Utility.goNext(mActivity);
    }

    public void goPrevious() {
        Utility.goPrevious(mActivity);
    }

}
