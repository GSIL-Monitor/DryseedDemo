package com.dryseed.dryseedapp.customView.foldingLayout.sample;

import android.app.Activity;
import android.os.Bundle;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.customView.foldingLayout.view.FoldLayout;

public class FoldLayoutActivity extends BaseActivity
{
	private FoldLayout mFoldLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_folding_layout_fold);

		mFoldLayout = (FoldLayout) findViewById(R.id.id_fold_layout);
	
		/*mFoldLayout.post(new Runnable()
		{

			@SuppressLint("NewApi")
			@Override
			public void run()
			{
				ObjectAnimator.ofFloat(mFoldLayout, "factor", 1, 0, 1)
						.setDuration(5000).start();
			}
		});*/

	}
}
