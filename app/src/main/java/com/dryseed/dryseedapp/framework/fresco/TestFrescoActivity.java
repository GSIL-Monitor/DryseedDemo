package com.dryseed.dryseedapp.framework.fresco;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.dryseed.dryseedapp.R;
import com.facebook.drawee.view.SimpleDraweeView;


/**
 * Created by caiminming on 2016/11/24.
 */
public class TestFrescoActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_layout);
        SimpleDraweeView img1 = (SimpleDraweeView) findViewById(R.id.fresco_eg1);
        Uri uri = Uri.parse("http://b.hiphotos.baidu.com/image/pic/item/e78.jpg");
        img1.setImageURI(uri);

        /*SimpleDraweeView drawview = (SimpleDraweeView) findViewById(R.id.fresco_eg2);
        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //加载drawable里的一张gif图
                .setUri(Uri.parse("res://"+getPackageName()+"/"+R.drawable.test_1334_48))//设置uri
                .build();
        //设置Controller
        drawview.setController(mDraweeController);*/
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
