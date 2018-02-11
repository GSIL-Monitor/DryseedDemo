package com.dryseed.dryseedapp.canvas.doubleBuffer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dryseed.dryseedapp.BaseActivity;

/**
 * 双缓冲绘图
 * https://www.jianshu.com/p/efc0bebfd22e
 */
public class TestDoubleBufferActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DoubleBufferView doubleBufferView = new DoubleBufferView(this);
        setContentView(doubleBufferView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
