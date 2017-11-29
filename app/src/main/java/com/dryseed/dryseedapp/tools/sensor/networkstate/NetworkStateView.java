package com.dryseed.dryseedapp.tools.sensor.networkstate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;

import java.util.List;

/**
 * Created by caiminming on 2017/11/29.
 */

public class NetworkStateView extends FrameLayout {

    public NetworkStateView(Context context) {
        this(context, null);
    }

    public NetworkStateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(final Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.app_network2_model, this);
        RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.app_network_model_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wifiSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
                List<ResolveInfo> acts = context.getPackageManager().queryIntentActivities(wifiSettingsIntent, 0);
                if (acts.size() > 0) {
                    context.startActivity(wifiSettingsIntent);
                } else {
                    Toast.makeText(context, "无法进入手机网络设置", Toast.LENGTH_SHORT).show();
                }
            }
        });

        NetworkState3Manager.getInstance().addObserver(new NetworkState3Manager.OnNetworkStateChangeListener() {
            @Override
            public void onNetConnected() {
                NetworkStateView.this.setVisibility(GONE);
            }

            @Override
            public void onNetDisconnected() {
                NetworkStateView.this.setVisibility(VISIBLE);
            }
        });
    }


}
