package com.dryseed.dryseedapp.tools.share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/11/28.
 */

public class ShareActivity extends BaseActivity {

    @BindView(R.id.share_btn)
    Button mShareBtn;

    ShareDialog mShareDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.share_btn)
    void onShareBtnClick(View view) {
        if (null == mShareDialog) {
            mShareDialog = new ShareDialog(ShareActivity.this, R.style.Dialog_Common);
            mShareDialog.setCallBack(new ShareDialog.CallBack() {
                @Override
                public void onShareToWeixin() {

                }

                @Override
                public void onShareToQQ() {
                    ShareManager.getInstance().shareQQ(ShareActivity.this);
                }
            });
        }
        mShareDialog.show();
    }
}
