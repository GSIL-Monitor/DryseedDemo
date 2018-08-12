package com.dryseed.dryseedapp.animation.scroll2finish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.Scroll2FinishHelper;

/**
 * @author CaiMinMing
 */
public class TestScroll2FinishActivity extends BaseActivity {

    private Scroll2FinishHelper mScroll2FinishHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra_viewpager_layout);
        mScroll2FinishHelper = new Scroll2FinishHelper(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (canScrollFinish() && mScroll2FinishHelper.dispatchTouchEvent(ev)) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否可以滑动返回
     *
     * @return 默认关闭
     */
    public boolean canScrollFinish() {
        return true;
    }
}
