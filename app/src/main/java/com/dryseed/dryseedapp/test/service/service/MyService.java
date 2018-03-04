package com.dryseed.dryseedapp.test.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by caiminming on 2017/8/23.
 * <p>
 * 那么如果我们是点击的Bind Service按钮呢？由于在绑定Service的时候指定的标志位是BIND_AUTO_CREATE，说明点击Bind Service按钮的时候Service也会被创建，这时应该怎么销毁Service呢？
 * 其实也很简单，点击一下Unbind Service按钮，将Activity和Service的关联解除就可以了。
 *
 * 以上这两种销毁的方式都很好理解。那么如果我们既点击了Start Service按钮，又点击了Bind Service按钮会怎么样呢？
 * 这个时候你会发现，不管你是单独点击Stop Service按钮还是Unbind Service按钮，Service都不会被销毁，必要将两个按钮都点击一下，Service才会被销毁。
 * 也就是说，点击Stop Service按钮只会让Service停止，点击Unbind Service按钮只会让Service和Activity解除关联，一个Service必须要在既没有和任何Activity关联又处理停止状态的时候才会被销毁。
 */

public class MyService extends Service {

    public static final String TAG = "MMM";

    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class MyBinder extends Binder {

        public void startDownload() {
            Log.d(TAG, "startDownload() executed");
            // 执行具体的下载任务
        }

    }

}
