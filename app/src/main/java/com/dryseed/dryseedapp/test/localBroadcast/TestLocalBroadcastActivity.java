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
import android.widget.Button;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TestLocalBroadcastActivity extends Activity {

    @Bind(R.id.broadcast_btn)
    Button btnSend;
    private BroadcastReceiver myReceiver;
    private IntentFilter filter;
    private final static String ACTION = "com.dryseed.dryseedapp.test.localBroadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broadcast_layout);

        ButterKnife.bind(this);

        myReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                String result = intent.getStringExtra("key");
                Toast.makeText(TestLocalBroadcastActivity.this, "the BroadCast action is " + action + "   the BroadCast receive result is " + result, Toast.LENGTH_SHORT).show();
            }
        };

        filter = new IntentFilter();
        filter.addAction(ACTION);
    }

    @OnClick(R.id.broadcast_btn)
    void onBtnClick() {
        startActivity(new Intent(this, SendLocalBroadcastActivity.class));
    }


    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, filter);//官方建议使用
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);//官方建议使用
        super.onDestroy();
    }

}
