package com.dryseed.dryseedapp.framework.rxJava.demo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by caiminming on 2017/11/24.
 * <p>
 * Rxjava2.0
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

    /**
     * log:
     *      MMM: create : RxNewThreadScheduler-1
     *      MMM: map : RxNewThreadScheduler-1
     *      MMM: doOnNext : RxNewThreadScheduler-1
     *      MMM: accept : main
     */
    public static void test2() {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        Log.d("MMM", "create : " + Thread.currentThread().getName());
                        e.onNext(1);
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        Log.d("MMM", "map : " + Thread.currentThread().getName());
                        return integer + "";
                    }
                })
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("MMM", "doOnNext : " + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("MMM", "accept : " + Thread.currentThread().getName());
                    }
                });
    }
}
