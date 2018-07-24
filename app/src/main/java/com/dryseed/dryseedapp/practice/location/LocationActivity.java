package com.dryseed.dryseedapp.practice.location;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.practice.location.lib.LocationInfo;
import com.dryseed.dryseedapp.practice.location.lib.LocationManager;
import com.luojilab.component.basiclib.utils.ToastUtil;

/**
 * Created by caiminming on 2018/1/3.
 */

public class LocationActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationManager.getInstance().addLocationListener(new LocationManager.LocationInfoCallBack() {
            @Override
            public void onLocationSucc() {
                LocationInfo locationInfo = LocationManager.getInstance().getLocationInfo();
                ToastUtil.showToast("city : " + locationInfo.getCityName());
            }
        });
        LocationManager.getInstance().startLocation();
    }
}
