package com.dryseed.dryseedapp.tools.sensor.networkstate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.DPIUtil;
import com.dryseed.dryseedapp.utils.NetWorkUtil;

import java.util.List;

/**
 * Created by caiminming on 2017/11/27.
 * <p>
 * 无网提示全局只有一个
 * 如果要扩展成全局的，则可以放到MainActivity中去监听注册，在BaseActivity中进行ui刷新
 */

public class NetworkStateManager {

    private BroadcastReceiver mNetwordStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("MMM", "NetworkStateManager onReceive");
            refreshNetworkTipUI(context);
        }
    };

    private ViewGroup rootFrameLayout;
    private View model;
    private RelativeLayout layout;
    private int top = 0;//DPIUtil.dip2px(70);

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
    public void unRegisterReciver(Context context) {
        try {
            context.unregisterReceiver(mNetwordStateReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新无网提示
     */
    private void refreshNetworkTipUI(Context context) {
        if (context instanceof NetworkStateActivity) {
            Log.d("MMM", "refreshNetworkTipUI");
            if (NetWorkUtil.isNetConnected()) {
                hideUI(context);
            } else {
                showUI(context);
            }
        }
    }

    /**
     * 显示无网UI
     *
     * @param context
     */
    private void showUI(Context context) {
        Log.d("MMM", "showUI");
        final ViewGroup rootView = getRootFrameLayout(context);

        View model = getModel(context);
        if (model == null) {
            return;
        }

        if (rootView.indexOfChild(model) != -1) {
            return;
        }
        rootView.addView(model);
        rootView.invalidate();
    }

    /**
     * 隐藏无网UI
     *
     * @param context
     */
    private void hideUI(Context context) {
        Log.d("MMM", "hideUI");
        final ViewGroup rootView = getRootFrameLayout(context);
        View model = getModel(context);
        if (model == null) {
            return;
        }
        if (rootView.indexOfChild(model) != -1) {
            rootView.removeView(model);
            rootView.invalidate();
        }
    }

    /**
     * 获取根布局FrameLayout
     *
     * @param context
     * @return
     */
    private ViewGroup getRootFrameLayout(Context context) {
        if (null != rootFrameLayout) {
            return rootFrameLayout;
        }

        rootFrameLayout = (ViewGroup) ((NetworkStateActivity) context).getWindow().peekDecorView();
        if (null == rootFrameLayout) {// 可能界面还没绘制或者还没设置。
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            rootFrameLayout = getRootFrameLayout(context);
        }

        return rootFrameLayout;
    }

    public void setUITop(int top) {
        this.top = top;
    }

    /**
     * 获取无网UI布局
     *
     * @param context
     * @return
     */
    private View getModel(final Context context) {
        int distanceToTop = top;
        if (model == null) {
            try {
                model = ((NetworkStateActivity) context).getLayoutInflater().inflate(R.layout.app_network_model, null);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        if (model == null) {
            return null;
        }

        if (layout == null) {
            layout = (RelativeLayout) model.findViewById(R.id.app_network_model_layout);
            layout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent wifiSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
                    List<ResolveInfo> acts = context.getPackageManager().queryIntentActivities(wifiSettingsIntent, 0);
                    if (acts.size() > 0) {
                        context.startActivity(wifiSettingsIntent);
                    } else {
                        Toast.makeText(context, "无法进入手机网络设置", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            });
        }
        if (Build.VERSION.SDK_INT >= 11) {
            layout.setY(distanceToTop);
        } else {
            ((AbsoluteLayout.LayoutParams) layout.getLayoutParams()).y = distanceToTop;
        }
        return model;
    }

}
