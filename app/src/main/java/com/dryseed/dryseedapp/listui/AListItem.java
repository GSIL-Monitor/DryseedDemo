/*
 * Copyright(c) 2016 Jing Dong Mall. All Rights Reserved.
 *
 * This software is the proprietary information of Jing Dong Mall.
 */

package com.dryseed.dryseedapp.listui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * {@link MutiTypeAdapter}
 *
 * @author: zhanwei
 * @date: 2016-06-16 18:22
 */
public abstract class AListItem<T, VH extends RecyclerView.ViewHolder> {

    protected WrapBundle wrapBundle;

    public AListItem injectData(WrapBundle wrapBundle) {
        this.wrapBundle = wrapBundle;
        return this;
    }

    public WrapBundle getWrapBundle() {
        if (wrapBundle == null) {
            wrapBundle = new WrapBundle();
        }
        return wrapBundle;
    }

    protected T data;

    public AListItem create(T t) {
        this.data = t;
        return this;
    }

    protected int position;//作为onBindViewHolder的一个参数比较好，但是不好改动了只能扩展

    public void setPosition(int position) {
        this.position = position;
    }

    @LayoutRes
    public abstract int getLayoutId();

    public abstract VH onCreateViewHolder(View itemView);

    public abstract void onBindViewHolder(VH vh, Context context);
}
