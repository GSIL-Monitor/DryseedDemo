package com.dryseed.aaccomponent.paging

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.dryseed.aaccomponent.R

/**
 * @author caiminming
 */
class CustomViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)) {
    private val nameView = itemView.findViewById<TextView>(R.id.text)
    var item: DataBean? = null

    fun bindTo(item: DataBean?) {
        this.item = item
        nameView.text = item?.name
    }
}