package com.dryseed.dryseedapp.widget.popupMenuView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.ToastUtil;
import com.dryseed.dryseedapp.widget.popupMenuView.lib.OptionMenu;
import com.dryseed.dryseedapp.widget.popupMenuView.lib.OptionMenuView;
import com.dryseed.dryseedapp.widget.popupMenuView.lib.PopupMenuView;
import com.dryseed.dryseedapp.widget.popupMenuView.lib.PopupView;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/12/22.
 */

public class PopupMenuViewActivity extends BaseActivity {
    PopupMenuView mPopupMenuView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu_view_layout);
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
    }

    @OnClick(R.id.button1)
    void onBtn1Click(View view) {
        mPopupMenuView.show(view);
    }

    @OnClick(R.id.button2)
    void onBtn2Click(View view) {
        mPopupMenuView.show(view);
    }

    @OnClick(R.id.button3)
    void onBtn3Click(View view) {
        mPopupMenuView.show(view);
    }

    @OnClick(R.id.button4)
    void onBtn4Click(View view) {
        mPopupMenuView.show(view);
    }
}
