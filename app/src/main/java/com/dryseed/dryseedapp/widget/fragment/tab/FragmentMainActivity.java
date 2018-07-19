package com.dryseed.dryseedapp.widget.fragment.tab;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

/**
 * 评价：每个Fragment中的控件的处理，都是独立到各自的类中，相对来说主Activity简化了不少，可惜没有左右滑动的效果了。
 */
public class FragmentMainActivity extends BaseActivity implements OnClickListener {
    private FrameLayout mContent;
    private MainTab02 mTab02;
    private MainTab01 mTab01;
    private MainTab03 mTab03;
    private MainTab04 mTab04;

    /**
     * 底部四个按钮
     */
    private LinearLayout mTabBtnWeixin;
    private LinearLayout mTabBtnFrd;
    private LinearLayout mTabBtnAddress;
    private LinearLayout mTabBtnSettings;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_viewpager_fragment_main);
        initViews();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
    }


    private void initViews() {
        mContent = (FrameLayout) findViewById(R.id.id_content);
        mTabBtnWeixin = (LinearLayout) findViewById(R.id.id_tab_bottom_weixin);
        mTabBtnFrd = (LinearLayout) findViewById(R.id.id_tab_bottom_friend);
        mTabBtnAddress = (LinearLayout) findViewById(R.id.id_tab_bottom_contact);
        mTabBtnSettings = (LinearLayout) findViewById(R.id.id_tab_bottom_setting);

        mTabBtnWeixin.setOnClickListener(this);
        mTabBtnFrd.setOnClickListener(this);
        mTabBtnAddress.setOnClickListener(this);
        mTabBtnSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("MMM", "findViewWithTag:" + ((TextView) mContent.findViewWithTag("tab01")).getText());
        switch (v.getId()) {
            case R.id.id_tab_bottom_weixin:
                setTabSelection(0);
                break;
            case R.id.id_tab_bottom_friend:
                setTabSelection(1);
                break;
            case R.id.id_tab_bottom_contact:
                setTabSelection(2);
                break;
            case R.id.id_tab_bottom_setting:
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     */
    @SuppressLint("NewApi")
    private void setTabSelection(int index) {
        // 重置按钮
        resetBtn();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
                        .setImageResource(R.drawable.tab_weixin_pressed);
                if (mTab01 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mTab01 = new MainTab01();
                    transaction.add(R.id.id_content, mTab01);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mTab01);
                }
                break;
            case 1:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
                        .setImageResource(R.drawable.tab_find_frd_pressed);
                if (mTab02 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mTab02 = new MainTab02();
                    transaction.add(R.id.id_content, mTab02);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mTab02);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
                        .setImageResource(R.drawable.tab_address_pressed);
                if (mTab03 == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    mTab03 = new MainTab03();
                    transaction.add(R.id.id_content, mTab03);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(mTab03);
                }
                break;
            case 3:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnSettings.findViewById(R.id.btn_tab_bottom_setting))
                        .setImageResource(R.drawable.tab_settings_pressed);
                if (mTab04 == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    mTab04 = new MainTab04();
                    transaction.add(R.id.id_content, mTab04);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(mTab04);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void resetBtn() {
        ((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
                .setImageResource(R.drawable.tab_weixin_normal);
        ((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
                .setImageResource(R.drawable.tab_find_frd_normal);
        ((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
                .setImageResource(R.drawable.tab_address_normal);
        ((ImageButton) mTabBtnSettings.findViewById(R.id.btn_tab_bottom_setting))
                .setImageResource(R.drawable.tab_settings_normal);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    @SuppressLint("NewApi")
    private void hideFragments(FragmentTransaction transaction) {
        if (mTab01 != null) {
            transaction.hide(mTab01);
        }
        if (mTab02 != null) {
            transaction.hide(mTab02);
        }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
        if (mTab04 != null) {
            transaction.hide(mTab04);
        }

    }

}
