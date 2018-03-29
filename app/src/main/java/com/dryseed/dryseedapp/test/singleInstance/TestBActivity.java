package com.dryseed.dryseedapp.test.singleInstance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.framework.fresco.TestFrescoActivity;

public class TestBActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("SingleInstance TestBActivity");
        setContentView(textView);
        startActivity(new Intent(this, TestFrescoActivity.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("MMM", "onNewIntent");
    }

}
