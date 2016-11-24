package com.dryseed.dryseedapp;

import android.app.Application;

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
    }



}
