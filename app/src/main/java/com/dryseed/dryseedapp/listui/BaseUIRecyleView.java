package com.dryseed.dryseedapp.listui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dryseed.dryseedapp.widget.multiTypeAdapter.lib.MultiTypeAdapter;

import java.util.List;

/**
 * Created by User on 2017/7/4.
 */
public class BaseUIRecyleView {

    private boolean isPullRefresh = true;
    private boolean isCanLoadMore = true;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private WrapRecyclerView mRecyclerView;
    private ILoadMore mILoadMore;
    private MultiTypeAdapter mMutiTypeAdapter;
    private LoadMoreRecyclerOnScrollListener mLoadMoreRecyclerOnScrollListener;
    private View mLoadMoreView;

    public BaseUIRecyleView(Context context, ViewGroup viewGroup, MultiTypeAdapter mutiTypeAdapter){
        mPullToRefreshRecyclerView = new PullToRefreshRecyclerView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        viewGroup.addView(mPullToRefreshRecyclerView, layoutParams);

        mRecyclerView = mPullToRefreshRecyclerView.getRefreshableView();
        if (mRecyclerView == null) return;
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(context);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setPersistentDrawingCache(ViewGroup.PERSISTENT_NO_CACHE);
        mRecyclerView.setNestedScrollingEnabled(true);
        mMutiTypeAdapter = mutiTypeAdapter;
        mRecyclerView.setAdapter(mMutiTypeAdapter);
        initLoadMore(linearLayoutManager);
    }

    private void initLoadMore(final LinearLayoutManager linearLayoutManager) {
        mLoadMoreRecyclerOnScrollListener = new LoadMoreRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public boolean onLoadMoreWithFootView() {
                boolean result = false;
                if (mILoadMore != null) {
                    if (mLoadMoreView == null)
                        throw new RuntimeException("please set LoadMoreFootView...eg LoadMoreView");
                    if (!mRecyclerView.containsFootView(mLoadMoreView)) {
                        addFootView(mLoadMoreView);
                        result = true;
                    }
                    isPullRefresh = false;
                    if (isCanLoadMore) {
                        mILoadMore.loadMore();
                    }
                }
                return result;
            }

            @Override
            public void onScroll(RecyclerView recyclerView, int dx, int dy) {

            }
        };
        mRecyclerView.addOnScrollListener(mLoadMoreRecyclerOnScrollListener);
    }

    public void addFootView(View view) {
        if (view != null && mRecyclerView != null) {
            if (!mRecyclerView.containsFootView(view)) {
                mRecyclerView.addFootView(view);
                mRecyclerView.getWrapAdapter().notifyItemChanged(getItemCount() - 1);
            }
        }
    }

    public void removeFootView(View view) {
        if (view != null && mRecyclerView != null) {
            mRecyclerView.removeFootView(view);
        }
    }

    public void setList(List<Object> list) {
        if (mMutiTypeAdapter != null) {
            mMutiTypeAdapter.setItems(list);
        }
        if (mLoadMoreRecyclerOnScrollListener != null) {
            mLoadMoreRecyclerOnScrollListener.updateLoadingState(false);
        }
    }

    public void notifyDataSetChanged() {
        if (mRecyclerView == null || mRecyclerView.getWrapAdapter() == null || mRecyclerView.isComputingLayout())
            return;
        mRecyclerView.getWrapAdapter().notifyDataSetChanged();
    }

    public void setVisibleThreshold(int visibleThreshold) {
        if (mLoadMoreRecyclerOnScrollListener != null) {
            mLoadMoreRecyclerOnScrollListener.setVisibleThreshold(visibleThreshold);
        }
    }

    public void setILoadMore(ILoadMore ILoadMore) {
        mILoadMore = ILoadMore;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.isCanLoadMore = canLoadMore;
    }

    public void setLoadMoreView(View view) {
        mLoadMoreView = view;
    }

    public int getItemCount() {
        if (mRecyclerView == null) {
            return 0;
        }
        return mRecyclerView.getWrapAdapter().getItemCount();
    }
}
