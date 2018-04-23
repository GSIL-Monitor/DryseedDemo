package com.dryseed.dryseedapp.canvas.bitmapShaderTitanic;

import android.os.Bundle;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class TestBitmapShaderTitanicActivity extends BaseActivity {

    Titanic mTitanic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_shader_titanic_layout);

        TitanicTextView tv = (TitanicTextView) findViewById(R.id.my_text_view);

        // set fancy typeface
        //tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        // start animation
        mTitanic = new Titanic();
        mTitanic.start(tv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTitanic.cancel();
    }
}
