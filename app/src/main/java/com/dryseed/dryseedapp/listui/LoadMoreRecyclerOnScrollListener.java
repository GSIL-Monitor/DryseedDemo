package com.dryseed.dryseedapp.listui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


public abstract class LoadMoreRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0; // The total number of items in the dataset after the last load

    private boolean loadMoreEnable = true;

    private int visibleThreshold = 1;//list到达 最后一个item的时候 触发加载

    private boolean isLoading = false;

    private int lastVisibleItem;

    private LinearLayoutManager mLinearLayoutManager;

    public LoadMoreRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    public boolean isLoadMoreEnable() {
        return loadMoreEnable;
    }

    public void setLoadMoreEnable(boolean loadMoreEnable) {
        this.loadMoreEnable = loadMoreEnable;
    }

    public void setVisibleThreshold(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (loadMoreEnable) {
            int totalItemCount = mLinearLayoutManager.getItemCount();//返回Adapter当前持有的Item的数量,等于List数据源的数目.
            int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            Log.d("MMM", String.format("totalItemCount : %d | previousTotal : %d", totalItemCount, previousTotal));

            if (isLoading) {
                if (totalItemCount > previousTotal) {
                    isLoading = false;
                    previousTotal = totalItemCount;
                } else if (totalItemCount < previousTotal) {
                    isLoading = false;
                }
            }

            if (!isLoading && dy > 0 && lastVisibleItem + visibleThreshold >= totalItemCount) {
                isLoading = true;
                if (onLoadMoreWithFootView()) {
                    previousTotal = totalItemCount + 1;
                } else {
                    previousTotal = totalItemCount;
                }
            }
        }


        onScroll(recyclerView, dx, dy);
    }

    public abstract boolean onLoadMoreWithFootView();

    public abstract void onScroll(RecyclerView recyclerView, int dx, int dy);

    public void updateLoadingState(boolean state) {
        this.isLoading = state;
    }
}