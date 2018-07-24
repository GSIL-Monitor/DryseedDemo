package com.dryseed.dryseedapp.widget.blurredView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/7/26.
 */

public class TestBlurredViewActivity extends BaseActivity {
    @BindView(R.id.blurred_view_image1)
    ImageView mImageView1;

    @BindView(R.id.blurred_view_image2)
    ImageView mImageView2;

    @BindView(R.id.blurred_view_image3)
    ImageView mImageView3;

    @BindView(R.id.blurred_view_image4)
    ImageView mImageView4;

    @BindView(R.id.blurred_text_view1)
    TextView mTextView1;

    @BindView(R.id.blurred_text_view2)
    TextView mTextView2;

    @BindView(R.id.blurred_text_view3)
    TextView mTextView3;

    @BindView(R.id.blurred_text_view4)
    TextView mTextView4;

    @BindView(R.id.blurred_view_btn)
    Button mButton;

    long timeStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurred_view_layout);
        ButterKnife.bind(this);

        //拿到初始图
        Bitmap initBitmap = ImageUtil.drawableToBitmap(getResources().getDrawable(R.drawable.bg));

        //处理得到模糊效果的图
        timeStart = System.currentTimeMillis();
        Bitmap blurBitmap = BlurBitmapUtil.blurBitmap(this, initBitmap, 20f, 0.5f);
        mImageView1.setImageBitmap(blurBitmap);
        mTextView1.setText(mTextView1.getText().toString() + (System.currentTimeMillis() - timeStart) + "ms");

        timeStart = System.currentTimeMillis();
        Bitmap blurBitmap2 = BlurBitmapUtil.blurBitmap(this, initBitmap, 20f, 0.125f);
        mImageView2.setImageBitmap(blurBitmap2);
        mTextView2.setText(mTextView2.getText().toString() + (System.currentTimeMillis() - timeStart) + "ms");

        timeStart = System.currentTimeMillis();
        Bitmap blurBitmap3 = BlurBitmapUtil.fastBlur(initBitmap, 20, 0.5f);
        mImageView3.setImageBitmap(blurBitmap3);
        mTextView3.setText(mTextView3.getText().toString() + (System.currentTimeMillis() - timeStart) + "ms");

        timeStart = System.currentTimeMillis();
        Bitmap blurBitmap4 = BlurBitmapUtil.fastBlur(initBitmap, 20, 0.125f);
        mImageView4.setImageBitmap(blurBitmap4);
        mTextView4.setText(mTextView4.getText().toString() + (System.currentTimeMillis() - timeStart) + "ms");
    }

    @OnClick(R.id.blurred_view_btn)
    void goToDynamic() {
        Intent intent = new Intent(this, TestDynamicBlurredViewActivity.class);
        startActivity(intent);
    }

}
