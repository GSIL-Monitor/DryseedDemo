package com.luojilab.component.basiclib;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.luojilab.component.basiclib.utils.DPIUtil;

/**
 * Created by caiminming on 2018/1/16.
 */

public class BaseApplication extends Application {
    /**
     * A singleton instance of the application class for easy access in other places
     */
    protected static BaseApplication sInstance;

    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized BaseApplication getInstance() {
        return sInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sInstance = this;

        onBaseContextAttached();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /**
     * 基类Application的onCreate初始化工作
     */
    protected void init() {
        try {
            DPIUtil.setDensity(sInstance.getResources().getDisplayMetrics().density);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        Utils.init(sInstance);
    }

    /**
     * 基类Application的onBaseContextAttached初始化工作
     */
    protected void onBaseContextAttached() {
        MultiDex.install(sInstance);
    }
}
