package com.dryseed.dryseedapp.framework.leakcanary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.dryseed.dryseedapp.R;

/**
 * Created by caiminming on 2016/11/24.
 */
public class TestLeakCanaryActivity extends Activity {
    static Demo sDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (sDemo == null) {
            sDemo = new Demo();
        }

        Button btn = new Button(TestLeakCanaryActivity.this);
        btn.setText("finish");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addContentView(btn, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
    }

    class Demo {
    }

}
