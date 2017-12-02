package com.dryseed.dryseedapp.test.statusBar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

/**
 * Created by caiminming on 2017/12/2.
 * <p>
 * 沉浸式模式:http://blog.csdn.net/guolin_blog/article/details/51763825
 */

public class TestStatusBarActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbar_layout);

        // activity的style设置的是去掉actionBar
        // 接下来我们使用了SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN和SYSTEM_UI_FLAG_LAYOUT_STABLE，注意两个Flag必须要结合在一起使用，
        // 表示会让应用的主体内容占用系统状态栏的空间，最后再调用Window的setStatusBarColor()方法将状态栏设置成透明色就可以了。
        // 如下代码实现的效果：使导航栏、状态栏变透明，底下透出页面内容
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //标准的沉浸式模式
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
