package com.dryseed.dryseedapp.application;

import android.content.Context;
import android.text.TextUtils;

import com.luojilab.component.basiclib.BaseApplication;
import com.luojilab.component.basiclib.utils.LogUtil;
import com.luojilab.component.basiclib.utils.ProcessUtil;


/**
 * Created by caiminming on 2016/11/23.
 */
public class MyApplication extends BaseApplication {
    public static final String HW_MI_PUSH = ":pushservice";

    private BaseApplicationProxy mProxy;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        String mProcessName = ProcessUtil.getCurrentProcessName(this);
        initProxyApplication(mProcessName);
        if (mProxy != null) {
            mProxy.attach(sInstance);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (null != mProxy) {
            mProxy.initWithoutPermission();
        }
    }

    /**
     * 根据进程名称创建代理Application
     *
     * @param mProcessName
     */
    private void initProxyApplication(String mProcessName) {
        if (!TextUtils.isEmpty(mProcessName)) {
            LogUtil.d("initProxyApplication mProcessName:", mProcessName);
            String packageName = getPackageName();
            if (TextUtils.equals(packageName, mProcessName)) {
                //主进程
                mProxy = new MainApplication();
            } else if (TextUtils.equals(mProcessName, packageName + HW_MI_PUSH)) {
                // ...
            }
        }
    }

}
