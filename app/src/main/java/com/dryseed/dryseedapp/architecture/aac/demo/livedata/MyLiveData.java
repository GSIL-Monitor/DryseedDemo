package com.dryseed.dryseedapp.architecture.aac.demo.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

public class MyLiveData<T> extends MutableLiveData<T> {
    @Override
    protected void onActive() {
        super.onActive();
        Log.d("MMM", "MyLiveData onActive");
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.d("MMM", "MyLiveData onInactive");
    }
}
