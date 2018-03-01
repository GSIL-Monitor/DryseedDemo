package com.dryseed.dryseedapp.test.handlerthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dryseed.dryseedapp.BaseActivity;

public class TestHandlerThreadActivity extends BaseActivity {

    HandlerThread mHandlerThread;
    Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandlerThread = new HandlerThread("HandlerThread");
        mHandlerThread.start();

        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //这个方法是运行在 handler-thread 线程中的 ，可以执行耗时操作
                //03-01 18:12:12.827 28195-28461/com.dryseed.dryseedapp D/MMM: 消息： 1  线程： HandlerThread
                //03-01 18:12:13.967 28195-28461/com.dryseed.dryseedapp D/MMM: 消息： 2  线程： HandlerThread
                Log.d("MMM ", "消息： " + msg.what + "  线程： " + Thread.currentThread().getName());
            }
        };

        mHandler.sendEmptyMessage(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程给handler发送数据
                mHandler.sendEmptyMessage(2);
            }
        }).start();
    }
}
