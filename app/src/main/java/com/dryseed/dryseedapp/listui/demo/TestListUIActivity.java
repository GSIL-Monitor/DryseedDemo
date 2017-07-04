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
public class TestListUIActivity extends Activity implements ILoadMore {

    Context mContext;
    Observable mObservable;
    ListUIPresenter mListUIPresenter;
    List<Object> items;
    BaseUIRecyleView mBaseUIRecyleView;
    JsonData data = new JsonData();
    View mLoadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listui_layout);
        mContext = this;
        mListUIPresenter = new ListUIPresenter();
        mListUIPresenter.getData(getObservable());

        ViewGroup rootView = (ViewGroup) (((ViewGroup) findViewById(android.R.id.content)).getChildAt(0));

        MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
        multiTypeAdapter.register(Category.class, new CategoryItemViewBinder());
        multiTypeAdapter.register(Post.class, new PostViewBinder());
        multiTypeAdapter.register(PostList.class, new HorizontalPostsViewBinder());

        mBaseUIRecyleView = new BaseUIRecyleView(mContext, rootView, multiTypeAdapter);
        mBaseUIRecyleView.setILoadMore(this);
        mBaseUIRecyleView.setLoadMoreView(mLoadView = getLoadMoreView(mContext));

        //插入12条数据
        items = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            items.add(data.category0);
            items.add(data.postArray[0]);
            items.add(data.postArray[1]);
            items.add(data.postArray[2]);
            items.add(data.postArray[3]);
            items.add(new PostList(data.postList));
        }
        mBaseUIRecyleView.setList(items);
        mBaseUIRecyleView.notifyDataSetChanged();
    }

    private Observable getObservable() {
        if (mObservable != null) return mObservable;
        mObservable = new Observable()
                .subscribe("refresh", new Observable.Action<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Toast.makeText(TestListUIActivity.this, integer + "", Toast.LENGTH_SHORT).show();
                    }

                });
        return mObservable;
    }

    @Override
    public void loadMore() {
        Log.d("MMM", "loadMore");
        Toast.makeText(mContext, "request...", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                items.add(data.category0);
                items.add(data.postArray[0]);
                items.add(data.postArray[1]);
                items.add(data.postArray[2]);
                items.add(data.postArray[3]);
                Log.d("MMM", "items size : " + items.size());
                //mBaseUIRecyleView.removeFootView(mLoadView);
                mBaseUIRecyleView.setList(items);
                mBaseUIRecyleView.notifyDataSetChanged();
            }
        }, 2000);
    }

    private View getLoadMoreView(Context context){
        TextView textView = new TextView(context);
        textView.setText("Loading...");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    private static class JsonData {

        private static final String PREFIX = "这是一条长长的达到两行的标题文字";

        private Post post00 = new Post(R.drawable.img_00, PREFIX + "post00");
        private Post post01 = new Post(R.drawable.img_01, PREFIX + "post01");
        private Post post10 = new Post(R.drawable.img_10, PREFIX + "post10");
        private Post post11 = new Post(R.drawable.img_11, PREFIX + "post11");

        Category category0 = new Category("title0");
        Post[] postArray = {post00, post01, post10, post11};

        List<Post> postList = new ArrayList<>();


        {
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
        }
    }
}
