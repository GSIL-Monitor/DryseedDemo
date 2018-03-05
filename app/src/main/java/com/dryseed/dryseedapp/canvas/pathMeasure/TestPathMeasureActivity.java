package com.dryseed.dryseedapp.canvas.pathMeasure;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dryseed.dryseedapp.BaseActivity;

/**
 * Created by User on 2017/9/17.
 */
public class TestPathMeasureActivity extends BaseActivity {

    BasePathMeasureView basePathMeasureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePathMeasureView = new BasePathMeasureView(this);
        addContentView(basePathMeasureView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        basePathMeasureView.startPathAnim(3000);
    }

    @Override
    protected void onDestroy() {
        if(null != basePathMeasureView){
            basePathMeasureView.stop();
        }
        super.onDestroy();
    }
}
