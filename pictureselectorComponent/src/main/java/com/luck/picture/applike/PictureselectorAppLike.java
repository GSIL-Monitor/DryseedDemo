package com.luck.picture.applike;

import com.luck.picture.serviceimpl.PictureselectorServiceImpl;
import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.luojilab.componentservice.pictureselector.PictureSelectorService;
import com.luojilab.componentservice.readerbook.ReadBookService;

/**
 * Created by mrzhang on 2017/6/15.
 */

public class PictureselectorAppLike implements IApplicationLike {

    Router router = Router.getInstance();
    UIRouter uiRouter = UIRouter.getInstance();

    @Override
    public void onCreate() {
        uiRouter.registerUI("pictureselector");
        router.addService(PictureSelectorService.class.getSimpleName(), new PictureselectorServiceImpl());
    }

    @Override
    public void onStop() {
        uiRouter.unregisterUI("pictureselector");
        router.removeService(PictureSelectorService.class.getSimpleName());
    }
}
