package com.dryseed.dryseedapp.test.localBroadcast;

/**
 * Created by User on 2017/8/8.
 */

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Button;

import com.dryseed.dryseedapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SendLocalBroadcastActivity extends Activity{

    @Bind(R.id.broadcast_btn)
    Button btnSend;
    private final static String ACTION = "com.dryseed.dryseedapp.test.localBroadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broadcast_layout);

        ButterKnife.bind(this);
        btnSend.setText("sendBroadcast");

    }

    @OnClick(R.id.broadcast_btn)
    void onBtnClick(){
        sendBraodCast();
    }


    private void sendBraodCast() {
        Intent intent = new Intent(ACTION);
        intent.putExtra("key", "动态广播测试");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
