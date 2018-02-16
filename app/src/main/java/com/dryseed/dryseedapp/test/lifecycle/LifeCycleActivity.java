package com.dryseed.dryseedapp.test.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;

/**
 * LifeCycle
 * https://developer.android.google.cn/topic/libraries/architecture/lifecycle.html
 */
public class LifeCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LifeCycleTextView lifeCycleTextView = new LifeCycleTextView(this);

        //注册Observer
        getLifecycle().addObserver(lifeCycleTextView);

        setContentView(lifeCycleTextView);
    }
}
