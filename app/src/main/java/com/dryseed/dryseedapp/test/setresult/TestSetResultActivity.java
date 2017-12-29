package com.dryseed.dryseedapp.test.setresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.layers.LottieDrawable;
import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.framework.rxBus.RxBus;
import com.dryseed.dryseedapp.utils.ToastUtil;
import com.luck.picture.lib.entity.EventEntity;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by caiminming on 2017/12/28.
 */

public class TestSetResultActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        String s = String.format("TestSetResultActivity === requestCode:%s|resultCode:%s|intent:%s", requestCode, resultCode, data);
        ToastUtil.showToast(s);
        Log.d("MMM", s);
    }
}
