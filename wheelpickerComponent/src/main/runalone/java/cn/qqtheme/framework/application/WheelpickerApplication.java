package cn.qqtheme.framework.application;

import com.luojilab.component.basicres.BaseApplication;
import com.luojilab.component.componentlib.router.Router;

public class WheelpickerApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //如果isRegisterCompoAuto为false，则需要通过反射加载组件
        //Router.registerComponent("com.luojilab.share.applike.ShareApplike");
        //Router.registerComponent("com.luojilab.share.kotlin.applike.KotlinApplike");
    }

}