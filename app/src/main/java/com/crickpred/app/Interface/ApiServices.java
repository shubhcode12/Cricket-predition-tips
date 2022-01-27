package com.crickpred.app.Interface;

import com.crickpred.app.Models.AppSettings.AppSettings;
import com.crickpred.app.Models.CheckUser.CheckUser;
import com.crickpred.app.Models.GeneralSettings.GeneralSettings;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {
    @GET("api.php?settings")
    Call<AppSettings> settings();

    @GET("general_setting")
    Call<GeneralSettings> general_settings();


    @FormUrlEncoded
    @POST("api.php?check_user")
    Call<CheckUser> check_user(@Field("phone") String phone,
                               @Field("device_id") String device_id);
/*

    @POST("users_api/register")
    Call<User> createUser(@Body User user);

    @FormUrlEncoded
    @POST("api.php?user_register")
    Call<User> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone,
            @Field("user_refrence_code") String confirm_code
    );
    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("api.php?user_register")
    Call<User> createPost(@FieldMap Map<String, String> fields);
*/





}
