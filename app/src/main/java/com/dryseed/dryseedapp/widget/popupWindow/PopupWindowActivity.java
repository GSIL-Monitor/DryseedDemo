package com.dryseed.dryseedapp.widget.popupWindow;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
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

    private PopupWindow mLottiePopupWindow;
    private FrameLayout mLottieRootView;
    private LottieAnimationView mLottieAnimationView;

    @BindView(R.id.button1)
    TextView mBtn1;

    @BindView(R.id.button2)
    TextView mBtn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    void onBtn1Click() {
        showMenuPopupWindow();
    }

    @OnClick(R.id.button2)
    void onBtn2Click() {
        showLottiePopupWindow();
    }

    private void showLottiePopupWindow() {
        if (null == mLottieRootView) {
            mLottieRootView = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.popup_window_lottie, null);
            mLottieAnimationView = mLottieRootView.findViewById(R.id.lottie_animation_view);
        }
        mLottieAnimationView.setAnimation("guide.json");
        mLottieAnimationView.loop(true);

        mLottiePopupWindow = new PopupWindow(mLottieRootView, DPIUtil.dip2px(200), DPIUtil.dip2px(60));
        mLottiePopupWindow.setFocusable(true);
        mLottiePopupWindow.setOutsideTouchable(true);
        //注意这里如果不设置，上面的setOutsideTouchable(true);允许点击外部消失会失效
        mLottiePopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mLottiePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        mLottiePopupWindow.update();
        if (!isFinishing()) {
            mLottiePopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER_VERTICAL | Gravity.RIGHT, 0, 0);
            mLottieAnimationView.playAnimation();
        }
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
        //注意这里如果不设置，上面的setOutsideTouchable(true);允许点击外部消失会失效
        mMenuPopupWindow.setBackgroundDrawable(new BitmapDrawable());
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
            mMenuPopupWindow.showAsDropDown(mBtn1, 0, 0);
        }
    }
}
