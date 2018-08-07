package com.dryseed.dryseedapp.test.asyncLayoutInflater;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.AsyncLayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.LogUtil;

/**
 * @author caiminming
 */
public class TestAsyncLayoutInflaterActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("onCreate");

        new AsyncLayoutInflater(this).inflate(
                //it does not support inflating layouts that contain fragments
                //R.layout.activity_eventbus_layout,
                R.layout.activity_main,
                null,
                new AsyncLayoutInflater.OnInflateFinishedListener() {
                    @Override
                    public void onInflateFinished(View view, int resid, ViewGroup parent) {
                        LogUtil.d("onInflateFinished");
                        setContentView(view);
                    }
                });

        LogUtil.d("onCreate 222");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("onResume");
    }
}
