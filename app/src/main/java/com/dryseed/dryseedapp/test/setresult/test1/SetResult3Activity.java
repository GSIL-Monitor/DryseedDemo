package com.dryseed.dryseedapp.test.setresult.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;

/**
 * Created by caiminming on 2017/12/28.
 */

public class SetResult3Activity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setText("SET RESULT");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "dryseed");
                setResult(321, intent);
                finish();

//                RxBus.getDefault().post(new Event());
//                finish();
            }
        });
        setContentView(button);
    }
}
