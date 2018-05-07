package com.moderndeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.model.CommonRequestResponse;
import com.moderndeveloper.fllawi.retrofit.WebServiceCaller;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgotPasswordActivity extends BaseActivity {

    private View view;
    private TextView tvSubmit;
    private EditText edtEmail;

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_forgot_password, llContainer);

        edtEmail = view.findViewById(R.id.edtEmail);
        tvSubmit = view.findViewById(R.id.tvSubmit);
        isHomeRunning = false;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            edtEmail.setText(bundle.getString(AppConstant.EXT_USER_EMAIL));
            edtEmail.setSelection(bundle.getString(AppConstant.EXT_USER_EMAIL).length());
        }

        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void initialization() {
        tvTopTitle.setText(R.string.forgotPassword);
        ivTopBack.setImageResource(R.drawable.back_arrow);
        ivTopBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Top Right Menu Drawer Button Click
            case R.id.ivTopBack:
                onBackPressed();
                break;
            case R.id.tvSubmit:
                if (isValidate()){
                    forgotPassword();
                }
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void forgotPassword() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(mActivity);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.userForgotPassword(edtEmail.getText().toString().trim());
                call.enqueue(new Callback<CommonRequestResponse>() {
                    @Override
                    public void onResponse(Call<CommonRequestResponse> call, Response<CommonRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                Utility.showError(response.body().getMessage());
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        onBackPressed();
                                    }
                                },1000);
                            } else {
                                Utility.showError(response.body().getMessage());
                            }
                        } else {
                            Utility.showError(response.body().getMessage());
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<CommonRequestResponse> call, Throwable t) {
                        Utility.log("" + t.getMessage());
                        Utility.hideProgress();
                        Utility.showError(t.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValidate() {
        if (TextUtils.isEmpty(edtEmail.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_email));
            return false;
        } else if (!Utility.isValidEmail(edtEmail.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_email2));
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Intent loginIntent = new Intent(mActivity, SignInActivity.class);
        startActivity(loginIntent);
        finish();
        goPrevious();
    }
}
