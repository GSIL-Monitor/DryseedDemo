package com.dryseed.dryseedapp.test.AIDL;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;


/**
 * Created by caiminming on 2017/9/18.
 */

public class TestAIDLRemoteService extends Service {
    public TestAIDLRemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder; //暴露给客户端
    }

    // 实现AIDL接口
    private final IRemoteService.Stub binder = new IRemoteService.Stub() {

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public MyProcess getProcess(MyProcess clientProcess) throws RemoteException {
            MyProcess process = new MyProcess(Process.myPid(), getCurProcessName(TestAIDLRemoteService.this));
            return process;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString) throws RemoteException {

        }
    };

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
