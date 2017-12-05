package com.dryseed.dryseedapp.framework.retrofit.demo1;


import com.dryseed.dryseedapp.framework.rxJava.entity.LoginRequest;
import com.dryseed.dryseedapp.framework.rxJava.entity.LoginResponse;
import com.dryseed.dryseedapp.framework.rxJava.entity.RegisterRequest;
import com.dryseed.dryseedapp.framework.rxJava.entity.RegisterResponse;
import com.dryseed.dryseedapp.framework.rxJava.entity.UserBaseInfoRequest;
import com.dryseed.dryseedapp.framework.rxJava.entity.UserBaseInfoResponse;
import com.dryseed.dryseedapp.framework.rxJava.entity.UserExtraInfoRequest;
import com.dryseed.dryseedapp.framework.rxJava.entity.UserExtraInfoResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Retrofit单独使用，不结合Rxjava
 */
public interface RetrofitService {
    @POST("/")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @POST("/")
    Call<UserBaseInfoResponse> getUserBaseInfo(@Body UserBaseInfoRequest request);

    @POST("/")
    Call<UserExtraInfoResponse> getUserExtraInfo(@Body UserExtraInfoRequest request);

    @GET("v2/movie/top250")
    Call<Response<ResponseBody>> getTop250(@Query("start") int start, @Query("count") int count);
}
