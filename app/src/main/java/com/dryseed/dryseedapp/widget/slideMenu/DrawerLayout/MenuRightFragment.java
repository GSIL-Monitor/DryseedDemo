package com.dryseed.dryseedapp.widget.slideMenu.DrawerLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.R;

public class MenuRightFragment extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.activity_drawer_layout_menu_right, container, false);
	}
}
