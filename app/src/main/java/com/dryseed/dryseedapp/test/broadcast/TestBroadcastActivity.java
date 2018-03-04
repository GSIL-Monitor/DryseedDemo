package com.dryseed.dryseedapp.test.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestBroadcastActivity extends BaseActivity {

    private final static String ACTION = "com.dryseed.dryseedapp.test.broadcast.TestBroadcastActivity";
    MyBroadcastReceiver1 myBroadcastReceiver1;
    MyBroadcastReceiver2 myBroadcastReceiver2;
    IntentFilter intentFilter1;
    IntentFilter intentFilter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_layout);
        ButterKnife.bind(this);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcastReceiver1, intentFilter1);
        registerReceiver(myBroadcastReceiver2, intentFilter2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcastReceiver1);
        unregisterReceiver(myBroadcastReceiver2);
    }

    private void init() {
        myBroadcastReceiver1 = new MyBroadcastReceiver1();
        myBroadcastReceiver2 = new MyBroadcastReceiver2();
        intentFilter1 = new IntentFilter();
        intentFilter1.addAction(ACTION);
        intentFilter1.setPriority(2); //设置广播的优先级， -1000～1000 ，数字越大，优先级越高。
        intentFilter2 = new IntentFilter();
        intentFilter2.addAction(ACTION);
        intentFilter2.setPriority(1);
    }

    @OnClick(R.id.button)
    void onBtnClick(View view) {
        /**
         * 申明广播的 action 行为
         */
        Intent intent = new Intent(ACTION);
        intent.putExtra("key", "有序广播测试");

        this.sendOrderedBroadcast(intent, null);
    }

    class MyBroadcastReceiver1 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String result = intent.getStringExtra("key");
            Log.d("MMM", "MyBroadcastReceiver1 : the BroadCast action is " + action + "   the BroadCast receive result is " + result);

            Bundle bundle = new Bundle();
            bundle.putString("key", "有序广播处理之后" + "\n" + "再次发送给下一个广播接收者");
            intent.putExtra("bundle", bundle);
            setResultExtras(bundle);
            //切断广播，不再让此广播继续往下发送。
            //abortBroadcast();
        }
    }

    class MyBroadcastReceiver2 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Bundle bundle = getResultExtras(true); //接受上一个广播接收器receiver2传来的的数据
            Log.d("MMM", "MyBroadcastReceiver2 : the BroadCast action is " + action + "   the BroadCast receive result is " + bundle.getString("key"));
        }
    }

}
