package com.dryseed.aaccomponent.applike;

import com.dryseed.aaccomponent.serviceimpl.AccServiceImpl;
import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.luojilab.componentservice.aac.AacService;

public class AacComponentAppLike implements IApplicationLike {

    Router router = Router.getInstance();
    UIRouter uiRouter = UIRouter.getInstance();

    @Override
    public void onCreate() {
        uiRouter.registerUI("aac");
        router.addService(AacService.class.getSimpleName(), new AccServiceImpl());
    }

    @Override
    public void onStop() {
        uiRouter.unregisterUI("aac");
        router.removeService(AacService.class.getSimpleName());
    }
}
