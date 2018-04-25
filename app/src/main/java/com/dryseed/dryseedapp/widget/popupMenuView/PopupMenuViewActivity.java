package com.dryseed.dryseedapp.widget.popupMenuView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.DPIUtil;
import com.dryseed.dryseedapp.utils.ToastUtil;
import com.dryseed.dryseedapp.widget.popupMenuView.lib.OptionMenu;
import com.dryseed.dryseedapp.widget.popupMenuView.lib.OptionMenuView;
import com.dryseed.dryseedapp.widget.popupMenuView.lib.PopLayout;
import com.dryseed.dryseedapp.widget.popupMenuView.lib.PopupMenuView;
import com.dryseed.dryseedapp.widget.popupMenuView.lib.PopupView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/12/22.
 * <p>
 * https://github.com/kareluo/PopupMenuView
 */

public class PopupMenuViewActivity extends BaseActivity {

    @BindView(R.id.button2)
    Button mBtn2;

    @BindView(R.id.button3)
    Button mBtn3;

    @BindView(R.id.pop_layout)
    PopLayout mPopLayout;

    FrameLayout mRootView;
    PopupMenuView mPopupMenuView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.activity_popup_menu_view_layout, null);
        setContentView(mRootView);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mPopupMenuView = new PopupMenuView(this);
        List<OptionMenu> optionMenus =
                Arrays.asList(
                        new OptionMenu("复制"),
                        new OptionMenu("粘贴"),
                        new OptionMenu("删除"),
                        new OptionMenu("分享")
                );

        mPopupMenuView.setMenuItems(optionMenus);
        mPopupMenuView.setSites(PopupView.SITE_BOTTOM);
        mPopupMenuView.setDividerDrawable(LinearLayoutCompat.SHOW_DIVIDER_MIDDLE, getResources().getDrawable(R.drawable.popup_menu_divider));
        mPopupMenuView.setOnMenuClickListener(new OptionMenuView.OnOptionMenuClickListener() {
            @Override
            public boolean onOptionMenuClick(int position, OptionMenu menu) {
                ToastUtil.showToast(menu.getTitle().toString());
                return true;
            }
        });

        mPopLayout.setSiteMode(PopLayout.SITE_BOTTOM);
    }

    @OnClick(R.id.button1)
    void onBtn1Click(View view) {
        mPopupMenuView.show(view);
    }

    @OnClick(R.id.button2)
    void onBtn2Click(View view) {
        //mPopupMenuView.show(view);
        showBubblePopView();
    }

    @OnClick(R.id.button3)
    void onBtn3Click(View view) {
        //mPopupMenuView.show(view);
        showNormalPopView();
    }

    @OnClick(R.id.button4)
    void onBtn4Click(View view) {
        mPopupMenuView.show(view);
    }

    /**
     * 这里使用了PopupMenuView，由于没用Menu布局，所以不会显示气泡
     */
    private void showNormalPopView() {
        View detail = LayoutInflater.from(this).inflate(R.layout.activity_popup_menu_item, null);
        detail.measure(DPIUtil.dip2px(200), ViewGroup.LayoutParams.WRAP_CONTENT);
        Log.d("MMM", detail.getMeasuredWidth() + " " + detail.getMeasuredHeight() + " " + DPIUtil.dip2px(200));
        PopupMenuView mPopupMenuView = new PopupMenuView(this);
        mPopupMenuView.setContentView(detail);
        mPopupMenuView.setSites(PopupView.SITE_RIGHT, PopupView.SITE_TOP); //按照这种顺序测试界面是否可以显示下菜单
        int[] location = new int[2];
        mBtn3.getLocationInWindow(location);
        mPopupMenuView.show(mBtn3);
    }

    /**
     * 带气泡的弹窗
     */
    private void showBubblePopView() {
        PopupView popupView = new PopupView(this);
        PopLayout popLayout = new PopLayout(this);

        TextView text = new TextView(this);
        text.setLayoutParams(new FrameLayout.LayoutParams(DPIUtil.dip2px(100), DPIUtil.dip2px(50)));
        text.setText("dryseed");
        text.setGravity(Gravity.CENTER);
        text.setTextColor(0xff000000);
        popLayout.addView(text);
        popLayout.setBackgroundColor(0xffcccccc);
        popLayout.setSiteMode(PopLayout.SITE_BOTTOM);
        popLayout.setOffset(mBtn2.getWidth() / 2);

        popupView.setContentView(popLayout);
        popupView.measureContentView();
        popupView.setSites(PopupView.SITE_TOP);
        popupView.show(mBtn2);
    }
}
