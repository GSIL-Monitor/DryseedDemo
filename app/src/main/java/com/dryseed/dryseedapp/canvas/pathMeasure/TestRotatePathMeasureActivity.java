package com.dryseed.dryseedapp.canvas.pathMeasure;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by User on 2017/9/17.
 */
public class TestRotatePathMeasureActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RotatePathMeasureView rotatePathMeasureView = new RotatePathMeasureView(this);
        addContentView(rotatePathMeasureView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rotatePathMeasureView.startAnim();
    }
}