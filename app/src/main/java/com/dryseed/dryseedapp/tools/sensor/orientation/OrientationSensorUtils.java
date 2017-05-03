package com.dryseed.dryseedapp.tools.sensor.orientation;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;


/**
 * 播放控手势控制
 *
 * @author dengjiaping
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class OrientationSensorUtils {
    public static final int ORIENTATION_8 = 1;
    public static final int ORIENTATION_9 = 2;
    public static final int ORIENTATION_0 = 3;
    public static final int ORIENTATION_1 = 4;

    private OrientationSensorListener mOrientationSensorListener;
    private SensorManager sm;
    private Sensor sensor;
    private Activity activity;

    public OrientationSensorUtils(Activity activity) {
        this.activity = activity;

        init();
    }

    public void init() {
        sm = (SensorManager) activity.getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mOrientationSensorListener = new OrientationSensorListener(handler, activity);
        //mOrientationSensorListener.setJustLandscape(true);
		//mOrientationSensorListener.setLock(true);
    }

    public void onResume() {
        sm.registerListener(mOrientationSensorListener, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    public void onPause() {
        sm.unregisterListener(mOrientationSensorListener);
    }

    public OrientationSensorListener getmOrientationSensorListener() {
        return mOrientationSensorListener;
    }

    public void setmOrientationSensorListener(OrientationSensorListener mOrientationSensorListener) {
        this.mOrientationSensorListener = mOrientationSensorListener;
    }

    public void setOrientation(int orientation) {
        handler.obtainMessage(orientation).sendToTarget();
    }

    public void destory() {
        if (mOrientationSensorListener != null) {
            mOrientationSensorListener.destory();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case OrientationSensorUtils.ORIENTATION_8:// 反横屏
                    if (activity != null) {
                        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    }
                    break;
                case OrientationSensorUtils.ORIENTATION_9:// 反竖屏
                    if (activity != null) {
                        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                    }
                    break;
                case OrientationSensorUtils.ORIENTATION_0:// 正横屏
                    if (activity != null) {
                        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    }
                    break;
                case OrientationSensorUtils.ORIENTATION_1:// 正竖屏
                    if (activity != null) {
                        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }
                    break;
            }
            super.handleMessage(msg);
        }

    };

}
