package com.dryseed.dryseedapp.widget.badgeView.redDotDemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.dryseed.dryseedapp.BaseFragment;
import com.dryseed.dryseedapp.application.MyApplication;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.rxBus.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by caiminming on 2017/12/21.
 */

public class RedDotFragment extends BaseFragment {
    private static final String TAG = "MMM";

    @BindView(R.id.button1)
    Button mButton;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "RedDotFragment onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "RedDotFragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "RedDotFragment onCreateView");
        FrameLayout mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_red_dot_layout, container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRedDot(view);
    }

    private void initRedDot(View view) {
        final int redDotViewId = getRedDotViewId(view);
        if (redDotViewId == R.id.button1) {
            final Badge mBadgeView1 = new QBadgeView(mButton.getContext()).bindTarget(mButton);
            mBadgeView1.setBadgeGravity(Gravity.END | Gravity.TOP);
            mBadgeView1.setBadgePadding(4, true);
            mBadgeView1.setShowShadow(false);
            mBadgeView1.setGravityOffset(4, 4, true);
            Disposable disposable = RxBus.getDefault().getObservable(RedDotEvent.class).subscribe(new Consumer<RedDotEvent>() {
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
    void onBtnClick() {
        //更新红点数据
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences(RedDotConstant.LINK_TYPE_CONSERVATION, Context.MODE_PRIVATE);
        sp.edit().putInt("number", RedDotConstant.RED_DOT_TYPE_HIDE).commit();

        //刷新红点路径
        RedDotManager.getInstance().updateDot(RedDotConstant.LINK_TYPE_CONSERVATION);
    }
}
