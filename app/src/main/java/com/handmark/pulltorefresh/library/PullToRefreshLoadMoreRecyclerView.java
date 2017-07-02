package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.ILoadMore;
import com.handmark.pulltorefresh.library.JDLoadingMoreLayout;
import com.handmark.pulltorefresh.library.LoadMoreListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

/**
 * Created by raojian on 2016/8/3.
 * 可以下拉刷新和上拉加载更多的RecyclerView，例子参考PersonalHomeRecyclerview
 */
public class PullToRefreshLoadMoreRecyclerView extends PullToRefreshRecyclerView implements ILoadMore{
    public static final String TAG = PullToRefreshLoadMoreRecyclerView.class.getSimpleName();

    private LoadMoreListener mLoadMoreListener;
    private JDLoadingMoreLayout mLoadingMoreLayout;
    private boolean mLastItemVisible;

    private JDLoadingMoreLayout.FooterState cachedState;
    private OnLastItemVisibleListener mOnLastItemVisibleListener;
    private OnScrollListener mExternalScrollListener;

    public PullToRefreshLoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToRefreshLoadMoreRecyclerView(Context context) {
        super(context);
        init();
    }

    public void init() {
        setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                invalidateView();
            }
        });

        mRefreshableView.addOnScrollListener(mScrollListener);
    }

    private OnScrollListener mScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (mOnLastItemVisibleListener != null
                    && newState == RecyclerView.SCROLL_STATE_IDLE && mLastItemVisible) {
                mOnLastItemVisibleListener.onLastItemVisible();
            }

            if (mExternalScrollListener != null) {
                mExternalScrollListener.onScrollStateChanged(recyclerView, newState);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            mLastItemVisible = isLastItemVisible();
            if (mExternalScrollListener != null) {
                mExternalScrollListener.onScrolled(recyclerView, dx, dy);
            }
        }
    };

    protected JDLoadingMoreLayout getLoadingMoreLayout(){
        final RecyclerView.Adapter adapter = mRefreshableView.getAdapter();
        if (adapter == null)
            return null;

        int footerPos = adapter.getItemCount() - 1;
        if (footerPos < 0)
            return null;

        LinearLayoutManager lm = (LinearLayoutManager) mRefreshableView.getLayoutManager();
        View view = lm.findViewByPosition(footerPos);
        if (view != null && view instanceof JDLoadingMoreLayout) {
            return (JDLoadingMoreLayout) view;
        }
        return null;
    }

    public void setReachEndInvisible() {
        cachedState = null;
        mLoadingMoreLayout = getLoadingMoreLayout();
        if (mLoadingMoreLayout != null)
            mLoadingMoreLayout.setFootersState(JDLoadingMoreLayout.FooterState.REACH_END_INVISIBLE);
        else
            cachedState = JDLoadingMoreLayout.FooterState.REACH_END_INVISIBLE;
    }


    private void invalidateView() {
        if (mLoadMoreListener != null) {
            cachedState = null;
            mLoadMoreListener.loadMore();
        }
    }

    @Override
    public void resetFooter() {
        cachedState = null;
        mLoadingMoreLayout = getLoadingMoreLayout();
        if (mLoadingMoreLayout != null)
            mLoadingMoreLayout.setFootersState(JDLoadingMoreLayout.FooterState.RESET);
        else
            cachedState = JDLoadingMoreLayout.FooterState.RESET;
    }

    @Override
    public void setLoadingMoreSucceed() {
        cachedState = null;
        mLoadingMoreLayout = getLoadingMoreLayout();
        if (mLoadingMoreLayout != null)
            mLoadingMoreLayout.setFootersState(JDLoadingMoreLayout.FooterState.LOADING_SUCCESS);
        else
            cachedState = JDLoadingMoreLayout.FooterState.LOADING_SUCCESS;
    }

    @Override
    public void setLoadingMoreFailed() {
        cachedState = null;
        mLoadingMoreLayout = getLoadingMoreLayout();
        if (mLoadingMoreLayout != null)
            mLoadingMoreLayout.setFootersState(JDLoadingMoreLayout.FooterState.LOADING_FAILED);
        else
            cachedState = JDLoadingMoreLayout.FooterState.LOADING_FAILED;
    }

    @Override
    public void setReachEnd() {
        cachedState = null;
        mLoadingMoreLayout = getLoadingMoreLayout();
        if (mLoadingMoreLayout != null) {
            mLoadingMoreLayout.setFootersState(JDLoadingMoreLayout.FooterState.REACH_END);
            if (mRefreshableView.getScrollState() != RecyclerView.SCROLL_STATE_DRAGGING) {
                RecyclerView.Adapter adapter = mRefreshableView.getAdapter();
                if (adapter != null) {
                    mRefreshableView.smoothScrollToPosition(adapter.getItemCount() - 1);
                }
            }
        } else {
            cachedState = JDLoadingMoreLayout.FooterState.REACH_END;
        }
    }

    private boolean isLastItemVisible() {
        final RecyclerView.Adapter adapter = mRefreshableView.getAdapter();

        if (null == adapter) {
            return true;
        } else {
            final int lastItemPosition = adapter.getItemCount() - 1;
            LinearLayoutManager lm = (LinearLayoutManager) mRefreshableView.getLayoutManager();
            final int lastVisiblePosition = lm.findLastVisibleItemPosition();
//            if (Log.D)
//                Log.d(TAG, "-----lastItemPosition---" + lastItemPosition + "--lastVisiblePosition---" + lastVisiblePosition);
            /**
             * This check should really just be: lastVisiblePosition ==
             * lastItemPosition, but PtRListView internally uses a FooterView
             * which messes the positions up. For me we'll just subtract one to
             * account for it and rely on the inner condition which checks
             * getBottom().
             */
            if (lastVisiblePosition >= lastItemPosition - 1) {
                final int childIndex = lastVisiblePosition;
                final View lastVisibleChild = lm.findViewByPosition(childIndex);
                if (lastVisibleChild != null) {//&& lastVisibleChild instanceof LoadingMoreLayout
                    return lastVisibleChild.getBottom() <= getBottom();
                }
            }
        }

        return false;
    }

    public void setOnLoadMoreListener(LoadMoreListener listener) {
        mLoadMoreListener = listener;
    }

    public void setExternalScrollListener(OnScrollListener listener) {
        mExternalScrollListener = listener;
    }

    public final void setOnLastItemVisibleListener(OnLastItemVisibleListener listener) {
        mOnLastItemVisibleListener = listener;
    }
}
