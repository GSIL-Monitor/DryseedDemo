package com.dryseed.blurcomponent.serviceimpl;

import android.content.Context;
import android.view.View;

import com.dryseed.blurcomponent.blur.DBlur;
import com.luojilab.componentservice.blur.BlurService;

/**
 * Created by caiminming
 */

public class BlurServiceImpl implements BlurService {
    @Override
    public void doBlur(Context context, int resId, View targetView) {
        DBlur.source(context, resId)
                .modeRs()
                .radius(10)
                .sampling(2)
                .intoTarget(targetView)
                .animAlpha(2000)
                .build()
                .doBlur();
    }
}
