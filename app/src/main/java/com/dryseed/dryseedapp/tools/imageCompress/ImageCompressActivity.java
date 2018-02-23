package com.dryseed.dryseedapp.tools.imageCompress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.CompressImageUtil;
import com.dryseed.dryseedapp.utils.DPIUtil;
import com.dryseed.dryseedapp.utils.StorageDirectoryUtil;
import com.dryseed.dryseedapp.utils.UnitUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageCompressActivity extends BaseActivity {

    @BindView(R.id.imageview1)
    ImageView mSrcImageView;

    @BindView(R.id.imageview2)
    ImageView mTargetImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_compress_layout);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        Bitmap mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tbug);
        mSrcImageView.setImageBitmap(mSrcBitmap);

        //compress

        File saveFile = new File(StorageDirectoryUtil.getImageDirectory(), "compressQuality.jpg");
        //CompressImageUtil.compressQuality(mSrcBitmap, 5, saveFile);
        //CompressImageUtil.compressSize(mSrcBitmap, saveFile);
        //CompressImageUtil.compressSample(mSrcBitmap, saveFile);
        //Bitmap bitmap = BitmapFactory.decodeFile(saveFile.getPath());
        //mTargetImageView.setImageBitmap(bitmap);

        Bitmap bitmap = CompressImageUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.tbug, DPIUtil.getWidth(), DPIUtil.dip2px(250));
        mTargetImageView.setImageBitmap(bitmap);

    }
}
