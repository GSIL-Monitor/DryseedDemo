package com.dryseed.dryseedapp;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

public class MyJunitRunner extends AndroidJUnitRunner {

    private static String mDirName = "dryseed_perf_test";

    private static int mLoopCount = 1;

    private static String mTargetAppName = "Dryseed";

    private static String mTargetProcessName = "com.dryseed.dryseedapp";

    public static String getDirName() {
        return mDirName;
    }

    public static int getLoopCount() {
        return mLoopCount;
    }

    public static String getTargetAppName() {
        return mTargetAppName;
    }

    public static String getTargetProcessName() {
        return mTargetProcessName;
    }

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        if (arguments != null) {
            String dirName = arguments.getString("dirName");
            String loopCount = arguments.getString("loopCount");
            String appName = arguments.getString("appName");
            String processName = arguments.getString("procName");
            if (dirName != null) {
                mDirName = dirName;
            }
            if (loopCount != null) {
                try {
                    mLoopCount = Integer.parseInt(loopCount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (appName != null) {
                mTargetAppName = appName;
            }
            if (processName != null) {
                mTargetProcessName = processName;
            }
        }
    }
}
