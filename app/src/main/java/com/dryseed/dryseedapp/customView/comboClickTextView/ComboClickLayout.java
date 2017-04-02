package com.dryseed.dryseedapp.customView.comboClickTextView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import com.dryseed.dryseedapp.R;

/**
 * Created by User on 2017/4/1.
 */
public class ComboClickLayout extends LinearLayout {

    private static final int COMBO_CLICK_INTERVAL = 1000;
    private static final int ANIMATION_IN_INTERVAL = 200;
    private static final int ANIMATION_OUT_INTERVAL = 300;

    private LayoutInflater mInflater;
    private ComboClickTextView mComboClickTextView;
    private CountDownTimer mCountDownTimer;

    private int mClickNum = 0;

    public ComboClickLayout(Context context) {
        super(context, null);
    }

    public ComboClickLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        View view = mInflater.inflate(R.layout.activity_test_thumbs_up_layout, null);
        mComboClickTextView = (ComboClickTextView) view.findViewById(R.id.comboClickTextView);
        this.addView(view);
    }

    public void addClick() {
        mClickNum++;
        showComboClickLayout();
    }

    private void showComboClickLayout() {
        if(null == mComboClickTextView) return;

        if (null != mComboClickTextView) {
            mComboClickTextView.setText(mClickNum + "");
        }

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(this, "scaleX", 0.5f, 1.0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(this, "scaleY", 0.5f, 1.0f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(this, "alpha", 0, 1.0f);
        AnimatorSet animSetIn = new AnimatorSet();
        animSetIn.setDuration(ANIMATION_IN_INTERVAL);
        animSetIn.setInterpolator(new AccelerateDecelerateInterpolator());
        animSetIn.playTogether(anim1, anim2, anim3);
        animSetIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
        animSetIn.start();
        restartCountDownTimer();
    }

    private void restartCountDownTimer(){
        if(null == mCountDownTimer){
            mCountDownTimer = new CountDownTimer(COMBO_CLICK_INTERVAL, COMBO_CLICK_INTERVAL) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    mClickNum = 0;
                    hideComboCLickLayout();
                }
            }.start();
        } else {
            mCountDownTimer.cancel();
            mCountDownTimer.start();
        }
    }

    private void hideComboCLickLayout() {
        if(null == mComboClickTextView) return;

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 0.5f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 0.5f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(this, "alpha", 1.0f, 0);
        AnimatorSet animSetOut = new AnimatorSet();
        animSetOut.setDuration(ANIMATION_OUT_INTERVAL);
        animSetOut.setInterpolator(new AccelerateDecelerateInterpolator());
        animSetOut.playTogether(anim1, anim2, anim3);
        animSetOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
        animSetOut.start();
    }

}
