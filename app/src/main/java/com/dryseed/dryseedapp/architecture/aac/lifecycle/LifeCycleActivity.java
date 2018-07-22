package com.dryseed.dryseedapp.architecture.aac.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.dryseed.dryseedapp.BaseActivity;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.luojilab.componentservice.aac.AacService;
import com.luojilab.componentservice.pictureselector.PictureSelectorService;

/**
 * LifeCycle
 * https://developer.android.google.cn/topic/libraries/architecture/lifecycle.html
 * 配合mvp的例子：MvpBaseActivity、BasePresenter
 */
public class LifeCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UIRouter.getInstance().openUri(this, "dryseed://aac/lifecycle", new Bundle());
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
