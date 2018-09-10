package com.dryseed.dryseedapp.tools.jobmanager.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.tools.jobmanager.JobManagerUtils;
import com.dryseed.dryseedapp.tools.jobmanager.Priority;
import com.luojilab.component.basiclib.utils.LogUtil;
import com.luojilab.component.basiclib.utils.ThreadUtils;

/**
 * @author CaiMinMing
 */
public class TestJobManagerActivity extends BaseActivity {
    public static final String TAG = "TestJobManagerActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final PlayerJob.ICallback callback = new PlayerJob.ICallback<String>() {
            @Override
            public void onSuccess(String data) {
                LogUtil.d("onSuccess111 " + Thread.currentThread().getName());
                JobManagerUtils.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.d("postRunnable onSuccess222 %s [isMainThread:%b]",
                                Thread.currentThread().getName(),
                                ThreadUtils.isMainThread()); // [isMainThread:false]
                    }
                }, TAG);

                JobManagerUtils.addJob(new PlayerJob(Priority.MID_MAX) {
                    @Override
                    public Object onRun(Object[] objects) throws Throwable {
                        LogUtil.d("addJob onSuccess333 %s [isMainThread:%b]",
                                Thread.currentThread().getName(),
                                ThreadUtils.isMainThread()); // [isMainThread:false]
                        return null;
                    }
                });
            }
        };
        JobManagerUtils.addJobInBackground(new PlayerJob(Priority.MID_MAX, callback) {
            @Override
            public Object onRun(Object[] objects) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.d("addJobInBackground onRun " + Thread.currentThread().getName());
                callback.onSuccess("success");
                return null;
            }
        });

    }
}
