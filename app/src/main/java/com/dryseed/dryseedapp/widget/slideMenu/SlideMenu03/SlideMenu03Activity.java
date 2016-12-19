package com.dryseed.dryseedapp.widget.slideMenu.SlideMenu03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.dryseed.dryseedapp.R;

public class SlideMenu03Activity extends Activity
{
	private SlidingMenu mMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_slide_menu_03_layout);
		mMenu = (SlidingMenu) findViewById(R.id.id_menu);

	}

	public void toggleMenu(View view)
	{
		mMenu.toggle();
	}
}
