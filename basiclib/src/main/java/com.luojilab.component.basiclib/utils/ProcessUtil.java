package com.luojilab.component.basiclib.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import java.util.List;

public class ProcessUtil {
    /**
     * 获取当前进程名
     *
     * @param context
     * @param pid
     * @return
     */
    public static String getProcessName(Context context, int pid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    public static String getCurrentProcessName(Context context) {
        return getProcessName(context, Process.myPid());
    }
}
