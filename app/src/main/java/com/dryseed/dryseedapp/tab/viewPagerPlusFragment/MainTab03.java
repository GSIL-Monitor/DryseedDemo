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

public class MainTab03 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("MMM", "MainTab03 onCreateView");
        View newsLayout = inflater.inflate(R.layout.activity_tab_viewpager_main_tab_03, container, false);
        return newsLayout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MMM", "MainTab03 onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MMM", "MainTab03 onStart");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("MMM", "MainTab03 onAttach");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MMM", "MainTab03 onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MMM", "MainTab03 onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MMM", "MainTab03 onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MMM", "MainTab03 onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("MMM", "MainTab03 onDetach");
    }
}
