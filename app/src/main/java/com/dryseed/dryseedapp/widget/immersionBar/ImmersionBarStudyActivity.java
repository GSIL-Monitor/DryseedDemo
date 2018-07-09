package com.dryseed.dryseedapp.widget.immersionBar;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.LogUtil;
import com.dryseed.dryseedapp.utils.UIUtil;

/**
 * @author caiminming
 * https://github.com/gyf-dev/ImmersionBar
 */
public class ImmersionBarStudyActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersion_bar_2_layout);

        //TODO: 判断MIUI版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // 透明状态栏 4.4 <= V < 5.0 :
            // 设置了之后，相当于隐藏了状态栏，下面的布局会填补状态栏。
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 创建一个占位状态栏
            setupStatusBarView();
        }

    }

    private void setupStatusBarView() {
        LogUtil.d("" + UIUtil.getStatusBarHeight(this));
    }
}
