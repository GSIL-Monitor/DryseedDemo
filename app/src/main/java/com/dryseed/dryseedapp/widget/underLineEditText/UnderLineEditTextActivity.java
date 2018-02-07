package com.dryseed.dryseedapp.widget.underLineEditText;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class UnderLineEditTextActivity extends BaseActivity {
    UnderLineEditText mUnderLineEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_underline_edittext_layout);

        initView();
    }

    private void initView() {
        //mUnderLineEditText = (EditText) findViewById(R.id.edit_text);
        //mUnderLineEditText = (UnderLineEditText) findViewById(R.id.edit_text);
    }


}
