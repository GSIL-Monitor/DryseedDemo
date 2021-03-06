package com.dryseed.dryseedapp.canvas.bitmapShaderRoundImage;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class TestBitmapShaderActivity extends BaseActivity {
    private RoundImageView mQiQiu;
    private RoundImageView mMeiNv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_shader_layout);

        mQiQiu = (RoundImageView) findViewById(R.id.id_qiqiu);
        mMeiNv = (RoundImageView) findViewById(R.id.id_meinv);

        mQiQiu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mQiQiu.setType(RoundImageView.TYPE_ROUND);
            }
        });

        mMeiNv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mMeiNv.setBorderRadius(90);
            }
        });
    }

}
