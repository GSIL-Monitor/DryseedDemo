package com.dryseed.dryseedapp.widget.blurredView;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.dryseed.dryseedapp.BaseActivity;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.componentservice.blur.BlurService;

/**
 * @author caiminming
 */
public class TestBlurComponentActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        doBlur();
    }

    private void doBlur() {
        Router router = Router.getInstance();
        if (router.getService(BlurService.class.getSimpleName()) != null) {
            BlurService service = (BlurService) router.getService(BlurService.class.getSimpleName());
            boolean ret = service.doBlur(this);
            ToastUtils.showShort("doBlur " + ret);
        } else {
            ToastUtils.showShort("Please load BlurComponent first");
        }
    }
}
