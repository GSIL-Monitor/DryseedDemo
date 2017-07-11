package com.dryseed.dryseedapp.widget.coordinatorLayout;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.widget.RadioGroup;

/**
 * 知乎效果底部 behavior
 */
public class FooterBehavior extends CoordinatorLayout.Behavior<View> {

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private int sinceDirectionChange;

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.d("MMM", "layoutDependsOn");
        return super.layoutDependsOn(parent, child, dependency) ||
                dependency instanceof RadioGroup;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.d("MMM", "onDependentViewChanged");
        return super.onDependentViewChanged(parent, child, dependency);
    }

    //1.判断滑动的方向 我们需要垂直滑动
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        Log.d("MMM", "onStartNestedScroll : " + nestedScrollAxes);
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int
            dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.d("MMM", "onNestedScroll");
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed);
    }

    //2.根据滑动的距离显示和隐藏footer view
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child,
                                  View target, int dx, int dy, int[] consumed) {
        Log.d("MMM", "onNestedPreScroll");
        if (dy > 0 && sinceDirectionChange < 0 || dy < 0 && sinceDirectionChange > 0) {
            child.animate().cancel();
            sinceDirectionChange = 0;
        }
        sinceDirectionChange += dy;
        int visibility = child.getVisibility();
        Log.d("MMM", "sinceDirectionChange : " + sinceDirectionChange + " | child.getHeight() : " + child.getHeight());
        if (sinceDirectionChange > child.getHeight() && visibility == View.VISIBLE) {
            hide(child);
            //child.setVisibility(View.INVISIBLE);
        } else if (sinceDirectionChange < 0 && child.getVisibility() == View.INVISIBLE) {
            show(child);
            //child.setVisibility(View.VISIBLE);
        }
    }

    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(view.getHeight()).
                setInterpolator(INTERPOLATOR).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).
                setInterpolator(INTERPOLATOR).
                setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();

    }
}
