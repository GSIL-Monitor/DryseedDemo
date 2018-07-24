package com.dryseed.dryseedapp.widget.slideMenu.SlideMenu04;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.DPIUtil;
import com.nineoldandroids.view.ViewHelper;
import com.orhanobut.logger.Logger;

/**
 * 区别1、QQ的内容区域会伴随菜单的出现而缩小
 * 区别2、QQ的侧滑菜单给人的感觉是隐藏在内容的后面，而不是拖出来的感觉
 * 区别3、QQ的侧滑菜单有一个缩放以及透明度的效果~
 * <p/>
 * 那么我们如何能做到呢：
 * 对于区别1：这个好办，我们可以在滑动的时候，不断的改变内容区域的大小；如何改变呢？我们在菜单出现的整个过程中，不断记录菜单显示的宽度与其总宽度的比值，是个从0到1的过程，然后把0~1转化为1~0.7（假设内容区域缩小至0.7)；不断去缩小内容区域；
 * 对于区别3：也比较好办，上面已经可以得到0到1的这个值了，那么缩放和透明度的动画就不在话下了；
 * 对于区别2：我们使用的HorizontalScrollView，然后水平放置了菜单和内容，如何让菜单可以隐藏到内容的后面呢？其实也比较简单，在菜单出现的过程中，不断设置菜单的x方向的偏移量；0的时候完全隐藏，0.3的时候，隐藏x方向偏移量为0.7个宽度，类推~~~
 */
public class SlidingMenu extends HorizontalScrollView {
    /**
     * 屏幕宽度
     */
    private int mScreenWidth;
    /**
     * dp
     */
    private int mMenuRightPadding;
    /**
     * 菜单的宽度
     */
    private int mMenuWidth;
    private int mHalfMenuWidth;

    private boolean isOpen;

    private boolean once;

    private ViewGroup mMenu;
    private ViewGroup mContent;

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Logger.d("MMM", "DPIUtil.getWidth() : " + DPIUtil.getWidth());
        mScreenWidth = DPIUtil.getWidth();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SlidingMenu, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.SlidingMenu_rightPadding:
                    // 默认50
                    mMenuRightPadding = a.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 50f,
                                    getResources().getDisplayMetrics()));// 默认为10DP
                    Logger.d("MMM", "mMenuRightPadding : " + mMenuRightPadding);
                    break;
            }
        }
        a.recycle();
    }

    public SlidingMenu(Context context) {
        this(context, null, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 显示的设置一个宽度
         */
        if (!once) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) wrapper.getChildAt(0);
            mContent = (ViewGroup) wrapper.getChildAt(1);

            mMenuWidth = mScreenWidth - mMenuRightPadding;
            mHalfMenuWidth = mMenuWidth / 2;
            mMenu.getLayoutParams().width = mMenuWidth;
            mContent.getLayoutParams().width = mScreenWidth;

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            // 将菜单隐藏
            this.scrollTo(mMenuWidth, 0);
            once = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            // Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX > mHalfMenuWidth) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                } else {
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 打开菜单
     */
    public void openMenu() {
        if (isOpen)
            return;
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (isOpen) {
            this.smoothScrollTo(mMenuWidth, 0);
            isOpen = false;
        }
    }

    /**
     * 切换菜单状态
     */
    public void toggle() {
        if (isOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    /**
     * 1、首先是内容区域的缩放比例计算：
     * 我们准备让在菜单出现的过程中，让内容区域从1.0~0.8进行变化~~
     * 那么怎么把1.0~0.0转化为1.0~0.8呢，其实很简单了：
     * float rightScale = 0.8f + scale * 0.2f; （scale 从1到0 ），是不是哦了~
     * 接下来还有3个动画：
     * 2、菜单的缩放比例计算
     * 仔细观察了下QQ，菜单大概缩放变化是0.7~1.0
     * float leftScale = 1 - 0.3f * scale;
     * 3、菜单的透明度比例：
     * 我们设置为0.6~1.0；即：0.6f + 0.4f * (1 - scale)
     * 4、菜单的x方向偏移量：
     * 看一下QQ，并非完全从被内容区域覆盖，还是有一点拖出的感觉，所以我们的偏移量这么设置：
     * tranlateX = mMenuWidth * scale * 0.6f ；刚开始还是让它隐藏一点点~~~
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        // DPIUtil.getWidth() : 1080
        // mMenuRightPadding : 300
        // l变化范围： 1080-300 ~ 0
        Logger.d("MMM", "l : " + l);
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / mMenuWidth; // 1 ~ 0
        float leftScale = 1 - 0.3f * scale;
        float rightScale = 0.8f + scale * 0.2f;

        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);
        ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
        Logger.d("MMM", "setTranslationX : " + mMenuWidth * scale);
//        ViewHelper.setTranslationX(mMenu, mMenuWidth * scale); //菜单的x方向偏移量。mMenuWidth * scale表示未显示的menu宽度，设置为TranslationX 表示 menu总是显示在未显示的右侧，即刚好全部显示
        ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.7f); //菜单的x方向偏移量

        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
        ViewHelper.setScaleX(mContent, rightScale);
        ViewHelper.setScaleY(mContent, rightScale);

    }

}
