package com.dryseed.dryseedapp.architecture.aac.lifecycle;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.luojilab.componentservice.aac.AacService;

/**
 * doc : https://developer.android.google.cn/topic/libraries/architecture/lifecycle
 * Fragments and Activities in Support Library 26.1.0 and later already implement the LifecycleOwner interface.
 * If you have a custom class that you would like to make a LifecycleOwner, you can use the LifecycleRegistry class, but you need to forward events into that class
 */
public class LifeCycleOwnerActivity extends Activity {
    private LifecycleRegistry mLifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UIRouter.getInstance().openUri(this, "dryseed://aac/lifecycleowner", new Bundle());
        finish();

        /*Router router = Router.getInstance();
        if (router.getService(AacService.class.getSimpleName()) != null) {
            AacService service = (AacService) router.getService(AacService.class.getSimpleName());
            service.openLifeCycleActivity(this);
            finish();
        } else {
            ToastUtils.showShort("Please load AacComponent first");
        }*/
    }
}
