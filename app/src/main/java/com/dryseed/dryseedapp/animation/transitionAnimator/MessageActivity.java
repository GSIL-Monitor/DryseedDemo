package com.dryseed.dryseedapp.animation.transitionAnimator;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

/**
 * Created by qibin on 16-11-20.
 */

public class MessageActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_animator_message_layout);
        setTitle("Content");

        TextView msgTextView = (TextView) findViewById(R.id.msg);
        msgTextView.setText(getIntent().getStringExtra("msg"));

        executeTransition();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void executeTransition() {
        postponeEnterTransition();

        final View decorView = getWindow().getDecorView();
        getWindow().getDecorView().getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                decorView.getViewTreeObserver().removeOnPreDrawListener(this);
                supportStartPostponedEnterTransition();
                return true;
            }
        });

        MyTransition transition = new MyTransition();
        transition.setPositionDuration(300);
        transition.setSizeDuration(300);
        transition.setPositionInterpolator(new FastOutLinearInInterpolator());
        transition.setSizeInterpolator(new FastOutSlowInInterpolator());
        transition.addTarget("message");

        getWindow().setSharedElementEnterTransition(transition);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
