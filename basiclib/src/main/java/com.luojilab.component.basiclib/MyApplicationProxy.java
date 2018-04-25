package com.luojilab.component.basiclib;

import android.content.Context;

public class MyApplicationProxy {
    public static Context getApplicationContext() {
        return sInstance;
    }

    public static void setApplicationContext(Context sInstance) {
        MyApplicationProxy.sInstance = sInstance;
    }

    private static Context sInstance;


}
