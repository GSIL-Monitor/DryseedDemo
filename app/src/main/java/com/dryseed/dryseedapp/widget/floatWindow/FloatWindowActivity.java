package com.dryseed.dryseedapp.widget.floatWindow;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.floatWindow.lib.FloatWindowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/12/25.
 * <p>
 * http://blog.csdn.net/self_study/article/details/52859790
 * https://github.com/zhaozepeng/FloatWindowPermission
 */

public class FloatWindowActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_window);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.show_btn)
    void onShowBtnClick() {
        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
    }

    @OnClick(R.id.hide_btn)
    void onHideBtnClick() {
        FloatWindowManager.getInstance().dismissWindow();
    }

}
