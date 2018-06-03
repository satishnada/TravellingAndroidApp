package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.model.LoginRequestResponse;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.SharedPreferenceUtil;
import com.profdeveloper.fllawi.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends BaseActivity {

    private LinearLayout lnrBase;
    private View view;
    private EditText edtEmail,edtPassword;
    private TextView tvSignIn,tvForgotPassword,tvNewAccount,tvEnglish,tvArabic,tvLoginTitle;
    private static final int SIGN_IN = 1;

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_signin, llContainer);
        setView();
        tvSignIn.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        tvNewAccount.setOnClickListener(this);
        tvEnglish.setOnClickListener(this);
        tvArabic.setOnClickListener(this);
    }

    private void setView(){
        lnrBase = (LinearLayout) findViewById(R.id.llBase);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);
        tvSignIn = (TextView) view.findViewById(R.id.tvSignIn);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        tvNewAccount = view.findViewById(R.id.tvNewAccount);
        tvLoginTitle = view.findViewById(R.id.tvLoginTitle);

        tvForgotPassword.setText(getString(R.string.forgot_password));
        tvNewAccount.setText(getString(R.string.create_account));
        tvTopTitle.setText(getString(R.string.login));

        tvEnglish = view.findViewById(R.id.tvEnglish);
        tvArabic = view.findViewById(R.id.tvArabic);
    }

    @Override
    public void initialization() {
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        tvTopTitle.setText("");
        hideTopBar();
        hideTopShadow();
        llTop.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.transparent));
        ivTopBack.setImageResource(R.drawable.ic_top_back_white);
        ivTopBack.setVisibility(View.GONE);
        checkLocal();
        tvEnglish.setBackground(getResources().getDrawable(R.drawable.rounded_search_text_bg));
        setLanguage();

    }

    private void setLanguage(){
        if (PreferenceData.getUserLanguage() != null){
            if (PreferenceData.getUserLanguage().equalsIgnoreCase("ar")){
                changeLanguage("ar");
                tvEnglish.setText("Eng");
            }else{
                tvEnglish.setText("التسجيل");
                changeLanguage("en");
            }
        }else{
            tvEnglish.setText("التسجيل");
            changeLanguage("en");
        }
    }

    private void setLanguageOnClick(){
        if (PreferenceData.getUserLanguage() != null){
            if (PreferenceData.getUserLanguage().equalsIgnoreCase("ar")){
                changeLanguage("en");
                PreferenceData.setUserLang("en");
                tvEnglish.setText("التسجيل");
            }else{
                tvEnglish.setText("Eng");
                PreferenceData.setUserLang("ar");
                changeLanguage("ar");
            }
        }else{
            PreferenceData.setUserLang("en");
            tvEnglish.setText("التسجيل");
            changeLanguage("en");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvEnglish:
                setLanguageOnClick();
                recreate();
                break;
            case R.id.tvArabic:
                //tvArabic.setBackground(getResources().getDrawable(R.drawable.rounded_search_text_bg));
                //tvEnglish.setBackgroundColor(getResources().getColor(R.color.transparent));
                tvEnglish.setText("Eng");
                changeLanguage("ar");
               recreate();

                break;
            // Top Right Menu Drawer Button Click
            case R.id.ivTopBack:
                finish();
                break;
            case R.id.tvSignIn:
                hideSoftKeyboard();
                if (isValidate()) {
                    userLogin();
                }
                break;
            case R.id.tvForgotPassword:
                Intent forgotIntent = new Intent(mActivity, ForgotPasswordActivity.class);
                forgotIntent.putExtra(AppConstant.EXT_USER_EMAIL,edtEmail.getText().toString());
                startActivity(forgotIntent);
                finish();
                goNext();
                break;
            case R.id.tvNewAccount:
                Intent signUpIntent = new Intent(mActivity, SignUpActivity.class);
                startActivity(signUpIntent);
                finish();
                goNext();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private boolean isValidate() {
        if (TextUtils.isEmpty(edtEmail.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_email));
            return false;
        } else if (!Utility.isValidEmail(edtEmail.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_email2));
            return false;
        } else if (TextUtils.isEmpty(edtPassword.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_password));
            return false;
        }

        return true;
    }

    private void userLogin() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<LoginRequestResponse> call = service.userLogin(Utility.getLocale(),edtEmail.getText().toString().trim(),edtPassword.getText().toString());
                call.enqueue(new Callback<LoginRequestResponse>() {
                    @Override
                    public void onResponse(Call<LoginRequestResponse> call, Response<LoginRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                PreferenceData.setLogin(true);
                                PreferenceData.setUserName(response.body().getLoginUserData().getFirstName() +" "+ response.body().getLoginUserData().getLastName());
                                PreferenceData.setProfilePic(response.body().getLoginUserData().getProfileImage());
                                PreferenceData.saveUserData(response.body().getLoginUserData());
                                startApp();
                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(getResources().getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<LoginRequestResponse> call, Throwable t) {
                        Utility.log("" + t.getMessage());
                        hideProgress();
                        Utility.showError(t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startApp() {
        Intent nextIntent = new Intent(mActivity, HomeActivity.class);
        startActivity(nextIntent);
        goNext();
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
        goPrevious();
    }
}
