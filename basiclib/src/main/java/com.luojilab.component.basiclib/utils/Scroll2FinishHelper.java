package com.luojilab.component.basiclib.utils;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.LinearInterpolator;


/**
 * 1. 将activity的主题设为Theme_Translucent_NoTitleBar
 * 2. 在activivty里面继承onDispatchEvent,并调用#dispatchTouchEvent()
 * <p/>
 * Created by shenshan on 15/6/4.
 */
public class Scroll2FinishHelper {

    private static final String TAG = "Scroll2FinishHelper";
    private final int mTouchSlop;//向右移动,缓冲，防止过于灵敏，用户易误操作，
    private ValueAnimator animator;
    private int screenWidth = 0;
    private ViewGroup contentView;
    private Activity activity;
    private boolean move = false;
    private int duration = 300;
    private ViewGroup rootView;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private VelocityTracker mVelocityTracker;

    public Scroll2FinishHelper(final Activity activity) {
        activity.setTheme(android.R.style.Theme_Translucent_NoTitleBar);
        this.activity = activity;
        final ViewConfiguration configuration = ViewConfiguration.get(activity);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
        screenWidth = activity.getResources().getDisplayMetrics().widthPixels;
        animator = new ValueAnimator();
        animator.setDuration(duration);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int x = (Integer) valueAnimator.getAnimatedValue();
                if (x <= 0) {
                    x = 0;
                }
                setTranslationX(x);
                if (x >= screenWidth) {
                    activity.finish();
                }
            }
        });
    }

    private static final String BLACK = "000000";//透明黑色

    private void setTranslationX(float x) {
        contentView.setTranslationX(x);
        // 0~Ef  230
        float range = (1 - Math.abs(x) / (screenWidth * 2 / 3f));
        if (range < 0) {
            range = 0;
        }
        if (range > 1) {
            range = 1;
        }
        String alpha = Integer.toHexString((int) (230 * range));
        if (alpha.length() == 1) {//保证两位
            alpha = "0" + alpha;
        }
        if (rootView != null) {
            String color = "#" + alpha + BLACK;
            rootView.setBackgroundColor(Color.parseColor(color));
        }
    }

    public boolean dispatchTouchEvent(MotionEvent event) {

        if (contentView == null) {
            ViewGroup contentViewContainer = ((ViewGroup) activity.findViewById(android.R.id.content));
            if (contentViewContainer != null) {
                contentView = (ViewGroup) contentViewContainer.getChildAt(0);
                if (contentView != null && contentView.getParent() instanceof ViewGroup) {
                    rootView = (ViewGroup) contentView.getParent();
                }
            }
        }
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        if (contentView == null) {
            return move;
        }
        if (animator.isRunning()) {
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Remember where the motion event started
                mInitialMotionX = event.getX();
                mInitialMotionY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!move) {
                    final float x = event.getX();
                    final float xDiff = Math.abs(x - mInitialMotionX);
                    final float y = event.getY();
                    final float yDiff = Math.abs(y - mInitialMotionY);
                    if (xDiff > mTouchSlop && xDiff > yDiff && mInitialMotionX < DPIUtil.dip2px(50)) {//必须从最左边开始滑动
                        move = true;
                        if (rootView != null) {
                            // Disallow Parent Intercept, just in case
                            ViewParent parent = rootView.getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                    }
                }
                if (move) {
                    setTranslationX(event.getX());
                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                int distance = (int) (event.getX() - mInitialMotionX);
                float velocity = 0;
                mVelocityTracker.computeCurrentVelocity(1);
                velocity = mVelocityTracker.getXVelocity();
                mVelocityTracker.recycle();
                mVelocityTracker = null;
                if (move && Math.abs(contentView.getTranslationX()) >= 0) {
                    if (distance >= screenWidth / 3 || velocity > 1f) {//划过了屏幕1/3或者滑动速度>1直接划出activity
                        animator.setIntValues((int) event.getX(), screenWidth);
                    } else {
                        animator.setIntValues((int) event.getX(), 0);
                    }
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    contentView.dispatchTouchEvent(event);
                    animator.start();
                    move = false;
                    mInitialMotionX = 0;
                    mInitialMotionY = 0;
                    return true;
                }
                break;
            default:
                break;
        }
        return move && Math.abs(contentView.getTranslationX()) > 0;//点击左边栏并向右有移动
    }
}
