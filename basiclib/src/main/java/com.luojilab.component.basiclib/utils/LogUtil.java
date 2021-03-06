package com.luojilab.component.basiclib.utils;


import android.util.Log;

/**
 * @author caiminming
 */
public class LogUtil {
    public static final String TAG = "MMM";

    public static boolean isDebug() {
        return true;
    }

    public static void d(String msg, Object... objects) {
        Log.d(TAG, String.format(msg, objects));
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }


}
