package com.dryseed.dryseedapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.widget.badgeView.redDotDemo.RedDotConstant;
import com.dryseed.dryseedapp.widget.badgeView.redDotDemo.RedDotManager;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by caiminming on 2017/12/21.
 */

public class BaseFragment extends Fragment {
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mCompositeDisposable != null) {
            Log.d("MMM", "mCompositeDisposable.dispose();");
            mCompositeDisposable.dispose();
        }
    }

    private void initFragment() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    protected int getRedDotViewId(View view) {
        if (view instanceof ViewGroup) {
            return RedDotManager.getInstance().getViewIdByTag((ViewGroup) view, RedDotConstant.RED_DOT_TAG);
        }
        return -1;
    }

    protected ArrayList<Integer> getRedDotViewIds(View view) {
        if (view instanceof ViewGroup) {
            return RedDotManager.getInstance().getViewIdsByTag((ViewGroup) view, RedDotConstant.RED_DOT_TAG);
        }
        return null;
    }
}
