package com.dryseed.aaccomponent.paging

import android.arch.paging.DataSource

/**
 * @author caiminming
 */
class CustomPageDataSourceFactory(val repository: DataRepository) : DataSource.Factory<Int, DataBean>() {
    override fun create(): DataSource<Int, DataBean> {
        return CustomPageDataSource(repository)
    }
}