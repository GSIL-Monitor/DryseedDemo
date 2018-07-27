package com.dryseed.dryseedapp.application;

import android.app.Application;

/**
 * @author caiminming
 */
public class BaseApplicationProxy {
    protected Application mContext;

    public final void attach(Application context) {
        mContext = context;
    }

    /**
     * 不需要权限执行的逻辑
     */
    public void initWithoutPermission() {

    }
}
