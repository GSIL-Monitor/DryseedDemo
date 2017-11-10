package com.dryseed.dryseedapp.listui.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.listui.BaseUIRecyleView;
import com.dryseed.dryseedapp.listui.ILoadMore;
import com.dryseed.dryseedapp.listui.IRefresh;
import com.dryseed.dryseedapp.listui.Observable;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.Category;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.CategoryItemViewBinder;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.HorizontalPostsViewBinder;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.Post;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.PostList;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.PostViewBinder;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.lib.MultiTypeAdapter;

import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017/6/28.
 */
public class TestListUIActivity extends Activity implements ILoadMore, IRefresh {

    Context mContext;
    Observable mObservable;
    ListUIPresenter mListUIPresenter;
    List<Object> items;
    BaseUIRecyleView mBaseUIRecyleView;
    View mLoadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listui_layout);
        mContext = this;

        ViewGroup rootView = (ViewGroup) (((ViewGroup) findViewById(android.R.id.content)).getChildAt(0));

        MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
        multiTypeAdapter.register(Category.class, new CategoryItemViewBinder());
        multiTypeAdapter.register(Post.class, new PostViewBinder());
        multiTypeAdapter.register(PostList.class, new HorizontalPostsViewBinder());

        mBaseUIRecyleView = new BaseUIRecyleView(mContext, rootView, multiTypeAdapter);
        mBaseUIRecyleView.setILoadMore(this);
        mBaseUIRecyleView.setLoadMoreView(mLoadView = getLoadMoreView(mContext));
        mBaseUIRecyleView.setIRefresh(this);

        //插入12条数据
        items = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            items.add(getPresenter().getData().category0);
            items.add(getPresenter().getData().postArray[0]);
            items.add(getPresenter().getData().postArray[1]);
            items.add(getPresenter().getData().postArray[2]);
            items.add(getPresenter().getData().postArray[3]);
            items.add(new PostList(getPresenter().getData().postList));
        }
        mBaseUIRecyleView.setList(items);
        mBaseUIRecyleView.notifyDataSetChanged();
    }

    private Observable getObservable() {
        if (mObservable != null) return mObservable;
        mObservable = new Observable()
                .subscribe("refresh", new Observable.Action<List<Object>>() {
                    @Override
                    public void call(List<Object> items) {
                        mBaseUIRecyleView.setList(items);
                        mBaseUIRecyleView.notifyDataSetChanged();
                        mBaseUIRecyleView.onRefreshComplete();
                        mBaseUIRecyleView.removeFootView(mLoadView);
                    }

                })
                .subscribe("loadMore", new Observable.Action<List<Object>>() {
                    @Override
                    public void call(List<Object> items) {
                        mBaseUIRecyleView.addList(items);
                        mBaseUIRecyleView.notifyDataSetChanged();
                    }

                });
        return mObservable;
    }

    @Override
    public void loadMore() {
        Log.d("MMM", "loadMore");
        getPresenter().request(getObservable(), false);
    }

    @Override
    public void refresh() {
        Log.d("MMM", "refresh");
        getPresenter().request(getObservable(), true);
    }

    private View getLoadMoreView(Context context) {
        TextView textView = new TextView(context);
        textView.setText("Loading...");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    private ListUIPresenter getPresenter() {
        if (mListUIPresenter == null) {
            mListUIPresenter = new ListUIPresenter();
        }
        return mListUIPresenter;
    }


}
