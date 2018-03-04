package com.dryseed.dryseedapp.test.localBroadcast;

/**
 * Created by User on 2017/8/8.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SendLocalBroadcastActivity extends BaseActivity {

    @BindView(R.id.broadcast_btn)
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
