package com.dryseed.dryseedapp.test.setresult.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.utils.ToastUtil;

/**
 * Created by caiminming on 2017/12/28.
 */

public class TestSetResultActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MMM", "TestSetResultActivity onCreate");
        Button button = new Button(this);
        button.setText("GO TO B");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(TestSetResultActivity.this, SetResult2Activity.class), 123);
            }
        });
        setContentView(button);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //String name = data.getStringExtra("name");

        String s = String.format("TestSetResultActivity onActivityResult === requestCode:%s|resultCode:%s|intent:%s", requestCode, resultCode, data);
        ToastUtil.showToast(s);
        Log.d("MMM", s);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MMM", "TestSetResultActivity onRestart");
    }
}
