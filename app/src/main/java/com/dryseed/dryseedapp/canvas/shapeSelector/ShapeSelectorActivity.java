package com.dryseed.dryseedapp.canvas.shapeSelector;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.DPIUtil;

public class ShapeSelectorActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_layout);

        LinearLayout btn = findViewById(R.id.buttonLayout);
        ViewCompat.setTranslationZ(btn, DPIUtil.dip2px(10));
    }
}
