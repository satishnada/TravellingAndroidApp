package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.enums.WEB_VIEW_CONTENT_TYPE;
import com.profdeveloper.fllawi.utils.AppConstant;

public class WebViewActivity extends BaseActivity {

    private final String TAG = WebViewActivity.class.getSimpleName();
    private View view;
    private WebView webView;
    private int contentType = WEB_VIEW_CONTENT_TYPE.PRIVACY_POLICY.value;
    private ProgressBar progressBar;

    @Override
    public void setLayoutView() {

        view = LayoutInflater.from(this).inflate(R.layout.activity_web_view, llContainer);
        webView = (WebView) view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        progressBar = view.findViewById(R.id.progressBar);

        isHomeRunning = false;
        if (getIntent().getExtras() != null) {
            if (getIntent().hasExtra(AppConstant.EXT_CONTENT_TYPE)) {
                contentType = getIntent().getIntExtra(AppConstant.EXT_CONTENT_TYPE,0);
            }
        }

        if (contentType == WEB_VIEW_CONTENT_TYPE.PRIVACY_POLICY.value){
            tvTopTitle.setText(R.string.privacy_and_policy);
            webView.loadUrl("https://www.fllawi.com/how-it-work");
        }else if (contentType == WEB_VIEW_CONTENT_TYPE.HELP_AND_SUPPORT.value){
            tvTopTitle.setText(R.string.help_and_support);
            webView.loadUrl("https://www.fllawi.com/faq");
        }else if (contentType == WEB_VIEW_CONTENT_TYPE.TERMS_AND_CONDITIONS.value){
            tvTopTitle.setText(R.string.term_and_conditions);
            webView.loadUrl("https://www.fllawi.com/legal/terms");
        }else{
            tvTopTitle.setText(R.string.overview);
            webView.loadUrl("https://www.fllawi.com/about");
        }

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void initialization() {
        tvTopTitle.setTextColor(ContextCompat.getColor(mActivity,R.color.white));
        tvTopTitle.setVisibility(View.VISIBLE);
        checkLocal();
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
