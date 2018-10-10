package com.dryseed.aaccomponent.paging

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup

/**
 * @author caiminming
 */
class CustomAdapter : PagedListAdapter<DataBean, CustomViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
            CustomViewHolder(parent)

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataBean>() {
            override fun areItemsTheSame(oldConcert: DataBean,
                                         newConcert: DataBean): Boolean =
                    oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: DataBean,
                                            newConcert: DataBean): Boolean =
                    oldConcert == newConcert
        }
    }

}