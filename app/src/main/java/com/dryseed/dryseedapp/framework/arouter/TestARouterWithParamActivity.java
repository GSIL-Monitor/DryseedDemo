package com.dryseed.dryseedapp.framework.arouter;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

@Route(path = "/framework/TestARouterWithParamActivity")
public class TestARouterWithParamActivity extends BaseActivity {
    @Autowired
    String key1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String value = getIntent().getStringExtra("key2");
        if (!TextUtils.isEmpty(value)) {
            Toast.makeText(this, "exist param :" + value, Toast.LENGTH_LONG).show();
        }
    }
}
