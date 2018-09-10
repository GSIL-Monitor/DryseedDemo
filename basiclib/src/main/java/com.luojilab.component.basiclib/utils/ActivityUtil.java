package com.luojilab.component.basiclib.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * @author caiminming
 */
public class ActivityUtil {
    //判断某一个类是否存在任务栈里面
    public static boolean isExistMainActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);  //获取从栈顶开始往下查找的10个activity
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }

    public static boolean printTaskInfo(Context context) {
        if (null == context) {
            return false;
        }
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = mActivityManager.getRunningTasks(50); //暂定50
        final int size = tasksInfo.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                ActivityManager.RunningTaskInfo runningTaskInfo = tasksInfo.get(i);
                ComponentName baseActivity = runningTaskInfo.baseActivity;
                ComponentName topActivity = runningTaskInfo.topActivity;
                LogUtil.d(String.format("[position:%d][baseActivity:%s][topActivity:%s][numRunning:%d][numActivities:%d]",
                        i,
                        baseActivity.getClassName(),
                        topActivity.getClassName(),
                        runningTaskInfo.numRunning,
                        runningTaskInfo.numActivities
                ));
            }
        }
        return false;
    }
}
