package com.dryseed.dryseedapp.widget.badgeView.linkDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by caiminming on 2017/12/18.
 */

public class BadgeViewActivity extends BaseActivity {
    Badge mBadgeView1;
    Badge mBadgeView2;
    Badge mBadgeView3;
    Badge mBadgeView4;
    Badge mBadgeView5;

    private BadgeModel mBadgeModel1;
    private BadgeModel mBadgeModel2;
    private BadgeModel mBadgeModel3;
    private BadgeModel mBadgeModel4;
    private BadgeModel mBadgeModel5;

    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.button4)
    Button mButton4;
    @BindView(R.id.button5)
    Button mButton5;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badgeview_layout);
        ButterKnife.bind(this);

        initView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mButton1.setTag("tag1");
        mButton2.setTag("tag2");
        mButton3.setTag("tag3");
        mButton4.setTag("tag4");
        mButton5.setTag("tag5");

        initBadgeView();

        mBadgeModel1 = new BadgeModel((String) mButton1.getTag(), "link1", new BadgeModel.OnUpdateListener() {
            @Override
            public void onUpdate() {
                mBadgeView1.setBadgeNumber(0);
            }
        });
        mBadgeModel2 = new BadgeModel((String) mButton3.getTag(), "link1", new BadgeModel.OnUpdateListener() {
            @Override
            public void onUpdate() {
                mBadgeView2.setBadgeNumber(0);
            }
        });
        mBadgeModel3 = new BadgeModel((String) mButton3.getTag(), new BadgeModel.OnUpdateListener() {
            @Override
            public void onUpdate() {
                mBadgeView3.setBadgeNumber(0);
            }
        });
        mBadgeModel4 = new BadgeModel((String) mButton3.getTag(), "link2", new BadgeModel.OnUpdateListener() {
            @Override
            public void onUpdate() {
                mBadgeView4.setBadgeNumber(0);
            }
        });
        mBadgeModel5 = new BadgeModel((String) mButton4.getTag(), "link2", new BadgeModel.OnUpdateListener() {
            @Override
            public void onUpdate() {
                mBadgeView5.setBadgeNumber(0);
            }
        });

        BadgeManager.getInstance().setDot(mBadgeModel1);
        BadgeManager.getInstance().setDot(mBadgeModel2);
        BadgeManager.getInstance().setDot(mBadgeModel3);
        BadgeManager.getInstance().setDot(mBadgeModel4);
        BadgeManager.getInstance().setDot(mBadgeModel5);

        BadgeManager.getInstance().print();
    }

    @OnClick(R.id.button1)
    void onBtn1Click(View view) {
        BadgeManager.getInstance().updateDot(mBadgeModel1);
        startActivity(new Intent(this, BadgeView2Activity.class));
    }

    @OnClick(R.id.button2)
    void onBtn2Click(View view) {
        BadgeManager.getInstance().updateDot(mBadgeModel2);

    }

    @OnClick(R.id.button3)
    void onBtn3Click(View view) {
        BadgeManager.getInstance().updateDot(mBadgeModel3);
    }

    @OnClick(R.id.button4)
    void onBtn4Click(View view) {
        BadgeManager.getInstance().updateDot(mBadgeModel4);
    }

    @OnClick(R.id.button5)
    void onBtn5Click(View view) {
        BadgeManager.getInstance().updateDot(mBadgeModel5);
    }

    private void initBadgeView() {
        mBadgeView1 = new QBadgeView(mButton1.getContext()).bindTarget(mButton1);
        mBadgeView1.setBadgeGravity(Gravity.END | Gravity.TOP);
        mBadgeView1.setBadgePadding(4, true);
        mBadgeView1.setShowShadow(false);
        mBadgeView1.setGravityOffset(4, 4, true);
        mBadgeView1.setBadgeNumber(-1);

        mBadgeView2 = new QBadgeView(mButton1.getContext()).bindTarget(mButton2);
        mBadgeView2.setBadgeGravity(Gravity.END | Gravity.TOP);
        mBadgeView2.setBadgePadding(4, true);
        mBadgeView2.setShowShadow(false);
        mBadgeView2.setGravityOffset(4, 4, true);
        mBadgeView2.setBadgeNumber(-1);

        mBadgeView3 = new QBadgeView(mButton1.getContext()).bindTarget(mButton3);
        mBadgeView3.setBadgeGravity(Gravity.END | Gravity.TOP);
        mBadgeView3.setBadgePadding(4, true);
        mBadgeView3.setShowShadow(false);
        mBadgeView3.setGravityOffset(4, 4, true);
        mBadgeView3.setBadgeNumber(-1);

        mBadgeView4 = new QBadgeView(mButton1.getContext()).bindTarget(mButton4);
        mBadgeView4.setBadgeGravity(Gravity.END | Gravity.TOP);
        mBadgeView4.setBadgePadding(4, true);
        mBadgeView4.setShowShadow(false);
        mBadgeView4.setGravityOffset(4, 4, true);
        mBadgeView4.setBadgeNumber(-1);

        mBadgeView5 = new QBadgeView(mButton1.getContext()).bindTarget(mButton5);
        mBadgeView5.setBadgeGravity(Gravity.END | Gravity.TOP);
        mBadgeView5.setBadgePadding(4, true);
        mBadgeView5.setShowShadow(false);
        mBadgeView5.setGravityOffset(4, 4, true);
        mBadgeView5.setBadgeNumber(-1);

    }
}

