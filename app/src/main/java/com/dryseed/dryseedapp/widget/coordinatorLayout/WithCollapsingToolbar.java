package com.dryseed.dryseedapp.widget.coordinatorLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;

public class WithCollapsingToolbar extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout_with_collapsingtoolbar_3);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("标题");
        toolbar.setLogo(getResources().getDrawable(R.drawable.dianhua));
        setSupportActionBar(toolbar);

        TextView tv = (TextView)findViewById(R.id.tv);
        for (int i = 0; i < 50; i++) {
            tv.append((i + 1) + "\n");
        }
    }
}
