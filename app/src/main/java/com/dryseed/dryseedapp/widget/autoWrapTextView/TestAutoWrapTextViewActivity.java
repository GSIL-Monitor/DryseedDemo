package com.dryseed.dryseedapp.widget.autoWrapTextView;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class TestAutoWrapTextViewActivity extends BaseActivity {

    private String text = "text密码：jokG5456KL542356jsjdherGHS";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_wrap_text_view_layout);
        ((AutoWrapTextView) findViewById(R.id.awtextview)).setText(text);
    }
}
