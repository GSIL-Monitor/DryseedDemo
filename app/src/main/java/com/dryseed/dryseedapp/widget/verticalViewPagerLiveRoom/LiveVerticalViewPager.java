package com.dryseed.dryseedapp.widget.verticalViewPagerLiveRoom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.dryseed.dryseedapp.widget.verticalViewPagerLiveRoom.lib.VerticalViewPager;

public class LiveVerticalViewPager extends VerticalViewPager {

    private boolean isCanScroll = true;

    public LiveVerticalViewPager(Context context) {
        super(context);
    }

    public LiveVerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (isCanScroll)
            return super.onTouchEvent(arg0);
        else
            return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isCanScroll)
            return super.onInterceptTouchEvent(arg0);
        else
            return false;
    }

    public void setCanScroll(final boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        // 注意：此方法onPageSelected还是会触发
        // 此时，ViewPager.setCurretItem方法也会失效，想通过点击Tab button 切换page也无法实现。
        /*if (isCanScroll) {
            super.scrollTo(x, y);
        }*/
        super.scrollTo(x, y);
    }

}