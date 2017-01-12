package com.dryseed.dryseedapp.canvas.pathMeasure;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dryseed.dryseedapp.R;


public class AlipayConfirmViewActivity extends Activity {

    private AlipayConfirmView confirmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay_confirm_layout);

        confirmView = (AlipayConfirmView) findViewById(R.id.confirm_view);
    }

    public void progressing(View view) {
        confirmView.animatedWithState(AlipayConfirmView.State.Progressing);
    }

    public void fail(View view) {
        confirmView.animatedWithState(AlipayConfirmView.State.Fail);
    }

    public void success(View view) {
        confirmView.animatedWithState(AlipayConfirmView.State.Success);
    }

}
