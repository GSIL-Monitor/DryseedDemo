package com.dryseed.dryseedapp.widget.slideMenu.BinarySlideMenu02;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//		Log.e("TAG", "MyLinearLayout");
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
//		Log.e("tag", "getChildDrawingOrder" + i + " , " + childCount);

        if (i == 0) //menu1
            return 1;
        if (i == 2) //menu2
            return 2;
        if (i == 1) //content
            return 0;
        return super.getChildDrawingOrder(childCount, i);

    }

}
