package com.dryseed.dryseedapp.widget.scrollLinearLayout;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.dryseed.dryseedapp.utils.FloatUtil;
import com.dryseed.dryseedapp.utils.LogUtil;

/**
 * 可滑动的LinearLayout，用于顶部空间的显示和隐藏
 *
 * @author zhaokaiyuan
 */
public class ScrollLinearLayout extends LinearLayout {

    private static final String TAG = "ScrollLinearLayout";

    private final static int STATE_NULL = 0; // 无状态
    private final static int STATE_HORIZONTAL = 1; // 横滑
    private final static int STATE_VERTICAL = 2; // 竖滑
    private final static int ANIMATOR_DURATION = 200; // 默认动画时长
    private final int mTouchSlop; // 滑动阈值
    private final ValueAnimator.AnimatorUpdateListener ul = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float value = (float) animation.getAnimatedValue();
            innerTranslationY(value);
        }
    };

    private int mGestureState = STATE_NULL; // 记录手势状态 0：无状态 1：横滑 2：竖滑
    private ValueAnimator mAnimator; // 显示隐藏的动画
    private float mLastY = 0; // 上次Y位置
    private float mInitialX = 0; // 初始X位置
    private float mInitialY = 0; // 初始Y位置
    private int mScrollPointerId = MotionEvent.INVALID_POINTER_ID; // 当前手指
    private boolean mHasDownEvent = false; // 是否接收到了Down事件
    private IScrollControlListener mControlListener; // 滑动控制
    private ITranslationChangeListener mTranslationChangeListener; // 位移监听

    public ScrollLinearLayout(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public ScrollLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public ScrollLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(final MotionEvent ev) {
        // 检查是否需要滑动
        if (mControlListener != null && mControlListener.shouldDispatchTouch()) {
            int action = MotionEventCompat.getActionMasked(ev);
            int eventIndex = MotionEventCompat.getActionIndex(ev);
            int scrollDistance = mControlListener.getScrollDistance();
            float translationY = getCurrentTranslationY();
            // 停止当前的动画
            stopAnimator();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    LogUtil.d("ACTION_DOWN");
                    // 第一个手指按下时 初始化状态
                    mScrollPointerId = ev.getPointerId(0);
                    mInitialX = ev.getX();
                    mInitialY = mLastY = ev.getY();

                    mHasDownEvent = true;
                    mGestureState = STATE_NULL;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    // 记录新按下的手指的状态
                    mScrollPointerId = ev.getPointerId(eventIndex);
                    mInitialX = ev.getX(eventIndex);
                    mInitialY = mLastY = ev.getY(eventIndex);
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    // 当前滑动手指离开屏幕
                    if (ev.getPointerId(eventIndex) == mScrollPointerId) {
                        final int newIndex = eventIndex == 0 ? 1 : 0;
                        mScrollPointerId = ev.getPointerId(newIndex);
                        mInitialX = ev.getX(newIndex);
                        mInitialY = mLastY = ev.getY(newIndex);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    LogUtil.d("ACTION_MOVE");
                    if (!mHasDownEvent || mScrollPointerId == MotionEvent.INVALID_POINTER_ID) {
                        break;
                    }
                    final int index = ev.findPointerIndex(mScrollPointerId);
                    if (index >= ev.getPointerCount() || index < 0) {
                        break;
                    }
                    try {
                        final float currentX = ev.getX(index);
                        final float currentY = ev.getY(index);
                        // 判断手指滑动状态 横向滑动的时候不处理
                        if (mGestureState == STATE_NULL) {
                            float xDiff = Math.abs(currentX - mInitialX);
                            float yDiff = Math.abs(currentY - mInitialY);
                            if (xDiff > mTouchSlop || yDiff > mTouchSlop) {
                                if (yDiff > xDiff) {
                                    mGestureState = STATE_VERTICAL;
                                } else {
                                    mGestureState = STATE_HORIZONTAL;
                                }
                            }
                        } else if (mGestureState == STATE_VERTICAL) {
                            float deltaY = ev.getY(index) - mLastY;
                            if (deltaY > 0) {
                                // 下拉，显示top
                                if (translationY < 0) {
                                    if (translationY + deltaY > 0) {
                                        innerTranslationY(0);
                                    } else {
                                        innerTranslationY(translationY + deltaY);
                                    }
                                }
                            } else {
                                // 上滑，隐藏top
                                if (translationY > -scrollDistance) {
                                    LogUtil.d("上滑，隐藏top");
                                    if (translationY + deltaY < -scrollDistance) {
                                        innerTranslationY(-scrollDistance);
                                    } else {
                                        innerTranslationY(translationY + deltaY);
                                    }
                                }
                            }
                        }
                        mLastY = currentY;
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    if (!mHasDownEvent) {
                        break;
                    }
                    // 松手的时候自动显示完整或者隐藏
                    if (translationY < 0 && translationY > -scrollDistance) {
                        if (translationY < -scrollDistance / 2f) {
                            hideScrollSpace(false);
                        } else {
                            showScrollSpace(false);
                        }
                    }
                    mHasDownEvent = false;
                    break;
                default:
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 停止动画
     * 对每一个ChildView在Y轴上位移
     *
     * @param translationY Y轴位移距离
     */
    public void translationY(float translationY) {
        stopAnimator();
        innerTranslationY(translationY);
    }

    /**
     * 内部移动 不会停止动画
     */
    private void innerTranslationY(float translationY) {
        float currentTranslationY = getCurrentTranslationY();
        if (FloatUtil.equals(translationY, currentTranslationY)) {
            return;
        }
        int count = getChildCount();
        for (int i = 0; i < count; ++i) {
            View view = getChildAt(i);
            view.setTranslationY(translationY);
        }
        if (mTranslationChangeListener != null) {
            mTranslationChangeListener.onTranslationChanged(translationY);
        }
    }

    /**
     * 停止动画
     */
    private void stopAnimator() {
        if (mAnimator != null) {
            mAnimator.cancel();
        }
    }

    /**
     * 显示隐藏的顶部空间
     *
     * @param immediately 是否有动画
     */
    public void showScrollSpace(boolean immediately) {
        if (immediately) {
            translationY(0);
            return;
        }
        if (mControlListener == null || !mControlListener.shouldDispatchTouch()) {
            return;
        }
        float translationY = getCurrentTranslationY();
        if (FloatUtil.equals(translationY, 0)) {
            return;
        }
        mAnimator = ValueAnimator.ofFloat(translationY, 0).setDuration(ANIMATOR_DURATION);
        mAnimator.addUpdateListener(ul);
        mAnimator.setInterpolator(new DecelerateInterpolator(2));
        mAnimator.start();
    }

    /**
     * 隐藏顶部空间
     */
    public void hideScrollSpace(boolean immediately) {
        if (immediately && mControlListener != null) {
            translationY(-mControlListener.getScrollDistance());
            return;
        }
        if (mControlListener == null || !mControlListener.shouldDispatchTouch()) {
            return;
        }
        int distance = mControlListener.getScrollDistance();
        float translationY = getCurrentTranslationY();
        if (FloatUtil.equals(translationY + distance, 0)) {
            return;
        }
        mAnimator = ValueAnimator.ofFloat(translationY, -distance).setDuration(ANIMATOR_DURATION);
        mAnimator.addUpdateListener(ul);
        mAnimator.setInterpolator(new AccelerateInterpolator(2));
        mAnimator.start();
    }

    /**
     * 设置ScrollControlListener
     */
    public void setIScrollControlListener(IScrollControlListener controlListener) {
        if (this.mControlListener != controlListener) {
            this.mControlListener = controlListener;
        }
    }

    /**
     * 设置位移监听
     */
    public void setTranslationChangeListener(ITranslationChangeListener translationChangeListener) {
        if (mTranslationChangeListener != translationChangeListener) {
            mTranslationChangeListener = translationChangeListener;
        }
    }

    /**
     * 获取当前的Y轴位移
     *
     * @return 当前的Y轴位移
     */
    public float getCurrentTranslationY() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                return child.getTranslationY();
            }
        }
        return 0;
    }

    /**
     * 当可展示区域变化时(status bar,navigation bar,action bars, input methods等
     * 系统UI变化时,回调的接口),这里主要处理status bar显隐时,如果设置了SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
     * 则将这个根布局向下移status bar高度的距离,解决进入首页出现页面下移的问题
     * 高版本应该使用onApplyWindowInsets,但是某些小品牌会存在WindowInsets类找不到的问题,所以替换为
     * 在MainActivity的setLayoutFullScreen通过setOnApplyWindowInsetsListener替代
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected boolean fitSystemWindows(Rect insets) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return super.fitSystemWindows(insets);
        }
        fitLayoutFullScreen(insets.top);
        return super.fitSystemWindows(insets);
    }

    /**
     * 如果是SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN模式,则设置topMargin = statusBarHeight;
     * 如果是SYSTEM_UI_FLAG_FULLSCREEN模式,则设置topMargin = 0;
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void fitLayoutFullScreen(int statusBarHeight) {
        MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
        int flags = ((Activity) getContext()).getWindow().getDecorView().getSystemUiVisibility();
        boolean layoutFullScreen = (flags & SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN) == SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        boolean fullScreen = (flags & SYSTEM_UI_FLAG_FULLSCREEN) == SYSTEM_UI_FLAG_FULLSCREEN;
        fullScreen |= (((Activity) getContext()).getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN)
                == WindowManager.LayoutParams.FLAG_FULLSCREEN;
        if (fullScreen && lp.topMargin != 0) {
            lp.topMargin = 0;
            setLayoutParams(lp);
            invalidate();
        } else if (layoutFullScreen && lp.topMargin != statusBarHeight && statusBarHeight != 0) {
            lp.topMargin = statusBarHeight;
            setLayoutParams(lp);
            invalidate();
        }
    }

    /**
     * 滑动控制的interface
     */
    public interface IScrollControlListener {

        /**
         * @return 是否应该滑动
         */
        boolean shouldDispatchTouch();

        /**
         * @return 滑动距离
         */
        int getScrollDistance();

    }

    public interface ITranslationChangeListener {

        void onTranslationChanged(float translation);

    }
}
