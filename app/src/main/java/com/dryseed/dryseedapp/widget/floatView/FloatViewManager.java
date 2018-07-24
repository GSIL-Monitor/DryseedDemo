package com.dryseed.dryseedapp.widget.floatView;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.DPIUtil;

import java.lang.ref.SoftReference;

/**
 * Created by caiminming on 2017/12/9.
 * <p>
 * 思路：在BaseActivity的onResume/onPause中，控制FloatView的显示和隐藏
 */

public class FloatViewManager {
    private static FloatViewManager mInstance;

    public static FloatViewManager getInstance() {
        if (mInstance == null) {
            synchronized (FloatViewManager.class) {
                if (mInstance == null) {
                    mInstance = new FloatViewManager();
                }
            }
        }
        return mInstance;
    }

    private boolean mSwitchFlag = false;
    private SoftReference<FloatView> mFloatViewReference;
    private SoftReference<Activity> mActivitySoftReference;
    private float mLastX = 0;
    private float mLastY = 0;

    public void showFloatView(Activity activity) {
        if (!mSwitchFlag) return;
        if (mActivitySoftReference != null) {
            mActivitySoftReference.clear();
        }
        mActivitySoftReference = new SoftReference<>(activity);

        removeFloatView();
        addFloatView();
    }

    public void hideFloatView() {
        if (!mSwitchFlag) return;
        removeFloatView();
    }

    private void addFloatView() {
        if (mActivitySoftReference == null) return;
        Activity activity = mActivitySoftReference.get();
        if (activity == null) return;

        FloatView floatView;
        FrameLayout rootLayout = (FrameLayout) activity.getWindow().findViewById(android.R.id.content);
        View floatViewCache = rootLayout.findViewById(R.id.floatview);
        if (floatViewCache != null && floatViewCache instanceof FloatView) {
            floatView = (FloatView) floatViewCache;
        } else {
            floatView = new FloatView(activity);
            floatView.setId(R.id.floatview);
            floatView.setBackgroundColor(0xffff0000);
            rootLayout.addView(floatView, new FrameLayout.LayoutParams(DPIUtil.dip2px(100), DPIUtil.dip2px(100)));
        }

        if (mFloatViewReference != null) {
            mFloatViewReference.clear();
        }
        mFloatViewReference = new SoftReference<>(floatView);

        floatView.show(mLastX, mLastY);
        floatView.setVisibility(View.VISIBLE);
    }

    private void removeFloatView() {
        if (mFloatViewReference == null) return;
        FloatView floatView = mFloatViewReference.get();
        if (floatView == null) return;
        mLastX = floatView.getTranslationX();
        mLastY = floatView.getTranslationY();
        floatView.setVisibility(View.GONE);
    }

    public void setSwitchOpen(Activity activity) {
        mSwitchFlag = true;
        showFloatView(activity);
    }
}
