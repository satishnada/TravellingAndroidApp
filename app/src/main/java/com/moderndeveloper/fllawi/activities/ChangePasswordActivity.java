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
import com.moderndeveloper.fllawi.utils.Utility;

import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends BaseActivity {

    private View view;
    private EditText edtOldPassword,edtNewPassword,edtConfirmPassword;
    private TextView tvUpdatePassword;

    @Override
    public void setLayoutView() {

        view = LayoutInflater.from(this).inflate(R.layout.activity_change_password, llContainer);
        edtOldPassword = view.findViewById(R.id.edtOldPassword);
        edtNewPassword = view.findViewById(R.id.edtNewPassword);
        edtConfirmPassword = view.findViewById(R.id.edtConfirmPassword);

        tvUpdatePassword = view.findViewById(R.id.tvUpdatePassword);

        tvUpdatePassword.setOnClickListener(this);
    }

    @Override
    public void initialization() {
        tvTopTitle.setText(getString(R.string.change_password));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Top Right Menu Drawer Button Click
            case R.id.tvUpdatePassword:
                    if (isValidate()){
                        userChangePassword();
                    }
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private boolean isValidate() {
        if (TextUtils.isEmpty(edtOldPassword.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_old_password));
            return false;
        } else if (TextUtils.isEmpty(edtNewPassword.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_new_password));
            return false;
        }  else if (edtNewPassword.getText().toString().trim().length() < 6) {
            Utility.showError(getString(R.string.valid_password_length));
            return false;
        } else if (TextUtils.isEmpty(edtConfirmPassword.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_confirm_new_password));
            return false;
        } else if (!edtNewPassword.getText().toString().trim().equalsIgnoreCase(edtConfirmPassword.getText().toString().trim())){
            Utility.showError(getString(R.string.valid_password_match));
            return false;
        }

        return true;
    }

    private void userChangePassword() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<LoginRequestResponse> call = service.userChangePassword(PreferenceData.getUserData().getId()+"",edtOldPassword.getText().toString(),edtConfirmPassword.getText().toString());
                call.enqueue(new Callback<LoginRequestResponse>() {
                    @Override
                    public void onResponse(Call<LoginRequestResponse> call, Response<LoginRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                Utility.showError(response.body().getMsg());
                                new android.os.Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startApp();
                                    }
                                },800);

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
        Intent nextIntent = new Intent(mActivity, HomeActivity.class);
        startActivity(nextIntent);
        finish();
        goPrevious();
    }
}
