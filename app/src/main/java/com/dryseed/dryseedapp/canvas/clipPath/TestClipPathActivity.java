package com.dryseed.dryseedapp.canvas.clipPath;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.DPIUtil;

/**
 * ClipPath实现圆角功能
 * 注：图片缩放时，圆角处理会有问题
 */
public class TestClipPathActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClipPathRoundImageView clipPathRoundImageView = new ClipPathRoundImageView(this);
        clipPathRoundImageView.setImageResource(R.drawable.tbug);
        addContentView(clipPathRoundImageView, new FrameLayout.LayoutParams(DPIUtil.dip2px(200), DPIUtil.dip2px(200)));
    }
}
