package com.dryseed.dryseedapp.tools.sensor.networkstate;

import android.util.Log;

import com.dryseed.dryseedapp.utils.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiminming on 2017/11/27.
 * <p>
 * 使用自定义View实现
 * 在ViewPagerFragmentActivity中使用
 */

public class NetworkState3Manager {

    NetworkState3Manager() {

    }

    private static class NetworkState3ManagerHolder {
        private static NetworkState3Manager instance = new NetworkState3Manager();
    }

    public static NetworkState3Manager getInstance() {
        return NetworkState3ManagerHolder.instance;
    }

    private List<OnNetworkStateChangeListener> mCallbacks = new ArrayList<>();

    /**
     * 更新无网提示
     */
    public void refreshNetworkTipUI() {
        Log.d("MMM", "refreshNetworkTipUI");
        if (NetWorkUtil.isNetConnected()) {
            hideUI();
        } else {
            showUI();
        }
    }

    /**
     * 隐藏无网UI
     */
    private void hideUI() {
        Log.d("MMM", "hideUI");
        for (OnNetworkStateChangeListener callback : mCallbacks) {
            callback.onNetConnected();
        }
    }

    /**
     * 显示无网UI
     */
    private void showUI() {
        Log.d("MMM", "showUI");
        for (OnNetworkStateChangeListener callback : mCallbacks) {
            callback.onNetDisconnected();
        }
    }

    public void addObserver(OnNetworkStateChangeListener onNetworkStateChangeListener) {
        mCallbacks.add(onNetworkStateChangeListener);
        refreshNetworkTipUI();
    }

    public interface OnNetworkStateChangeListener {
        void onNetConnected();

        void onNetDisconnected();
    }
}
