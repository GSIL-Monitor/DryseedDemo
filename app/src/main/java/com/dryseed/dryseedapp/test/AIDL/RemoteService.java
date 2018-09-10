package com.dryseed.dryseedapp.test.AIDL;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.os.RemoteException;

import com.luojilab.component.basiclib.utils.LogUtil;

/**
 * @author caiminming
 */
public class RemoteService extends IRemoteService.Stub {
    private Context mContext;

    public RemoteService(Context context) {
        mContext = context;
    }

    @Override
    public int getPid() throws RemoteException {
        return Process.myPid();
    }

    @Override
    public MyProcess getProcess(MyProcess clientProcess) throws RemoteException {
        // 在远程进程中
        LogUtil.d("RemoteService getProcess : " + Process.myPid()); // RemoteService getProcess : 25053
        MyProcess process = new MyProcess(Process.myPid(), getCurProcessName(mContext));
        return process;
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                           double aDouble, String aString) throws RemoteException {

    }

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
