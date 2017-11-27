package com.dryseed.dryseedapp.tools.sensor.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;

import com.dryseed.dryseedapp.BaseActivity;

/**
 * Created by User on 2017/7/22.
 */
public class AccelerometerActivity extends BaseActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float mSensorX;
    private float mSensorY;
    private float mSensorZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager) getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
        // event 的参照系是手机【手机左侧、右侧、头部、尾部】
        //Log.d("MMM", String.format("X:%f | Y:%f | Z:%f", event.values[0], event.values[1], event.values[2]));
        int rt = getWindowManager().getDefaultDisplay().getRotation();
        switch (rt) {
            case Surface.ROTATION_0:
                mSensorX = event.values[0];
                mSensorY = event.values[1];
                break;
            case Surface.ROTATION_90:
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
                break;
            case Surface.ROTATION_180:
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
                break;
            case Surface.ROTATION_270:
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
                break;
        }
        mSensorZ = event.values[2];
        Log.d("MMM", String.format("X:%f | Y:%f | Z:%f", mSensorX, mSensorY, mSensorZ));
        /*
            转换成相对参照系中的 左侧、右侧、前方、后方
            值的变化范围 ： [0-10]
            手机左边往下倾 ：  X:6.818686 | Y:0.507571
            手机右边往下倾 ：  X:-6.818686 | Y:0.507571
            手机头部往下倾 ：  X:0.335188 | Y:-4.544195
            手机尾部往下倾 ：  X:0.335188 | Y:4.544195
         */
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
