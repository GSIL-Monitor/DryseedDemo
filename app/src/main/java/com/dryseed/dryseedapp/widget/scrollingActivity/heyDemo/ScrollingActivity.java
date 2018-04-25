package com.dryseed.dryseedapp.widget.scrollingActivity.heyDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.DPIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caiminming on 2017/12/19.
 */

public class ScrollingActivity extends BaseActivity {

    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.toolbar)
    Toolbar mBToolBar;

    @BindView(R.id.parallax)
    FrameLayout mParallax;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hey_scrolling_layout);
        ButterKnife.bind(this);

        initToolBar();
    }

    private void initToolBar() {
        int actionBar = getActionBarHeight(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            int statusbar = DPIUtil.dip2px(25);
            mBToolBar.getLayoutParams().height = actionBar + statusbar;
            mBToolBar.setPadding(0, statusbar, 0, 0);
        }

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int max = mBToolBar.getHeight() - mParallax.getHeight(); // max = -657
                Log.d("MMM", "verticalOffset : " + verticalOffset + " | max : " + max);

                if (max != 0) {
                    float offset = verticalOffset * 1f / max; //重合时offset=1，toolbar出现
                    updateThemeColor(offset != 1);
                }

            }
        });
    }

    private int getActionBarHeight(Context context) {
        TypedArray array = context.getTheme().obtainStyledAttributes(new int[]{R.attr.actionBarSize});
        int size = array.getDimensionPixelOffset(0, 0);
        array.recycle();
        return size;
    }

    private int getStatusBarColor(Context c) {
        TypedArray array = c.getTheme().obtainStyledAttributes(new int[]{R.attr.defaultStatusBarColor,});
        int mAccentColor = array.getColor(0, 0);
        array.recycle();
        return mAccentColor;
    }

    private void updateThemeColor(boolean isTransparent) {
        if (isTransparent) {
            mBToolBar.setBackgroundColor(Color.TRANSPARENT);
        } else {
            mBToolBar.setBackgroundColor(getStatusBarColor(this));
        }
    }
}
