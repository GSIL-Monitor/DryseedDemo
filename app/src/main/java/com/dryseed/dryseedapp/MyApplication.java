package com.dryseed.dryseedapp;

import android.app.Application;

import com.dryseed.dryseedapp.utils.DPIUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by caiminming on 2016/11/23.
 */
public class MyApplication extends Application {
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
        try {
            DPIUtil.setDensity(sInstance.getResources().getDisplayMetrics().density);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }


}
