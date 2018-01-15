package com.dryseed.dryseedapp.framework.rxJava.demo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by caiminming on 2017/11/24.
 * <p>
 * Rxjava1.0
 */

public class TestRxjava {
    public static void test() {
        Observable.just(1, 2, 3)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("MMM", String.format("Task%d begin thread : %s", integer, Thread.currentThread().getName()));
                        Thread.sleep(2000);
                    }
                });
    }
}
