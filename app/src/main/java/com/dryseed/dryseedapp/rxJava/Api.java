package com.dryseed.dryseedapp.rxJava;

import com.dryseed.dryseedapp.rxJava.entity.LoginRequest;
import com.dryseed.dryseedapp.rxJava.entity.LoginResponse;
import com.dryseed.dryseedapp.rxJava.entity.RegisterRequest;
import com.dryseed.dryseedapp.rxJava.entity.RegisterResponse;
import com.dryseed.dryseedapp.rxJava.entity.UserBaseInfoRequest;
import com.dryseed.dryseedapp.rxJava.entity.UserBaseInfoResponse;
import com.dryseed.dryseedapp.rxJava.entity.UserExtraInfoRequest;
import com.dryseed.dryseedapp.rxJava.entity.UserExtraInfoResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/12/6
 * Time: 11:30
 * FIXME
 */
public interface Api {
    @POST("/")
    Observable<LoginResponse> login(@Body LoginRequest request);

    @POST("/")
    Observable<RegisterResponse> register(@Body RegisterRequest request);

    @POST("/")
    Observable<UserBaseInfoResponse> getUserBaseInfo(@Body UserBaseInfoRequest request);

    @POST("/")
    Observable<UserExtraInfoResponse> getUserExtraInfo(@Body UserExtraInfoRequest request);

    @GET("v2/movie/top250")
    Observable<Response<ResponseBody>> getTop250(@Query("start") int start, @Query("count") int count);
}
