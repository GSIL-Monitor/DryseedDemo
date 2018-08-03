package com.dryseed.dryseedapp.canvas.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.view.View;

import com.luojilab.component.basiclib.utils.LogUtil;

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
        drawable.addState(new int[]{android.R.attr.state_focused}, selected);
        drawable.addState(new int[]{}, normal);
        return drawable;
    }

    public static ColorStateList createColorStateList(int normal, int selected) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_checked},
                new int[]{android.R.attr.state_selected},
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_focused},
                new int[]{}
        };
        int[] colors = new int[]{
                selected,
                selected,
                selected,
                selected,
                normal
        };
        return new ColorStateList(states, colors);
    }

    public static GradientDrawable createGradientDrawable(int start, int end) {
        GradientDrawable drawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{start, end}
        );
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        return drawable;
    }

    public static void setBgDrawableColor(View view, int color) {
        if (view.getBackground() instanceof LayerDrawable) {
            LogUtil.d("setBgDrawableColor LayerDrawable");
            LayerDrawable layer = (LayerDrawable) view.getBackground().mutate();
            if (layer.getDrawable(0) instanceof GradientDrawable) {
                GradientDrawable drawable = (GradientDrawable) layer.getDrawable(0);
                drawable.setColor(color);
            }
        } else if (view.getBackground() instanceof GradientDrawable) {
            LogUtil.d("setBgDrawableColor GradientDrawable");
            GradientDrawable drawable = (GradientDrawable) view.getBackground().mutate();
            drawable.setColor(color);
        }
    }

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }
}
