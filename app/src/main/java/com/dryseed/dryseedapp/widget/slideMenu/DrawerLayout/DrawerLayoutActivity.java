package com.dryseed.dryseedapp.widget.slideMenu.DrawerLayout;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.dryseed.dryseedapp.R;
import com.nineoldandroids.view.ViewHelper;

public class DrawerLayoutActivity extends FragmentActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_drawer_layout_layout);

        initView();
        initEvents();

    }

    public void OpenRightMenu(View view) {
        mDrawerLayout.openDrawer(Gravity.RIGHT);
        /*
		为了模拟QQ的右侧菜单需要点击才能出现，所以在初始化DrawerLayout的时候，使用了mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,Gravity.RIGHT);意思是只有编程才能将其弹出。
		然后在弹出以后，需要让手势可以滑动回去，所以在OpenRightMenu中又编写了：
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,Gravity.RIGHT); UNLOCK了一下。
		最后在onDrawerClosed回调中，继续设置mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,Gravity.RIGHT)；
		 */
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.RIGHT);
    }


    private void initEvents() {
        mDrawerLayout.setDrawerListener(new DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;

                if (drawerView.getTag().equals("LEFT")) {

                    float leftScale = 1 - 0.3f * scale;

                    ViewHelper.setScaleX(mMenu, leftScale);
                    ViewHelper.setScaleY(mMenu, leftScale);
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                    ViewHelper.setTranslationX(mContent,
                            mMenu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                } else {
                    ViewHelper.setTranslationX(mContent,
                            -mMenu.getMeasuredWidth() * slideOffset);
                    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }

            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }
        });
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);
    }

}
