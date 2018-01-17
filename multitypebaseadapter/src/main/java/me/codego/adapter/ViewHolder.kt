package me.codego.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by mengxn on 2017/9/21.
 */
class ViewHolder<in T>(view: View, private val bind: (View, T, Int) -> Unit) : RecyclerView.ViewHolder(view) {

    fun bind(data: T, position: Int) {
        bind(itemView, data, position)
    }

}