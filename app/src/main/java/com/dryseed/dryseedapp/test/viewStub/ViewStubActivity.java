package com.dryseed.dryseedapp.test.viewStub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/12/20.
 */

public class ViewStubActivity extends BaseActivity {
    @Bind(R.id.button1)
    Button mButton;

    @Bind(R.id.stub)
    ViewStub mViewStub;

    View mInflateView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    void onBtnClick() {
        mInflateView = mViewStub.inflate();
    }
}
