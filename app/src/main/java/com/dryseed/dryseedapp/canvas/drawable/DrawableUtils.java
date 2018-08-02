package com.dryseed.dryseedapp.canvas.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;

/**
 * @author caiminming
 */
public class DrawableUtils {
    public static StateListDrawable createStateListDrawable(Context context, Bitmap normal, Bitmap selected) {
        if (context == null || normal == null || selected == null) {
            return null;
        }
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_selected},
                new BitmapDrawable(context.getResources(), selected));
        drawable.addState(new int[]{android.R.attr.state_pressed},
                new BitmapDrawable(context.getResources(), selected));
        drawable.addState(new int[]{},
                new BitmapDrawable(context.getResources(), normal));
        return drawable;
    }

    public static StateListDrawable createStateListDrawable(@NonNull Drawable normal,
                                                            @NonNull Drawable selected) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_selected}, selected);
        drawable.addState(new int[]{android.R.attr.state_pressed}, selected);
        drawable.addState(new int[]{}, normal);
        return drawable;
    }

    public static ColorStateList createColorStateList(int normal, int selected) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_checked},
                new int[]{android.R.attr.state_selected},
                new int[]{android.R.attr.state_pressed},
                new int[]{}
        };
        int[] colors = new int[]{
                selected,
                selected,
                selected,
                normal
        };
        return new ColorStateList(states, colors);
    }
}
