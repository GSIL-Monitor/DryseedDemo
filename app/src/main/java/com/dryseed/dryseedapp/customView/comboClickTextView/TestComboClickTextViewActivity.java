package com.dryseed.dryseedapp.customView.comboClickTextView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.DPIUtil;

/**
 * Created by User on 2017/4/1.
 */
public class TestComboClickTextViewActivity extends BaseActivity {
    private Button mBtn;
    private ComboClickLayout mComboClickLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thumbs_up_textview);

        mComboClickLayout = (ComboClickLayout) findViewById(R.id.comboClickLayout);

        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mComboClickLayout)
                mComboClickLayout.addClick();
            }
        });

        TextView textView = new TextView(this);
        Paint paint = textView.getPaint();
        paint.setStrokeWidth(DPIUtil.dip2px(3));// 描边宽度
        paint.setStyle(Paint.Style.STROKE);
        textView.setText("777");
        textView.setTextSize(90);
        addContentView(textView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
    }
}
