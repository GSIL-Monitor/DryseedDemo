package com.dryseed.dryseedapp.test.setresult.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.framework.rxBus.RxBus;
import com.dryseed.dryseedapp.utils.ToastUtil;
import com.luck.picture.lib.entity.EventEntity;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by caiminming on 2017/12/28.
 * <p>
 * 测试：从A跳到B -> B跳到C，杀死自己 -> C setResult -> A能收到onActivityResult，但是intent收到的为null！！！
 * 测试：从A跳到B -> B跳到C -> C发送广播给B、杀死自己 -> B收到广播，setResult -> A能正常收到
 */

public class SetResult2Activity extends BaseActivity {
    private Disposable mDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("MMM", "SetResult2Activity onCreate");
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setText("GO TO C");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(SetResult2Activity.this, SetResult3Activity.class), 123);
                finish();
            }
        });
        setContentView(button);

        initEvents();
    }

    private void initEvents() {
        mDisposable = RxBus.getDefault().getObservable(EventEntity.class).subscribe(new Consumer<EventEntity>() {
            @Override
            public void accept(EventEntity eventEntity) throws Exception {
                Log.d("MMM", "SetResult2Activity onNext");
                Intent intent = new Intent();
                intent.putExtra("name", "dryseed");
                setResult(321, intent);
                finish();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MMM", "SetResult2Activity onRestart");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //String name = data.getStringExtra("name");

        String s = String.format("SetResult2Activity onActivityResult === requestCode:%s|resultCode:%s|intent:%s", requestCode, resultCode, data);
        ToastUtil.showToast(s);
        Log.d("MMM", s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }
}
