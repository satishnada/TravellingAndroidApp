package com.moderndeveloper.fllawi.activities;

import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class MediaFullScreenActvity extends BaseActivity {

    private View view;
    private ImageView ivFullScreen;
    private ImageView ivClose;
    private ProgressBar pbLoading;
    private RelativeLayout rlFullScreenMedia;

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_media_full_screen, llContainer);
        findViewById();
    }

    private void findViewById() {
        ivFullScreen = (ImageView) view.findViewById(R.id.ivFullScreen);
        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        pbLoading = (ProgressBar) view.findViewById(R.id.pbLoading);
        rlFullScreenMedia = (RelativeLayout) view.findViewById(R.id.rlFullScreenMedia);
        ivClose.setOnClickListener(this);
    }

    @Override
    public void initialization() {
        hideTopBar();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
                onBackPressed();
                break;
        }
    }
}