package com.dryseed.dryseedapp.framework.glide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dryseed.dryseedapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2017/7/23.
 */
public class TestGlideActivity extends Activity {
    @Bind(R.id.glide_image)
    ImageView mImageView;

    @Bind(R.id.glide_button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.glide_button)
    void loadImage(){
        String url = "http://p1.pstatp.com/large/166200019850062839d3";
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.content)
                .into(mImageView);
    }
}
