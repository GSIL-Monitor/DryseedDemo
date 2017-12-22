package com.dryseed.dryseedapp.widget.badgeView.redDotDemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.MyApplication;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.rxBus.RxBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by caiminming on 2017/12/19.
 */

public class RedDot2Activity extends BaseActivity {

    @Bind(R.id.button1)
    Button mButton1;

    @Bind(R.id.fragment_content)
    FrameLayout mFragmentContent;

    private RedDotFragment mRedDotFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_dot_2_layout);
        ButterKnife.bind(this);
        initRedDot();
    }

    private void initRedDot() {
        final int redDotViewId = getRedDotViewId();
        if (redDotViewId == R.id.button1) {
            final Badge mBadgeView1 = new QBadgeView(mButton1.getContext()).bindTarget(mButton1);
            mBadgeView1.setBadgeGravity(Gravity.END | Gravity.TOP);
            mBadgeView1.setBadgePadding(4, true);
            mBadgeView1.setShowShadow(false);
            mBadgeView1.setGravityOffset(4, 4, true);
            Disposable disposable = RxBus.getDefault().getObservable(RedDotEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<RedDotEvent>() {
                @Override
                public void accept(RedDotEvent redDotEvent) throws Exception {
                    if (redDotEvent.getId() == redDotViewId) {
                        Log.d("MMM", "RedDotActivity onUpdate");
                        mBadgeView1.setBadgeNumber(redDotEvent.getNumber());
                    }
                }
            });
            addDisposable(disposable);
            //保存红点路径
            RedDotManager.getInstance().setDot(redDotViewId, RedDotConstant.LINK_TYPE_CONSERVATION);
            //刷新红点
            RedDotManager.getInstance().updateDot(RedDotConstant.LINK_TYPE_CONSERVATION);
        }

        /*ArrayList<Integer> redDotViewIds = getRedDotViewIds();
        for (final int id : redDotViewIds) {
            switch (id) {
                case R.id.button1:
                    final Badge mBadgeView1 = new QBadgeView(mButton1.getContext()).bindTarget(mButton1);
                    mBadgeView1.setBadgeGravity(Gravity.END | Gravity.TOP);
                    mBadgeView1.setBadgePadding(4, true);
                    mBadgeView1.setShowShadow(false);
                    mBadgeView1.setGravityOffset(4, 4, true);
                    Disposable disposable = RxBus.getDefault().getObservable(RedDotEvent.class).subscribe(new Consumer<RedDotEvent>() {
                        @Override
                        public void accept(RedDotEvent redDotEvent) throws Exception {
                            if (redDotEvent.getId() == id) {
                                Log.d("MMM", "RedDotActivity onUpdate");
                                mBadgeView1.setBadgeNumber(redDotEvent.getNumber());
                            }
                        }
                    });
                    addDisposable(disposable);
                    //保存红点路径
                    RedDotManager.getInstance().setDot(id, RedDotConstant.LINK_TYPE_CONSERVATION);
                    break;
                default:
                    break;
            }
        }*/
    }


    @OnClick(R.id.button1)
    void onBtn1Click(View view) {
        startActivity(new Intent(this, RedDot3Activity.class));
    }

    @OnClick(R.id.update)
    void onUpdateClick(View view) {
        //更新红点数据
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences(RedDotConstant.LINK_TYPE_CONSERVATION, Context.MODE_PRIVATE);
        sp.edit().putInt("number", RedDotConstant.RED_DOT_TYPE_HIDE).commit();

        //刷新红点路径
        RedDotManager.getInstance().updateDot(RedDotConstant.LINK_TYPE_CONSERVATION);
    }

    @OnClick(R.id.load_fragment_btn)
    void onLoadFragmentBtnClick(View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mRedDotFragment = new RedDotFragment();
        transaction.replace(R.id.fragment_content, mRedDotFragment);
        transaction.commit();
    }


}
