package com.dryseed.dryseedapp.rxJava.demo;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ChapterOne {

    public static void demo1(final List list) {
        list.add("============ ChapterOne demo1 =============");
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                list.add("subscribe");
            }

            @Override
            public void onNext(Integer value) {
                list.add("" + value);
            }

            @Override
            public void onError(Throwable e) {
                list.add("error");
            }

            @Override
            public void onComplete() {
                list.add("complete");
            }
        };
        //建立连接
        observable.subscribe(observer);
    }

    public static void demo2(final List list) {
        list.add("============ ChapterOne demo2 =============");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                list.add("subscribe");
            }

            @Override
            public void onNext(Integer value) {
                list.add("" + value);
            }

            @Override
            public void onError(Throwable e) {
                list.add("error");
            }

            @Override
            public void onComplete() {
                list.add("complete");
            }
        });
    }

    public static void demo3(final List list) {
        list.add("============ ChapterOne demo3 =============");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                list.add("emit 1");
                emitter.onNext(1);
                list.add("emit 2");
                emitter.onNext(2);
                list.add("emit 3");
                emitter.onNext(3);
                list.add("emit complete");
                emitter.onComplete();
                list.add("emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                list.add("subscribe");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                list.add("onNext: " + value);
                i++;
                if (i == 2) {
                    list.add("dispose");
                    mDisposable.dispose();
                    list.add("isDisposed : " + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                list.add("error");
            }

            @Override
            public void onComplete() {
                list.add("complete");
            }
        });
    }

    public static void demo4(final List list) {
        list.add("============ ChapterOne demo4 =============");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                list.add("emit 1");
                emitter.onNext(1);
                list.add("emit 2");
                emitter.onNext(2);
                list.add("emit 3");
                emitter.onNext(3);
                list.add("emit complete");
                emitter.onComplete();
                list.add("emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                list.add("onNext: " + integer);
            }
        });
    }
}
