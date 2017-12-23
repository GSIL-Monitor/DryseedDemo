package com.dryseed.dryseedapp.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by caiminming on 2017-12-23
 * 该类用于判断客户端从后台到前台的状态转换。
 * 通过维护stateCounter一个计数值来判断状态
 */
public class BackForegroundWatcher implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "BackForegroundWatcher";

    private static BackForegroundWatcher sInstance;

    public static BackForegroundWatcher getInstance() {
        if (sInstance == null) {
            synchronized (BackForegroundWatcher.class) {
                if (sInstance == null) {
                    sInstance = new BackForegroundWatcher();
                }
            }
        }
        return sInstance;
    }

    /**
     * stateCount == 0 表示应用处于后台状态
     * 该计数在onstart里可以判定是后台返回到前台
     * 在onStop里可以判定是前台撤回到后台
     */
    private volatile int stateCounter = 0;

    /**
     * 标志位，为了避免应用第一次启动带来的重复请求问题
     */
    private boolean mFirstLaunchActivity;

    /**
     * 用户注册的回调，用于监控应用前后台切换时候使用
     */
    private ArrayList<BackForegroundListener> mListeners = new ArrayList<BackForegroundListener>();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (stateCounter == 0) {
            if (mFirstLaunchActivity) {
                mFirstLaunchActivity = false;
            } else {
                Log.i(TAG, "onBackToForeground");
                synchronized (this) {
                    for (BackForegroundListener listener : mListeners) {
                        listener.onBackToForeground();
                    }
                }
            }

        }
        stateCounter++;
        Log.i(TAG, "onActivityStarted stateCounter = " + stateCounter);
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        stateCounter--;
        if (stateCounter == 0) {
            synchronized (this) {
                Log.i(TAG, "onForeToBackground");
                for (BackForegroundListener listener : mListeners) {
                    listener.onForeToBackground();
                }
            }
        }

        Log.i(TAG, "onActivityStopped stateCounter = " + stateCounter);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    public void init(Application application) {
        if (application == null) {
            return;
        }

        mFirstLaunchActivity = true;
        application.registerActivityLifecycleCallbacks(this);
    }

    public synchronized void registerListener(BackForegroundListener listener) {
        if (!mListeners.contains(listener)) {
            mListeners.add(listener);
        }
    }

    public synchronized void unRegisterListener(BackForegroundListener listener) {
        mListeners.remove(listener);
    }

    public interface BackForegroundListener {
        void onBackToForeground();

        void onForeToBackground();
    }
}
