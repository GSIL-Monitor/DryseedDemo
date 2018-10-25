package com.dryseed.blurcomponent.runalone.application;

import com.luojilab.component.basiclib.BaseApplication;
import com.luojilab.component.componentlib.router.Router;

public class BlurApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Router.registerComponent("com.dryseed.blurcomponent.applike.BlurComponentAppLike");
        init();
    }

}
