package com.dryseed.dryseedapp.tab.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.R;


@SuppressLint("NewApi")
public class MainTab04 extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View newsLayout = inflater.inflate(R.layout.activity_tab_viewpager_main_tab_04, container, false);
		return newsLayout;
	}

}
