package com.dryseed.dryseedapp.canvas.pathMeasure;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by User on 2017/9/17.
 */
public class TestPathMeasureActivity extends Activity {

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
