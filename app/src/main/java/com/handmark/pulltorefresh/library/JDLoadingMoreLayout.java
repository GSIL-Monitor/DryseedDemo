package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.dryseed.dryseedapp.R;


/**
 * Created by raojian on 2016/8/3.
 * 与LoadingMoreLayout逻辑代码一样，只是调整代码顺序，方便继承和复用
 */
public class JDLoadingMoreLayout extends FrameLayout{

    public static enum FooterState {
        RESET,
        LOADING,
        LOADING_SUCCESS,
        LOADING_FAILED,
        REACH_END,
        REACH_END_INVISIBLE
    }

    protected View mFooterLoadingView;
    protected View mFootRetryView;
    protected View mFootReachEndView;

    protected FooterState mFooterState;

    protected RetryListener mRetryListener;

    public JDLoadingMoreLayout(Context context) {
        super(context);
        getLoadingLayout();
    }

    public JDLoadingMoreLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getLoadingLayout();
    }

    public FooterState getFooterState() {
        return mFooterState;
    }

    public void setFootersState(FooterState state) {
        mFooterState = state;
        switch (state) {
            case RESET:
            case LOADING:
            case LOADING_SUCCESS:
                mFooterLoadingView.setVisibility(View.VISIBLE);
                mFootRetryView.setVisibility(View.GONE);
                mFootReachEndView.setVisibility(View.GONE);
                break;
            case LOADING_FAILED:
                mFooterLoadingView.setVisibility(View.GONE);
                mFootRetryView.setVisibility(View.VISIBLE);
                mFootReachEndView.setVisibility(View.GONE);
                break;
            case REACH_END:
                mFooterLoadingView.setVisibility(View.GONE);
                mFootRetryView.setVisibility(View.GONE);
                mFootReachEndView.setVisibility(View.VISIBLE);
                break;
            case REACH_END_INVISIBLE:
                mFooterLoadingView.setVisibility(View.GONE);
                mFootRetryView.setVisibility(View.GONE);
                mFootReachEndView.setVisibility(View.GONE);
            default:
                break;
        }
    }

    public void getLoadingLayout(){
        LayoutInflater.from(getContext()).inflate(R.layout.ptr_footer, this, true);

        mFooterLoadingView = findViewById(R.id.loading_layout);
        mFootRetryView = findViewById(R.id.footer_retry_view);
        mFootReachEndView = findViewById(R.id.footer_reach_end_view);

        mFootRetryView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mRetryListener != null) {
                    mRetryListener.onRetry();
                }
            }
        });

        mFooterState = FooterState.RESET;
    }

    public void setOnRetryListener(RetryListener retryListener) {
        mRetryListener = retryListener;
    }

    public RetryListener getRetryListener() {
        return mRetryListener;
    }

    public interface RetryListener {
        public void onRetry();
    }
}
