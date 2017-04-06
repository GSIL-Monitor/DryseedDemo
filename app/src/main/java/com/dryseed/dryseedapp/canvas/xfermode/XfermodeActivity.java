package com.dryseed.dryseedapp.canvas.xfermode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.canvas.canvas.MyCanvasView;


public class XfermodeActivity extends Activity {

    private MyXfermode2View myXfermodeView = null;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfermode_layout);
        myXfermodeView = (MyXfermode2View) findViewById(R.id.myView);

        /*handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myXfermodeView.animatedWithState(MyXfermode2View.State.Scaning);
            }
        }, 2000L);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myXfermodeView.animatedWithState(MyXfermode2View.State.Recognize);
            }
        }, 8000L);*/

        /*handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myXfermodeView.animatedWithState(MyXfermode2View.State.Scaning);
//                myXfermodeView.animatedWithState(MyXfermode2View.State.Stop);
            }
        }, 6000L);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myXfermodeView.animatedWithState(MyXfermode2View.State.Stop);
            }
        }, 8000L);*/
    }

}
