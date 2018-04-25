package com.dryseed.dryseedapp.animation.viewanimator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.animation.viewanimator.lib.ViewAnimator;
import com.luojilab.component.basiclib.DPIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/12/21.
 */

public class TestViewAnimatorActivity extends BaseActivity {

    @BindView(R.id.button)
    Button mButton;

    @BindView(R.id.imageview)
    ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animator_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    void onButtonClick() {
        /*ViewAnimator
                .animate(mImageView)
                .translationY(0, 1000)
                .alpha(1.0f, 0.1f)
                .duration(1000)
                .interpolator(new AccelerateDecelerateInterpolator())

                .thenAnimate(mImageView)
                .alpha(0.1f, 1.0f)
                .width(mImageView.getWidth(), DPIUtil.dip2px(100))
                .duration(2000)
                .decelerate()

                .thenAnimate(mImageView)
                .pulse()
                .repeatCount(-1)
                .start();*/

        ViewAnimator
                .animate(mImageView)
                .scale(1f, 1.3f, 1f)
                .duration(300)
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();
    }
}
