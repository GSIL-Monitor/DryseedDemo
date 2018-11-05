/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.data.JsonDataGenerator;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.bean.Category;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.bean.Post;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.bean.PostList;
import com.luojilab.component.basiclib.recyclerview.multitypeadapter.MultiTypeAdapter;
import com.luojilab.component.basiclib.utils.DPIUtil;

import java.util.List;


/**
 * @author drakeet
 */
public class BilibiliActivity extends BaseActivity {

    private static final int SPAN_COUNT = 2;
    @VisibleForTesting
    List<Object> items;
    @VisibleForTesting
    MultiTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type_adapter_list);

        adapter = new MultiTypeAdapter();
        adapter.register(Category.class, new CategoryItemViewBinder());
        adapter.register(Post.class, new PostViewBinder());
        adapter.register(PostList.class, new HorizontalPostsViewBinder());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        SpanSizeLookup spanSizeLookup = new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Object item = items.get(position);
                return (item instanceof PostList || item instanceof Category) ? SPAN_COUNT : 1;
            }
        };
        layoutManager.setSpanSizeLookup(spanSizeLookup);
        recyclerView.setLayoutManager(layoutManager);
        int space = DPIUtil.dip2px(12);
        recyclerView.addItemDecoration(new PostItemDecoration(space, spanSizeLookup));

        recyclerView.setAdapter(adapter);

        items = JsonDataGenerator.generateListDataWithTitle();
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
