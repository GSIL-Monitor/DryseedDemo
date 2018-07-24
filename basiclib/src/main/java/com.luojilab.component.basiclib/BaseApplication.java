package com.luojilab.component.basiclib;

import android.app.Application;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.luojilab.component.basiclib.utils.ProcessUtil;

/**
 * Created by mrzhang on 2018/1/16.
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
    public void onCreate() {
        super.onCreate();

        Log.d("MMM", " MyApplication onCreate");

        String processName = ProcessUtil.getProcessName(this, Process.myPid());
        if (!TextUtils.isEmpty(processName) && processName.equals("com.dryseed.dryseedapp")) {
            init();
        }
    }

    protected void init() {
        sInstance = this;
    }
}
