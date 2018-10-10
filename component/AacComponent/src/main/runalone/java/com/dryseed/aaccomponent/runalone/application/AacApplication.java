package com.dryseed.aaccomponent.runalone.application;

import com.luojilab.component.basiclib.BaseApplication;
import com.luojilab.component.componentlib.router.Router;

public class AacApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Router.registerComponent("com.dryseed.aaccomponent.applike.AacComponentAppLike");
        init();
    }

}
