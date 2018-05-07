package com.moderndeveloper.fllawi.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.moderndeveloper.fllawi.utils.SharedPreferenceUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class FllawiApplication extends MultiDexApplication {

    private static FllawiApplication mInstance;

    public static synchronized FllawiApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initImageLoader(getApplicationContext());
        SharedPreferenceUtil.init(this);
        SharedPreferenceUtil.save();
    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.MAX_PRIORITY);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(10 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        config.imageDownloader(new BaseImageDownloader(context, 20 * 1000, 60 * 1000));
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
