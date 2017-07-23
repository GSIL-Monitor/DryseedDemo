package com.dryseed.dryseedapp.framework.rxJava.demo;

import android.content.Context;
import android.widget.Toast;

import com.dryseed.dryseedapp.framework.rxJava.Api;
import com.dryseed.dryseedapp.framework.rxJava.RetrofitProvider;
import com.dryseed.dryseedapp.framework.rxJava.entity.LoginRequest;
import com.dryseed.dryseedapp.framework.rxJava.entity.LoginResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChapterTwo {
    public static void demo1(final List list) {
        list.add("============ ChapterTwo demo1 =============");
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                list.add("Observable thread is : " + Thread.currentThread().getName());
                list.add("emit 1");
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                list.add("Observer thread is : " + Thread.currentThread().getName());
                list.add("onNext: " + integer);
            }
        };

        observable.subscribe(consumer);
    }

    public static void demo2(final List list) {
        list.add("============ ChapterTwo demo2 =============");
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                list.add("Observable thread is : " + Thread.currentThread().getName());
                list.add("emit 1");
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                list.add("Observer thread is : " + Thread.currentThread().getName());
                list.add("onNext: " + integer);
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    public static void demo3(final List list) {
        list.add("============ ChapterTwo demo3 =============");
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                list.add("Observable thread is : " + Thread.currentThread().getName());
                list.add("emit 1");
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                list.add("Observer thread is : " + Thread.currentThread().getName());
                list.add("onNext: " + integer);
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        list.add("After observeOn(mainThread), current thread is: " + Thread.currentThread()
                                .getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        list.add("After observeOn(io), current thread is : " + Thread.currentThread().getName());
                    }
                })
                .subscribe(consumer);
    }

    public static void practice1(final Context context) {
        Api api = RetrofitProvider.get().create(Api.class);
        api.login(new LoginRequest())
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求结果
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LoginResponse value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
