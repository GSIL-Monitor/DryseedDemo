package com.dryseed.dryseedapp.tools.jobmanager;

import android.annotation.SuppressLint;
import android.util.Log;

/**
 * by default, logs to nowhere
 */

@SuppressLint("UseLogDirectly")
public class JqLog {

    private static final String TAG = "JOBS";

    private static boolean DBG = Configuration.SHOWLOG;

    private JqLog(){
        throw new IllegalStateException("Utility class");
    }

    public static boolean isDebugEnabled() {
        return DBG;
    }

    public static void d(String text, Object... args) {
        if (isDebugEnabled()) {
            Log.d(TAG, String.format(text, args));
        }
    }

    public static void d(String tag, String text) {
        if (isDebugEnabled()) {
            Log.d(tag, text);
        }
    }

    public static void e(Throwable t, String text, Object... args) {
        if (isDebugEnabled()) {
            Log.d(TAG, String.format(text, args), t);
        }
    }

    public static void e(String text, Object... args) {
        if (isDebugEnabled()) {
            Log.d(TAG, String.format(text, args));
        }
    }
}
