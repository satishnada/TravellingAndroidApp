package com.moderndeveloper.fllawi.utils;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.moderndeveloper.fllawi.model.LoginUserData;

public class PreferenceData {

    public static boolean isIntroductionVisitComplete() {
        return SharedPreferenceUtil.contains(AppConstant.isIntroductionVisitComplete)
                && (SharedPreferenceUtil.getBoolean(AppConstant.isIntroductionVisitComplete, false));
    }

    public static void saveUserData(LoginUserData userLoginData){
        Gson gson = new Gson();
        String userData = gson.toJson(userLoginData);
        SharedPreferenceUtil.putValue(AppConstant.USER_DATA,userData);
    }

    public static LoginUserData getUserData(){
        Gson gson = new Gson();
        String userData = SharedPreferenceUtil.getString(AppConstant.USER_DATA,"");
        return gson.fromJson(userData,LoginUserData.class);
    }

    public static boolean isLogin() {
        return !TextUtils.isEmpty(SharedPreferenceUtil.getString(AppConstant.USER_DATA,""));
    }

    public static void setProfilePic(String profilePic){
        SharedPreferenceUtil.putValue(AppConstant.USER_PROFILE_PIC,profilePic);
    }

    public static void setUserName(String userName){
        SharedPreferenceUtil.putValue(AppConstant.USER_NAME,userName);
    }

    public static String getUserName(){
        return SharedPreferenceUtil.getString(AppConstant.USER_NAME,"");
    }

    public static void setFromDate(String fromDate){
        SharedPreferenceUtil.putValue(AppConstant.FROM_DATE,fromDate);
    }

    public static String getFromDate(){
        return SharedPreferenceUtil.getString(AppConstant.FROM_DATE,"");
    }

    public static void setToDate(String toDate){
        SharedPreferenceUtil.putValue(AppConstant.TO_DATE,toDate);
    }

    public static String getToDate(){
        return SharedPreferenceUtil.getString(AppConstant.TO_DATE,"");
    }

    public static void setRoom(String room){
        SharedPreferenceUtil.putValue(AppConstant.ROOM,room);
    }

    public static String getRoom(){
        return SharedPreferenceUtil.getString(AppConstant.ROOM,"");
    }

    public static void setMember(String member){
        SharedPreferenceUtil.putValue(AppConstant.MEMBER,member);
    }

    public static String getMember(){
        return SharedPreferenceUtil.getString(AppConstant.MEMBER,"");
    }

    public static String getUserProfilePic(){
        return SharedPreferenceUtil.getString(AppConstant.USER_PROFILE_PIC,"");
    }
}
