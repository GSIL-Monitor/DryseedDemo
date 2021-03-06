package com.dryseed.dryseedapp.test.setresult.test2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;

/**
 * Created by caiminming on 2017/12/28.
 */

public class SetResultFinish2Activity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setText("Set Result");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "dryseed");
                setResult(321, intent);
                Log.d("MMM", "SetResultFinish2Activity setResult");
            }
        });
        setContentView(button);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
