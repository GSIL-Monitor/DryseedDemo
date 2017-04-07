package com.dryseed.dryseedapp.tab.viewPagerPlusFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.R;

public class MainTab01 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("MMM", "MainTab01 onCreateView");
        return inflater.inflate(R.layout.activity_tab_viewpager_main_tab_01, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MMM", "MainTab01 onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MMM", "MainTab01 onStart");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("MMM", "MainTab01 onAttach");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MMM", "MainTab01 onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MMM", "MainTab01 onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MMM", "MainTab01 onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MMM", "MainTab01 onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("MMM", "MainTab01 onDetach");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("MMM", "MainTab01 onHiddenChanged : " + hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("MMM", "MainTab01 setUserVisibleHint : " + isVisibleToUser);
    }
}
