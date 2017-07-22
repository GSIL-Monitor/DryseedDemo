package com.dryseed.dryseedapp.canvas.xfermode;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.dryseed.dryseedapp.R;


public class XfermodeActivity extends Activity {

    private ScanFrameView scanFrameView = null;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfermode_layout);
        scanFrameView = (ScanFrameView) findViewById(R.id.myView);

        scanFrameView.animatedWithState(ScanFrameView.State.Scaning);

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

    @Override
    protected void onDestroy() {
        if(null != scanFrameView){
            scanFrameView.destroy();
        }
        super.onDestroy();
    }
}
