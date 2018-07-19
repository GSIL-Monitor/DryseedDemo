package com.dryseed.dryseedapp.tools.jobmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.dryseed.dryseedapp.utils.LogUtil;

/**
 * Created by niejunjiang on 2017/2/23.
 */

public class DefaultJobHandler extends Handler implements IJobHandler {

private static final String TAG = "DefaultJobHandler";
    public DefaultJobHandler(boolean postToMain) {
        super(postToMain ? Looper.getMainLooper() :
                (Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper()));
    }

    @Override
    public void postResult(int resultCode, Object result) {
        Message message = new Message();
        message.obj = result;
        message.what = resultCode;
        sendMessage(message);
    }

    @Override
    public void postSuccess(Object result) {

        LogUtil.d(TAG,"postSuccess");
    }

    @Override
    public void postFailed() {
        LogUtil.d(TAG,"postFailed");

    }

    @Override
    public void handleMessage(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case SUCCESS:
                    postSuccess(msg.obj);
                    break;
                case FAILED:
                    postFailed();
                    break;
                default:break;
            }
        }
    }

}
