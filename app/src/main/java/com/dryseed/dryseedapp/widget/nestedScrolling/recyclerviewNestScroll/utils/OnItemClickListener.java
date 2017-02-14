package com.dryseed.dryseedapp.widget.nestedScrolling.recyclerviewNestScroll.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by caiminming on 2017/2/14.
 */
public interface OnItemClickListener<T>
{
    void onItemClick(ViewGroup parent, View view, T t, int position);
    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}
