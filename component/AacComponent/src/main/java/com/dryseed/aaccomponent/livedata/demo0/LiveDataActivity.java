package com.dryseed.aaccomponent.livedata.demo0;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.luojilab.component.basiclib.utils.ToastUtil;
import com.luojilab.router.facade.annotation.RouteNode;

/**
 * @author caiminming
 */
@RouteNode(path = "/livedatademo0", desc = "livedatademo0")
public class LiveDataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MutableLiveData<String> liveData = new MutableLiveData<>();

        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                ToastUtil.showToast("liveData onChanged : " + s);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                liveData.postValue("new Value");
            }
        }, 1000);
    }
}
