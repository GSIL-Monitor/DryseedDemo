package com.dryseed.dryseedapp.widget.clipZoomImage;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class ClipZoomImageActivity extends BaseActivity
{
	private ClipImageLayout mClipImageLayout;
	private Button mBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clip_zoom_image_activity);

		mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);
		mBtn = (Button) findViewById(R.id.id_btn);
		mBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Bitmap bitmap = mClipImageLayout.clip();

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				byte[] datas = baos.toByteArray();

				Intent intent = new Intent(ClipZoomImageActivity.this, ShowImageActivity.class);
				intent.putExtra("bitmap", datas);
				startActivity(intent);
			}
		});
	}

}
