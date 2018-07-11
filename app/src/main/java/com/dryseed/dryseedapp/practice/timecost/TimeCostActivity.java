package com.dryseed.dryseedapp.practice.timecost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dryseed.dryseedapp.R;
import com.dryseed.timecost.annotations.TimeCost;

public class TimeCostActivity extends AppCompatActivity {

    @Override
    @TimeCost(name = "onCreate")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_cost_layout);
    }

    @TimeCost(name = "myMethod", milliTime = 500L)
    public void myMethod(View view) {
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @TimeCost(name = "myMethod2")
    public void myMethod2(View view) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void myMethod3(View view) {
//        TestLibrary testLibrary = new TestLibrary();
//        testLibrary.testLibraryMethod();
    }

    @TimeCost(name = "myMethod4")
    public void myMethod4(View view) {
        new Thread(new Runnable() {
            @Override
            @TimeCost(name = "myMethod4_run", monitorOnlyMainThread = true)
            public void run() {
                myMehtod4Inner();
            }
        }).start();
    }

    @TimeCost(name = "myMethod4_inner", monitorOnlyMainThread = true)
    private void myMehtod4Inner() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void myMethod5() {
//        new TestAar().testAarMethod();
    }
}
