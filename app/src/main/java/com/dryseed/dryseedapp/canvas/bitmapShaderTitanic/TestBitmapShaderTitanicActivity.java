package com.dryseed.dryseedapp.canvas.bitmapShaderTitanic;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class TestBitmapShaderTitanicActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_shader_titanic_layout);

        TitanicTextView tv = (TitanicTextView) findViewById(R.id.my_text_view);

        // set fancy typeface
        tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        // start animation
        new Titanic().start(tv);
    }

}
