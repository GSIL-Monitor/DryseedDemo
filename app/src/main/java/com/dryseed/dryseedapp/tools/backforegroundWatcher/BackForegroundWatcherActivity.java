package com.dryseed.dryseedapp.tools.backforegroundWatcher;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.luojilab.component.basiclib.utils.BackForegroundWatcher;
import com.luojilab.component.basiclib.utils.ToastUtil;

/**
 * Created by caiminming on 2017/12/23.
 */

public class BackForegroundWatcherActivity extends BaseActivity implements BackForegroundWatcher.BackForegroundListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BackForegroundWatcher.getInstance().registerListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BackForegroundWatcher.getInstance().unRegisterListener(this);
    }

    @Override
    public void onBackToForeground() {
        ToastUtil.showToast("onBackToForeground");
    }

    @Override
    public void onForeToBackground() {
        ToastUtil.showToast("onForeToBackground");
    }
}
