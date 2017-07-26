package com.dryseed.dryseedapp.widget.BlurredView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.ImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by caiminming on 2017/7/26.
 */

public class TestBlurredViewActivity extends Activity {
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
