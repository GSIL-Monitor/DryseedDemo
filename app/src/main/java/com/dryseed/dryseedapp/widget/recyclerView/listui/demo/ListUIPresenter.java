package com.dryseed.dryseedapp.widget.recyclerView.listui.demo;

import android.os.Handler;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.recyclerView.listui.Observable;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo.Category;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017/6/28.
 */
public class ListUIPresenter {
    JsonData data = new JsonData();

    public void request(final Observable observable, final boolean isRefresh){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isRefresh){
                    List<Object> items = new ArrayList<Object>();
                    items.add(data.category0);
                    observable.postMainThread("refresh", items);
                } else {
                    List<Object> items = new ArrayList<Object>();
                    items.add(data.category0);
                    observable.postMainThread("loadMore", items);
                }
            }
        }, 2000);
    }

    public JsonData getData(){
        return data;
    }

    public static class JsonData {

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
