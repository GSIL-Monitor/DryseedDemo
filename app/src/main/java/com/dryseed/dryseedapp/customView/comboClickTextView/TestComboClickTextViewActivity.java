package com.dryseed.dryseedapp.customView.comboClickTextView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.R;

/**
 * Created by User on 2017/4/1.
 */
public class TestComboClickTextViewActivity extends Activity {
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
    }
}
