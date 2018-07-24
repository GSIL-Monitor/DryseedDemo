package com.dryseed.dryseedapp.test.statusBar;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.UIUtil;

/**
 * Created by caiminming on 2017/12/2.
 * <p>
 * 沉浸式状态栏实战做法
 * <p>
 * http://www.jianshu.com/p/0acc12c29c1b
 * 这里直接在v21\styles.xml下设置android:statusBarColor来控制状态栏颜色。与参考中的做法不一致，但实现更为简单
 */

public class TestStatusBar2Activity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statusbar2_layout);

        if (isStatusBarLight()) { //设置状态栏字体为深色样式
            UIUtil.MIUISetStatusBarLightMode(getWindow(), true);
            UIUtil.FlymeSetStatusBarLightMode(getWindow(), true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    public boolean isStatusBarLight() {
        return true;
    }
}


