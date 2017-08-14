package com.dryseed.dryseedapp.framework.fresco;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.dryseed.dryseedapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by caiminming on 2016/11/24.
 */
public class TestFrescoActivity extends Activity {

//    @Bind(R.id.fresco_eg1)
//    SimpleDraweeView img1;

    @Bind(R.id.fresco_btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_layout);

        ButterKnife.bind(this);

        SimpleDraweeView img1 = (SimpleDraweeView) findViewById(R.id.fresco_eg1);
        Uri uri = Uri.parse("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1177005900,1815516380&fm=26&gp=0.jpg");
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

    @OnClick(R.id.fresco_btn)
    void onBtnClick(){
        startActivity(new Intent(this, TestFresco2Activity.class));
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
