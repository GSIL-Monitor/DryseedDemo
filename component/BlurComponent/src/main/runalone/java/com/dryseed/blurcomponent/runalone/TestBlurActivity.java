package com.dryseed.blurcomponent.runalone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dryseed.blurcomponent.R;
import com.dryseed.blurcomponent.blur.DBlur;
import com.luojilab.component.basiclib.BaseActivity;

import butterknife.ButterKnife;

/**
 * @author caiminming
 */
public class TestBlurActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_layout);
        ButterKnife.bind(this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBlur();
            }
        });
    }

    private void doBlur() {
        DBlur.source(this, R.drawable.city1)
                .modeRs()
                .radius(10)
                .sampling(2)
                .intoTarget(findViewById(R.id.image))
                .animAlpha(2000)
                .build()
                .doBlur();
    }
}
