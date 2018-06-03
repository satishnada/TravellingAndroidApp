package com.profdeveloper.fllawi.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.SignInActivity;
import com.profdeveloper.fllawi.adapters.CommonListAdapter;
import com.profdeveloper.fllawi.app.FllawiApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.profdeveloper.fllawi.utils.BitmapUtil.getFilename;


public class Utility {

    private static Dialog popupWindow;
    public static final int SNACK_TIME = 2000;
    public static String BASE_URL = "";
    public static String BASE_GALLERY_URL = "";
    public static final String DOWNLOAD_APP_LINK = "https://play.google.com/store/apps/details?id=com.tinker";
    private static final String TAG = "Sahm";
    public static final java.text.DateFormat dateFormatMMMMdd = new SimpleDateFormat("MMMM dd", Locale.getDefault());
    public static final java.text.DateFormat dateFormatddMMMyyhhmmaa = new SimpleDateFormat("dd MMM''yy | hh:mm aa", Locale.getDefault());
    public static final java.text.DateFormat DATEFORMATddMMMyyy = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    public static final java.text.DateFormat DATEFORMATddMMyyyy = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    public static final java.text.DateFormat dateFormatddMMyyyy = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    public static final java.text.DateFormat dateFormathhmma = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    private static String filename = "";

    public static void showToast(String message, Context context) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, pxToDp(context, 70));
        toast.show();
    }

    public static int dpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static String getLocale(){
        if (PreferenceData.getUserData() !=null){
             if (PreferenceData.getUserLanguage().equalsIgnoreCase("ar")){
                 return "ar";
             }else{
                 return "en";
             }
        }else{
            return "en";
        }
    }

    public static int pxToDp(Context context, float px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getFileName(String s) {
        int start = s.lastIndexOf("/");
        return s.substring(start + 1, s.length());
    }

    public static String getFileExtension(String s) {
        int start = s.lastIndexOf(".");
        return s.substring(start + 1, s.length());
    }

    public static String getAppStoragePath(Context context, String dir) {
        String path;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            path = Environment.getExternalStorageDirectory() + "/" + context.getString(R.string.app_name);
        } else {
            path = context.getFilesDir() + "/" + context.getString(R.string.app_name);
        }

        String directory = +dir.trim().length() > 0 ? (dir) : "";
        File file = new File(path + directory);
        if (!file.exists()) file.mkdirs();

        return file.getAbsolutePath();
    }

    public static File getAppStorageFile(Context context, String dir) {
        String path;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            path = Environment.getExternalStorageDirectory() + "/" + context.getString(R.string.app_name);
        } else {
            path = context.getFilesDir() + "/" + context.getString(R.string.app_name);
        }
        String directory = +dir.trim().length() > 0 ? (dir) : "";
        File file = new File(path + directory);
        if (!file.exists()) file.mkdirs();
        return file;
    }

    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static String convertTimeStampToDate(Long timeStamp, String format) {
        String date = "";
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeStamp * 1000);
        date = DateFormat.format(format, cal).toString();
        return date;
    }

    public static TimeZone getTimeZone() {
        TimeZone tz = TimeZone.getTimeZone("GMT");
        return tz;
    }

    public static RequestBody imageToBody(String text) {
        RequestBody requestBody;
        if (text != null && text.length() > 0) {
            MediaType MEDIA_TYPE = MediaType.parse("image/*");
            File file = new File(text);
            requestBody = RequestBody.create(MEDIA_TYPE, file);
        } else {
            requestBody = null;
        }
        return requestBody;
    }

    public static void showProgress(final Context context) {
        try {
            if (!((Activity) context).isFinishing()) {
                View layout = LayoutInflater.from(context).inflate(R.layout.layout_popup_loading, null);
                popupWindow = new Dialog(context, R.style.ProgressDialog);
                popupWindow.requestWindowFeature(Window.FEATURE_NO_TITLE);
                popupWindow.setContentView(layout);
                popupWindow.setCancelable(false);

                if (!((Activity) context).isFinishing()) {
                    popupWindow.show();
                }
            }

        } catch (Exception e)

        {
            e.printStackTrace();
        }
    }

    public static void showProgressWithText(final Context context, String msg) {
        try {
            if (!((Activity) context).isFinishing()) {
                View layout = LayoutInflater.from(context).inflate(R.layout.layout_popup_loading, null);
                popupWindow = new Dialog(context, R.style.ProgressDialog);
                popupWindow.requestWindowFeature(Window.FEATURE_NO_TITLE);
                popupWindow.setContentView(layout);
                popupWindow.setCancelable(false);
                TextView tvProgressText = (TextView) layout.findViewById(R.id.tvProgressText);
                tvProgressText.setVisibility(View.VISIBLE);
                tvProgressText.setText(msg);
                if (!((Activity) context).isFinishing()) {
                    popupWindow.show();
                }
            }

        } catch (Exception e)

        {
            e.printStackTrace();
        }
    }

    public static void hideProgress() {
        try {
            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    public static int getScreenResolutionWidth(Context context) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        Log.e("screen width", screenWidth + "");
        //int screenHeight = displayMetrics.heightPixels;
        return screenWidth;
    }

    public static String getDate(long startTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startTime * 1000L);
        return DateFormat.format("dd/MM/yyyy", cal).toString();
    }

    public static String getOpenDate(long startTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startTime * 1000L);
        return DateFormat.format("dd/MM/yyyy", cal).toString();
    }

    public static String generateFileName(String s) {
        String filename = "";
        Calendar c = Calendar.getInstance();
        filename = s + "_" + (c.getTimeInMillis() + "");
        return filename;
    }

    public static void hideSoftKeyboard(Activity mActivity) {
        InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
    }

    public static ProgressDialog initProgress(Object mActivity) {
        ProgressDialog mpDialog = null;
        if (mActivity instanceof Activity) {
            mpDialog = new ProgressDialog((Activity) mActivity);
        } else if (mActivity instanceof Context) {
            mpDialog = new ProgressDialog((Context) mActivity);
        }
        mpDialog.setTitle("Please wait...");
        mpDialog.setMessage("Loading...");
        mpDialog.setCanceledOnTouchOutside(false);
        // Set the progress dialog background color
//        if (checkIsLollipopOrHigher()) {
//            mpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
//        } else {
//            mpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
//        }
        return mpDialog;
    }

    public static void dialogShow(Activity mActivity, final ProgressDialog mpDialog) {
        if (mpDialog != null && !mpDialog.isShowing()) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mpDialog.show();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void dialogDismiss(Activity mActivity, final ProgressDialog mpDialog) {
        if (mpDialog != null && mpDialog.isShowing()) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mpDialog.dismiss();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void goNext(Activity mActivity) {
        mActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public static void goPrevious(Activity mActivity) {
        mActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public static void goTop(Activity mActivity) {
        mActivity.overridePendingTransition(R.anim.slide_up, R.anim.stay);
    }

    public static void goBottom(Activity mActivity) {
        mActivity.overridePendingTransition(R.anim.stay, R.anim.slide_down);
    }

    public static DisplayImageOptions getDisplayOption(int placeholder) {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(placeholder)
                .showImageForEmptyUri(placeholder)
                .showImageOnFail(placeholder)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public static DisplayImageOptions getDisplayRoundOption(int placeholder) {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(placeholder)
                .showImageForEmptyUri(placeholder)
                .displayer(new RoundedBitmapDisplayer(20)) //rounded corner bitmap
                .showImageOnFail(placeholder)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public static void log(String message) {
        if (message != null) {
            Log.e(TAG, message);
        } else {
            Log.e("Message", "null");
        }
    }

    public static void showKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null)
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


    public static void hideKeyBoardFromView(Activity mActivity) {
        InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = ((Activity) mActivity).getCurrentFocus();
        if (view == null) {
            view = new View(mActivity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showToast(final String message) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(FllawiApplication.getInstance(), message, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, pxToDp(FllawiApplication.getInstance(), 70));
                toast.show();
//                Toast.makeText(AppController.getInstance(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showError(final String message) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(FllawiApplication.getInstance(), message, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, pxToDp(FllawiApplication.getInstance(), 70));
                toast.show();
//                Toast.makeText(AppController.getInstance(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static Date getCurrentDate() {
        Date currentDate = null;
        try {
            currentDate = Utility.DATEFORMATddMMMyyy.parse(Utility.dateFormatMMMMdd.format(Calendar.getInstance().getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Log.e("UTILS currentDate", "" + currentDate);
        return currentDate;
    }

    public static String getDateFromMILI(long milliSeconds, java.text.DateFormat dateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return dateFormat.format(calendar.getTime());
    }

    public static String getDateFromSeconds(long seconds, java.text.DateFormat dateFormat) {
        long miliseconds = convertSecondsToMilliSeconds(seconds);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(miliseconds);
        return dateFormat.format(calendar.getTime());
    }

    public static String getDateFromMILINSuffixEEEMMMd(long milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        String dayNumberSuffix = getDayNumberSuffix(calendar
                .get(Calendar.DAY_OF_MONTH));
        // if (date.endsWith("1") && !date.endsWith("11"))
        // // MMM dd yyyy
        // format = new SimpleDateFormat("EEE, MMM d"
        // + Html.fromHtml("<sup><small>'st'</small></sup>"));
        // else if (date.endsWith("2") && !date.endsWith("12"))
        // format = new SimpleDateFormat("EEE, MMM d"
        // + Html.fromHtml("<sup><small>'nd'</small></sup>"));
        // else if (date.endsWith("3") && !date.endsWith("13"))
        // format = new SimpleDateFormat("EEE, MMM d"
        // + Html.fromHtml("<sup><small>'rd'</small></sup>"));
        // else
        // format = new SimpleDateFormat("EEE, MMM d"
        // + Html.fromHtml("<sup><small>'th'</small></sup>"));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d'" + dayNumberSuffix + "'");
        return dateFormat.format(calendar.getTime());
    }

    private static String getDayNumberSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "<sup>th</sup>";
        }
        switch (day % 10) {
            case 1:
                return "<sup>st</sup>";
            case 2:
                return "<sup>nd</sup>";
            case 3:
                return "<sup>rd</sup>";
            default:
                return "<sup>th</sup>";
        }
    }

    public static String getDateFromMILINSuffixMMMMdYYYY(long milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        String dayNumberSuffix = getDayNumberSuffix(calendar
                .get(Calendar.DAY_OF_MONTH));
        // SimpleDateFormat format = new SimpleDateFormat("d");
        // String date = format.format(calendar.getTime());
        // Log.e("date", "" + date);
        // if (date.endsWith("1") && !date.endsWith("11"))
        // // MMM dd yyyy
        // format = new SimpleDateFormat("MMMM d'"
        // + Html.fromHtml("<sup><small>'Ë¢áµ—'</small></sup>") +
        // "',yyyy");
        // else if (date.endsWith("2") && !date.endsWith("12"))
        // format = new SimpleDateFormat("MMMM d'"
        // + Html.fromHtml("<sup><small>'â�¿áµˆ'</small></sup>") +
        // "',yyyy");
        // else if (date.endsWith("3") && !date.endsWith("13"))
        // format = new SimpleDateFormat("MMMM d'"
        // + Html.fromHtml("<sup><small>'Ê³áµˆ'</small></sup>") +
        // "',yyyy");
        // else
        // format = new SimpleDateFormat("MMMM d'"
        // + Html.fromHtml("<sup><small>'áµ—Ê°'</small></sup>") +
        // "',yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d'" + dayNumberSuffix + "' ,yyyy");
        return dateFormat.format(calendar.getTime());
    }

    public static String milisecondsToHHmm(long millis) {
        long seconds = millis / 1000;
        @SuppressWarnings("unused")
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;
        Utility.log("h " + h + " m " + m);
        return h + ":" + m;
    }

    public static Date getDateFromString(String dateString) {
        Date convertedDate = new Date();
        try {
            convertedDate = Utility.dateFormatMMMMdd.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    // public static boolean compareTimes(String startTime, String endTime) {
    // LocalTime start = new LocalTime(startTime);
    // LocalTime end = new LocalTime(endTime);
    // Boolean isLate = end.isAfter(start);
    // return isLate;
    // }

    public static String getFrontACT(Context mContext) {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        String actNAME = componentInfo.getClassName();
        Log.e("Current ACT", "" + actNAME);
        return actNAME;
    }

    public static String getFrontAppPackageName(Context mContext) {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        Log.d("topActivity", "CURRENT Activity ::" + taskInfo.get(0).topActivity.getClassName());
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        String packageName = componentInfo.getPackageName();
        return packageName;
    }

    public static RecyclerView.LayoutManager getLayoutManager(Activity mActivity) {
        return new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
    }

    public static RecyclerView.LayoutManager getLayoutManagerHorizontal(Activity mActivity) {
        return new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
    }

    public static long convertMillisecondsToSeconds(long millis) {
        return TimeUnit.MILLISECONDS.toSeconds(millis);
    }

    public static long convertSecondsToMilliSeconds(long seconds) {
        return TimeUnit.SECONDS.toMillis(seconds);
    }

    public static void clearAllNotification() {
        NotificationManager notificationManager = (NotificationManager) FllawiApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    public static final String SHARE_APP = "https://play.google.com/store/apps/details?id=" + FllawiApplication.getInstance().getPackageName();

    public static void shareApp(Activity mActivity, String shareText) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "" + shareText);
        mActivity.startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

//    public static boolean isGooglePlayServicesAvailable(Activity activity) {
//        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
//        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
//        if (status != ConnectionResult.SUCCESS) {
//            if (googleApiAvailability.isUserResolvableError(status)) {
//                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
//            }
//            return false;
//        }
//        return true;
//    }

    public static void generateHashkey(Activity mActivity) {
        try {
            PackageInfo info = mActivity.getPackageManager().getPackageInfo(
                    mActivity.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                log("Key Hash: " + Base64.encodeToString(md.digest(),
                        Base64.NO_WRAP));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("Name not found", e.getMessage(), e);

        } catch (NoSuchAlgorithmException e) {
            Log.d("Error", e.getMessage(), e);
        }
    }

    public static RequestBody textToBody(String text) {
        RequestBody requestBody = null;
        if (text != null && text.length() > 0) {
            MediaType MEDIA_TYPE = MediaType.parse("text/plain");
            requestBody = RequestBody.create(MEDIA_TYPE, text);
        } else {
            requestBody = null;
        }
        return requestBody;
    }

    public static String getVideoThumbNialPath(Activity mActivity, Bitmap imgBitmap) {
        String path = "";
        try {
            path = Environment.getExternalStorageDirectory().toString() + "/DCIM/" + mActivity.getString(R.string.app_name) + "video.jpg";


            Bitmap croppedImage = imgBitmap;
            File imageFile = new File(path);
            FileOutputStream outputStream = new FileOutputStream(imageFile);

            Matrix matrix = new Matrix();
            croppedImage = scaleDown(croppedImage, 500, true);
            if (croppedImage.getWidth() > croppedImage.getHeight()) {
                croppedImage = Bitmap.createBitmap(croppedImage, 0, 0, croppedImage.getWidth(), croppedImage.getHeight(), matrix, true);
            }

            int quality = 100;
            croppedImage.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            // openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }

        return path;
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize, boolean filter) {
        Bitmap newBitmap;
        if (realImage.getWidth() > maxImageSize || realImage.getHeight() > maxImageSize) {
            float ratio = Math.min(maxImageSize / realImage.getWidth(), maxImageSize / realImage.getHeight());
            int width = Math.round(ratio * realImage.getWidth());
            int height = Math.round(ratio * realImage.getHeight());
            newBitmap = Bitmap.createScaledBitmap(realImage, width, height, filter);
        } else {
            newBitmap = Bitmap.createScaledBitmap(realImage, realImage.getWidth(), realImage.getHeight(), filter);
        }
        return newBitmap;
    }

    public static RequestBody videoToBody(String text) {
        RequestBody requestBody;
        if (text != null && text.length() > 0) {
            MediaType MEDIA_TYPE = MediaType.parse("video/*");
            File file = new File(text);
            requestBody = RequestBody.create(MEDIA_TYPE, file);
        } else {
            requestBody = null;
        }
        return requestBody;
    }




    public static void shareContent(Activity mActivity, int requestCode) {
        Intent intentShare = new Intent();
        intentShare.setAction(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        String shareContact = "Hi, i have found this article on Ambit App , for visit this article follow this below link www.ambit.com" /*+ Utility.DOWNLOAD_APP_LINK*/;
        intentShare.putExtra(Intent.EXTRA_TEXT, shareContact);
        mActivity.startActivityForResult(Intent.createChooser(intentShare, "Select"), requestCode);
    }

    public static void shareContent(Activity mActivity) {
        Intent intentShare = new Intent();
        intentShare.setAction(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        String shareContact = "Hi, i have found this article on Ambit App , for visit this article follow this below link www.ambit.com" /*+ Utility.DOWNLOAD_APP_LINK*/;
        intentShare.putExtra(Intent.EXTRA_TEXT, shareContact);
        mActivity.startActivity(Intent.createChooser(intentShare, "Select"));
    }

    public static void shareContent(Activity mActivity, String content, int requestCode) {
        Intent intentShare = new Intent();
        intentShare.setAction(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        String shareContact = content + "";
        intentShare.putExtra(Intent.EXTRA_TEXT, shareContact);
        mActivity.startActivityForResult(Intent.createChooser(intentShare, "Select"), requestCode);
    }

    public static void openAppInPlayStore(Activity mActivity) {
        final String appPackageName = mActivity.getPackageName();
        try {
            mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static String compressImage(String imageUri, Context mContext) {

        String filePath = getRealPathFromURI(imageUri, mContext);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }


    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    private static String getRealPathFromURI(String contentURI,Context mContext) {
        Uri contentUri  = Uri.parse(contentURI);
        Cursor cursor ;

        cursor = mContext.getContentResolver().query(contentUri, null,
                null, null, null);

        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor
                    .getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public static void alertButtonTextColor(AlertDialog alertDialog) {
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#444444"));
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.parseColor("#444444"));
        alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#444444"));
    }
}
