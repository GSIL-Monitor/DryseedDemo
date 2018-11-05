package com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo3;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.bean.Category;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.bean.Post;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo3.wrapper.HeaderAndFooterWrapper;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo3.wrapper.LoadMoreWrapper;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiminming on 2017/4/7.
 */

public class TestRecyclerViewActivity extends BaseActivity {
    private final int SPAN_COUNT = 2;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private RecyclerView mRecyclerView;
    private List mDatas;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private LoadMoreWrapper mLoadMoreWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_layout);

        initData();

        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.pull_to_refresh_recycler_view);
        mPullToRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() {
            @Override
            public void onRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                Log.d("MMM", "onRefresh");
                mDatas.add(0, new Category("title header"));
                mLoadMoreWrapper.notifyDataSetChanged();
                mPullToRefreshRecyclerView.onRefreshComplete();
            }
        });

        mRecyclerView = mPullToRefreshRecyclerView.getRefreshableView();
        //mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        //LayoutManager
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Log.d("MMM", "getSpanSize : " + position);
                int realPosition = position - mHeaderAndFooterWrapper.getHeadersCount();
                if (mDatas.get(realPosition) instanceof Post) {
                    return 1;
                }
                return SPAN_COUNT;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //ItemDecoration
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 2;
            }
        });


        //adapter
        /*CommonAdapter mAdapter = new CommonAdapter<String>(this, R.layout.activity_recycler_view_item, mDatas) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.id_num, s + " : " + holder.getAdapterPosition() + " , " + holder.getLayoutPosition());
            }
        };*/
        BiliMultiRecyclerAdapter mAdapter = new BiliMultiRecyclerAdapter(this, mDatas);

        // header and footer
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
        TextView t1 = new TextView(this);
        t1.setText("Header 1");
        TextView t2 = new TextView(this);
        t2.setText("Header 2");
        TextView t3 = new TextView(this);
        t3.setText("Footer 1");
        mHeaderAndFooterWrapper.addHeaderView(t1);
        mHeaderAndFooterWrapper.addHeaderView(t2);
        mHeaderAndFooterWrapper.addFootView(t3);

        // load more
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        mLoadMoreWrapper.setLoadMoreView(getLoadMoreView(TestRecyclerViewActivity.this));
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Category category = new Category("title");
                        Post post = new Post(R.drawable.img_10, "post");
                        mDatas.add(category);
                        mDatas.add(post);
                        mDatas.add(post);
                        mLoadMoreWrapper.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });

        mRecyclerView.setAdapter(mLoadMoreWrapper);
        //mRecyclerView.setAdapter(new HomeAdapter());

    }

    protected void initData() {
        /*mDatas = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            mDatas.add("" + (char) i);
        }*/

        mDatas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String PREFIX = "这是一条长长的达到两行的标题文字";
            Post post00 = new Post(R.drawable.img_00, PREFIX + "post00");
            Post post01 = new Post(R.drawable.img_01, PREFIX + "post01");
            Post post10 = new Post(R.drawable.img_10, PREFIX + "post10");
            Post post11 = new Post(R.drawable.img_11, PREFIX + "post11");

            Category category0 = new Category("title0");
            mDatas.add(category0);
            mDatas.add(post00);
            mDatas.add(post01);
            mDatas.add(post10);
            mDatas.add(post11);
        }
    }

    /*class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d("MMM", "onCreateViewHolder");
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(TestRecyclerViewActivity.this).inflate(R.layout.activity_recycler_view_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Log.d("MMM", "onBindViewHolder : " + position);
            holder.tv.setText(mDatas.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }*/

    private View getLoadMoreView(Context context) {
        TextView textView = new TextView(context);
        textView.setText("Loading...");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
