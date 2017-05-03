package com.dryseed.dryseedapp.tools.sensor.shake;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;

public class ShakeSensorActivity extends Activity {

    private TextView startTV = null;
    private TextView stopTV = null;
    ShakeDetector shakeDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_layout);

        startTV = (TextView) findViewById(R.id.start);
        stopTV = (TextView) findViewById(R.id.stop);

        shakeDetector = new ShakeDetector(ShakeSensorActivity.this);
        startTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shakeDetector.start();
            }
        });
        stopTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shakeDetector.stop();
            }
        });
    }

    @Override
    protected void onDestroy() {
        shakeDetector.stop();
        super.onDestroy();
    }
}
