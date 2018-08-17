package com.dryseed.aaccomponent.serviceimpl;

import android.app.Activity;
import android.content.Intent;

import com.dryseed.aaccomponent.lifecycle.LifeCycleActivity;
import com.dryseed.aaccomponent.lifecycle.LifeCycleOwnerActivity;
import com.luojilab.componentservice.aac.AacService;

/**
 * Created by caiminming
 */

public class AccServiceImpl implements AacService {
    @Override
    public void openLifeCycleActivity(Activity activity) {
        Intent intent = new Intent(activity, LifeCycleActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void openLifeCycleOwnerActivity(Activity activity) {
        Intent intent = new Intent(activity, LifeCycleOwnerActivity.class);
        activity.startActivity(intent);
    }
}
