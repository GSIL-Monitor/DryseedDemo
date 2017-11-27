package com.dryseed.dryseedapp.tools.sensor.networkstate;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;

/**
 * Created by caiminming on 2017/11/27.
 * <p>
 * 如果要扩展成全局的，则可以放到MainActivity中去监听注册，在BaseActivity中进行ui刷新
 */

public class NetworkStateActivity extends BaseActivity {

    private NetworkStateManager networkStateManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerNetworkReceiver(); //注册网络监听
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(); //反注册网络监听
    }

    private void registerNetworkReceiver() {
        if (null == networkStateManager) {
            NetworkStateManager networkStateManager = new NetworkStateManager();
            networkStateManager.registerReceiver(this);
        }
    }

    private void unregisterReceiver() {
        if (null != networkStateManager) {
            networkStateManager.unRegisterReciver(this);
        }
    }

}
