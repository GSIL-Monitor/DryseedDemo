package com.dryseed.aaccomponent.livedata.demo2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dryseed.aaccomponent.R;
import com.luojilab.component.basiclib.utils.LogUtil;
import com.luojilab.router.facade.annotation.RouteNode;

import java.util.List;

/**
 * LiveData + ViewModel
 * <p>
 * 在界面inactive的状态下发生了数据的改变，不会立即通知观察者，而是要等到界面重新active之后，才会调用observer的onChanged()方法。
 * <p>
 * # 使用 ViewModel 存储数据，并处理屏幕旋转
 * 旋转屏幕时，并不需要做什么序列化的操作，也不需要进行手动的赋值操作，ViewModel已经为我们处理好了。
 * <p>
 * # 在 Fragment 之间共享数据
 * 当我们希望在Fragment之间进行交互或者共享数据的时候，通常都是通过在它们共同的宿主Activity声明接口，再由Activity找到目标Fragment去通知它。
 * 当使用ViewModel后，这一交互过程将会很简单。只需要让两个Fragment都共享同一个ViewModel（也就是说，ViewModelProvider.of(x)传入的是它们共同的宿主Activity），
 * 当一方的数据改变后，改变ViewModel中的LiveData，其它观察者就会收到通知，再去进行界面的刷新就可以了。
 * <p>
 * https://www.jianshu.com/p/14af4b8c29e3
 */
@RouteNode(path = "/livedatademo2", desc = "livedatademo2")
public class LiveData2Activity extends AppCompatActivity {
    private Button mBtnRefresh;
    private TextView mTvResult;
    private DataViewModel mViewModel;
    //private Observer mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_lifecycle_layout);
        mTvResult = findViewById(R.id.lifecycle_textview);

        //1.创建 ViewModel。
        mViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        //2.添加观察者。
        mViewModel.getWatcher().observe(this, new Observer<List<String>>() {

            @Override
            public void onChanged(@Nullable List<String> strings) {
                LogUtil.d("observe onChanged");
                String tvDisplay = "";
                for (String result : strings) {
                    tvDisplay += (result + "\n");
                }
                //4.数据发生了改变后会回调到这里。
                mTvResult.setText(tvDisplay);
            }
        });

        //不与组件的生命周期绑定。
        /*mViewModel.getWatcher().observeForever(mObserver = new Observer<List<String>>() {

            @Override
            public void onChanged(@Nullable List<String> strings) {
                LogUtil.d("observeForever onChanged");
                String tvDisplay = "";
                for (String result : strings) {
                    tvDisplay += (result + "\n");
                }
                //4.数据发生了改变后会回调到这里。
                mTvResult.setText(tvDisplay);
            }
        });*/

        mBtnRefresh = findViewById(R.id.bind_btn);
        mBtnRefresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //3.触发加载。
                LogUtil.d("mViewModel.load()");
                mViewModel.load();
            }
        });
        findViewById(R.id.unbind_btn).setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d("onPause()");

        //mViewModel.getWatcher().removeObserver(mObserver);
    }
}
