package com.dryseed.dryseedapp.widget.floatView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.DPIUtil;

/**
 * Created by caiminming on 2017/12/9.
 * <p>
 * app应用内的悬浮窗
 * 思路：在BaseActivity的onResume/onPause中，控制FloatView的显示和隐藏
 */

public class FloatViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textview);
        textView.setText("open float view");
        textView.setBackgroundColor(0x0000ff);
        RelativeLayout.LayoutParams flp = new RelativeLayout.LayoutParams(DPIUtil.dip2px(200), DPIUtil.dip2px(200));
        flp.leftMargin = DPIUtil.dip2px(100);
        textView.setLayoutParams(flp);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatViewManager.getInstance().setSwitchOpen(FloatViewActivity.this);
            }
        });
    }

    @Override
    protected boolean showFloatView() {
        return false;
    }
}
