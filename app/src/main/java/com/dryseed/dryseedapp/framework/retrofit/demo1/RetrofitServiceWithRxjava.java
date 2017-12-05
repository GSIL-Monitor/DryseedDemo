package com.dryseed.dryseedapp.framework.retrofit.demo1;//package com.dryseed.rxjavademo.retrofit;


import com.dryseed.dryseedapp.framework.rxJava.entity.LoginRequest;
import com.dryseed.dryseedapp.framework.rxJava.entity.LoginResponse;
import com.dryseed.dryseedapp.framework.rxJava.entity.RegisterRequest;
import com.dryseed.dryseedapp.framework.rxJava.entity.RegisterResponse;
import com.dryseed.dryseedapp.framework.rxJava.entity.UserBaseInfoRequest;
import com.dryseed.dryseedapp.framework.rxJava.entity.UserBaseInfoResponse;
import com.dryseed.dryseedapp.framework.rxJava.entity.UserExtraInfoRequest;
import com.dryseed.dryseedapp.framework.rxJava.entity.UserExtraInfoResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Retrofit单独使用，不结合Rxjava
 */
public interface RetrofitServiceWithRxjava {
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
