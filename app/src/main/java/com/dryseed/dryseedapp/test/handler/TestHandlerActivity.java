package com.dryseed.dryseedapp.test.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dryseed.dryseedapp.BaseActivity;

public class TestHandlerActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LooperThread looperThread = new LooperThread();
        looperThread.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //03-01 16:58:40.629 22418-22418/com.dryseed.dryseedapp D/MMM: MainThread : main
                Log.d("MMM", "MainThread : " + Thread.currentThread().getName());
                Message message = Message.obtain();
                message.what = 1;
                message.arg1 = 2;
                looperThread.getHandler().sendMessage(message);
            }
        }, 1000);


    }
}


class LooperThread extends Thread {
    public Handler mHandler;

    public void run() {
        Looper.prepare();
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                //03-01 16:56:57.919 22056-22220/com.dryseed.dryseedapp D/MMM: LooperThread : Thread-2287
                Log.d("MMM", "LooperThread : " + Thread.currentThread().getName());
            }
        };
        Looper.loop();
    }

    public Handler getHandler() {
        return mHandler;
    }
}