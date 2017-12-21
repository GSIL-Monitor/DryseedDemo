package com.dryseed.dryseedapp.widget.badgeView.redDotDemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.MyApplication;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.rxBus.RxBus;
import com.dryseed.dryseedapp.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by caiminming on 2017/12/19.
 */

public class RedDotActivity extends BaseActivity {

    @Bind(R.id.button1)
    Button mButton1;

    @Bind(R.id.button2)
    Button mButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_dot_layout);
        ButterKnife.bind(this);
        initRedDot();
    }

    private void initRedDot() {
        /*final int redDotViewId = getRedDotViewId();
        if (redDotViewId == R.id.button1) {
            final Badge badgeView = new QBadgeView(mButton1.getContext()).bindTarget(mButton1);
            badgeView.setBadgeGravity(Gravity.END | Gravity.TOP);
            badgeView.setBadgePadding(4, true);
            badgeView.setShowShadow(false);
            badgeView.setGravityOffset(4, 4, true);
            Disposable disposable = RxBus.getDefault().getObservable(RedDotEvent.class).subscribe(new Consumer<RedDotEvent>() {
                @Override
                public void accept(RedDotEvent redDotEvent) throws Exception {
                    if (redDotEvent.getId() == redDotViewId) {
                        Log.d("MMM", "RedDotActivity onUpdate");
                        badgeView.setBadgeNumber(redDotEvent.getNumber());
                    }
                }
            });
            addDisposable(disposable);
            //保存红点路径
            RedDotManager.getInstance().setDot(redDotViewId, RedDotConstant.LINK_TYPE_CONSERVATION);
        }*/

        ArrayList<Integer> redDotViewIds = getRedDotViewIds();
        for (final int id : redDotViewIds) {
            switch (id) {
                case R.id.button1:
                    final Badge badgeView = new QBadgeView(mButton1.getContext()).bindTarget(mButton1);
                    badgeView.setBadgeGravity(Gravity.END | Gravity.TOP);
                    badgeView.setBadgePadding(4, true);
                    badgeView.setShowShadow(false);
                    badgeView.setGravityOffset(4, 4, true);
                    Disposable disposable = RxBus.getDefault().getObservable(RedDotEvent.class).subscribe(new Consumer<RedDotEvent>() {
                        @Override
                        public void accept(RedDotEvent redDotEvent) throws Exception {
                            if (redDotEvent.getId() == id) {
                                Log.d("MMM", "RedDotActivity onUpdate");
                                badgeView.setBadgeNumber(redDotEvent.getNumber());
                            }
                        }
                    });
                    addDisposable(disposable);
                    //保存红点路径
                    RedDotManager.getInstance().setDot(id, RedDotConstant.LINK_TYPE_CONSERVATION);
                    break;
                case R.id.button2:
                    final Badge badgeView2 = new QBadgeView(mButton2.getContext()).bindTarget(mButton2);
                    badgeView2.setBadgeGravity(Gravity.END | Gravity.TOP);
                    badgeView2.setBadgePadding(4, true);
                    badgeView2.setShowShadow(false);
                    badgeView2.setGravityOffset(4, 4, true);
                    Disposable disposable2 = RxBus.getDefault().getObservable(RedDotEvent.class).subscribe(new Consumer<RedDotEvent>() {
                        @Override
                        public void accept(RedDotEvent redDotEvent) throws Exception {
                            if (redDotEvent.getId() == id) {
                                Log.d("MMM", "RedDotActivity onUpdate");
                                badgeView2.setBadgeNumber(redDotEvent.getNumber());
                            }
                        }
                    });
                    addDisposable(disposable2);
                    //保存红点路径
                    RedDotManager.getInstance().setDot(id, RedDotConstant.LINK_TYPE_CONSERVATION);
                    break;
                default:
                    break;
            }
        }
    }


    @OnClick(R.id.button1)
    void onBtn1Click(View view) {
        startActivity(new Intent(this, RedDot2Activity.class));
    }

    @OnClick(R.id.button2)
    void onBtn2Click(View view) {
        //更新红点数据
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences(RedDotConstant.LINK_TYPE_CONSERVATION, Context.MODE_PRIVATE);
        sp.edit().putInt("number", RedDotConstant.RED_DOT_TYPE_HIDE).commit();

        //刷新红点路径
        RedDotManager.getInstance().updateDot(RedDotConstant.LINK_TYPE_CONSERVATION);
    }

    @OnClick(R.id.update)
    void onUpdateClick(View view) {
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences(RedDotConstant.LINK_TYPE_CONSERVATION, Context.MODE_PRIVATE);
        sp.edit().putInt("number", RedDotConstant.RED_DOT_TYPE_SHOW_DOT).commit();
        RedDotManager.getInstance().updateDot(RedDotConstant.LINK_TYPE_CONSERVATION);
    }

}
