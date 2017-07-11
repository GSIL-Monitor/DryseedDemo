package com.dryseed.dryseedapp.widget.coordinatorLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dryseed.dryseedapp.R;

/**
 * Created by caiminming on 2017/7/10.
 */

public class TestCoodinatorLayoutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout_main);
    }

    public void intentTo(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, WithFloatingActionButton.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this, WithAppBarLayout.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(this, WithCollapsingToolbar.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(this, WithViewPager.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(this, WithZhiHu.class));
                break;
        }
    }
}
