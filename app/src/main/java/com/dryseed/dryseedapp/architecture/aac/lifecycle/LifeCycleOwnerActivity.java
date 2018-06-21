package com.dryseed.dryseedapp.architecture.aac.lifecycle;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * doc : https://developer.android.google.cn/topic/libraries/architecture/lifecycle
 * Fragments and Activities in Support Library 26.1.0 and later already implement the LifecycleOwner interface.
 * If you have a custom class that you would like to make a LifecycleOwner, you can use the LifecycleRegistry class, but you need to forward events into that class
 */
public class LifeCycleOwnerActivity extends Activity implements LifecycleOwner {
    private LifecycleRegistry mLifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

        LifeCycleTextView lifeCycleTextView = new LifeCycleTextView(this);

        //注册Observer
        mLifecycleRegistry.addObserver(lifeCycleTextView);

        setContentView(lifeCycleTextView);
    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
