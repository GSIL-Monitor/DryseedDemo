package com.dryseed.dryseedapp.framework.retrofit.demo1;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by caiminming on 2017/11/24.
 */

public class TestRetrofitWithRxjava {
    public static void test() {
        RetrofitServiceWithRxjava retrofitService = RetrofitProvider.getRetrofitWithRxjava().create(RetrofitServiceWithRxjava.class);

        retrofitService.getTop250(0, 5)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                        Log.d("MMM", "onCompleted " + responseBodyResponse.message());
                    }
                });
    }
}
