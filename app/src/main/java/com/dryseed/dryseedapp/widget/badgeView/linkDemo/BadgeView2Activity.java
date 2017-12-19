package com.dryseed.dryseedapp.widget.badgeView.linkDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by caiminming on 2017/12/19.
 */

public class BadgeView2Activity extends BaseActivity {
    @Bind(R.id.button1)
    Button mButton1;
    private Badge mBadgeView1;
    private BadgeModel mBadgeModel1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badgeview_layout);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mButton1.setTag("BadgeView2Activity tag1");

        mBadgeView1 = new QBadgeView(mButton1.getContext()).bindTarget(mButton1);
        mBadgeView1.setBadgeGravity(Gravity.END | Gravity.TOP);
        mBadgeView1.setBadgePadding(4, true);
        mBadgeView1.setShowShadow(false);
        mBadgeView1.setGravityOffset(4, 4, true);
        mBadgeView1.setBadgeNumber(-1);

        mBadgeModel1 = new BadgeModel((String) mButton1.getTag(), "link1", new BadgeModel.OnUpdateListener() {
            @Override
            public void onUpdate() {
                mBadgeView1.setBadgeNumber(0);
            }
        });

        BadgeManager.getInstance().setDot(mBadgeModel1);
    }

    @OnClick(R.id.button1)
    void onBtn1Click(View view) {
        BadgeManager.getInstance().updateDot(mBadgeModel1);
    }

}
