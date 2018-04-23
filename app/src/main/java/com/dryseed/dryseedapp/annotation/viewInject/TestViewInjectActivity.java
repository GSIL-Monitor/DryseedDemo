package com.dryseed.dryseedapp.annotation.viewInject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.annotation.viewInject.annotation.ContentView;
import com.dryseed.dryseedapp.annotation.viewInject.annotation.OnDsClick;
import com.dryseed.dryseedapp.annotation.viewInject.annotation.ViewInject;

/**
 * Created by caiminming on 2017/7/12.
 */

@ContentView(value = R.layout.activity_view_inject_layout)
public class TestViewInjectActivity extends BaseActivity {
    @ViewInject(R.id.id_btn)
    private Button mBtn1;
    @ViewInject(R.id.id_btn02)
    private Button mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewInjectUtils.inject(this);
    }

    @OnDsClick({R.id.id_btn, R.id.id_btn02})
    public void clickBtnInvoked(View view) {
        switch (view.getId()) {
            case R.id.id_btn:
                Toast.makeText(this, "Inject Btn01 !", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn02:
                Toast.makeText(this, "Inject Btn02 !", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /*@Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.id_btn:
                Toast.makeText(TestViewInjectActivity.this, "Why do you click me ?",
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_btn02:
                Toast.makeText(TestViewInjectActivity.this, "I am sleeping !!!",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }*/
}
