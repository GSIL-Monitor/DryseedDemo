package com.dryseed.blurcomponent.applike;

import com.dryseed.blurcomponent.serviceimpl.BlurServiceImpl;
import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.luojilab.componentservice.blur.BlurService;

public class BlurComponentAppLike implements IApplicationLike {

    Router router = Router.getInstance();
    UIRouter uiRouter = UIRouter.getInstance();

    @Override
    public void onCreate() {
        uiRouter.registerUI("blur");
        router.addService(BlurService.class.getSimpleName(), new BlurServiceImpl());
    }

    @Override
    public void onStop() {
        uiRouter.unregisterUI("blur");
        router.removeService(BlurService.class.getSimpleName());
    }
}
