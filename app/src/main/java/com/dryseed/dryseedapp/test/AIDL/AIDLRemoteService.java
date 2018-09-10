package com.dryseed.dryseedapp.test.AIDL;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * Created by caiminming on 2017/9/18.
 */

public class AIDLRemoteService extends Service {
    public AIDLRemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new RemoteService(this); //暴露给客户端
    }
}
