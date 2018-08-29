package com.dryseed.dryseedapp.widget.viewPager.lazyViewPagerFragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.R;

public class LazyMainTab02 extends LazyFragment {

    private static final String TAG = "MMM";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "MainTab02 onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "MainTab02 onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "MainTab02 onCreateView");
        View view = inflater.inflate(R.layout.activity_tab_viewpager_main_tab_02, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "MainTab02 onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "MainTab02 onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "MainTab02 onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "MainTab02 onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        Log.i(TAG, "MainTab02 onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "MainTab02 onStop");
        super.onStop();
    }

    @Override
    public void onResume() {
        Log.i(TAG, "MainTab02 onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, "MainTab02 onPause");
        super.onPause();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "MainTab02 onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.i(TAG, "MainTab02 setUserVisibleHint : " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void lazyLoad() {
        Log.i(TAG, "MainTab02 lazyLoad");
    }
}
