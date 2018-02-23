package com.dryseed.dryseedapp.framework.retrofit.mvpDemo;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
    private static final String URL = "http://gank.io/";

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(
                        new OkHttpClient.Builder()
                                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)) //开启请求头
                                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) //开启body日志
                                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)) //基础日志输出
                                .addInterceptor(new Interceptor() { //增加通用头部信息
                                    @Override
                                    public Response intercept(Chain chain) throws IOException {
                                        Request request = chain.request()
                                                .newBuilder()
                                                .addHeader("userId", "dryseedcai")
                                                .build();
                                        return chain.proceed(request);
                                    }
                                })
                                .build()
                )
                .build();
    }

}
