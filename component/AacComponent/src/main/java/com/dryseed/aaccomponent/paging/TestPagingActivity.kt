package com.dryseed.aaccomponent.paging

import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.dryseed.aaccomponent.R
import org.jetbrains.anko.find

/**
 * @author caiminming
 */
class TestPagingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_layout)

        val adapter = CustomAdapter()

        var recyclerView = find<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter

        val data = LivePagedListBuilder(
                CustomPageDataSourceFactory(DataRepository()),
                PagedList.Config.Builder()
                        .setPageSize(20)
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(20)
                        .build()
        ).build()

        data.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}
