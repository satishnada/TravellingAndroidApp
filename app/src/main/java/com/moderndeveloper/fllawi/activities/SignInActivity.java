package com.moderndeveloper.fllawi.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.model.LoginRequestResponse;
import com.moderndeveloper.fllawi.retrofit.WebServiceCaller;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.PreferenceData;
import com.moderndeveloper.fllawi.utils.SharedPreferenceUtil;
import com.moderndeveloper.fllawi.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends BaseActivity {

    private LinearLayout lnrBase;
    private View view;
    private EditText edtEmail,edtPassword;
    private TextView tvSignIn,tvForgotPassword,tvNewAccount;
    private static final int SIGN_IN = 1;

    @Override
    public void setLayoutView() {

        view = LayoutInflater.from(this).inflate(R.layout.activity_signin, llContainer);
        lnrBase = (LinearLayout) findViewById(R.id.llBase);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);
        tvSignIn = (TextView) view.findViewById(R.id.tvSignIn);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        tvNewAccount = view.findViewById(R.id.tvNewAccount);

        tvSignIn.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        tvNewAccount.setOnClickListener(this);

    }

    @Override
    public void initialization() {
        tvTopTitle.setText("");
        hideTopBar();
        hideTopShadow();
        llTop.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.transparent));
        ivTopBack.setImageResource(R.drawable.ic_top_back_white);
        ivTopBack.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                Call<LoginRequestResponse> call = service.userLogin(edtEmail.getText().toString().trim(),edtPassword.getText().toString());
                call.enqueue(new Callback<LoginRequestResponse>() {
                    @Override
                    public void onResponse(Call<LoginRequestResponse> call, Response<LoginRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
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
