package com.dryseed.aaccomponent.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dryseed.aaccomponent.R;
import com.luojilab.router.facade.annotation.RouteNode;

/**
 * LifeCycle
 * https://developer.android.google.cn/topic/libraries/architecture/lifecycle.html
 * 配合mvp的例子：MvpBaseActivity、BasePresenter
 */
@RouteNode(path = "/lifecycle", desc = "lifecycle")
public class LifeCycleActivity extends AppCompatActivity {

    LifeCycleTextView mLifeCycleTextView;
    Button mBindBtn;
    Button mUnbindBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acitivty_lifecycle_layout);

        mLifeCycleTextView = findViewById(R.id.lifecycle_textview);
        mBindBtn = findViewById(R.id.bind_btn);
        mUnbindBtn = findViewById(R.id.unbind_btn);

        mBindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLifecycle().addObserver(mLifeCycleTextView);
            }
        });

        mUnbindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLifecycle().removeObserver(mLifeCycleTextView);
            }
        });

    }
}
