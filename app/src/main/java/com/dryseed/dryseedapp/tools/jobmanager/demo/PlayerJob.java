package com.dryseed.dryseedapp.tools.jobmanager.demo;


import com.dryseed.dryseedapp.tools.jobmanager.Job;
import com.dryseed.dryseedapp.tools.jobmanager.Params;

/**
 * @author zhongshan
 * @date 2016-08-08.
 */
public abstract class PlayerJob extends Job {

    private ICallback mICallback;

    protected PlayerJob(int priority) {
        super(new Params(priority), Object.class);
    }

    protected PlayerJob(int priority, ICallback callback) {
        super(new Params(priority), Object.class);
        mICallback = callback;
    }

    protected PlayerJob(int priority, long delayInMs) {
        super(new Params(priority).delayInMs(delayInMs), Object.class);
    }

    @Override
    public void onAdded() {

    }

    @Override
    protected void onCancel() {

    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }

    public interface ICallback<T> {
        void onSuccess(T data);
    }
}
