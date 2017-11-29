package com.dryseed.dryseedapp.tools.sensor.networkstate;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.NetWorkUtil;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caiminming on 2017/11/27.
 * <p>
 * 每个页面维护自己的无网提示
 */

public class NetworkState2Manager {

    NetworkState2Manager() {

    }

    private static class NetworkState2ManagerHolder {
        private static NetworkState2Manager instance = new NetworkState2Manager();
    }

    public static NetworkState2Manager getInstance() {
        return NetworkState2ManagerHolder.instance;
    }

    private BroadcastReceiver mNetwordStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("MMM", "NetworkStateManager onReceive");
            refreshNetworkTipUI();
        }
    };

    private LinkedHashMap<OnNetworkStateChangeListener, SoftReference<View>> mViewsCache = new LinkedHashMap<>();

    /**
     * 注册网络监听广播
     *
     * @param context
     */
    public void registerReceiver(Context context) {
        final IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(mNetwordStateReceiver, mFilter);
    }

    /**
     * 反注册网络监听广播
     *
     * @param context
     */
    public void unRegisterReceiver(Context context) {
        try {
            context.unregisterReceiver(mNetwordStateReceiver);
            mViewsCache.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
     * 显示无网UI
     */
    private void showUI() {
        Log.d("MMM", "showUI");
        for (Map.Entry<OnNetworkStateChangeListener, SoftReference<View>> entry : mViewsCache.entrySet()) {
            OnNetworkStateChangeListener onNetworkStateChangeListener = entry.getKey();
            SoftReference<View> viewSoftReference = entry.getValue();
            if (viewSoftReference == null || viewSoftReference.get() == null) {
                Log.d("MMM", "viewSoftReference == null || viewSoftReference.get() == null");
                continue;
            }
            if (onNetworkStateChangeListener == null) {
                Log.d("MMM", "onNetworkStateChangeListener == null");
                continue;
            }
            if (viewSoftReference.get().getParent() != null) {
                Log.d("MMM", "view has parent");
                continue;
            }
            onNetworkStateChangeListener.onNetConnected(viewSoftReference.get());
        }
    }

    /**
     * 隐藏无网UI
     */
    private void hideUI() {
        Log.d("MMM", "hideUI");
        if (null == mViewsCache) return;

        for (Map.Entry<OnNetworkStateChangeListener, SoftReference<View>> entry : mViewsCache.entrySet()) {
            SoftReference<View> viewSoftReference = entry.getValue();
            if (viewSoftReference != null) {
                View view = viewSoftReference.get();
                if (null == view || null == view.getParent() || !(view.getParent() instanceof ViewGroup))
                    continue;
                ((ViewGroup) (view.getParent())).removeView(view);
            }
        }

    }

    public void addObserver(OnNetworkStateChangeListener onNetworkStateChangeListener, View view) {
        if (null != mViewsCache) {
            mViewsCache.put(onNetworkStateChangeListener, new SoftReference<>(view));
        }
        refreshNetworkTipUI(); //避免页面销毁而导致没有重绘问题
    }

    /**
     * 获取无网UI布局
     *
     * @param activity
     * @return
     */
    public View getModel(final Activity activity) {
        View model = null;
        try {
            model = activity.getLayoutInflater().inflate(R.layout.app_network_model, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (model == null) {
            return null;
        }

        RelativeLayout layout = (RelativeLayout) model.findViewById(R.id.app_network_model_layout);
        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent wifiSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
                List<ResolveInfo> acts = activity.getPackageManager().queryIntentActivities(wifiSettingsIntent, 0);
                if (acts.size() > 0) {
                    activity.startActivity(wifiSettingsIntent);
                } else {
                    Toast.makeText(activity, "无法进入手机网络设置", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return model;
    }

    public interface OnNetworkStateChangeListener {
        void onNetConnected(View view);
    }
}
