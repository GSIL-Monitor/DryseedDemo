package com.dryseed.dryseedapp.framework.rxBus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.rxBus.event.MessageEvent;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by caiminming on 2017/12/5.
 */
public class TestRxBusActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus_layout);
        ButterKnife.bind(this);

        Disposable disposable = RxBus.getDefault().getObservable(MessageEvent.class).subscribe(new Consumer<MessageEvent>() {
            @Override
            public void accept(MessageEvent messageEvent) throws Exception {
                Toast.makeText(TestRxBusActivity.this, messageEvent.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        addDisposable(disposable);
    }

    @OnClick(R.id.rxbus_btn)
    void onRxBusBtnClick(View view) {
        startActivity(new Intent(TestRxBusActivity.this, RxBusPostEventActivity.class));
    }
}
