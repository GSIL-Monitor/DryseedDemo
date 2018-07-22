package com.dryseed.dryseedapp.test.jobScheduler;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.utils.LogUtil;
import com.dryseed.timecost.utils.DebugLog;

/**
 * @author CaiMinMing
 */
public class TestJobSchedulerActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startJobService();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startJobService() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        ComponentName mServiceComponent = new ComponentName(this, JobSchedulerService.class);
        JobInfo.Builder jobInfoBuilder = new JobInfo.Builder(JobSchedulerService.JOB_ID, mServiceComponent)
                .setMinimumLatency(2000) // 设置任务允许最少延迟时间
                .setOverrideDeadline(10000) // 设置deadline,若到期还没有到达规定的条件也会执行
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresCharging(false) // 设置充电条件
                .setRequiresDeviceIdle(false); // 设置手机是否idle状态
                //.setPeriodic(5 * 1000);

        JobScheduler mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int jobStatus = mJobScheduler.schedule(jobInfoBuilder.build());
        LogUtil.d("DownloadJobService Scheduling jobStatus:" + jobStatus);
    }
}
