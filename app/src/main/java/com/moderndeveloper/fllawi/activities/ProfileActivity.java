package com.moderndeveloper.fllawi.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.OnCommonAdapterItemClickListener;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.adapters.CommonListAdapter;
import com.moderndeveloper.fllawi.model.ArrBusiness;
import com.moderndeveloper.fllawi.model.ArrCountry;
import com.moderndeveloper.fllawi.model.ArrCurrency;
import com.moderndeveloper.fllawi.model.ArrData;
import com.moderndeveloper.fllawi.model.ArrFunctionalLanguage;
import com.moderndeveloper.fllawi.model.ArrInterest;
import com.moderndeveloper.fllawi.model.ArrPaymentType;
import com.moderndeveloper.fllawi.model.CommonRequestResponse;
import com.moderndeveloper.fllawi.model.LoginRequestResponse;
import com.moderndeveloper.fllawi.model.LoginUserData;
import com.moderndeveloper.fllawi.model.UserProfileDataRequestResponse;
import com.moderndeveloper.fllawi.retrofit.WebServiceCaller;
import com.moderndeveloper.fllawi.retrofit.WebUtility;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.PreferenceData;
import com.moderndeveloper.fllawi.utils.SharedPreferenceUtil;
import com.moderndeveloper.fllawi.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends BaseActivity implements OnCommonAdapterItemClickListener{

    private View view;
    private ImageView ivTopProfileBack, ivTopProfileSearch;
    private TextView tvUpDateProfile;
    private ImageView ivProfilePic;
    private SimpleDateFormat dateFormatter;
    private TextView tvUserName, tvUserMobile, tvDateOfBirth, tvCountry, tvPaymentType, tvCurrency,
            tvLanguage, tvInterest;
    private EditText edtFirstName, edtLastName, edtEmail, edtMobileNumber, edtWebSite, edtAddress;
    private RadioButton radioMale, radioFemale;
    private LoginUserData loginUserData;
    private ArrayList<ArrCountry> countryList = new ArrayList<>();
    private ArrCountry sltCountry = null;
    private ArrayList<ArrPaymentType> paymentTypesList = new ArrayList<>();
    private ArrPaymentType sltPaymentType = null;
    private ArrayList<ArrCurrency> currencyList = new ArrayList<>();
    private ArrCurrency sltCurrency = null;
    private ArrayList<ArrInterest> interestList = new ArrayList<>();
    private ArrInterest sltInterest = null;
    private ArrayList<ArrBusiness> businessesList = new ArrayList<>();
    private ArrBusiness sltBusiness = null;
    private ArrayList<ArrFunctionalLanguage> languageList = new ArrayList<>();
    private ArrFunctionalLanguage sltLanguage = null;
    private int sltCountryId = 0, sltPaymentTypeId = 0, sltCurrencyId = 0,
            sltLanguageId = 0,sltInterestId = 0;
    private List<Integer> interestIdsArray = new ArrayList<>();
    private String sltGender = "M";
    private int sltType = 0;

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_profile, llContainer);
        tvUpDateProfile = view.findViewById(R.id.tvUpDateProfile);
        ivTopProfileBack = view.findViewById(R.id.ivTopProfileBack);
        ivTopProfileSearch = view.findViewById(R.id.ivTopProfileSearch);

        ivProfilePic = view.findViewById(R.id.ivProfilePic);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserMobile = view.findViewById(R.id.tvUserMobile);
        tvDateOfBirth = view.findViewById(R.id.tvDateOfBirth);
        tvCountry = view.findViewById(R.id.tvCountry);
        tvPaymentType = view.findViewById(R.id.tvPaymentType);
        tvCurrency = view.findViewById(R.id.tvCurrency);
        tvLanguage = view.findViewById(R.id.tvLanguage);
        tvInterest = view.findViewById(R.id.tvInterest);

        edtFirstName = view.findViewById(R.id.edtFirstName);
        edtLastName = view.findViewById(R.id.edtLastName);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtMobileNumber = view.findViewById(R.id.edtMobileNumber);
        edtWebSite = view.findViewById(R.id.edtWebSite);
        edtAddress = view.findViewById(R.id.edtAddress);

        radioMale = view.findViewById(R.id.radioMale);
        radioFemale = view.findViewById(R.id.radioFemale);

        ivProfilePic.setOnClickListener(this);
        ivTopProfileBack.setOnClickListener(this);
        ivTopProfileSearch.setOnClickListener(this);
        tvUpDateProfile.setOnClickListener(this);
        radioMale.setOnClickListener(this);
        radioFemale.setOnClickListener(this);
        tvDateOfBirth.setOnClickListener(this);
        tvCountry.setOnClickListener(this);
        tvPaymentType.setOnClickListener(this);
        tvCurrency.setOnClickListener(this);
        tvLanguage.setOnClickListener(this);
        tvInterest.setOnClickListener(this);

        isHomeRunning = false;
        loginUserData = PreferenceData.getUserData();
        getUserProfile(false,"");
    }

    @Override
    public void initialization() {
        hideTopBar();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvDateOfBirth:
                showDatePickerDialog();
                break;
            case R.id.radioFemale:
                sltGender = "F";

                break;
            case R.id.radioMale:

                sltGender = "M";
                break;
            case R.id.tvCountry:
                ArrayList<Object> objectArrayList = new ArrayList<>();
                for (Object obj:countryList){
                    objectArrayList.add(obj);
                }
                CommonListAdapter commonListAdapter = new CommonListAdapter(ProfileActivity.this,0,objectArrayList,this);
                openItemSelectList(this,commonListAdapter);
                break;
            case R.id.tvPaymentType:
                ArrayList<Object> objectPaymentList = new ArrayList<>();
                for (Object obj:paymentTypesList){
                    objectPaymentList.add(obj);
                }
                CommonListAdapter paymentTypeAdapter = new CommonListAdapter(ProfileActivity.this,4,objectPaymentList,this);
                openItemSelectList(this,paymentTypeAdapter);
                break;
            case R.id.tvCurrency:
                ArrayList<Object> objectCurrencyList = new ArrayList<>();
                for (Object obj:currencyList){
                    objectCurrencyList.add(obj);
                }
                CommonListAdapter currencyAdapter = new CommonListAdapter(ProfileActivity.this,2,objectCurrencyList,this);
                openItemSelectList(this,currencyAdapter);
                break;
            case R.id.tvLanguage:
                ArrayList<Object> objectLanguageList = new ArrayList<>();
                for (Object obj:languageList){
                    objectLanguageList.add(obj);
                }
                CommonListAdapter languageAdapter = new CommonListAdapter(ProfileActivity.this,1,objectLanguageList,this);
                openItemSelectList(this,languageAdapter);
                break;
            case R.id.tvInterest:
                ArrayList<Object> objectInterestList = new ArrayList<>();
                for (Object obj:interestList){
                    objectInterestList.add(obj);
                }
                CommonListAdapter interestAdapter = new CommonListAdapter(ProfileActivity.this,3,objectInterestList,this);
                openItemSelectList(this,interestAdapter);
                break;
            case R.id.ivProfilePic:
                //TODO need to get profile
                break;
            // Top Right Menu Drawer Button Click
            case R.id.ivTopProfileBack:
                closeSideMenu();
                break;
            case R.id.ivTopProfileSearch:

                break;
            case R.id.tvUpDateProfile:
                if (isValidate()) {
                    updateUserProfile();
                }
                break;
        }
    }

    private void getUserProfile(final boolean isFromUpdateProfile, final String message) {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                if (!isFromUpdateProfile) {
                    Utility.showProgress(this);
                }

                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<UserProfileDataRequestResponse> call = service.getUserProfile(WebUtility.USER_GET_PROFILE + "?user_id=" + PreferenceData.getUserData().getId());
                call.enqueue(new Callback<UserProfileDataRequestResponse>() {
                    @Override
                    public void onResponse(Call<UserProfileDataRequestResponse> call, Response<UserProfileDataRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                PreferenceData.setUserName(response.body().getArrData().getFirstName() + " " + response.body().getArrData().getLastName());
                                PreferenceData.setProfilePic(response.body().getArrData().getProfileImage());
                                setProfileData(response.body());

                                if (isFromUpdateProfile) {
                                    Utility.showError(message);
                                }

                            } else {
                                Utility.showError(response.body().getMsg());
                            }
                        } else {
                            Utility.showError(getResources().getString(R.string.message_something_wrong));
                        }
                        Utility.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<UserProfileDataRequestResponse> call, Throwable t) {
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

    private void updateUserProfile() {
        try {
            if (!Utility.isNetworkAvailable(mActivity)) {
                Utility.showError(getString(R.string.no_internet_connection));
            } else {
                Utility.showProgress(this);
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<CommonRequestResponse> call = service.updateUserProfile(PreferenceData.getUserData().getId()+"",
                        edtFirstName.getText().toString().trim(),edtLastName.getText().toString().trim(),
                        edtEmail.getText().toString().trim(),sltCountryId+"",tvDateOfBirth.getText().toString().trim(),
                        edtMobileNumber.getText().toString().trim(),edtAddress.getText().toString().trim(),sltGender,sltPaymentTypeId+"",sltCurrencyId+"",sltLanguageId+"",edtWebSite.getText().toString().trim(),"1");
                call.enqueue(new Callback<CommonRequestResponse>() {
                    @Override
                    public void onResponse(Call<CommonRequestResponse> call, Response<CommonRequestResponse> response) {
                        Utility.log(response.getClass().getSimpleName() + " : " + new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                                getUserProfile(true, response.body().getMessage());
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
        if (TextUtils.isEmpty(edtFirstName.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_first_name));
            return false;
        } else if (TextUtils.isEmpty(edtLastName.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_last_name));
            return false;
        } else if (TextUtils.isEmpty(edtEmail.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_email));
            return false;
        } else if (!Utility.isValidEmail(edtEmail.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_email2));
            return false;
        } else if (TextUtils.isEmpty(tvDateOfBirth.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_date_of_birth));
            return false;
        } else if (TextUtils.isEmpty(edtMobileNumber.getText().toString().trim())) {
            Utility.showError(getString(R.string.valid_mobile));
            return false;
        }

        return true;
    }

    private void setProfileData(UserProfileDataRequestResponse data) {
        ArrData userData = data.getArrData();

        if (userData.getEmail() != null && userData.getEmail().length() > 0) {
            edtEmail.setText(userData.getEmail());
        } else {
            edtEmail.setText("");
        }

        if (userData.getFirstName() != null && userData.getFirstName().length() > 0) {
            tvUserName.setText(userData.getFirstName() + " " + userData.getLastName());
            edtFirstName.setText(userData.getFirstName());
        } else {
            edtFirstName.setText("");
            tvUserName.setVisibility(View.INVISIBLE);
        }

        if (userData.getLastName() != null && userData.getLastName().length() > 0) {
            edtLastName.setText(userData.getLastName());
        } else {
            edtLastName.setText("");
        }

        if (userData.getMobileNo() != null && userData.getMobileNo().length() > 0) {
            edtMobileNumber.setText(userData.getMobileNo());
            tvUserMobile.setText(userData.getMobileNo());
        } else {
            edtMobileNumber.setText("");
            tvUserMobile.setText("");
            tvUserMobile.setVisibility(View.INVISIBLE);
        }

        if (userData.getBirthday() != null && userData.getBirthday().length() > 0) {
            //TODO need to convert SearchResultMainData.
            tvDateOfBirth.setText(userData.getBirthday());
        } else {
            tvDateOfBirth.setText("");
        }

        if (userData.getGender() != null && userData.getGender().length() > 0) {
            if (userData.getGender().equalsIgnoreCase("M")) {
                radioMale.performClick();
            } else {
                radioFemale.performClick();
            }
        } else {
            radioMale.performClick();
        }

        if (userData.getAddress() != null && userData.getAddress().length() > 0){
            edtAddress.setText(userData.getAddress());
        }else{
            edtAddress.setText("");
        }

        if (userData.getWebsite() != null && userData.getWebsite().length() > 0){
            edtWebSite.setText(userData.getWebsite());
        }else{
            edtWebSite.setText("");
        }

        countryList = (ArrayList<ArrCountry>) data.getArrCountry();
        paymentTypesList = (ArrayList<ArrPaymentType>) data.getArrPaymentType();
        currencyList = (ArrayList<ArrCurrency>) data.getArrCurrency();
        interestList = (ArrayList<ArrInterest>) data.getArrInterest();
        businessesList = (ArrayList<ArrBusiness>) data.getArrBusiness();
        languageList = (ArrayList<ArrFunctionalLanguage>) data.getArrFunctionalLanguage();
        interestIdsArray = userData.getInterests();
        if (countryList != null && countryList.isEmpty()) {
            for (int i = 0; i < countryList.size(); i++) {
                if (userData.getCountryId() == countryList.get(i).getId()) {
                    tvCountry.setText(countryList.get(i).getTitle());
                    sltCountryId = countryList.get(i).getId();
                    break;
                }
            }
        }

        if (paymentTypesList != null && !paymentTypesList.isEmpty()) {
            for (int i = 0; i < paymentTypesList.size(); i++) {
                if (userData.getPreferredPaymentId() == paymentTypesList.get(i).getId()) {
                    tvPaymentType.setText(paymentTypesList.get(i).getTitle());
                    sltPaymentTypeId = paymentTypesList.get(i).getId();
                    break;
                }
            }
        }

        if (currencyList != null && !currencyList.isEmpty()) {
            for (int i = 0; i < currencyList.size(); i++) {
                if (userData.getPreferredCurrencyId() == currencyList.get(i).getId()) {
                    tvCurrency.setText(currencyList.get(i).getTitle());
                    sltCurrencyId = currencyList.get(i).getId();
                    break;
                }
            }
        }

        if (languageList != null && !languageList.isEmpty()) {
            for (int i = 0; i < languageList.size(); i++) {
                if (userData.getPreferredLanguageId() == languageList.get(i).getId()) {
                    tvLanguage.setText(languageList.get(i).getTitle());
                    sltLanguageId = languageList.get(i).getId();
                    break;
                }
            }
        }

        if (interestList != null && !interestList.isEmpty() && interestIdsArray != null && !interestIdsArray.isEmpty()){
            String interest = "";

            for (int i=0;i<interestList.size();i++){
                for (int j=0;j<interestIdsArray.size();j++){
                    if (interestIdsArray.get(j) == interestList.get(i).getId()){
                        interest = interest +" "+ interestList.get(i).getTitle() +",";
                    }
                }
            }
            tvInterest.setText(interest);
        }

    }

    private void showDatePickerDialog() {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

                tvDateOfBirth.setText(dateFormatter.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setTitle("Select date");
        datePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
        if (datePickerDialog.getWindow() != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    public void openItemSelectList(Activity context, CommonListAdapter arrayListAdapter) {
        final Dialog popupWindowDialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar);

        final View layout = LayoutInflater.from(context).inflate(R.layout.dialog_select_item, null);
        popupWindowDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (popupWindowDialog.getWindow() != null) {
            popupWindowDialog.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.transparent)));
        }
        popupWindowDialog.setContentView(layout);
        popupWindowDialog.setCancelable(false);

        RecyclerView rvSelectItem = layout.findViewById(R.id.rvSelectItem);
        rvSelectItem.setLayoutManager(Utility.getLayoutManager(context));
        rvSelectItem.setAdapter(arrayListAdapter);
        ImageView ivClose = (ImageView) layout.findViewById(R.id.ivClose);
        TextView tvSubmit = layout.findViewById(R.id.tvSubmit);

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDialog.dismiss();
                if (sltType == 0){ //Country
                    tvCountry.setText(sltCountry.getTitle());
                    sltCountryId = sltCountry.getId();
                }else if (sltType == 1){ // Language
                    tvLanguage.setText(sltLanguage.getTitle());
                    sltLanguageId = sltLanguage.getId();
                }else if (sltType == 2){ // Currency
                   tvCurrency.setText(sltCurrency.getTitle());
                   sltCurrencyId = sltCurrency.getId();
                }else if (sltType == 3){ // Interest Multiple selection
                    tvInterest.setText(sltInterest.getTitle());
                    sltInterestId = sltInterest.getId();
                }else if (sltType == 4){ // Payment Type
                   tvPaymentType.setText(sltPaymentType.getTitle());
                    sltPaymentTypeId = sltPaymentType.getId();
                }
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDialog.dismiss();
            }
        });

        popupWindowDialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(mActivity, HomeActivity.class);
        startActivity(intent);
        finish();
        goPrevious();
    }

    @Override
    public void onCommonItemClickListener(int type, int position, Object object) {
        sltType = type;
        if (type == 0){ //Country
            sltCountry = (ArrCountry) object;
        }else if (type == 1){ // Language
            sltLanguage = (ArrFunctionalLanguage) object;
        }else if (type == 2){ // Currency
            sltCurrency = (ArrCurrency) object;
        }else if (type == 3){ // Interest Multiple selection
            sltInterest = (ArrInterest) object;
        }else if (type == 4){ // Payment Type
            sltPaymentType = (ArrPaymentType) object;
        }
    }
}
