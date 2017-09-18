package com.dryseed.dryseedapp.test.AIDL;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;

/**
 * Created by caiminming on 2017/9/18.
 *
 * https://github.com/goeasyway/AIDL_Test
 * http://www.jianshu.com/p/467016b4487c
 */

public class TestAIDLActivity extends Activity {
    private final static String TAG = "MainActivity";
    private IRemoteService remoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setClass(this, TestAIDLRemoteService.class);
        bindService(intent, connection, Service.BIND_AUTO_CREATE); // 绑定服务
    }


    /*private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            *//*
              ● Stub类：Binder的实现类，服务端需要实现这个类来提供服务。
              ● asInterface函数： 一个静态函数，用来将IBinder转换成对应的Binder的引用。
                  先通过queryLocalInterface查询，如果服务端和客户端都是在同一个进程，那么就不需要跨进程了，直接将IRemoteService当做普通的对象来使用，
                  否则会返回远程对象的代理对象（Proxy）。
             *//*
            remoteService = IRemoteService.Stub.asInterface(service); //获取AIDL的接口实现引用
            try {
                String s = String.format("Client pid = %s \n RemoteService pid= %s", Process.myPid(), remoteService.getPid());
                Toast.makeText(TestAIDLActivity.this, s, Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.e(TAG, "Service has unexpectedly disconnected");
            remoteService = null;
        }
    };*/

    private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            remoteService = IRemoteService.Stub.asInterface(service); //获取AIDL的接口实现引用
            try {
                MyProcess clientProcess = new MyProcess(Process.myPid(), TestAIDLActivity.this.getPackageName());
                MyProcess myProcess = remoteService.getProcess(clientProcess);

                String s = String.format("RemoeteService pName = %s \n RemoteService pid= %s", myProcess.name, myProcess.pid);
                Toast.makeText(TestAIDLActivity.this, s, Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.e(TAG, "Service has unexpectedly disconnected");
            remoteService = null;
        }
    };

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }
}
