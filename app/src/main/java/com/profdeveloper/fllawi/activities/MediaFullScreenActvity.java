package com.profdeveloper.fllawi.activities;

import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class MediaFullScreenActvity extends BaseActivity {

    private View view;
    private ImageView ivFullScreen;
    private ImageView ivClose;
    private ProgressBar pbLoading;
    private RelativeLayout rlFullScreenMedia;
    private String imageUrl = "";

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

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            imageUrl = bundle.getString(AppConstant.EXT_HOTEL_IMAGE);

            ImageLoader.getInstance().loadImage(Utility.BASE_GALLERY_URL+"/"+imageUrl, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    // Do whatever you want with Bitmap
                    ivFullScreen.setImageBitmap(loadedImage);
                    pbLoading.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public void initialization() {
        hideTopBar();
        checkLocal();
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