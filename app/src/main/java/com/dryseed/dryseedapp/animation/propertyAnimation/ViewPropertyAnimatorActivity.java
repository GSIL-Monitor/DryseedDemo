package com.dryseed.dryseedapp.animation.propertyAnimation;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.luojilab.component.basiclib.utils.DPIUtil;

public class ViewPropertyAnimatorActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextView textView = new TextView(this);
        FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(DPIUtil.dip2px(50), DPIUtil.dip2px(50));
        flp.gravity = Gravity.CENTER;
        textView.setText("BTN");
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(0xffcccccc);
        addContentView(textView, flp);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*
                    仅仅只是 View.animate().scaleX() 这样使用时，就算不主动调用 start() ，其实内部也会自动安排一个 Runnable，
                    最迟在下一个屏幕刷新信号来的时候，就会自动去调用 startAnimation() 来启动动画。
                    但如果主动调用了 start()，内部就需要先将安排好的 Runnable 操作取消掉，然后直接调用 startAnimation() 来启动动画。
                 */
                textView.animate().alpha(0.5f);
            }
        }, 1000);
    }
}
