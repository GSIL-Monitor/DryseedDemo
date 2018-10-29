package com.dryseed.dryseedapp.test.path;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.dsutils.DsPathUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author caiminming
 */
public class TestPathActivity extends BaseActivity {
    @BindView(R.id.textview)
    TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    public void getInternalAppDataPath(View view) {
        mTextView.setText(DsPathUtils.getInternalAppDataPath());
    }

    @OnClick(R.id.button2)
    public void getInternalAppCachePath(View view) {
        mTextView.setText(DsPathUtils.getInternalAppCachePath());
    }

    @OnClick(R.id.button3)
    public void getInternalAppFilesPath(View view) {
        mTextView.setText(DsPathUtils.getInternalAppFilesPath());
    }

    @OnClick(R.id.button4)
    public void getExternalAppDataPath(View view) {
        mTextView.setText(DsPathUtils.getExternalAppDataPath());
    }

    @OnClick(R.id.button5)
    public void getExternalAppCachePath(View view) {
        mTextView.setText(DsPathUtils.getExternalAppCachePath());
    }

    @OnClick(R.id.button6)
    public void getExternalAppFilesPath(View view) {
        mTextView.setText(DsPathUtils.getExternalAppFilesPath());
    }

    @OnClick(R.id.button7)
    public void getExternalStoragePath(View view) {
        mTextView.setText(DsPathUtils.getExternalStoragePath());
    }
}
