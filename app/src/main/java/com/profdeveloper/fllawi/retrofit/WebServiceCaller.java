package com.profdeveloper.fllawi.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.profdeveloper.fllawi.model.AccommodationBooking.AccommodationBookingRequestResponse;
import com.profdeveloper.fllawi.model.AccommodationDetails.AccommodationDetailsRequestResponse;
import com.profdeveloper.fllawi.model.Addons.AddonsRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistory.BookingHistoryDetailRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistory.BookingHistoryRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistoryCoupon.BookingHistoryCouponRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistoryCoupon.BookingHistoryDetailCouponRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistoryThingToDo.BookingHistoryDetailThingToDoRequestResponse;
import com.profdeveloper.fllawi.model.BookingHistoryThingToDo.BookingHistoryThingToDoRequestResponse;
import com.profdeveloper.fllawi.model.CalculatedPriceRequestResponse;
import com.profdeveloper.fllawi.model.CommonRequestResponse;
import com.profdeveloper.fllawi.model.Coupons.CouponRequestResponse;
import com.profdeveloper.fllawi.model.Coupons.GetCouponRequestResponse;
import com.profdeveloper.fllawi.model.InitPaymentRequestResponse;
import com.profdeveloper.fllawi.model.LoginRequestResponse;
import com.profdeveloper.fllawi.model.Reviews.ReviewsRequestResponse;
import com.profdeveloper.fllawi.model.SearchHotel.SearchRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDo.GetThingToDoCategoryRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDoDetails.ThingToDoDetailRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDoPriceCalcu.ThingToDoCalculatePriceRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDoSearch.GetThingToDoRequestResponse;
import com.profdeveloper.fllawi.model.ThingToDoTimeSlot.ThingToDoTimeSlotRequestResponse;
import com.profdeveloper.fllawi.model.UserProfileDataRequestResponse;
import com.profdeveloper.fllawi.model.couponDetails.GetCouponDetailsRequestResponse;
import com.profdeveloper.fllawi.utils.AppConstant;

import java.util.concurrent.TimeUnit;

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
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
        @POST(WebUtility.USER_LOGIN)
        Call<LoginRequestResponse> userLogin(@Header("locale") String locale,
                                             @Field(AppConstant.email) String email,
                                             @Field(AppConstant.password) String password);

        @FormUrlEncoded
        @POST(WebUtility.USER_SIGN_UP)
        Call<CommonRequestResponse> userSignUp(@Header("locale") String locale,
                                               @Field(AppConstant.email) String email,
                                               @Field(AppConstant.first_name) String first_name,
                                               @Field(AppConstant.last_name) String last_name,
                                               @Field(AppConstant.password) String password,
                                               @Field(AppConstant.contact_no) String contact_no);

        @GET
        Call<UserProfileDataRequestResponse> getUserProfile(@Header("locale") String locale,
                                                            @Url String url);

        @Multipart
        @POST(WebUtility.USER_UPDATE_PROFILE)
        Call<CommonRequestResponse> updateUserProfile(@Header("locale") String locale,
                                                      @Part(AppConstant.user_id) RequestBody user_id,
                                                      @Part(AppConstant.first_name) RequestBody first_name,
                                                      @Part(AppConstant.last_name) RequestBody last_name,
                                                      @Part(AppConstant.email) RequestBody email,
                                                      @Part(AppConstant.country_id) RequestBody country_id,
                                                      @Part(AppConstant.birthday) RequestBody birthday,
                                                      @Part(AppConstant.mobile_no) RequestBody mobile_no,
                                                      @Part(AppConstant.address) RequestBody address,
                                                      @Part(AppConstant.gender) RequestBody gender,
                                                      @Part(AppConstant.preferred_payment_id) RequestBody preferred_payment_id,
                                                      @Part(AppConstant.preferred_currency_id) RequestBody preferred_currency_id,
                                                      @Part(AppConstant.preferred_language_id) RequestBody preferred_language_id,
                                                      @Part(AppConstant.website) RequestBody website,
                                                      @Part(AppConstant.interest) RequestBody interest,
                                                      @Part("profile_image\"; filename=\"profile_image.png\" ") RequestBody profile_image,
                                                      @Part("id_proof\"; filename=\"id_proof.png\" ") RequestBody id_proof);

        @Multipart
        @POST(WebUtility.BOOKING_UPLOAD_ACCOMMODATION_BANK_RECEIPT)
        Call<CommonRequestResponse> uploadAccommodationReceipt(@Part(AppConstant.booking_id) RequestBody booking_id,
                                                               @Part("bank_receipt\"; filename=\"bank_receipt.png\" ") RequestBody bank_receipt);

        @Multipart
        @POST(WebUtility.BOOKING_UPLOAD_THING_TO_DO_BANK_RECEIPT)
        Call<CommonRequestResponse> uploadThingToDoReceipt(@Part(AppConstant.booking_id) RequestBody booking_id,
                                                           @Part("bank_receipt\"; filename=\"bank_receipt.png\" ") RequestBody bank_receipt);

        @Multipart
        @POST(WebUtility.BOOKING_UPLOAD_COUPON_BANK_RECEIPT)
        Call<CommonRequestResponse> uploadCouponReceipt(@Part(AppConstant.booking_id) RequestBody booking_id,
                                                        @Part("bank_receipt\"; filename=\"bank_receipt.png\" ") RequestBody bank_receipt);

        @FormUrlEncoded
        @POST(WebUtility.USER_FORGOT_PASSWORD)
        Call<CommonRequestResponse> userForgotPassword(@Header("locale") String locale,
                                                       @Field(AppConstant.email) String email);

        @FormUrlEncoded
        @POST(WebUtility.APPLY_COUPON)
        Call<CouponRequestResponse> applyCoupon(@Header("locale") String locale,
                                                @Field(AppConstant.coupon_code) String coupon_code);

        @GET
        Call<SearchRequestResponse> searchAccommodation(@Header("locale") String locale,
                                                        @Url String url);

        @GET
        Call<SearchRequestResponse> getDetails(@Header("locale") String locale,
                                               @Url String url);

        @FormUrlEncoded
        @POST(WebUtility.USER_UPDATE_PASSWORD)
        Call<LoginRequestResponse> userChangePassword(@Header("locale") String locale,
                                                      @Field(AppConstant.user_id) String user_id,
                                                      @Field(AppConstant.old_password) String old_password,
                                                      @Field(AppConstant.new_password) String new_password);

        @GET
        Call<AccommodationDetailsRequestResponse> getAccommodationDetails(@Header("locale") String locale,
                                                                          @Url String url);

        @GET
        Call<CalculatedPriceRequestResponse> getCalculatedPrice(@Header("locale") String locale,
                                                                @Url String url);

        @GET
        Call<CalculatedPriceRequestResponse> getCalculatedCouponPrice(@Header("locale") String locale, @Url String url);

        @GET
        Call<ReviewsRequestResponse> getAccommodationReviewList(@Header("locale") String locale,
                                                                @Url String url);

        @GET
        Call<ReviewsRequestResponse> getCouponReviewList(@Header("locale") String locale,
                                                         @Url String url);

        @GET
        Call<ReviewsRequestResponse> getThingToDoReviewList(@Header("locale") String locale,
                                                            @Url String url);

        @FormUrlEncoded
        @POST(WebUtility.ACCOMMODATION_REVIEW_CREATE)
        Call<CommonRequestResponse> addReviewAccommodation(@Header("locale") String locale,
                                                           @Field(AppConstant.user_id) String user_id,
                                                           @Field(AppConstant.accomodation_id) String accomodation_id,
                                                           @Field(AppConstant.rating) String rating,
                                                           @Field(AppConstant.message) String message,
                                                           @Field(AppConstant.name) String name);

        @FormUrlEncoded
        @POST(WebUtility.THING_TO_REVIEW_CREATE)
        Call<CommonRequestResponse> addReviewThingToDo(@Header("locale") String locale,
                                                       @Field(AppConstant.user_id) String user_id,
                                                       @Field(AppConstant.thingtodo_id) String thingtodo_id,
                                                       @Field(AppConstant.rating) String rating,
                                                       @Field(AppConstant.message) String message,
                                                       @Field(AppConstant.name) String name);

        @FormUrlEncoded
        @POST(WebUtility.COUPON_REVIEW_CREATE)
        Call<CommonRequestResponse> addReviewCoupon(@Header("locale") String locale,
                                                    @Field(AppConstant.user_id) String user_id,
                                                    @Field(AppConstant.coupon_id) String coupon_id,
                                                    @Field(AppConstant.rating) String rating,
                                                    @Field(AppConstant.message) String message,
                                                    @Field(AppConstant.name) String name);

        @GET
        Call<GetCouponRequestResponse> searchCoupon(@Header("locale") String locale,
                                                    @Url String url);

        @GET
        Call<AddonsRequestResponse> getAccommodationAddons(@Header("locale") String locale,
                                                           @Url String url);

        @GET
        Call<GetThingToDoCategoryRequestResponse> getThingToDoCategory(@Header("locale") String locale,
                                                                       @Url String url);

        @GET
        Call<GetThingToDoRequestResponse> searchThingToDo(@Header("locale") String locale,
                                                          @Url String url);

        @GET
        Call<GetCouponDetailsRequestResponse> getCouponDetails(@Header("locale") String locale,
                                                               @Url String url);

        @GET
        Call<ThingToDoDetailRequestResponse> getThingToDoDetails(@Header("locale") String locale,
                                                                 @Url String url);

        @GET
        Call<AccommodationBookingRequestResponse> getAccommodationBookingCalculation(@Header("locale") String locale,
                                                                                     @Url String url);

        @GET
        Call<AccommodationBookingRequestResponse> getCouponBookingCalculation(@Header("locale") String locale,
                                                                              @Url String url);

        @GET
        Call<ThingToDoTimeSlotRequestResponse> getThingToDoTimeSlot(@Header("locale") String locale,
                                                                    @Url String url);

        @GET
        Call<ThingToDoCalculatePriceRequestResponse> getThingToDoCalculatePrice(@Header("locale") String locale,
                                                                                @Url String url);

        @FormUrlEncoded
        @POST(WebUtility.ACCOMMODATION_INIT_PAYMENT)
        Call<InitPaymentRequestResponse> accommodationInitPayment(@Header("locale") String locale,
                                                                  @Field(AppConstant.payment_mode) String payment_mode,
                                                                  @Field(AppConstant.accomodation_id) String accomodation_id,
                                                                  @Field(AppConstant.adults) String adults,
                                                                  @Field(AppConstant.kids) String kids,
                                                                  @Field(AppConstant.from_date) String from_date,
                                                                  @Field(AppConstant.to_date) String to_date,
                                                                  @Field(AppConstant.CALCULATION_AFTER_DISCOUNT) String calAfterDis,
                                                                  @Field(AppConstant.CALCULATION_DISCOUNT_PERCENT) String calDisPer,
                                                                  @Field(AppConstant.CALCULATION_DISCOUNT_AMOUNT) String calDisAmount,
                                                                  @Field(AppConstant.user_id) int user_id,
                                                                  @Field(AppConstant.applied_coupon) String appliedCoupon,
                                                                  @Field(AppConstant.applied_coupon_id) String appliedCouponId,
                                                                  @Field(AppConstant.addons) String addons,
                                                                  @Field(AppConstant.result) String result,
                                                                  @Field(AppConstant.response) String response);

//        @Field(AppConstant.addons) String addons
                                                                  /*  @Field(AppConstant.applied_coupon[is_applyied]) String applied_coupon,
                                                            @Field(AppConstant.applied_coupon[coupon_code_row_id]) String applied_coupon,*/

        @FormUrlEncoded
        @POST(WebUtility.COUPON_INIT_PAYMENT)
        Call<InitPaymentRequestResponse> couponInitPayment(@Header("locale") String locale,
                                                           @Field(AppConstant.payment_mode) String payment_mode,
                                                           @Field(AppConstant.coupon_id) String coupon_id,
                                                           @Field(AppConstant.qty) String qty,
                                                           @Field(AppConstant.CALCULATION_AFTER_DISCOUNT) String calAfterDis,
                                                           @Field(AppConstant.CALCULATION_DISCOUNT_PERCENT) String calDisPer,
                                                           @Field(AppConstant.CALCULATION_DISCOUNT_AMOUNT) String calDisAmount,
                                                           @Field(AppConstant.user_id) int user_id);

        /*     @POST(WebUtility.THING_TO_DO_INIT_PAYMENT)
             Call<ResponseBody> thingToDoInitPayment(@Header("locale") String locale,@Body RequestBody requestBody);
     */
        @FormUrlEncoded
        @POST(WebUtility.THING_TO_DO_INIT_PAYMENT)
        Call<InitPaymentRequestResponse> thingToDoInitPayment(@Header("locale") String locale,
                                                              @Field(AppConstant.payment_mode) String payment_mode,
                                                              @Field(AppConstant.thing_to_do_id) String thing_to_do_id,
                                                              @Field(AppConstant.adults) String adults,
                                                              @Field(AppConstant.kids) String kids,
                                                              @Field(AppConstant.schedule_date) String schedule_date,
                                                              @Field(AppConstant.schedule_type) String schedule_type,
                                                              @Field(AppConstant.user_id) int user_id,
                                                              @Field(AppConstant.addons_t) String addons,
                                                              @Field(AppConstant.CALCULATION_AFTER_DISCOUNT) String afterDiscount,
                                                              @Field(AppConstant.ARR_SCHEDULE_START_TIME) String startTime,
                                                              @Field(AppConstant.ARR_SCHEDULE_END_TIME) String endTime);

        /*@Field(AppConstant.arr_calculation_break_down) String arr_calculation_break_down,
        @Field(AppConstant.arr_schedule_time) String scheduleJson,
        @Field(AppConstant.applied_coupon_t) String applied_couponJson,
        @Field(AppConstant.result) String result,
        @Field(AppConstant.response) String response*/
        @FormUrlEncoded
        @POST(WebUtility.ACCOMMODATION_BOOKING_UPDATE)
        Call<CommonRequestResponse> accommodationUpdateBooking(@Header("locale") String locale,
                                                               @Field(AppConstant.order_id) String order_id,
                                                               @Field(AppConstant.response) String response,
                                                               @Field(AppConstant.result) String result);

        @FormUrlEncoded
        @POST(WebUtility.BOOKING_HISTORY_ACCOMMODATION_LIST)
        Call<BookingHistoryRequestResponse> getBookingHistoryList(@Header("locale") String locale,
                                                                  @Field(AppConstant.type) String type,
                                                                  @Field(AppConstant.user_id) String user_id);

        @FormUrlEncoded
        @POST(WebUtility.BOOKING_HISTORY_THING_TO_DO_LIST)
        Call<BookingHistoryThingToDoRequestResponse> getBookingHistoryThingToDoList(@Header("locale") String locale,
                                                                                    @Field(AppConstant.type) String type,
                                                                                    @Field(AppConstant.user_id) String user_id);

        @FormUrlEncoded
        @POST(WebUtility.BOOKING_HISTORY_COUPON_LIST)
        Call<BookingHistoryCouponRequestResponse> getBookingHistoryCouponList(@Header("locale") String locale,
                                                                              @Field(AppConstant.type) String type,
                                                                              @Field(AppConstant.user_id) String user_id);

        @FormUrlEncoded
        @POST(WebUtility.BOOKING_HISTORY_ACCOMMODATION_DETAIL)
        Call<BookingHistoryDetailRequestResponse> getBookingHistoryDetails(@Header("locale") String locale,
                                                                           @Field(AppConstant.customer_id) String customer_id,
                                                                           @Field(AppConstant.booking_id) String booking_id);

        @FormUrlEncoded
        @POST(WebUtility.BOOKING_HISTORY_THING_TO_DO_DETAIL)
        Call<BookingHistoryDetailThingToDoRequestResponse> getBookingHistoryThingToDoDetails(@Header("locale") String locale,
                                                                                             @Field(AppConstant.customer_id) String customer_id,
                                                                                             @Field(AppConstant.booking_id) String booking_id);

        @FormUrlEncoded
        @POST(WebUtility.BOOKING_COUPON_CANCEL)
        Call<CommonRequestResponse> cancelCouponBooking(@Header("locale") String locale,
                                                        @Field(AppConstant.booking_id) String booking_id,
                                                        @Field(AppConstant.user_id) String user_id);

        @FormUrlEncoded
        @POST(WebUtility.BOOKING_THING_TO_DO_CANCEL)
        Call<CommonRequestResponse> cancelThingToDoBooking(@Header("locale") String locale,
                                                           @Field(AppConstant.booking_id) String booking_id,
                                                           @Field(AppConstant.user_id) String user_id);

        @FormUrlEncoded
        @POST(WebUtility.BOOKING_ACCOMMODATION_CANCEL)
        Call<CommonRequestResponse> cancelAccommodationBooking(@Header("locale") String locale,
                                                               @Field(AppConstant.booking_id) String booking_id,
                                                               @Field(AppConstant.user_id) String user_id);

        @FormUrlEncoded
        @POST(WebUtility.BOOKING_HISTORY_COUPON_DETAIL)
        Call<BookingHistoryDetailCouponRequestResponse> getBookingHistoryCouponDetails(@Header("locale") String locale,
                                                                                       @Field(AppConstant.customer_id) String customer_id,
                                                                                       @Field(AppConstant.booking_id) String booking_id);

        @FormUrlEncoded
        @POST(WebUtility.THING_TO_DO_BOOKING_UPDATE)
        Call<CommonRequestResponse> thingToDoUpdateBooking(@Header("locale") String locale,
                                                           @Field(AppConstant.order_id) String order_id,
                                                           @Field(AppConstant.response) String response,
                                                           @Field(AppConstant.result) String result);

        @FormUrlEncoded
        @POST(WebUtility.COUPON_BOOKING_UPDATE)
        Call<CommonRequestResponse> couponUpdateBooking(@Header("locale") String locale,
                                                        @Field(AppConstant.order_id) String order_id,
                                                        @Field(AppConstant.response) String response,
                                                        @Field(AppConstant.result) String result);

        @GET
        Call<ThingToDoTimeSlotRequestResponse> getTopDestination(@Header("locale") String locale,
                                                                 @Url String url);
    }
}
