package com.dryseed.dryseedapp.framework.lottie;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

/**
 * Created by caiminming on 2017/7/27.
 */

public class TestLottieActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_layout);

        LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.animation_view2);
        animationView.setAnimation("favourite_app_icon.json");
        animationView.loop(true);
        animationView.playAnimation();
    }
}
