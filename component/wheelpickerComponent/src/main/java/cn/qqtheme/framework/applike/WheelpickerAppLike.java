package cn.qqtheme.framework.applike;

import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.luojilab.componentservice.wheelpicker.WheelPickerService;

import cn.qqtheme.framework.serviceimpl.WheelpickerServiceImpl;

public class WheelpickerAppLike implements IApplicationLike {

    Router router = Router.getInstance();
    UIRouter uiRouter = UIRouter.getInstance();

    @Override
    public void onCreate() {
        uiRouter.registerUI("wheelpicker");
        router.addService(WheelPickerService.class.getSimpleName(), new WheelpickerServiceImpl());
    }

    @Override
    public void onStop() {
        uiRouter.unregisterUI("wheelpicker");
        router.removeService(WheelPickerService.class.getSimpleName());
    }
}
