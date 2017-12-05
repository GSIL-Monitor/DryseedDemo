package com.dryseed.dryseedapp.framework.retrofit.demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dryseed.dryseedapp.BaseActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by caiminming on 2017/11/24.
 */

public class TestRetrofitActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTop250();
    }

    public static void getTop250() {
        RetrofitService retrofitService = RetrofitProvider.getRetrofit().create(RetrofitService.class);
        Call<Response<ResponseBody>> call = retrofitService.getTop250(0, 5);
        //call.enqueue(new Callback<LoginResult>）异步方式 ； call.execute() 同步方式
        call.enqueue(new Callback<Response<ResponseBody>>() {
            @Override
            public void onResponse(Call<Response<ResponseBody>> call, Response<Response<ResponseBody>> response) {
                Log.d("MMM", "onResponse : " + response.message());
            }

            @Override
            public void onFailure(Call<Response<ResponseBody>> call, Throwable t) {
                Log.d("MMM", "onFailure");
            }
        });
    }
}
