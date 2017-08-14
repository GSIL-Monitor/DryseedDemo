package com.dryseed.dryseedapp.framework.fresco;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.dryseed.dryseedapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by caiminming on 2016/11/24.
 */
public class TestFresco2Activity extends Activity {

    @Bind(R.id.fresco_eg1)
    SimpleDraweeView img1;

    @Bind(R.id.fresco_btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_layout);

        ButterKnife.bind(this);

        img1 = (SimpleDraweeView) findViewById(R.id.fresco_eg1);
        img1.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Uri uri = Uri.parse(TestFrescoActivity.picUrl);
        img1.setImageURI(uri);

        /*SimpleDraweeView drawview = (SimpleDraweeView) findViewById(R.id.fresco_eg2);
        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //加载drawable里的一张gif图
                .setUri(Uri.parse("res://"+getPackageName()+"/"+R.drawable.test_1334_48))//设置uri
                .build();
        //设置Controller
        drawview.setController(mDraweeController);*/

        btn.setVisibility(View.GONE);
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
