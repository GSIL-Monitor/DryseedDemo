/*
 * Copyright(c) 2016 Jing Dong Mall. All Rights Reserved.
 *
 * This software is the proprietary information of Jing Dong Mall.
 */

package com.dryseed.dryseedapp.listui;


import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.ConcurrentHashMap;


/**
 * 简易版，待完善，去掉传入的key
 * 暂时只试用单页面
 *
 * @author: zhanwei
 * @date: 2016-07-04 12:54
 */
public class Observable {

    public interface Action<T> {

        void call(T t);
    }


    private ConcurrentHashMap<String, Action> mConcurrentHashMap = new ConcurrentHashMap<>();
    private Handler mHandler;
    private volatile boolean isCancel;


    public Observable() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public <T> Observable subscribe(String typeClass, @NonNull Action<T> action) {
        if (typeClass != null && !mConcurrentHashMap.containsKey(typeClass)) {
            mConcurrentHashMap.put(typeClass, action);
        }
        return this;
    }


    private final void post(String typeClass, Object t) {
        if (typeClass != null) {
            if (mConcurrentHashMap.containsKey(typeClass) && !isCancel) {
                try {
                    mConcurrentHashMap.get(typeClass).call(t);
                } catch (Exception e) {

                }
            }
        }
    }


    public final void postMainThread(final String typeClass, final Object t) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            post(typeClass, t);
        } else {
            if (mHandler == null) return;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    post(typeClass, t);
                }
            });
        }

    }

    public void clear() {
        isCancel = true;
        mHandler = null;
        mConcurrentHashMap.clear();
    }


}
