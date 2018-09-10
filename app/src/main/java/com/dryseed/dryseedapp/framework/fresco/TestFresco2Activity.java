package com.dryseed.dryseedapp.framework.fresco;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.ActivityUtil;
import com.luojilab.component.basiclib.utils.DPIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author caiminming
 */
public class TestFresco2Activity extends BaseActivity {

    @BindView(R.id.my_image_view)
    MyRadioButton mMyRadioButton;

    @BindView(R.id.my_image_view_2)
    MyRadioButton mMyRadioButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco2_layout);
        ButterKnife.bind(this);

        ActivityUtil.printTaskInfo(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMyRadioButton.setButtonDrawable(new ColorDrawable());
                mMyRadioButton.setText("dryseed");
                mMyRadioButton.setGravity(Gravity.CENTER);
                mMyRadioButton.setLines(1);
                mMyRadioButton.setIncludeFontPadding(false);
                mMyRadioButton.setBackgroundColor(0xffcccccc);

                mMyRadioButton.setImageUri(TestFrescoActivity.GIF_URL, null);

                /**
                 * fresco 不支持 .ico 图片
                 * https://github.com/facebook/fresco/blob/master/docs/_docs/03-customizing-image-formats.md
                 */
                //mMyRadioButton.setImageUri(TestFrescoActivity.ICO_PIC_URL, null);
            }
        }, 1000);


        Drawable drawable = getResources().getDrawable(R.drawable.city2);
        drawable.setBounds(0, 0, 50, 50);
        mMyRadioButton2.setCompoundDrawables(drawable, null, null, null);
        mMyRadioButton2.setCompoundDrawablePadding(DPIUtil.dip2px(5));
        mMyRadioButton2.setBackgroundColor(0xffcccccc);
        Log.d("MMM", String.format("[paddingLeft:%d][getCompoundPaddingRight:%d][drawableWidth:%d]",
                mMyRadioButton2.getPaddingLeft(),
                mMyRadioButton2.getCompoundPaddingRight(),
                mMyRadioButton2.getCompoundDrawables()[0].getBounds().width()));
    }

}
