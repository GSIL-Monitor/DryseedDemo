package com.dryseed.dryseedapp.test.workManager;

import android.arch.lifecycle.Observer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.utils.LogUtil;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkStatus;

/**
 * @author CaiMinMing
 */
public class TestWorkManagerActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Constraints
        Constraints myConstraints = null;
        Constraints.Builder myConstraintsBuilder = new Constraints.Builder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            myConstraintsBuilder.setRequiresDeviceIdle(true);
        }
        myConstraintsBuilder.setRequiresCharging(true);
        myConstraints = myConstraintsBuilder.build();

        // WorkRequest
        OneTimeWorkRequest compressionWork = new OneTimeWorkRequest.Builder(CompressWorker.class)
                .setInputData(
                        new Data.Builder()
                                .putBoolean(CompressWorker.COMPRESS_WORKER_PARAM_NAME, true)
                                .build()
                )
                .setConstraints(myConstraints)
                .build();
        WorkManager.getInstance().enqueue(compressionWork);

        // WorkStatus
        WorkManager.getInstance().getStatusById(compressionWork.getId())
                .observe(this, new Observer<WorkStatus>() {
                    @Override
                    public void onChanged(@Nullable WorkStatus workStatus) {
                        String result = workStatus.getOutputData().getString(CompressWorker.COMPRESS_WORKER_RESULT_NAME, "");
                        LogUtil.d(String.format("onChanged [result:%s][thread:%s]", result, Thread.currentThread().getName()));
                    }
                });


    }
}
