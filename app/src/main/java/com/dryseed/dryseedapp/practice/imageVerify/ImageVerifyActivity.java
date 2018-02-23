package com.dryseed.dryseedapp.practice.imageVerify;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageVerifyActivity extends BaseActivity {

    @BindView(R.id.image_verify)
    ImageVerifyView mImageVerifyView;

    @BindView(R.id.image_scroll_view)
    ImageVerifyScrollView mImageVerifyScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_verify_layout);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        mImageVerifyScrollView.setCallback(new ImageVerifyScrollView.Callback() {
            @Override
            public void onSure(float target) {
                if (target > mImageVerifyView.getTargetProgress() - 0.03f && target < mImageVerifyView.getTargetProgress() + 0.03f) {
                    ToastUtil.showToast("Succ");
                }
            }

            @Override
            public void onProgress(float target) {
                if (null != mImageVerifyView) {
                    mImageVerifyView.setProgress(target);
                }
            }
        });
    }
}
