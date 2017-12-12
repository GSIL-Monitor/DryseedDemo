package com.dryseed.dryseedapp.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/12/12.
 */

public class TestDialogActivity extends BaseActivity {
    int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.dialog1)
    void onDialog1BtnClick() {
        DsDialogFactory.showTipDialog(TestDialogActivity.this, "MMM" + index++);
    }

    @OnClick(R.id.dialog2)
    void onDialog2BtnClick() {
        DsDialogFactory.showTipDialog(
                TestDialogActivity.this,
                "MMM" + index++,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );
    }

    @OnClick(R.id.dialog3)
    void onDialog3BtnClick() {
        DsDialogFactory.showTipDialog(
                TestDialogActivity.this,
                "MMM" + index++,
                "OK",
                "CANCEL",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );
    }

    @OnClick(R.id.dialog4)
    void onDialog4BtnClick() {
        DsDialogFactory.showCustomDialog(
                TestDialogActivity.this,
                R.layout.activity_alipay_confirm_layout,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );
    }

}
