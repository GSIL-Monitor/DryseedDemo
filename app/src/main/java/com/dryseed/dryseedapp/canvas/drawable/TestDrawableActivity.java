package com.dryseed.dryseedapp.canvas.drawable;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

/**
 * @author caiminming
 */
public class TestDrawableActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawable_layout);

        ImageView imageView = findViewById(R.id.imageview);
        TextView textView = findViewById(R.id.textview);

        Drawable normalDrawable = getResources().getDrawable(R.drawable.bol_green).mutate();
        Drawable selectDrawable = getResources().getDrawable(R.drawable.bol_red).mutate();
        StateListDrawable stateListDrawable = DrawableUtils.createStateListDrawable(normalDrawable, selectDrawable);
        imageView.setImageDrawable(stateListDrawable);

        int normalColor = 0xffcccccc;
        int selectColor = 0xff000000;
        ColorStateList colorStateList = DrawableUtils.createColorStateList(normalColor, selectColor);
        textView.setTextColor(colorStateList);

    }


}
