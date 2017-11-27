package com.dryseed.dryseedapp.widget.viewFlipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class TestViewFlipperActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper_layout);

        /*
        这里介绍ViewFlipper用到的属性，这些属性其实都可以使用代码实现，只不过这里为了代码看上去美观，才放在布局里的

        android:autoStart：设置自动加载下一个View
        android:flipInterval：设置View之间切换的时间间隔
        android:inAnimation：设置切换View的进入动画
        android:outAnimation：设置切换View的退出动画

        下面是ViewFlipper常用的方法介绍，除了可以设置上面的属性之外，还提供了其他方法

        isFlipping： 判断View切换是否正在进行
        setFilpInterval：设置View之间切换的时间间隔
        startFlipping：开始View的切换，而且会循环进行
        stopFlipping：停止View的切换
        setOutAnimation：设置切换View的退出动画
        setInAnimation：设置切换View的进入动画
        showNext： 显示ViewFlipper里的下一个View
        showPrevious：显示ViewFlipper里的上一个View
         */
        ViewFlipper vf = (ViewFlipper) findViewById(R.id.vf);

        vf.addView(View.inflate(this, R.layout.activity_view_flipper_advertisement01, null));
        vf.addView(View.inflate(this, R.layout.activity_view_flipper_advertisement02, null));
        vf.addView(View.inflate(this, R.layout.activity_view_flipper_advertisement03, null));
    }
}
