package com.dryseed.dryseedapp.customView.customViewGroup02;

import android.app.Activity;
import android.os.Bundle;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import java.util.Arrays;
import java.util.List;

public class TestCustomViewGroup02Activity extends BaseActivity
{

    private List<String> mDatas = Arrays.asList("Android", "Java");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_view_group_02);
    }

}
