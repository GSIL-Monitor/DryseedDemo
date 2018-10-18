package com.dryseed.dryseedapp.widget.merge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

/**
 * @author caiminming
 */
public class TestMergeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_merge_layout);

        ViewGroup mergeLayout = findViewById(R.id.merge_layout);
        LayoutInflater.from(this).inflate(R.layout.merge_test_layout, mergeLayout);
    }
}
