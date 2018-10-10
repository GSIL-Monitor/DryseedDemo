package com.dryseed.aaccomponent.paging

import android.arch.paging.PageKeyedDataSource
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * @author caiminming
 */
class CustomPageDataSource(val repository: DataRepository) : PageKeyedDataSource<Int, DataBean>(), AnkoLogger {
    /**
     * 初始加载数据
     */
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DataBean>) {
        info("loadInitial size : ${params.requestedLoadSize} ")
        val data = repository.loadData(params.requestedLoadSize)
        callback.onResult(data, null, 2)
    }

    /**
     * 向后分页加载数据
     */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataBean>) {
        info("loadAfter size : ${params.requestedLoadSize}  page:${params.key}")
        val data = repository.loadPageData(params.key, params.requestedLoadSize)
        //表示data不为null的条件下，才会去执行let函数体
        data?.let {
            callback.onResult(data, params.key + 1)
        }
    }

    /**
     * 向前分页加载数据
     */
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataBean>) {
        info("loadBefore size : ${params.requestedLoadSize}  page:${params.key}")
        val data = repository.loadPageData(params.key, params.requestedLoadSize)
        //表示data不为null的条件下，才会去执行let函数体
        data?.let {
            callback.onResult(data, params.key - 1)
        }
    }

}