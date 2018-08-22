package com.dryseed.dryseedapp.framework.fresco;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author caiminming
 */
public class TestFresco2Activity extends BaseActivity {

    @BindView(R.id.my_image_view)
    MyRadioButton mMyRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco2_layout);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMyRadioButton.setButtonDrawable(new ColorDrawable());
                mMyRadioButton.setText("dryseed");
                mMyRadioButton.setGravity(Gravity.CENTER);
                mMyRadioButton.setLines(1);
                mMyRadioButton.setIncludeFontPadding(false);
                mMyRadioButton.setBackgroundColor(0xffcccccc);

                mMyRadioButton.setImageUri(TestFrescoActivity.SMALL_PIC_URL, null);

                /**
                 * fresco 不支持 .ico 图片
                 * https://github.com/facebook/fresco/blob/master/docs/_docs/03-customizing-image-formats.md
                 */
                //mMyRadioButton.setImageUri(TestFrescoActivity.ICO_PIC_URL, null);
            }
        }, 1000);

    }

}
