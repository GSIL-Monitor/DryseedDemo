package com.dryseed.dryseedapp.dispatchEvent.testDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;

/**
 * Created by caiminming on 2017/4/7.
 */

public class DispatchEventDemoActivity3 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dispatch_event_content_layout3);

        TextView textView = (TextView) findViewById(R.id.title);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DispatchEventDemoActivity3.this, "textView onClick", Toast.LENGTH_SHORT).show();
            }
        });

        View myCustomView = (View) findViewById(R.id.my_custom_view);
        /*myCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DispatchEventDemoActivity3.this, "myCustomView onClick", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
