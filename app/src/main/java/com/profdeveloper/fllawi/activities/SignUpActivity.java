package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.model.CommonRequestResponse;
import com.profdeveloper.fllawi.retrofit.WebServiceCaller;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends BaseActivity {

    private View view;
    private EditText edtFirstName, edtLastName, edtEmail,
            edtMobileNumber, edtPassword, edtConfirmPassword;
    private TextView tvSignUp;

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_signup, llContainer);

        edtFirstName = view.findViewById(R.id.edtFirstName);
        edtLastName = view.findViewById(R.id.edtLastName);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtMobileNumber = view.findViewById(R.id.edtMobileNumber);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtConfirmPassword = view.findViewById(R.id.edtConfirmPassword);
        tvSignUp = view.findViewById(R.id.tvSignUp);

        tvSignUp.setOnClickListener(this);

    }

    @Override
    public void initialization() {
        tvTopTitle.setText(R.string.register);
        ivTopBack.setImageResource(R.drawable.ic_back_arrow);
        ivTopBack.setVisibility(View.VISIBLE);
        checkLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Top Right Menu Drawer Button Click
            case R.id.ivTopBack:
                onBackPressed();
                break;
            case R.id.tvSignUp:
                hideSoftKeyboard();
                if (isValidate()) {
                    userRegistration();
                }
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void userRegistration() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
               Utility.showProgress(this);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.userSignUp(Utility.getLocale(),edtEmail.getText().toString().trim(),edtFirstName.getText().toString().trim(),edtLastName.getText().toString().trim(), edtPassword.getText().toString(),edtMobileNumber.getText().toString().trim());
                call.enqueue(new Callback<CommonRequestResponse>() {
                    @Override
                    public void onResponse(Call<CommonRequestResponse> call, Response<CommonRequestResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                Utility.showError(response.body().getMessage());
                                onBackPressed();
                            } else {
                                Utility.showError(response.body().getMessage());
                            }
                        } else {
                            Utility.showError(getResources().getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<CommonRequestResponse> call, Throwable t) {
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

    private boolean isValidate() {
        if (TextUtils.isEmpty(edtEmail.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_email));
            return false;
        } else if (!Utility.isValidEmail(edtEmail.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_email2));
            return false;
        } else if (TextUtils.isEmpty(edtFirstName.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_first_name));
            return false;
        } else if (TextUtils.isEmpty(edtLastName.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_last_name));
            return false;
        } else if (TextUtils.isEmpty(edtMobileNumber.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_contact_number));
            return false;
        } else if (TextUtils.isEmpty(edtPassword.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_password));
            return false;
        } else if (edtPassword.getText().toString().trim().length() < 6) {
            Utility.showError(getString(R.string.valid_password_length));
            return false;
        } else if (!edtPassword.getText().toString().trim().equalsIgnoreCase(edtConfirmPassword.getText().toString().trim())){
            Utility.showError(getString(R.string.valid_password_match));
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
