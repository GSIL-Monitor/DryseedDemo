package com.dryseed.dryseedapp.widget.popupWindow;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.DPIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2018/1/2.
 */

public class PopupWindowActivity extends BaseActivity {

    private PopupWindow mMenuPopupWindow;
    private View mMenuTipView;
    private View mMusicLocalAddLayout;
    private View mMusicUploadLayout;

    @BindView(R.id.btn)
    TextView mBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    void onBtnClick() {
        showMenuPopupWindow();
    }

    private void showMenuPopupWindow() {
        if (mMenuTipView == null) {
            mMenuTipView = LayoutInflater.from(this).inflate(R.layout.popup_window_menu, null);
            mMusicLocalAddLayout = mMenuTipView.findViewById(R.id.music_local_add_layout);
            mMusicUploadLayout = mMenuTipView.findViewById(R.id.music_upload_layout);
        }
        mMenuPopupWindow = new PopupWindow(mMenuTipView, DPIUtil.dip2px(139), DPIUtil.dip2px(84));
        mMenuPopupWindow.setFocusable(true);
        mMenuPopupWindow.setOutsideTouchable(true);
        mMenuPopupWindow.setBackgroundDrawable(new BitmapDrawable()); //注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
        mMenuPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        mMusicLocalAddLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mMusicUploadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mMenuPopupWindow.update();
        if (!isFinishing()) {
            mMenuPopupWindow.showAsDropDown(mBtn, 0, 0);
        }
    }
}
