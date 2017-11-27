package com.dryseed.dryseedapp.dispatchEvent.testDemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

/**
 * Created by caiminming on 2017/4/7.
 */

public class DispatchEventDemoActivity3 extends BaseActivity {

    private String TAG = "MMM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dispatch_event_content_layout3);

        MyViewGroup myViewGroup = (MyViewGroup) findViewById(R.id.my_view_group);
        myViewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "MyViewGroup onTouch------->>ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "MyViewGroup onTouch------->>ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "MyViewGroup onTouch------->>ACTION_UP");
                        break;
                }
                return false;
            }
        });
        myViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "MyViewGroup onClick");
                Toast.makeText(DispatchEventDemoActivity3.this, "MyViewGroup onClick", Toast.LENGTH_SHORT).show();
            }
        });


        View myCustomView = findViewById(R.id.my_custom_view);
        myCustomView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "MyCustomView onTouch------->>ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "MyCustomView onTouch------->>ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "MyCustomView onTouch------->>ACTION_UP");
                        break;
                }
                return false;
            }
        });
        myCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "MyCustomView onClick");
                Toast.makeText(DispatchEventDemoActivity3.this, "MyCustomView onClick", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
