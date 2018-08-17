package com.dryseed.aaccomponent.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.dryseed.aaccomponent.R;
import com.luojilab.component.basicres.BaseActivity;
import com.luojilab.router.facade.annotation.RouteNode;

@RouteNode(path = "/demo", desc = "demo")
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setId(R.id.btn1);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(rootLayout);

        if (savedInstanceState == null) {
            UserFragment userFragment = UserFragment.getUserFragment("ds");
            getSupportFragmentManager().beginTransaction().add(R.id.btn1, userFragment).commit();
        }
    }
}
