package com.dryseed.dryseedapp.widget.blurredView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.ImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/7/26.
 */

public class TestBlurredViewActivity extends BaseActivity {
    @Bind(R.id.blurred_view_image)
    ImageView mImageView;

    @Bind(R.id.blurred_view_btn)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurred_view_layout);
        ButterKnife.bind(this);

        //拿到初始图
        Bitmap initBitmap = ImageUtil.drawableToBitmap(getResources().getDrawable(R.drawable.bg));
        //处理得到模糊效果的图
        Bitmap blurBitmap = BlurBitmapUtil.blurBitmap(this, initBitmap, 20f);
        mImageView.setImageBitmap(blurBitmap);
    }

    @OnClick(R.id.blurred_view_btn)
    void goToDynamic() {
        Intent intent = new Intent(this, TestDynamicBlurredViewActivity.class);
        startActivity(intent);
    }

}
