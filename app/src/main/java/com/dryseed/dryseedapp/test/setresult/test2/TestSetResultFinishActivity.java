package com.dryseed.dryseedapp.test.setresult.test2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.test.setresult.test1.SetResult2Activity;
import com.dryseed.dryseedapp.utils.ToastUtil;

/**
 * Created by caiminming on 2017/12/28.
 * <p>
 * 测试：从A跳到B，开发者模式设为不保留活动 -> B setResult -> A能正常收到onActivityResult
 */

public class TestSetResultFinishActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MMM", "TestSetResultFinishActivity onCreate");
        Button button = new Button(this);
        button.setText("GO TO B");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(TestSetResultFinishActivity.this, SetResultFinish2Activity.class), 123);
            }
        });
        setContentView(button);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MMM", "TestSetResultFinishActivity onResume");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //String name = data.getStringExtra("name");

        String s = String.format("TestSetResultFinishActivity onActivityResult === requestCode:%s|resultCode:%s|intent:%s", requestCode, resultCode, data);
        ToastUtil.showToast(s);
        Log.d("MMM", s);
    }
}
