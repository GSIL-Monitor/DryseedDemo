package com.dryseed.dryseedapp.framework.retrofit.demo2;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by caiminming on 2017/12/4.
 */

public class RetrofitProvider {
    private static final String URL = "http://gank.io/";

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
