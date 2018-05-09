package com.moderndeveloper.fllawi.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moderndeveloper.fllawi.model.AccommodationDetails.AccommodationDetailsRequestResponse;
import com.moderndeveloper.fllawi.model.CalculatedPriceRequestResponse;
import com.moderndeveloper.fllawi.model.CommonRequestResponse;
import com.moderndeveloper.fllawi.model.Coupons.GetCouponRequestResponse;
import com.moderndeveloper.fllawi.model.LoginRequestResponse;
import com.moderndeveloper.fllawi.model.SearchHotel.SearchRequestResponse;
import com.moderndeveloper.fllawi.model.ThingToDo.GetThingToDoCategoryRequestResponse;
import com.moderndeveloper.fllawi.model.UserProfileDataRequestResponse;
import com.moderndeveloper.fllawi.utils.AppConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class WebServiceCaller {

    private static ApiInterface apiInterface;

    public static ApiInterface getClient() {
        if (apiInterface == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okclient = new OkHttpClient();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(WebUtility.BASE_URL)
                    .client(okclient.newBuilder()
                            .connectTimeout(100, TimeUnit.SECONDS)
                            .readTimeout(100, TimeUnit.SECONDS)
                            .writeTimeout(100, TimeUnit.SECONDS)
                            .addInterceptor(logging).build())
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();

            apiInterface = client.create(ApiInterface.class);
        }
        return apiInterface;
    }

    public interface ApiInterface {

        @FormUrlEncoded
        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @POST(WebUtility.USER_LOGIN)
        Call<LoginRequestResponse> userLogin(@Field(AppConstant.email) String email,
                                             @Field(AppConstant.password) String password);

        @FormUrlEncoded
        @POST(WebUtility.USER_SIGN_UP)
        Call<CommonRequestResponse> userSignUp(@Field(AppConstant.email) String email,
                                               @Field(AppConstant.first_name) String first_name,
                                               @Field(AppConstant.last_name) String last_name,
                                               @Field(AppConstant.password) String password,
                                               @Field(AppConstant.contact_no) String contact_no);

        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @GET
        Call<UserProfileDataRequestResponse> getUserProfile(@Url String url);

        @FormUrlEncoded
        @POST(WebUtility.USER_UPDATE_PROFILE)
        Call<CommonRequestResponse> updateUserProfile(@Field(AppConstant.user_id) String user_id,
                                                      @Field(AppConstant.first_name) String first_name,
                                                      @Field(AppConstant.last_name) String last_name,
                                                      @Field(AppConstant.email) String email,
                                                      @Field(AppConstant.country_id) String country_id,
                                                      @Field(AppConstant.birthday) String birthday,
                                                      @Field(AppConstant.mobile_no) String mobile_no,
                                                      @Field(AppConstant.address) String address,
                                                      @Field(AppConstant.gender) String gender,
                                                      @Field(AppConstant.preferred_payment_id) String preferred_payment_id,
                                                      @Field(AppConstant.preferred_currency_id) String preferred_currency_id,
                                                      @Field(AppConstant.preferred_language_id) String preferred_language_id,
                                                      @Field(AppConstant.website) String website,
                                                      @Field(AppConstant.interest) String interest);

        @FormUrlEncoded
        @POST(WebUtility.USER_FORGOT_PASSWORD)
        Call<CommonRequestResponse> userForgotPassword(@Field(AppConstant.email) String email);

        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @GET
        Call<SearchRequestResponse> searchAccommodation(@Url String url);

        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @GET
        Call<SearchRequestResponse> getDetails(@Url String url);

        @FormUrlEncoded
        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @POST(WebUtility.USER_UPDATE_PASSWORD)
        Call<LoginRequestResponse> userChangePassword(@Field(AppConstant.user_id) String user_id,
                                                      @Field(AppConstant.old_password) String old_password,
                                                      @Field(AppConstant.new_password) String new_password);

        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @GET
        Call<AccommodationDetailsRequestResponse> getAccommodationDetails(@Url String url);

        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @GET
        Call<CalculatedPriceRequestResponse> getCalculatedPrice(@Url String url);

        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @GET
        Call<CalculatedPriceRequestResponse> getReviewList(@Url String url);

        @FormUrlEncoded
        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @POST(WebUtility.ACCOMMODATION_REVIEW_CREATE)
        Call<CommonRequestResponse> addReview(@Field(AppConstant.user_id) String user_id,
                                              @Field(AppConstant.accomodation_id) String accomodation_id,
                                              @Field(AppConstant.rating) String rating,
                                              @Field(AppConstant.message) String message,
                                              @Field(AppConstant.name) String name);

        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @GET
        Call<GetCouponRequestResponse> searchCoupon(@Url String url);

        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @GET
        Call<GetThingToDoCategoryRequestResponse> getThingToDoCategory(@Url String url);

        @Headers("local:" + AppConstant.LANGUAGE_ENGLISH + "")
        @GET
        Call<GetCouponRequestResponse> getCouponDetails(@Url String url);

    }
}
