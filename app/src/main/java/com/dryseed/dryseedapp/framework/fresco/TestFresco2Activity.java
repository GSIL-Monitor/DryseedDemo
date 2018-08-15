package com.dryseed.dryseedapp.framework.fresco;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author caiminming
 */
public class TestFresco2Activity extends BaseActivity {

    @BindView(R.id.my_image_view)
    MyImageView mMyImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco2_layout);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMyImageView.setImageUri(TestFrescoActivity.GIF_URL, null);
                //mMyImageView.setImage(Uri.parse(TestFrescoActivity.PIC_URL));
            }
        }, 1000);
    }

}
