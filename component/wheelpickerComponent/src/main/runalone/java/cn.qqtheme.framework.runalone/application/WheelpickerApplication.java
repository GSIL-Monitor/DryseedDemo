package cn.qqtheme.framework.runalone.application;

import com.luojilab.component.basiclib.BaseApplication;

public class WheelpickerApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //如果isRegisterCompoAuto为false，则需要通过反射加载组件
        //Router.registerComponent("com.luck.picture.applike.PictureselectorAppLike");
        //Router.registerComponent("cn.qqtheme.framework.applike.WheelpickerAppLike");

        init();
    }

}
