package com.dryseed.dryseedapp.tools.sensor.orientation;

import android.app.Activity;
import android.os.Bundle;

import com.dryseed.dryseedapp.R;

/**
 * Created by caiminming on 2017/5/3.
 */

public class OrientationSensorActivity extends Activity {

    private OrientationSensorUtils mOrientationSensorUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_layout);
        mOrientationSensorUtils = new OrientationSensorUtils(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mOrientationSensorUtils) {
            mOrientationSensorUtils.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != mOrientationSensorUtils) {
            mOrientationSensorUtils.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOrientationSensorUtils != null) {
            mOrientationSensorUtils.onPause();
            mOrientationSensorUtils.destory();
            mOrientationSensorUtils = null;
        }
    }
}
