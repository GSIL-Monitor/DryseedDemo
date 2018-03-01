package com.dryseed.dryseedapp.test.measureSpec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

/**
 * //父控件不强加任何约束给子控件，它可以是它想要任何大小。
 * public static final int UNSPECIFIED = 0 << MODE_SHIFT;  //0
 * <p>
 * //父控件决定给孩子一个精确的尺寸
 * public static final int EXACTLY     = 1 << MODE_SHIFT;  //1073741824
 * <p>
 * //父控件会给子控件尽可能大的尺寸
 * public static final int AT_MOST     = 2 << MODE_SHIFT;   //-2147483648
 * <p>
 * <p>
 * Q: 分析为什么我们自定义的MyTextView设置了wrap_content却填充屏幕
 * A:  onMeasure方法调用了setMeasuredDimension(int measuredWidth, int measuredHeight)方法，而传入的参数已经是测量过的默认宽和高的值了；
 * 我们看看getDefaultSize 方法是怎么计算测量宽高的。根据父控件给予的约束，发现AT_MOST （相当于wrap_content ）和EXACTLY （相当于match_parent ）
 * 两种情况返回的测量宽高都是specSize，而这个specSize正是我们上面说的父控件剩余的宽高，所以默认onMeasure方法中wrap_content 和match_parent 的效果
 * 是一样的，都是填充剩余的空间。
 */
public class TestMeasureSpecActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_measure_spec_layout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MMM", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MMM", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MMM", "onRestart");
    }
}
