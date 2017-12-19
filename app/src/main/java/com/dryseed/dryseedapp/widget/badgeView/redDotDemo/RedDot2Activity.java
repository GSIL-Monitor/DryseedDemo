package com.dryseed.dryseedapp.widget.badgeView.redDotDemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.MyApplication;
import com.dryseed.dryseedapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by caiminming on 2017/12/19.
 */

public class RedDot2Activity extends BaseActivity {

    private Badge mBadgeView1;

    private BadgeModel mBadgeModel1;

    @Bind(R.id.button1)
    Button mButton1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_dot_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mButton1.setTag("RedDot2 tag1");

        initBadgeView();

        mBadgeModel1 = new BadgeModel((String) mButton1.getTag(), RedDotConstant.LINK_TYPE_CONSERVATION, new BadgeModel.OnUpdateListener() {
            @Override
            public void onUpdate(int ret) {
                mBadgeView1.setBadgeNumber(ret);
            }
        });

        RedDotManager.getInstance().setDot(mBadgeModel1);
    }

    private void initBadgeView() {
        mBadgeView1 = new QBadgeView(mButton1.getContext()).bindTarget(mButton1);
        mBadgeView1.setBadgeGravity(Gravity.END | Gravity.TOP);
        mBadgeView1.setBadgePadding(4, true);
        mBadgeView1.setShowShadow(false);
        mBadgeView1.setGravityOffset(4, 4, true);
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences(RedDotConstant.LINK_TYPE_CONSERVATION, Context.MODE_PRIVATE);
        mBadgeView1.setBadgeNumber(sp.getInt("number", 0));
    }

    @OnClick(R.id.button1)
    void onBtn1Click(View view) {
        //更新红点数据
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences(RedDotConstant.LINK_TYPE_CONSERVATION, Context.MODE_PRIVATE);
        sp.edit().putInt("number", RedDotConstant.RED_DOT_TYPE_HIDE).commit();

        //刷新红点路径
        RedDotManager.getInstance().updateDot(RedDotConstant.LINK_TYPE_CONSERVATION);
    }
}
