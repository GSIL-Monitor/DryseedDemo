package com.dryseed.dryseedapp.test.messenger;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by caiminming on 2017/9/18.
 */

public class MessengerService extends Service {
    private static final int MSG_SUM = 0x110;

    //最好换成HandlerThread的形式
    @SuppressLint("HandlerLeak")
    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgfromClient) {
            //com.dryseed.dryseedapp:remote D/MMM: MessengerService handleMessage : main
            Log.d("MMM", "MessengerService handleMessage : " + Thread.currentThread().getName());
            Message msgToClient = Message.obtain(msgfromClient);
            switch (msgfromClient.what) {
                //msg 客户端传来的消息
                case MSG_SUM:
                    msgToClient.what = MSG_SUM;
                    try {
                        //模拟耗时
                        Thread.sleep(2000);
                        msgToClient.arg2 = msgfromClient.arg1 + msgfromClient.arg2;
                        /**
                         * msgfromClient.replyTo:
                         * Optional Messenger where replies to this message can be sent.  The
                         * semantics of exactly how this is used are up to the sender and
                         * receiver.
                         */
                        msgfromClient.replyTo.send(msgToClient);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            super.handleMessage(msgfromClient);
        }
    });

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
