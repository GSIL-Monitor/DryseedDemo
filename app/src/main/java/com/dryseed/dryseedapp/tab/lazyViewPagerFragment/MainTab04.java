package com.dryseed.dryseedapp.tab.lazyViewPagerFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.R;

public class MainTab04 extends LazyFragment {

    private static final String TAG = "MMM";

    /**
     * 标志位，标志已经初始化完成。
     */
    private boolean isPrepared;

    /**
     * 标记已加载完成，保证懒加载只能加载一次
     */
    private boolean hasLoaded = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "MainTab04 onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "MainTab04 onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "MainTab04 onCreateView");
        View view = inflater.inflate(R.layout.activity_tab_viewpager_main_tab_01, container, false);

        isPrepared = true;
        lazyLoad();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "MainTab04 onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "MainTab04 onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "MainTab04 onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "MainTab04 onDestroyView");
        isPrepared = false;
        hasLoaded = false;
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        Log.i(TAG, "MainTab04 onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "MainTab04 onStop");
        super.onStop();
    }

    @Override
    public void onResume() {
        Log.i(TAG, "MainTab04 onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, "MainTab04 onPause");
        super.onPause();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "MainTab04 onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void  (boolean isVisibleToUser) {
        Log.i(TAG, "MainTab04 setUserVisibleHint : " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void lazyLoad() {
        Log.i(TAG, "MainTab04 lazyLoad");
        if(!isPrepared || !isVisible || hasLoaded) {
            return;
        }
        Log.i(TAG, "MainTab04 lazyLoad do something");
        hasLoaded = true;
    }
}
