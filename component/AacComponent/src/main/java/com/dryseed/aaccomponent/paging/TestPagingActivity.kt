package com.dryseed.aaccomponent.paging

import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.dryseed.aaccomponent.R
import com.luojilab.router.facade.annotation.RouteNode
import org.jetbrains.anko.find

/**
 * @author caiminming
 */
@RouteNode(path = "/paging", desc = "paging")
class TestPagingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_layout)

        val adapter = CustomAdapter()

        var recyclerView = find<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val data = LivePagedListBuilder(
                CustomPageDataSourceFactory(DataRepository()),
                PagedList.Config.Builder()
                        .setPageSize(20)                //每次加载多少数据
                        .setPrefetchDistance(10)        //距底部还有几条数据时，加载下一页数据
                        .setEnablePlaceholders(false)   //是否启用占位符，若为true，则视为固定数量的item
                        .setInitialLoadSizeHint(20)     //第一次加载多少数据
                        .build()
        ).build()

        data.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}
