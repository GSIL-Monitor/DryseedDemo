package com.dryseed.dryseedapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.widget.badgeView.redDotDemo.RedDotConstant;
import com.dryseed.dryseedapp.widget.badgeView.redDotDemo.RedDotManager;
import com.dryseed.dryseedapp.widget.floatView.FloatViewManager;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by caiminming on 2017/11/27.
 */

public class BaseActivity extends AppCompatActivity {

    protected CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActivity();
    }

    private void initActivity() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (showFloatView()) {
            // 全局悬浮窗，在FloatViewActivity开启
            FloatViewManager.getInstance().showFloatView(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (showFloatView()) {
            // 全局悬浮窗，在FloatViewActivity开启
            FloatViewManager.getInstance().hideFloatView();
        }
    }

    protected boolean showFloatView() {
        return true;
    }

    protected int getRedDotViewId() {
        ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
        if (null != contentView) {
            return RedDotManager.getInstance().getViewIdByTag(contentView, RedDotConstant.RED_DOT_TAG);
        }
        return -1;
    }

    protected ArrayList<Integer> getRedDotViewIds() {
        ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
        if (null != contentView) {
            return RedDotManager.getInstance().getViewIdsByTag(contentView, RedDotConstant.RED_DOT_TAG);
        }
        return null;
    }

}
