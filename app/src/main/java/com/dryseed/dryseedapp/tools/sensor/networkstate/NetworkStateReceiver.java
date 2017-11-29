package com.dryseed.dryseedapp.tools.sensor.networkstate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * Created by caiminming on 2017/11/29.
 */

public class NetworkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MMM", "NetworkStateManager onReceive");
        //NetworkState2Manager.getInstance().refreshNetworkTipUI();
        NetworkState3Manager.getInstance().refreshNetworkTipUI();
    }

    /**
     * 注册网络监听广播
     *
     * @param context
     */
    public void registerReceiver(Context context) {
        final IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(this, mFilter);
    }

    /**
     * 反注册网络监听广播
     *
     * @param context
     */
    public void unRegisterReciver(Context context) {
        try {
            context.unregisterReceiver(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
