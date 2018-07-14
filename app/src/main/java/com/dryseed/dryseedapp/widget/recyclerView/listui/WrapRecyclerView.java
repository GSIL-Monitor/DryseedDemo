/*
 * Copyright(c) 2016 Jing Dong Mall. All Rights Reserved.
 *
 * This software is the proprietary information of Jing Dong Mall.
 */

package com.dryseed.dryseedapp.widget.recyclerView.listui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * 可无限添加header 和 footview的 RecyclerView
 */
public class WrapRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViews = new ArrayList<>();

    private ArrayList<View> mFootViews = new ArrayList<>();

    private WrapRecyclerAdapter mAdapter;

    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public WrapRecyclerAdapter getWrapAdapter() {
        return mAdapter;
    }

    public void addHeaderView(View view) {
        mHeaderViews.add(view);
    }

    public void addFootView(View view) {
        mFootViews.add(view);
    }

    public void cleanHeaderView() {
        mHeaderViews.clear();
    }

    public void removeHeaderView(View view) {
        int size = mHeaderViews.size();
        for (int i = 0; i < size; i++) {
            if (view.equals(mHeaderViews.get(i))) {
                mHeaderViews.remove(i);
                break;
            }
        }

    }

    public void cleanFootView() {
        mFootViews.clear();
    }

    public void removeFootView(View view) {
        int size = mFootViews.size();
        for (int i = 0; i < size; i++) {
            if (view.equals(mFootViews.get(i))) {
                mFootViews.remove(i);
                break;
            }
        }
    }

    public boolean containsFootView(View view) {
        int size = mFootViews.size();
        for (int i = 0; i < size; i++) {
            if (view.equals(mFootViews.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mAdapter = new WrapRecyclerAdapter(mHeaderViews, mFootViews, adapter);
        super.setAdapter(mAdapter);
    }

}
