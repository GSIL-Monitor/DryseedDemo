package com.dryseed.dryseedapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.dryseed.dryseedapp.utils.DPIUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by caiminming on 2016/11/23.
 */
public class MyApplication extends MultiDexApplication {
    /**
     * A singleton instance of the application class for easy access in other places
     */
    private static MyApplication sInstance;

    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        try {
            DPIUtil.setDensity(sInstance.getResources().getDisplayMetrics().density);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        Fresco.initialize(sInstance);
        LeakCanary.install(sInstance);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
