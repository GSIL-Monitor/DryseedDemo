package com.dryseed.dryseedapp.test.singleTask;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class TestSingleInstanceAActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_layout);
        SimpleDraweeView img1 = (SimpleDraweeView) findViewById(R.id.fresco_eg1);

        Uri uri = Uri.parse("http://b.hiphotos.baidu.com/image/pic/item/e78.jpg");
        img1.setImageURI(uri);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestSingleInstanceAActivity.this.startActivity(new Intent(TestSingleInstanceAActivity.this, TestSingleInstanceBActivity.class));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MMM", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MMM", "onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MMM", "onActivityResult " + requestCode);
    }
}
