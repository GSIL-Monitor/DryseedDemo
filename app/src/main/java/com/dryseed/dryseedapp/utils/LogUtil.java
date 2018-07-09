package com.dryseed.dryseedapp.utils;


import com.orhanobut.logger.Logger;

/**
 * @author caiminming
 */
public class LogUtil {
    private static final String TAG = "MMM";

    public static void d(String msg) {
        Logger.d(TAG, msg);
    }

}
