package com.dryseed.dryseedapp.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dryseed.dryseedapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by caiminming on 2017/5/12.
 */

/*
  startActivityForResult 方式启动 启动模式设为singleTask的activity，则onActivityResult会立即受到一个cancel的result，并且singleTask模式也会失效
  特例：在小米2A手机上，singleTask不会失效
 */
public class TestAActivity extends Activity {
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
                TestAActivity.this.startActivity(new Intent(TestAActivity.this, TestBActivity.class));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MMM", "onActivityResult " + requestCode);
    }
}
