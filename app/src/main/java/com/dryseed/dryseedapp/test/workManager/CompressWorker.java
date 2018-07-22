package com.dryseed.dryseedapp.test.workManager;

import com.dryseed.dryseedapp.utils.LogUtil;

import androidx.work.Data;
import androidx.work.Worker;

/**
 * @author CaiMinMing
 */
public class CompressWorker extends Worker {
    public static final String COMPRESS_WORKER_PARAM_NAME = "COMPRESS_WORKER_PARAM_NAME";
    public static final String COMPRESS_WORKER_RESULT_NAME = "COMPRESS_WORKER_RESULT_NAME";

    @Override
    public WorkerResult doWork() {

        // Do the work here--in this case, compress the stored images.
        // In this example no parameters are passed; the task is
        // assumed to be "compress the whole library."
        myCompress();

        // Indicate success or failure with your return value:
        return WorkerResult.SUCCESS;

        // (Returning RETRY tells WorkManager to try this task again
        // later; FAILURE says not to try again.)
    }

    private void myCompress() {
        boolean param = this.getInputData().getBoolean(COMPRESS_WORKER_PARAM_NAME, false);
        LogUtil.d(String.format("myCompress [param:%b][thread:%s]", param, Thread.currentThread().getName()));

        Data result = new Data.Builder().putString(COMPRESS_WORKER_RESULT_NAME, "dryseed").build();
        setOutputData(result);
    }
}
