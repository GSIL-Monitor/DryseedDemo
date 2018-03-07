package com.dryseed.dryseedapp.test.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;

/**
 * LifeCycle
 * https://developer.android.google.cn/topic/libraries/architecture/lifecycle.html
 * 配合mvp的例子：MvpBaseActivity、BasePresenter
 */
public class LifeCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LifeCycleTextView lifeCycleTextView = new LifeCycleTextView(this);

        //注册Observer
        getLifecycle().addObserver(lifeCycleTextView);

        setContentView(lifeCycleTextView);

        View decorView = getWindow().getDecorView();
        View parent = lifeCycleTextView;
        while (parent != null) {
            Log.d("MMM", parent.toString());
            if (parent == decorView) {
                Log.d("MMM", "find decorView");
                break;
            }
            if (parent.getParent() instanceof View) {
                parent = (View) parent.getParent();
            }
        }
    }
}
