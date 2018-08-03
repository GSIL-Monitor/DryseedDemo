package com.dryseed.dryseedapp.canvas.drawable;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
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

        ImageView imageView1 = findViewById(R.id.imageview1);
        ImageView imageView2 = findViewById(R.id.imageview2);
        TextView textView1 = findViewById(R.id.textview_1);
        TextView textView2 = findViewById(R.id.textview_2);
        TextView textView3 = findViewById(R.id.textview_3);

        // createStateListDrawable
        Drawable normalDrawable = getResources().getDrawable(R.drawable.bol_green).mutate();
        Drawable selectDrawable = getResources().getDrawable(R.drawable.bol_red).mutate();
        StateListDrawable stateListDrawable = DrawableUtils.createStateListDrawable(normalDrawable, selectDrawable);
        imageView1.setImageDrawable(stateListDrawable);
        imageView1.setClickable(true);

        // createColorStateList
        int normalColor = 0xffcccccc;
        int selectColor = 0xff000000;
        ColorStateList colorStateList = DrawableUtils.createColorStateList(normalColor, selectColor);
        textView1.setTextColor(colorStateList);
        textView1.setClickable(true);

        // createGradientDrawable
        GradientDrawable gradientDrawable = DrawableUtils.createGradientDrawable(0xffffffff, 0xff000000);
        textView2.setBackground(gradientDrawable);

        // setBgDrawableColor
        // textView3 has a background : @drawable/quiz_choice_selected_shape
        DrawableUtils.setBgDrawableColor(textView3, 0xff000000);

        // tintDrawable
        Drawable drawable = getResources().getDrawable(R.drawable.t4_image).mutate();
        DrawableUtils.tintDrawable(drawable, ColorStateList.valueOf(0xffff0000));
        imageView2.setImageDrawable(drawable);
    }

}
