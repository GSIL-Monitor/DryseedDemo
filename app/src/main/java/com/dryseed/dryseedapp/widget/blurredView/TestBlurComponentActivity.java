package com.dryseed.dryseedapp.widget.blurredView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.componentservice.blur.BlurService;

/**
 * @author caiminming
 */
public class TestBlurComponentActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_blur_layout);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBlur();
            }
        });
    }

    private void doBlur() {
        Router router = Router.getInstance();
        if (router.getService(BlurService.class.getSimpleName()) != null) {
            ToastUtils.showShort("doBlur ");
            BlurService service = (BlurService) router.getService(BlurService.class.getSimpleName());
            service.doBlur(this, R.drawable.city1, findViewById(R.id.image));
        } else {
            ToastUtils.showShort("Please load BlurComponent first");
        }
    }
}
