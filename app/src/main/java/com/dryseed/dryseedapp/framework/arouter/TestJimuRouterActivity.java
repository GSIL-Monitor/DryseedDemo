package com.dryseed.dryseedapp.framework.arouter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.componentlib.router.ui.UIRouter;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * annotationProcessor 'com.luojilab.ddcomponent:router-anno-compiler:1.0.0'
 * https://www.jianshu.com/p/03c498e05a46
 */
public class TestJimuRouterActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arouter_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn1)
    void goTestButterKnifeActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("NAME", "Gone with the Wind");
        UIRouter.getInstance().openUri(this, "dryseed://app/butterknife", bundle);
    }

}
