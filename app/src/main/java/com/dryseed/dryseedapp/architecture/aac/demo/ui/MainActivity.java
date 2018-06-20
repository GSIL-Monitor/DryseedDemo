package com.dryseed.dryseedapp.architecture.aac.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setId(R.id.layout_about);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(rootLayout);

        if (savedInstanceState == null) {
            UserFragment userFragment = UserFragment.getUserFragment("ds");
            getSupportFragmentManager().beginTransaction().add(R.id.layout_about, userFragment).commit();
        }
    }
}
