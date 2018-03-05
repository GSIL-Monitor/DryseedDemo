package com.dryseed.dryseedapp.test.singleTask;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.dryseed.dryseedapp.BaseActivity;

import java.util.List;

/**
 * Created by caiminming on 2017/5/12.
 */

public class TestBActivity extends BaseActivity {
    public static final String EXTRA = "extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MMM", "TestBActivty onCreate");

        String processName = null;
        String topActivityName = null;

        ActivityManager activityManager = (ActivityManager) (getSystemService(android.content.Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(5);
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTaskInfos) {
            ComponentName f = runningTaskInfo.topActivity;
            String topActivityClassName = f.getClassName();
            String temp[] = topActivityClassName.split("\\.");
            //栈顶Activity的名称
            topActivityName = temp[temp.length - 1];
            int index = topActivityClassName.lastIndexOf(".");
            //栈顶Activity所属进程的名称
            processName = topActivityClassName.substring(0, index);
            Log.d("MMM", "---->topActivityName=" + topActivityName + ",processName=" + processName);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.putExtra(EXTRA, "dryseed");
                setResult(RESULT_OK, intent);
                finish();
            }
        }, 2000);
    }
}
