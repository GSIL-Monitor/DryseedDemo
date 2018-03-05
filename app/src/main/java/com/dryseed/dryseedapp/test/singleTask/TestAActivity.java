package com.dryseed.dryseedapp.test.singleTask;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by caiminming on 2017/5/12.
 */

/*
  startActivityForResult 方式启动 启动模式设为singleTask的activity
 */
public class TestAActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MMM", "TestAActivity onCreate");

        setContentView(R.layout.activity_fresco_layout);
        SimpleDraweeView img1 = (SimpleDraweeView) findViewById(R.id.fresco_eg1);

        Uri uri = Uri.parse("http://b.hiphotos.baidu.com/image/pic/item/e78.jpg");
        img1.setImageURI(uri);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestAActivity.this, TestBActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                /**
                 * Note that this method should only be used with Intent protocols
                 * that are defined to return a result.  In other protocols (such as
                 * {@link Intent#ACTION_MAIN} or {@link Intent#ACTION_VIEW}), you may
                 * not get the result when you expect.  For example, if the activity you
                 * are launching uses the singleTask launch mode, it will not run in your
                 * task and thus you will immediately receive a cancel result.
                 */
                startActivityForResult(intent, 1000);

                /*
                    ① 使用FLAG_ACTIVITY_NEW_TASK启动app时，会立马收到onActivityResult回调intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK)
                    log:
                        com.dryseed.dryseedapp D/MMM: onActivityResult 1000 0 null
                        com.dryseed.dryseedapp D/MMM: TestBActivity onCreate

                    ② 而使用xml注册launchMode为singleTask，在android5.0及以后的手机上，能正常work
                 */
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MMM", "onActivityResult " + requestCode + " " + resultCode + " " + data);
    }
}
