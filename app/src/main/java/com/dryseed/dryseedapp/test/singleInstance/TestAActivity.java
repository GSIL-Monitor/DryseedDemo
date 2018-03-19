package com.dryseed.dryseedapp.test.singleInstance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.MyApplication;

/**
 * SingleInstance测试
 * 同时启动TestBActivity(launchMode为Singleinstance)多次，观察栈结构(adb shell dumpsys activity activities)
 *
 */
public class TestAActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("go");
        setContentView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getInstance(), TestBActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getInstance().startActivity(intent);
                MyApplication.getInstance().startActivity(intent);
                //startActivity(new Intent(TestAActivity.this, TestBActivity.class));
                //startActivity(new Intent(TestAActivity.this, TestBActivity.class));
            }
        });

        /**
         * ①
         * Task id #10501
         *      Hist #0 TestBActivity
         * Task id #10500
         *      Hist #2 TestAActivity
         *      Hist #1 MainActivity
         *      Hist #0 MainActivity
         *
         *
         * ② TestBActivity中立马跳转TestFrescoActivity的情况
         * Task id #10506
         *      Hist #3 TestFrescoActivity
         *      Hist #2 TestAActivity
         *      Hist #1 MainActivity
         *      Hist #0 MainActivity
         * Task id #10507
         *      Hist #0 TestBActivity
         */
    }
}
