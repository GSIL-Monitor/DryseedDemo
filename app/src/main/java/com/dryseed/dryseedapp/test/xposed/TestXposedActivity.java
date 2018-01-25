package com.dryseed.dryseedapp.test.xposed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class TestXposedActivity extends BaseActivity {
    private Button mBtn;
    private TextView mText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xposed_layout);

        mBtn = (Button) findViewById(R.id.button);
        mText = (TextView) findViewById(R.id.textview);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText.setText("dryseed");
            }
        });
    }
}
