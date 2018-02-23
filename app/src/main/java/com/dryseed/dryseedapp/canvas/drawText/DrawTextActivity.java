package com.dryseed.dryseedapp.canvas.drawText;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawTextActivity extends BaseActivity {

    @BindView(R.id.textview_1)
    MyTextView mMyTextView1;

    @BindView(R.id.textview_2)
    MyCenterTextView mMyTextView2;

    @BindView(R.id.textview_3)
    MyMultiCenterTextView mMyTextView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_text_layout);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
    }
}
