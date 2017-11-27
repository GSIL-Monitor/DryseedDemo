package com.dryseed.dryseedapp.animation.basicPropertyAnimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class Xml4AnimActivity extends BaseActivity {

    private ImageView mMv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_xml_layout);

        mMv = (ImageView) findViewById(R.id.id_mv);

    }

    public void scaleX(View view) {
        // 加载动画
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scalex);
        anim.setTarget(mMv);
        anim.start();
    }

    public void scaleXandScaleY(View view) {
        // 加载动画
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scale);
        mMv.setPivotX(0);
        mMv.setPivotY(0);
        //显示的调用invalidate
        mMv.invalidate();
        anim.setTarget(mMv);
        anim.start();
    }

}
