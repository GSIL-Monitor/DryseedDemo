/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Phone工程下复制PullToRefreshRecyclerView类，推荐插件统一使用lib下这个类，便于维护
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {

    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }

    @Override
    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    @TargetApi(4)
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView = new RecyclerView(context);

        return recyclerView;
    }

    @Override
    protected boolean isReadyForPullStart() {
        if (mRefreshableView.getLayoutManager() == null || mRefreshableView.getAdapter() == null) {
            return false;
        }

        try {
            View child = mRefreshableView.getChildAt(0);
            int height = (child == null) ? 0 : child.getHeight();
            if (height > mRefreshableView.getHeight()) {//第一行的高度大于RecyclerView的高度
                int top = (child == null) ? 0 : child.getTop();
                return top == 0 && ((LinearLayoutManager) mRefreshableView.getLayoutManager()).findFirstVisibleItemPosition() == 0;
            }
            return ((LinearLayoutManager) mRefreshableView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() == 0;
        } catch (Throwable tr) {

        }
        return false;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        if (mRefreshableView.getLayoutManager() == null || mRefreshableView.getAdapter() == null) {
            return false;
        }
        try {
            return ((LinearLayoutManager) mRefreshableView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == mRefreshableView.getAdapter().getItemCount() - 1;
        } catch (Throwable tr) {

        }
        return false;
    }

}
