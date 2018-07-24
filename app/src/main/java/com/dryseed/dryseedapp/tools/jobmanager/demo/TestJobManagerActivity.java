package com.dryseed.dryseedapp.tools.jobmanager.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.tools.jobmanager.JobManagerUtils;
import com.dryseed.dryseedapp.tools.jobmanager.Priority;
import com.luojilab.component.basiclib.utils.LogUtil;
import com.dryseed.timecost.utils.ThreadUtils;

/**
 * @author CaiMinMing
 */
public class TestJobManagerActivity extends BaseActivity {
    public static final String TAG = "TestJobManagerActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JobManagerUtils.addJobInBackground(new PlayerJob(Priority.MID_MAX) {
            @Override
            public Object onRun(Object[] objects) {
                LogUtil.d("addJobInBackground onRun " + Thread.currentThread().getName());
                return null;
            }
        });

        JobManagerUtils.postRunnable(new Runnable() {
            @Override
            public void run() {
                LogUtil.d("postRunnable run " + Thread.currentThread().getName());
            }
        }, TAG);

        JobManagerUtils.addJob(new PlayerJob(Priority.MID_MAX) {
            @Override
            public Object onRun(Object[] objects) throws Throwable {
                LogUtil.d("addJob onRun " + Thread.currentThread().getName());
                LogUtil.d("isMainThread : " + ThreadUtils.isMainThread());
                return null;
            }
        });
    }
}
