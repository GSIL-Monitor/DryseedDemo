package com.dryseed.dryseedapp.service.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/8/23.
 */

public class TestServiceActivity extends BaseActivity {

    @Bind(R.id.start_service)
    public Button mStartService;

    @Bind(R.id.stop_service)
    public Button mStopService;

    @Bind(R.id.bind_service)
    public Button mBindService;

    @Bind(R.id.unbind_service)
    public Button mUnbindService;

    private MyService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_layout);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.start_service)
    public void startService() {
        Intent startIntent = new Intent(this, MyService.class);
        startService(startIntent);
    }

    @OnClick(R.id.stop_service)
    public void stopService() {
        Intent stopIntent = new Intent(this, MyService.class);
        stopService(stopIntent);
    }

    @OnClick(R.id.bind_service)
    public void bindService() {
        Intent startIntent = new Intent(this, MyService.class);
        bindService(startIntent, connection, BIND_AUTO_CREATE);
    }

    @OnClick(R.id.unbind_service)
    public void unbindService() {
        Intent stopIntent = new Intent(this, MyService.class);
        unbindService(connection);
    }

}
