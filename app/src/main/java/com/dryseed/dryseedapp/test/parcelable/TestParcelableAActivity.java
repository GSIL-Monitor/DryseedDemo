package com.dryseed.dryseedapp.test.parcelable;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.customView.comboClickTextView.CountDownTimer;
import com.dryseed.dryseedapp.test.singleTask.TestBActivity;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by caiminming on 2017/5/12.
 */

public class TestParcelableAActivity extends BaseActivity {
    public final static String SER_KEY = "com.dryseed.dryseedapp.serkey";
    public final static String PAR_KEY = "com.dryseed.dryseedapp.parkey";

    TimerUtils timerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_layout);
        SimpleDraweeView img1 = (SimpleDraweeView) findViewById(R.id.fresco_eg1);

        Uri uri = Uri.parse("http://b.hiphotos.baidu.com/image/pic/item/e78.jpg");
        img1.setImageURI(uri);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity();
            }
        });


        timerUtils = new TimerUtils(this);
    }


    private void startActivity() {
//        PersonByParcelable mPerson = new PersonByParcelable("dryseedcai", "dryseed", 28);
//        Intent mIntent = new Intent(this, TestParcelableBActivity.class);
//        Bundle mBundle = new Bundle();
//        mBundle.putParcelable(PAR_KEY, mPerson);
//        mIntent.putExtras(mBundle);

        PersonBySerializable mPerson = new PersonBySerializable("dryseedcai", "dryseed", 28);
        Intent mIntent = new Intent(this, TestParcelableBActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(SER_KEY, mPerson);
        mIntent.putExtras(mBundle);

        startActivity(mIntent);
    }

    @Override
    protected void onStop() {
        Log.d("MMM", "onStop");
        if (null != timerUtils) {
            timerUtils.pauseTimer();
        }
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MMM", "onStart");
        if (null != timerUtils) {
            timerUtils.startTimer();
        }
    }

    @Override
    protected void onDestroy() {
        if(null != timerUtils){
            timerUtils.pauseTimer();
            timerUtils = null;
        }
        super.onDestroy();
    }
}
